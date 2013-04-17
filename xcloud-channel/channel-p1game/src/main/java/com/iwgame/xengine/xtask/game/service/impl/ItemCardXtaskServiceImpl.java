package com.iwgame.xengine.xtask.game.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import com.iwgame.pconf.exception.PconfNotFoundException;
import com.iwgame.pconf.model.GGameGroupNode;
import com.iwgame.pconf.service.ProductConfigurationService;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xconnector.exeception.DBConnectFailedException;
import com.iwgame.xconnector.exeception.GMConnectException;
import com.iwgame.xengine.xtask.game.model.ItemCardBean;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/**
 * 
 * @描述: 激活道具卡
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-25上午11:31:40
 * @版本: 1.0
 */
@Service
public class ItemCardXtaskServiceImpl {

	private final Logger logger = Logger.getLogger("itemcard");

	// @Resource
	// private ProxoolDataSource defaultDataSource;

	@Resource
	private ProductConfigurationService productConfigurationService;

	@Resource
	private RabbitTemplate rabbitTemplate;

	@Resource
	private DBConnection dbConnectorConnection;

	/**
	 * 
	 * @说明: 激活道具卡
	 * @return: void
	 */
	public void itemCardActivate(ItemCardBean itemCard) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String pid = ConfigProperties.getString("PID");
			if ("P-P1.5".equalsIgnoreCase(pid)) {
				if (!itemCard.getUsername().endsWith("_zxy")) {
					itemCard.setUsername(itemCard.getUsername() + "_zxy");
				}
			}
			params.put("type", itemCard.getType());
			params.put("name", itemCard.getUsername());
			params.put("str1", itemCard.getCardnum());
			params.put("str2", itemCard.getCardpwd());
			params.put("i1", itemCard.getI1());
			params.put("i2", itemCard.getI2());
			params.put("i3", itemCard.getI3());

			// 所有大区激活
			if ("all".equalsIgnoreCase(itemCard.getGuid())) {
				// 取得大区列表
				List<String> list = getAllgidByProduct(pid);

				for (String targetid : list) {
					SqlSessionTemplate sqlSessionTemplate = null;
					try {
						sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);
						if (sqlSessionTemplate != null) {
							int reslut = 0;
							reslut = sqlSessionTemplate.insert("game-sqlmap.itemcardActivate", params);
							if (reslut > 0) {
								String url = "";
								try {
									ProxoolDataSource dataSource = (ProxoolDataSource) sqlSessionTemplate.getConfiguration().getEnvironment().getDataSource();
									url = dataSource.getDriverUrl();
								} catch (Exception e) {
									logger.error("获取数据库连接池jdbc_url失败!",e);
								}
								logger.info("帐号[" + itemCard.getUsername() + "]大区[" + targetid + "]道具卡激活成功!  jdbc:" + url);
							} else {
								logger.info("帐号[" + itemCard.getUsername() + "]大区[" + targetid + "]道具卡激活失败,数据库返回失败!请求资源:{" + itemCard.getSource() + "}");
							}
						} else {
							logger.error("道具卡激活失败,获取sqlSessionTemplate失败!]:请求资源:{" + itemCard.getSource() + "}");
							throw new PconfNotFoundException("道具卡激活失败,获取sqlSessionTemplate失败!]:请求资源:{" + itemCard.getSource() + "}");
						}
					} catch (Exception e) {
						throw e;
					} 
				}
			} else {
				String targetid = pid + "-" + itemCard.getGuid();
				SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);
				if (sqlSessionTemplate != null) {
					int reslut = sqlSessionTemplate.insert("game-sqlmap.itemcardActivate", params);
					if (reslut > 0) {
						String url = "";
						try {
							ProxoolDataSource dataSource = (ProxoolDataSource) sqlSessionTemplate.getConfiguration().getEnvironment().getDataSource();
							url = dataSource.getDriverUrl();
						} catch (Exception e) {
							logger.error("获取数据库连接池jdbc_url失败!",e);
						}
						logger.info("帐号[" + itemCard.getUsername() + "]大区[" + targetid + "]道具卡激活成功! jdbc:" + url);
					} else {
						logger.error("帐号[" + itemCard.getUsername() + "]大区[" + targetid + "]道具卡激活失败!,数据库返回失败!请求资源:" + itemCard.getSource());
					}
				} else {
					logger.error("道具卡激活失败,获取sqlSessionTemplate失败!]请求资源:" + itemCard.getSource());
					throw new PconfNotFoundException("道具卡激活失败,获取sqlSessionTemplate失败!]:请求资源:{" + itemCard.getSource() + "}");
				}
			}
		} catch (BadSqlGrammarException e) {
			logger.error("[重试,语法异常]道具卡激活出错,获取服务器列表失败,请求资源:{" + itemCard.getSource() + "}, 异常信息:", e);
			throw e;
		} catch (MySQLSyntaxErrorException e) {
			logger.error("[重试,语法异常]道具卡激活出错,获取服务器列表失败,请求资源:{" + itemCard.getSource() + "}, 异常信息:", e);
			throw e;
		} catch (SQLException e) {
			logger.error("[重试,请求返回备份通道][连接异常1]道具卡激活出错,获取服务器列表失败,请求资源:{" + itemCard.getSource() + "}, 异常信息:", e);
			throw e;
		} catch (GMConnectException e) {
			logger.error("[重试,请求返回备份通道][连接异常2]道具卡激活出错,请求资源:{" + itemCard.getSource() + "}, 消息返回队列,异常信息:", e);
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("[重试,请求返回备份通道][连接异常3]道具卡激活出错,请求资源:{" + itemCard.getSource() + "}, 消息返回队列,异常信息:", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("[重试,请求返回备份通道][连接异常4]道具卡激活出错,AO[xportal_pconf]表中的配置[g_properties]信息连接失败,消息返回队列,请求资源:{" + itemCard.getSource() + "}, 消息返回队列,异常信息:", e);
			throw e;
		} catch (PconfNotFoundException e) {
			logger.error("[重试,请求返回备份通道][配置找不到]道具卡激活出错,AO[xportal_pconf]表中找不到配置信息,消息返回队列,请求资源:{" + itemCard.getSource() + "}, 消息返回队列,异常信息:", e);
			throw e;
		} catch (Exception e) {
			logger.error("[忽略]道具卡激活失败,其他错误信息,请求资源:" + itemCard.getSource(), e);
		} catch(Throwable t){
			logger.error("Throwable:",t);
			throw new PconfNotFoundException(t.getMessage());
		}
	}

	/**
	 * 
	 * @说明: 获取大区列表
	 * @return: List<String>
	 */
	private List<String> getAllgidByProduct(String product) {
		List<String> list = new ArrayList<String>();
		List<GGameGroupNode> areas = productConfigurationService.getAreaByProduct(product);
		for (GGameGroupNode gGameGroupNode : areas) {
			list.add(gGameGroupNode.getId());
		}
		return list;
	}

	// /**
	// *
	// * @说明: 获取大区列表
	// * @return: List<String>
	// */
	// private List<String> getAllgidByProduct(String product) throws
	// SQLException {
	//
	// List<String> list = null;
	// Connection connection = null;
	// PreparedStatement statement = null;
	// try {
	// connection = defaultDataSource.getConnection();
	// statement =
	// connection.prepareStatement("select g_id from xportal_pconf where g_type = ? and g_product = ?");
	// statement.setString(1, "group");
	// statement.setString(2, product);
	// ResultSet resultSet = statement.executeQuery();
	// list = new ArrayList<String>();
	// while (resultSet.next()) {
	// list.add(resultSet.getString(1));
	// }
	// return list;
	// } catch (SQLException e) {
	// logger.error("激活所有大区,获取所有大区列表失败!");
	// throw e;
	// } finally {
	// if (statement != null) {
	// try {
	// statement.close();
	// statement = null;
	// } catch (Exception ex) {
	// }
	// }
	//
	// if (connection != null) {
	// try {
	// connection.close();
	// connection = null;
	// } catch (Exception ex) {
	// }
	// }
	// }
	// }

	/**
	 * 
	 * @说明: 发送到[道具卡激活备份通道]
	 * @return: void
	 */
	public void reSendMQBackupItemCardActivate(String message) {
		rabbitTemplate.convertAndSend("p1.itemcard.exchange", "itemcard-backup", message);
		logger.info("发送道具卡到[备份通道]成功....");
	}
}
