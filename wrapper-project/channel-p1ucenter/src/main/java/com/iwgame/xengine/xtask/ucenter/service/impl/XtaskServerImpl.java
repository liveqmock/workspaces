/****************************************************************
 *  系统名称  ： '消息任务系统-蜀门&醉逍遥'
 *  文件名    ： XtaskServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.ucenter.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import com.iwgame.pconf.exception.PconfNotFoundException;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xconnector.exeception.DBConnectFailedException;
import com.iwgame.xconnector.exeception.GMConnectException;
import com.iwgame.xengine.xtask.ucenter.model.GoldSend;
import com.iwgame.xengine.xtask.ucenter.util.Constant;

/**
 * 
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-26下午02:29:01
 * @版本: v1.0
 */
@Service("xtaskServer")
public class XtaskServerImpl {

	private final Logger logger = Logger.getLogger(XtaskServerImpl.class);

	@Resource
	private DBConnection dbConnectorConnection;

	/**
	 * 水晶发放
	 * 
	 * @param goldSend
	 * @throws GMConnectException
	 */
	public String goldSend(GoldSend goldSend) {

		int result = -1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("actid", goldSend.getActiveid());
		params.put("accountid", goldSend.getAccountid());
		params.put("user_name", goldSend.getName());
		params.put("role_name", goldSend.getActor());
		params.put("type", goldSend.getType());
		params.put("gameid", goldSend.getGameid());
		params.put("zone", goldSend.getZoneid());
		params.put("value", goldSend.getValue());
		try {
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, Constant.PID, Constant.GUID, null);
			if (null != sqlSessionTemplate) {
				String gameid = params.get("gameid").toString();
				if ("2".equals(gameid)) {// 醉逍遥帐号处理
					String name = params.get("user_name").toString();
					name = name + "_zxy";
					params.put("user_name", name);
				}
				result = sqlSessionTemplate.insert("goldpay.pay", params);
				if (result > 0) {
					return Constant.SUCCESS;
				}
				return "错误返回值:" + result;
			}
			return "获取[sqlSessionTemplate]错误:" + result;
		} catch (GMConnectException e) {
			sleepCustomTime(1);
			logger.error("[重试,请求返回备份通道][连接异常1]请求资源:" + params.toString(), e);
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			sleepCustomTime(1);
			logger.error("[重试,请求返回备份通道][连接异常2]请求资源:" + params.toString(), e);
			throw e;
		} catch (DBConnectFailedException e) {
			sleepCustomTime(1);
			logger.error("[重试,请求返回备份通道][连接异常3]请求资源=>" + params.toString(), e);
			throw e;
		} catch (PconfNotFoundException e) {
			sleepCustomTime(1);
			logger.error("[重试,请求返回备份通道][xportal_pconf 找不到配置信息],请求资源=>" + params.toString(), e);
			throw e;
		} catch (Exception e) {
			logger.error("[忽略]其他错误信息", e);
			return Constant.ERROR + e.getMessage();
		}
	}
	
	
	/**
	 * @说明: 线程睡眠
	 * @return: void
	*/
	private void sleepCustomTime(int second) {
		if(second > 0){
			try {
				Thread.sleep(second * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
