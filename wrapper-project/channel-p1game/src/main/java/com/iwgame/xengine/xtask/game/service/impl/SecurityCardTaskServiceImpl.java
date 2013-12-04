package com.iwgame.xengine.xtask.game.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import com.iwgame.pconf.exception.PconfNotFoundException;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xconnector.exeception.DBConnectFailedException;
import com.iwgame.xconnector.exeception.GMConnectException;
import com.iwgame.xengine.xtask.game.model.SecurityCardBean;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;

/**
 * 
 * @描述: 密保卡绑定&解除
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-25上午11:29:01
 * @版本: 1.0
 */
@Service
public class SecurityCardTaskServiceImpl {

	private final Logger logger = Logger.getLogger("game");

	@Resource
	private DBConnection dbConnectorConnection;

	@Resource
	private RabbitTemplate rabbitTemplate;

	/**
	 * 
	 * @说明: 密保卡绑定&解除
	 * @return: void
	 */
	public void securityCardActivity(SecurityCardBean securityCardBean) {

		Map<String, Object> params = new HashMap<String, Object>();

		String command = "";

		// 产品PID (P-P1,P-P1.5)
		String pid = ConfigProperties.getString("PID");

		try {
			// 绑定
			if ("1".equals(securityCardBean.getOperate())) {
				command = "密保卡绑定";
				params.put("type", securityCardBean.getType());
				params.put("name", securityCardBean.getName());
				params.put("str3", securityCardBean.getStr3());
			}
			// 解绑
			if ("2".equals(securityCardBean.getOperate())) {
				command = "密保卡解绑";
				params.put("type", securityCardBean.getType());
				params.put("name", securityCardBean.getName());
				params.put("str3", "");
			}

			// 醉逍遥帐号加后缀
			if ("P-P1.5".equalsIgnoreCase(pid)) {
				if (!securityCardBean.getName().endsWith("_zxy")) {
					params.put("name", securityCardBean.getName() + "_zxy");
				}
			}

			// g_id 数据库ID唯一标识(由产品PID+大区ID或者 具体组的ID)
			String targetid = ConfigProperties.getString("PID") + "-" + securityCardBean.getGuid();
			// 获取连接
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, pid, targetid, null);

			if (sqlSessionTemplate != null) {
				int result = sqlSessionTemplate.insert("game-sqlmap.scecuritycardAction", params);
				if (result > 0) {
					logger.info("[" + command + " 成功!]:服务器返回值[" + result + "]请求资源=>" + securityCardBean.getSource());
				} else {
					logger.error("[" + command + " 失败!]:服务器返回值[" + result + "]请求资源=>" + securityCardBean.getSource());
				}
			} else {
				logger.error("" + command + " 失败!]:请求资源" + securityCardBean.getSource());
			}
		} catch (BadSqlGrammarException e) {
			logger.error("[连接异常1]" + command + "出错,请求资源:" + securityCardBean.getSource() + ", 消息返回队列,异常信息:", e);
			throw e;
		} catch (GMConnectException e) {
			logger.error("[连接异常1]" + command + "出错,请求资源:" + securityCardBean.getSource() + ", 消息返回队列,异常信息:", e);
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("[连接异常2]" + command + "出错,请求资源:" + securityCardBean.getSource() + ", 消息返回队列,异常信息:", e);
			throw e;
		} catch (DBConnectFailedException e) {
			logger.error("[连接异常3]" + command + "出错,AO[xportal_pconf]表中的配置[g_properties]信息连接失败,消息返回队列,请求资源=>" + securityCardBean.getSource() + ", 消息返回队列,异常信息:", e);
			throw e;
		} catch (PconfNotFoundException e) {
			logger.error("[配置找不到]" + command + "出错,AO[xportal_pconf]表中找不到配置信息,消息返回队列,请求资源=>" + securityCardBean.getSource() + ", 消息返回队列,异常信息:", e);
			throw e;
		} catch (Exception e) {
			logger.error("[忽略]其他异常信息", e);
		}

	}

	/**
	 * 密保卡绑定&解除发送到备份通道
	 * 
	 * @param message
	 */
	public void reSendMQBackupSecurityCardActivate(String message) {
		rabbitTemplate.convertAndSend("p1.securitycard.exchange", "securitycard-backup", message);
		logger.info("发送[密保卡业务]请求到备用队列成功....");
	}

}
