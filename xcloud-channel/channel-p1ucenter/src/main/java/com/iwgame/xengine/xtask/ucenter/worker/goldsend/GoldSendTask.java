/****************************************************************
 *  系统名称  ： '消息任务系统-蜀门&醉逍遥'
 *  文件名    ： XtaskServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.ucenter.worker.goldsend;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

import com.iwgame.pconf.exception.PconfNotFoundException;
import com.iwgame.xconnector.exeception.DBConnectFailedException;
import com.iwgame.xconnector.exeception.GMConnectException;
import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.ucenter.model.GoldSend;
import com.iwgame.xengine.xtask.ucenter.service.impl.XtaskServerImpl;
import com.iwgame.xengine.xtask.ucenter.util.Constant;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 类说明
 * 
 * @简述：活动发送处理监听
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-4-11 下午07:40:39
 */
@Component
public class GoldSendTask extends AbstractMQWorker {

	private final Logger logger = Logger.getLogger(GoldSendTask.class);

	// @Resource
	// private XtaskWebService xtaskWebService;

	@Resource
	private XtaskServerImpl xtaskServer;
	
	@Resource
	private XtaskTools xtaskTools;

	/**
	 * 
	 * @说明: 处理 收到玩家水晶点卡充值活动的消息
	 * @return: void
	 */
	public void handleMessage(String source) {

		logger.info("收到水晶相关活动请求: " + source);

		GoldSend goldSend = null;
		
		SupportLog monitorlog = new SupportLog();
		

		try {
			goldSend = new GoldSend(source);
			monitorlog.setAppname(goldSend.getAppname());
			monitorlog.setBizcode(31);
			String productid = "";
			if(goldSend.getGameid() == 1){
				productid = "P-P1";
			}else{
				productid = "P-P1.5";
			}
			monitorlog.setProductid(productid);
			monitorlog.setResource(source);
			monitorlog.setVal1(goldSend.getName());
			monitorlog.setVal2(String.valueOf(goldSend.getZoneid()));
			
			
			if (Xvalidator.getInstance().validate(goldSend)) {
				String reslut = xtaskServer.goldSend(goldSend);
				if(Constant.SUCCESS.equalsIgnoreCase(reslut)){
					logger.info("水晶点卡充值成功!");
					monitorlog.setLogtype(0);
					monitorlog.setLognote("水晶点卡充值成功!");
				}else{
					logger.error("水晶点卡充值失败,忽略此记录,返回信息:"+reslut);
					monitorlog.setLognote("水晶点卡充值失败,忽略此记录,返回信息:"+reslut);
				}
			} else {
				logger.error("水晶点卡充值失败,忽略此条记录,请求参数必要字段为空或为NULL!");
				monitorlog.setLognote("水晶点卡充值失败,忽略此条记录,请求参数必要字段为空或为NULL!");
			}
		} catch (GMConnectException e) {
			monitorlog.setLognote("数据库连接异常,重试!,异常:"+e.getMessage());
			throw e;
		} catch (CannotGetJdbcConnectionException e) {
			monitorlog.setLognote("数据库连接异常,重试!,异常:"+e.getMessage());
			throw e;
		} catch (PconfNotFoundException e) {
			monitorlog.setLognote("数据库连接异常,重试!,异常:"+e.getMessage());
			throw e;
		} catch (DBConnectFailedException e) {
			monitorlog.setLognote("数据库连接异常,重试!,异常:"+e.getMessage());
			throw e;
		} catch (Exception e) {
			monitorlog.setLognote("水晶点卡充值失败,忽略此条记录,"+e.getMessage());
			logger.error("[忽略此条消息]请求资源:" + source, e);
		} finally {
			try {
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception ex) {
				logger.error("[水晶]存入日志失败",ex);
			}
		}
	}
}
