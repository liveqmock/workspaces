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
import com.iwgame.xengine.xtask.game.dao.ScurityOAHandlerDao;
import com.iwgame.xengine.xtask.game.model.AccountBean;
import com.iwgame.xengine.xtask.game.model.KickBean;
import com.iwgame.xengine.xtask.game.model.SafeModelBean;
import com.iwgame.xengine.xtask.game.model.TalkBean;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.iwgame.xengine.xtask.game.util.ParamValidator;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/**
 * 类说明
 * 
 * @简述： 用户和角色操作类实现
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改：2012-12-12 下午02:30:24
 */
@Service
public class GameTaskServiceImpl {

	private final Logger logger = Logger.getLogger("game");

	@Resource
	private ScurityOAHandlerDao scurityOAHandlerDao;

	@Resource
	private DBConnection dbConnectorConnection;

	@Resource
	private RabbitTemplate rabbitTemplate;

	@Resource
	private ProductConfigurationService productConfigurationService;

	/**
	 * @说明: 增加安全模式
	 * @return: void
	 */
	public void addSafeModel(SafeModelBean safeModelBean) {
		Map<String, String> params = new HashMap<String, String>();
		String pid = ConfigProperties.getString("PID");
		try {
			// DoGwsCmd {unban trade $rolename} -gws $str_group
			StringBuilder txt = new StringBuilder();
			txt.append("DoGwsCmd {ban trade ");
			txt.append(ParamValidator.tranferEncode(safeModelBean.getRolename()));
			txt.append("} ");
			txt.append("-gws ");

			params.put("txt", txt.toString());

			// g_id 数据库ID唯一标识(由产品PID+大区ID或者 具体组的ID)
			String targetid = pid + "-" + safeModelBean.getGuid();

			// 获取连接
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);
			ProxoolDataSource dataSource = (ProxoolDataSource) sqlSessionTemplate.getConfiguration().getEnvironment().getDataSource();
			String url = dataSource.getDriverUrl();
			int result = sqlSessionTemplate.insert("game-sqlmap.insertCommand", params);
			if (result > 0) {
				logger.info("[添加安全模式  " + safeModelBean.getRolename() + " 成功!]:服务器返回值[" + result + "] " + url);
			}
			updateResultTrackSuccess(safeModelBean.getBatchid());
		} catch (BadSqlGrammarException e) {
			logger.error("[重试,增加安全模式,请求返回备份通道][连接异常1]添加安全模式,请求资源:" + safeModelBean.toString() + ",异常信息:", e);
			throw e;
		} catch (GMConnectException e) {
			logger.error("[重试,增加安全模式,请求返回备份通道][连接异常1]添加安全模式,请求资源:" + safeModelBean.toString() + ",异常信息:", e);
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("[重试,增加安全模式,请求返回备份通道][连接异常2]添加安全模式,请求资源:" + safeModelBean.toString() + ",异常信息:", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("[重试,增加安全模式,请求返回备份通道][连接异常3]添加安全模式,AO[xportal_pconf]表中的配置[g_properties]信息连接失败,请求资源=>" + safeModelBean.toString() + ",异常信息:", e);
			throw e;
		} catch (PconfNotFoundException e) {
			logger.error("[重试,增加安全模式,请求返回备份通道][配置找不到]添加安全模式,AO[xportal_pconf]表中找不到配置信息,请求资源=>" + safeModelBean.toString() + " ,异常信息:", e);
			throw e;
		} catch (Exception e) {
			updateResultTrackFailed(safeModelBean.getBatchid());
			logger.error("[此条消息忽略] [踢人]其他异常信息", e);
		}
	}

	/**
	 * @说明: 解除安全模式
	 * @return: void
	 */
	public void unSafeModel(SafeModelBean safeModelBean) {
		Map<String, String> params = new HashMap<String, String>();
		String pid = ConfigProperties.getString("PID");
		try {
			// DoGwsCmd {unban trade $rolename} -gws $str_group
			StringBuilder txt = new StringBuilder();
			txt.append("DoGwsCmd {unban trade ");
			txt.append(ParamValidator.tranferEncode(safeModelBean.getRolename()));
			txt.append("} ");
			txt.append("-gws ");

			params.put("txt", txt.toString());

			// g_id 数据库ID唯一标识(由产品PID+大区ID或者 具体组的ID)
			String targetid = pid + "-" + safeModelBean.getGuid();

			// 获取连接
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);

			ProxoolDataSource dataSource = (ProxoolDataSource) sqlSessionTemplate.getConfiguration().getEnvironment().getDataSource();
			String url = dataSource.getDriverUrl();

			int result = sqlSessionTemplate.insert("game-sqlmap.insertCommand", params);
			if (result > 0) {
				logger.info("[解除安全模式  " + safeModelBean.getRolename() + " 成功!]:服务器返回值[" + result + "] " + url);
			}
			updateResultTrackSuccess(safeModelBean.getBatchid());

			// 添加自助解除安全模式添加记录
			addSafeRecordByUnSafe(safeModelBean);
		} catch (BadSqlGrammarException e) {
			logger.error("[重试,请求返回备份通道][连接异常1]解除安全模式,请求资源:" + safeModelBean.toString() + ",异常信息:", e);
			throw e;
		} catch (GMConnectException e) {
			logger.error("[重试,请求返回备份通道][连接异常1]解除安全模式,请求资源:" + safeModelBean.toString() + ",异常信息:", e);
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("[重试,请求返回备份通道][连接异常2]解除安全模式,请求资源:" + safeModelBean.toString() + ",异常信息:", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("[重试,请求返回备份通道][连接异常3]解除安全模式,AO[xportal_pconf]表中的配置[g_properties]信息连接失败,请求资源=>" + safeModelBean.toString() + ",异常信息:", e);
			throw e;
		} catch (PconfNotFoundException e) {
			logger.error("[重试,请求返回备份通道][配置找不到]解除安全模式,AO[xportal_pconf]表中找不到配置信息,请求资源=>" + safeModelBean.toString() + " ,异常信息:", e);
			throw e;
		} catch (Exception e) {
			updateResultTrackFailed(safeModelBean.getBatchid());
			logger.error("[此条消息忽略] [踢人]其他异常信息", e);
		}
	}

	/**
	 * 
	 * @说明: 踢人 操作,一般不要用
	 * @return: void
	 */
	public void kickUserActivity(KickBean kickBean) throws Exception {

		Map<String, String> params = new HashMap<String, String>();
		// 产品PID (P-P1,P-P1.5)
		String pid = ConfigProperties.getString("PID");

		// 醉逍遥帐号加后缀
		if ("P-P1.5".equalsIgnoreCase(pid)) {
			if (!kickBean.getUsername().endsWith("_zxy")) {
				kickBean.setUsername(kickBean.getUsername() + "_zxy");
			}
		}

		try {
			// T人命令 =>
			// kick 角色名
			StringBuilder txt = new StringBuilder();
			txt.append("acct lock {");
			txt.append(ParamValidator.tranferEncode(kickBean.getUsername()));
			txt.append("} ");
			txt.append(0);
			txt.append(" {");
			txt.append("}");

			params.put("txt", txt.toString());

			// g_id 数据库ID唯一标识(由产品PID+大区ID或者 具体组的ID)
			String targetid = pid + "-" + kickBean.getGuid();

			// 获取连接
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);
			ProxoolDataSource dataSource = (ProxoolDataSource) sqlSessionTemplate.getConfiguration().getEnvironment().getDataSource();
			String url = dataSource.getDriverUrl();
			int result = sqlSessionTemplate.insert("game-sqlmap.insertCommand", params);
			if (result > 0) {
				logger.info("[踢人  " + kickBean.getUsername() + " 成功!]:服务器返回值[" + result + "]" + url);
			}
		} catch (BadSqlGrammarException e) {
			logger.error("[重试,请求返回备份通道][连接异常1]踢人出错,请求资源:" + kickBean.getSource() + ",异常信息:", e);
			throw e;
		} catch (GMConnectException e) {
			logger.error("[重试,请求返回备份通道][连接异常1]踢人出错,请求资源:" + kickBean.getSource() + ",异常信息:", e);
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("[重试,请求返回备份通道][连接异常2]踢人出错,请求资源:" + kickBean.getSource() + ",异常信息:", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("[重试,请求返回备份通道][连接异常3]踢人出错,AO[xportal_pconf]表中的配置[g_properties]信息连接失败,请求资源=>" + kickBean.getSource() + ",异常信息:", e);
			throw e;
		} catch (PconfNotFoundException e) {
			logger.error("[重试,请求返回备份通道][配置找不到]踢人出错,AO[xportal_pconf]表中找不到配置信息,请求资源=>" + kickBean.getSource() + " ,异常信息:", e);
			throw e;
		} catch (Exception e) {
			logger.error("[此条消息忽略] [踢人]其他异常信息", e);
		}
	}

	/**
	 * 
	 * @说明: 禁言 & 解除禁言
	 * @return: void
	 */
	public void talkActivity(TalkBean talkBean, String command) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();

		String txt = "";

		try {
			if ("禁言".equals(command)) {
				// DoGwsCmd {ban surespeak 角色名称 1} -gws 组名称
				StringBuilder builder = new StringBuilder();
				builder.append("DoGwsCmd ");
				builder.append(" {ban surespeak ");
				builder.append(ParamValidator.tranferEncode(talkBean.getRolename()));
				builder.append(" ");
				builder.append(talkBean.getValidtime());
				builder.append("}");
				builder.append(" -gws ");
				builder.append(ParamValidator.tranferEncode(talkBean.getGroupname()));
				txt = builder.toString();
				params.put("txt", txt);

			} else {
				// DoGwsCmd {unban surespeak 角色名称} -gws 组名称
				StringBuilder builder = new StringBuilder();
				builder.append("DoGwsCmd ");
				builder.append(" {unban surespeak ");
				builder.append(ParamValidator.tranferEncode(talkBean.getRolename()));
				builder.append("}");
				builder.append(" -gws ");
				builder.append(ParamValidator.tranferEncode(talkBean.getGroupname()));
				txt = builder.toString();
				params.put("txt", txt);
			}

			// 产品PID (P-P1,P-P1.5)
			String pid = ConfigProperties.getString("PID");
			// g_id 数据库ID唯一标识(由产品PID+大区ID或者 具体组的ID)
			String targetid = pid + "-" + talkBean.getGuid();
			// 获取连接
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);

			ProxoolDataSource dataSource = (ProxoolDataSource) sqlSessionTemplate.getConfiguration().getEnvironment().getDataSource();
			String url = dataSource.getDriverUrl();

			int result = sqlSessionTemplate.insert("game-sqlmap.insertCommand", params);

			if (result > 0) {
				logger.info("[" + command + " 用户 (" + talkBean.getRolename() + ")成功]:服务器返回结果[" + result + "]" + url);
			}
		} catch (BadSqlGrammarException e) {
			logger.error("[重试,请求返回备份通道][连接异常1]" + command + "出错,请求资源=>" + talkBean.getSource() + ",异常信息:", e);
			throw e;
		} catch (GMConnectException e) {
			logger.error("[重试,请求返回备份通道][连接异常1]" + command + "出错,请求资源=>" + talkBean.getSource() + ",异常信息:", e);
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("[重试,请求返回备份通道][连接异常2]" + command + "出错,请求资源=>" + talkBean.getSource() + ",异常信息:", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("[重试,请求返回备份通道][连接异常3]" + command + "出错,AO[xportal_pconf]表中的配置[g_properties]信息连接失败,请求资源=>" + talkBean.getSource() + ",异常信息:", e);
			throw e;
		} catch (PconfNotFoundException e) {
			logger.error("[重试,请求返回备份通道][配置找不到]" + command + "出错,AO[xportal_pconf]表中找不到配置信息,请求资源=>" + talkBean.getSource() + ",异常信息:", e);
			throw e;
		} catch (Exception e) {
			logger.error("[此条消息忽略] [" + command + "] 出错,其他错误信息", e);
		}
	}

	/**
	 * 
	 * @说明: 帐号解封,解冻
	 * @return: void
	 */
	public void accountUnlock(AccountBean accountBean) throws Exception {

		// 产品PID (P-P1,P-P1.5)
		String pid = ConfigProperties.getString("PID");
		// 醉逍遥帐号加后缀
		if ("P-P1.5".equalsIgnoreCase(pid)) {
			if (!accountBean.getUsername().endsWith("_zxy")) {
				accountBean.setUsername(accountBean.getUsername() + "_zxy");
			}
		}

		// 玩家自助解冻
		if (accountBean.getOptype() == 4) {
			// 状态说明: 1封杀 2冻结 3正常,只有当冻结状态才去解冻
			Map<String, Integer> reslut = null;
			Integer status = -1;
			try {
				reslut = scurityOAHandlerDao.checkUserStatusByUsername(accountBean.getUsername());
				if (reslut != null) {
					status = reslut.get("type");
				}
			} catch (Exception e) {
				logger.error("查询OA-Security当前玩家状态异常,如有需要请补发,忽略!请求: " + accountBean.getSource(), e);
				status = -10;
			}

			if (status != null && 2 == status) {
				unlook(accountBean, pid);
				// 前台自助解除冻结,添加记录到OASecurity平台的历史记录表
				addKilledRecordByUnlook(accountBean);
			} else {
				logger.error("前台自助解封失败, 封杀记录中玩家当前状态为: " + status + ", [-1: 封杀记录中没对应记录],[1:封杀],[2:冻结],[3:正常],[-10:查询状态异常],忽略前台自助解冻请求! 请求:" + accountBean.getSource());
			}
		} else {
			unlook(accountBean, pid);
		}
	}

	/**
	 * 
	 * @说明: 帐号封杀,冻结
	 * @return: void
	 */
	public void accountLock(AccountBean accountBean) throws Exception {
		// 产品PID (P-P1,P-P1.5)
		String pid = ConfigProperties.getString("PID");
		// 醉逍遥帐号加后缀
		if ("P-P1.5".equalsIgnoreCase(pid)) {
			if (!accountBean.getUsername().endsWith("_zxy")) {
				accountBean.setUsername(accountBean.getUsername() + "_zxy");
			}
		}

		// optype 1 封杀 2冻结 3解封 4自助解冻
		Map<String, Integer> reslut = null;
		if (accountBean.getOptype() == 1 || accountBean.getOptype() == 2) {
			try {
				reslut = scurityOAHandlerDao.checkUserStatusByUsername(accountBean.getUsername());
			} catch (Exception e) {
				logger.error("查询OA-Security当前玩家状态异常,如有需要请补发,忽略!请求: " + accountBean.getSource(), e);
			}
		}
		if (reslut != null) {
			try {
				if (accountBean.getOptype() == 1) {
					Integer days = reslut.get("days");
					Integer type = reslut.get("type");
					if (type == 1 && days >= 5000) {
						logger.info("忽略封杀请求,帐号[ " + accountBean.getUsername() + " ],此帐号的封杀记录中的状态为: " + type + " (1:封杀,2冻结,3正常) ,天数为:" + days);
					} else {
						lock(accountBean, pid);
					}
				} else {
					// 冻结限制条件,前台已经限制
					lock(accountBean, pid);
				}
			} catch (Exception e) {
				logger.error("获取玩家状态异常!,取消冻结操作! 如有需要请补发,请求:" + accountBean.getSource(), e);
			}
		} else {
			lock(accountBean, pid);
		}

	}

	/**
	 * 解封,解冻实现类
	 * 
	 * @param accountBean
	 * @throws MySQLSyntaxErrorException
	 */
	private void unlook(AccountBean accountBean, String pid) {
		try {
			// 封停开始时间 Linux时间戳到秒
			long begintime = System.currentTimeMillis() / 1000;

			// 解封参数
			Map<String, Object> acconutParams = new HashMap<String, Object>();
			acconutParams.put("ld", 0);
			acconutParams.put("ls", begintime);
			acconutParams.put("le", begintime);
			acconutParams.put("lr", ParamValidator.tranferEncode(accountBean.getNote()));
			acconutParams.put("name", ParamValidator.tranferEncode(accountBean.getUsername()));

			if ("all".equalsIgnoreCase(accountBean.getGuid())) {
				// 全区解封,解冻,得到所有大区列表
				List<String> list = getAllgidByProduct(pid);
				for (String targetid : list) {
					SqlSessionTemplate sqlSessionTemplate = null;
					sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);
					sqlSessionTemplate.update("game-sqlmap.lockAccount", acconutParams);
				}
				logger.info("解封帐号  [" + accountBean.getUsername() + "]成功!");

				// 更新OA封杀历史记录的成功状态
				updateKilledRecordSuccess(accountBean, pid);

				// 更新结果追踪的完成数量
				updateResultTrackSuccess(accountBean.getBatchid());

			} else {
				// 单独大区封杀解封,解冻 g_id 数据库ID唯一标识(由产品PID+大区ID或者 具体组的ID)
				String targetid = ConfigProperties.getString("PID") + "-" + accountBean.getGuid();
				SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);
				int update = sqlSessionTemplate.update("game-sqlmap.lockAccount", acconutParams);
				if (update > 0) {
					logger.info("解封帐号  [" + accountBean.getUsername() + "]成功!");
				}
				// 更新OA封杀历史记录的成功状态
				updateKilledRecordSuccess(accountBean, pid);

				// 更新结果追踪的完成数量
				updateResultTrackSuccess(accountBean.getBatchid());
			}
		} catch (BadSqlGrammarException e) {
			logger.error("[mysql 语法异常][语法异常]解封,解冻出错,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (GMConnectException e) {
			logger.error("[重试,请求返回备份通道][连接异常1]解封,解冻出错,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("[重试,请求返回备份通道][连接异常2]解封,解冻出错,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("[重试,请求返回备份通道][连接异常3]解封,解冻出错,AO[xportal_pconf]表中的配置[g_properties]信息连接失败,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (PconfNotFoundException e) {
			logger.error("[重试,请求返回备份通道][配置找不到]解封,解冻出错,AO[xportal_pconf]表中找不到配置信息,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (Exception e) {
			logger.error("[此条消息忽略][ 解封,解冻 ] 出错,其他异常信息:", e);
		}
	}

	/**
	 * @param 封杀冻结实现类
	 */
	private void lock(AccountBean accountBean, String pid) throws MySQLSyntaxErrorException, SQLException {
		// 封停开始时间 Linux时间戳到秒
		long begintime = System.currentTimeMillis() / 1000;
		// 封停结束时间
		long endtime = begintime + (accountBean.getSealtime() * 60);

		/**
		 * 是否显示封停的时间 0:不显示封停时间,1:显示封停时间
		 */
		if (accountBean.getShowtime() == 0) {
			accountBean.setNote("_登入失败原因为:" + accountBean.getNote());
		}

		Map<String, Object> commandParams = null;
		// 是否需要踢人
		if (accountBean.getOnline() == 1) {
			commandParams = new HashMap<String, Object>();
			// 踢人命令
			StringBuilder txt = new StringBuilder();
			txt.append("acct lock {");
			txt.append(ParamValidator.tranferEncode(accountBean.getUsername()));
			txt.append("} ");
			txt.append(accountBean.getSealtime());
			txt.append(" {");
			txt.append(ParamValidator.tranferEncode(accountBean.getNote()));
			txt.append("}");
			commandParams.put("txt", txt.toString());
		}

		Map<String, Object> acconutParams = new HashMap<String, Object>();
		// 封停帐号
		acconutParams.put("ld", 1);
		acconutParams.put("ls", begintime);
		acconutParams.put("le", endtime);
		acconutParams.put("lr", ParamValidator.tranferEncode(accountBean.getNote()));
		acconutParams.put("name", ParamValidator.tranferEncode(accountBean.getUsername()));

		try {
			/**
			 * 全区封杀,冻结
			 */
			if ("all".equalsIgnoreCase(accountBean.getGuid())) {
				List<String> list = getAllgidByProduct(pid);
				SqlSessionTemplate sqlSessionTemplate = null;
				for (String targetid : list) {
					sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);

					/**
					 * 是否踢下线 0:不执行踢人操作,1:执行踢人操作
					 */
					if (accountBean.getOnline() == 1) {
						// T用户下线命令
						sqlSessionTemplate.insert("game-sqlmap.insertCommand", commandParams);
					}

					/**
					 * 封停操作
					 */
					sqlSessionTemplate.update("game-sqlmap.lockAccount", acconutParams);
				}

				logger.info("封停,冻结,帐号  [" + accountBean.getUsername() + " ]成功!");
				// 更新历史记录处理状态
				updateKilledRecordSuccess(accountBean, pid);
				// 更新结果跟踪表记录
				updateResultTrackSuccess(accountBean.getBatchid());
			} else {
				String targetid = ConfigProperties.getString("PID") + "-" + accountBean.getGuid();
				// 获取连接
				SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);

				/**
				 * 是否踢下线 0:不执行踢人操作,1:执行踢人操作
				 */
				if (accountBean.getOnline() == 1) {
					// T用户下线命令
					sqlSessionTemplate.insert("game-sqlmap.insertCommand", commandParams);
					logger.info("强制下线  帐号 [" + accountBean.getUsername() + "]成功!");
				}

				/**
				 * 封停操作
				 */
				sqlSessionTemplate.update("game-sqlmap.lockAccount", acconutParams);
				logger.info("封停,冻结,帐号  [" + accountBean.getUsername() + " ]成功!");
				// 更新历史记录处理状态
				updateKilledRecordSuccess(accountBean, pid);

				// 更新结果跟踪表记录
				updateResultTrackSuccess(accountBean.getBatchid());
			}
		} catch (BadSqlGrammarException e) {
			logger.error("[mysql 语法异常][语法异常]封杀,冻结出错,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (MySQLSyntaxErrorException e) {
			logger.error("[mysql 语法异常][语法异常]封杀,冻结出错,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (SQLException e) {
			logger.error("[重试,请求返回备份通道][连接异常0]封杀,冻结出错,请求资源:{" + accountBean.getSource() + "}, 异常信息:", e);
			throw e;
		} catch (GMConnectException e) {
			logger.error("[重试,请求返回备份通道][连接异常1]封杀,冻结出错,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("[重试,请求返回备份通道][连接异常2]封杀,冻结出错,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("[重试,请求返回备份通道][连接异常3]封杀,冻结出错,AO[xportal_pconf]表中的配置[g_properties]信息连接失败,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (PconfNotFoundException e) {
			logger.error("[重试,请求返回备份通道][配置找不到]封杀,冻结出错,AO[xportal_pconf]表中找不到配置信息,请求资源=>" + accountBean.getSource() + "异常信息:", e);
			throw e;
		} catch (Exception e) {
			updateResultTrackFailed(accountBean.getBatchid());
			updateKilledRecordFailed(accountBean, pid);
			logger.error("[此条消息忽略][ 封杀,冻结 ] 出错,其他异常信息:", e);
		}
	}

	/**
	 * @说明: 玩家自助解冻,添加记录到OASecurity平台的历史记录表
	 * @return: void
	 */
	private void addKilledRecordByUnlook(AccountBean accountBean) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("zone", accountBean.getGuid());
			param.put("username", accountBean.getUsername());
			param.put("type", 3);
			param.put("days", 0);
			param.put("batchid", "");
			param.put("operator", accountBean.getUsername());
			param.put("handleStatus", 0);
			param.put("causeNote", "玩家自助解冻");
			param.put("causeType", 4);
			param.put("killedType", 4);
			scurityOAHandlerDao.insertKilledRecordByUnlook(param);
		} catch (Exception e) {
			logger.error("前台自助解除安全模式,添加记录到[OASecurity]失败! 异常:", e);
		}
	}

	/**
	 * @说明: 前台自助解除安全模式,添加记录到OASecurity平台的历史记录表
	 * @return: void
	 */
	private void addSafeRecordByUnSafe(SafeModelBean safeModelBean) {
		try {
			// 前台自助解除安全模式批次号为空
			if ("".equals(safeModelBean.getBatchid())) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("username", safeModelBean.getUsername());
				param.put("rolename", safeModelBean.getRolename());
				param.put("dbid", safeModelBean.getDbid());
				param.put("operator", safeModelBean.getUsername());
				param.put("mode_type", 2);
				param.put("cause_note", "玩家自助解冻");
				param.put("groupname", safeModelBean.getGroupname());
				param.put("guid", safeModelBean.getGuid());
				scurityOAHandlerDao.insertSafeRecordByUnSafe(param);
			}
		} catch (Exception e) {
			logger.error("前台自助解除安全模式,添加到OASecurity平台的历史记录表失败!");
		}

	}

	/**
	 * @说明: 更新OA封杀历史记录的成功状态
	 * @return: void
	 */
	private void updateKilledRecordSuccess(AccountBean accountBean, String pid) {
		// 更新返回状态
		if (!"".equals(accountBean.getBatchid()) && !"".equals(accountBean.getUsername())) {

			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("batchid", accountBean.getBatchid());
				param.put("username", accountBean.getUsername());
				scurityOAHandlerDao.updateKilledRecordSuccess(param);
				logger.info("帐号: [ " + accountBean.getUsername() + " ] 更新封杀,冻结,状态成功!");
			} catch (Exception e) {
				logger.error("更新封杀状态失败! 异常信息:" + e.getMessage());
			}
		}
	}

	/**
	 * @说明: 更新OA封杀历史记录的失败状态
	 * @return: void
	 */
	private void updateKilledRecordFailed(AccountBean accountBean, String pid) {
		// 更新返回状态
		if (!"".equals(accountBean.getBatchid()) && !"".equals(accountBean.getUsername())) {

			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("batchid", accountBean.getBatchid());
				param.put("username", accountBean.getUsername());
				scurityOAHandlerDao.updateKilledRecordFailed(param);
			} catch (Exception e) {
				logger.error("更新封杀状态失败! 异常信息:" + e.getMessage());
			}
		}
	}

	/**
	 * @说明: 更新结果追踪的成功数量
	 * @return: void
	 */
	private void updateResultTrackSuccess(String batchid) {
		// 更新返回状态
		if (!"".equals(batchid)) {
			try {
				scurityOAHandlerDao.updateResultTrackSuccess(batchid);
				logger.info("更新追踪结果记录成功!batchid:" + batchid);
			} catch (Exception e) {
				logger.error("更新追踪结果记录失败! 异常信息:" + e.getMessage());
			}
		}
	}

	/**
	 * @说明: 更新结果追踪的是失败数量
	 * @return: void
	 */
	private void updateResultTrackFailed(String batchid) {
		// 更新返回状态
		if (!"".equals(batchid)) {
			try {
				scurityOAHandlerDao.updateResultTrackFailed(batchid);
			} catch (Exception e) {
				logger.error("更新追踪结构记录失败! 异常信息:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @说明: 发送到[踢人备份通道]
	 * @return: void
	 */
	public void reSendMQBackupKickUserActivate(String message) {
		rabbitTemplate.convertAndSend("p1.account.exchange", "kickuser-backup", message);
		logger.info("发送[踢人]请求到备用队列成功....");
	}

	/**
	 * 
	 * @说明: 发到[封停帐号备份通道]
	 * @return: void
	 */
	public void reSendMQBackupLockAccountActivate(String message) {
		rabbitTemplate.convertAndSend("p1.account.exchange", "lockaccount-backup", message);
		logger.info("发送[封停帐号]请求到备用队列成功....");
	}

	/**
	 * 
	 * @说明: 发送到[解除封停帐号备份通道]
	 * @return: void
	 */
	public void reSendMQBackupUnLockAccountActivate(String message) {
		rabbitTemplate.convertAndSend("p1.account.exchange", "unlockaccount-backup", message);
		logger.info("发送[解除封停]请求到备用队列成功....");
	}

	/**
	 * 
	 * @说明: 发送到[解除禁言到备份通道]
	 * @return: void
	 */
	public void reSendMQBackupTalkUserActivate(String message) {
		rabbitTemplate.convertAndSend("p1.account.exchange", "talk-backup", message);
		logger.info("发送[解除禁言]请求到备用队列成功....");
	}

	/**
	 * 
	 * @说明: 发送到[添加安全模式到备份通道]
	 * @return: void
	 */
	public void reSendMQBackupSafeModelActivate(String message) {
		rabbitTemplate.convertAndSend("p1.account.exchange", "safemodel-backup", message);
		logger.info("发送[添加安全模式]请求到备用队列成功....");
	}

	/**
	 * 
	 * @说明: 发送到[添加安全模式到备份通道]
	 * @return: void
	 */
	public void reSendMQBackupUnSafeModelActivate(String message) {
		rabbitTemplate.convertAndSend("p1.account.exchange", "unsafemodel-backup", message);
		logger.info("发送[解除安全模式]请求到备用队列成功....");
	}

	/**
	 * 
	 * @说明: 发送到[禁言备用通道]
	 * @return: void
	 */
	public void reSendMQBackupNoTalkUserActivate(String message) {
		rabbitTemplate.convertAndSend("p1.account.exchange", "notalk-backup", message);
		logger.info("发送[禁言]请求到备用队列成功....");
	}

	/**
	 * 
	 * @说明: 获取大区列表
	 * @return: List<String>
	 */
	private List<String> getAllgidByProduct(String product) throws SQLException {
		List<String> list = new ArrayList<String>();
		List<GGameGroupNode> areas = productConfigurationService.getAreaByProduct(product);
		for (GGameGroupNode gGameGroupNode : areas) {
			list.add(gGameGroupNode.getId());
		}
		return list;
	}
}
