/****************************************************************
 *  系统名称  ： '消息任务系统-蜀门&醉逍遥'
 *  文件名    ： XtaskServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.ucenter.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.exeception.GMConnectException;
import com.iwgame.xconnector.service.GMWebServiceConnection;
import com.iwgame.xengine.xtask.ucenter.service.XtaskWebService;
import com.iwgame.xengine.xtask.ucenter.util.Constant;
/**
 * 类说明
 * 
 * @简述： XtaskService实现类
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-2-10 下午06:01:16
 */
@Service
public class XtaskWebServiceImpl implements XtaskWebService {

	private Logger logger = Logger.getLogger(XtaskWebServiceImpl.class);

	@Resource
	private GMWebServiceConnection webserviceConnectorConnection;

	/**
	 * webservice 方法废弃
	 */
	@Override
	@Deprecated
	public void goldSend(String activename, Map<String, Object> params) throws GMConnectException{
		
		//webservice url
		String webServiceURL = "/service/pay/do/key/m1yvPFJJiHT8Mcjiuzai?wsdl";
		
		try {
			// 调用webservice
			Object[] callbackResult = webserviceConnectorConnection.invoke(TargetType.GAME, Constant.PID, Constant.GUID, webServiceURL, "pay", params);
			
			if (callbackResult != null && callbackResult.length > 0) {
				
				String callbackValue = callbackResult[0].toString();
				// 1：表示成功，其他表示失败！
				if (callbackValue.equals("1")) {
					logger.info("["+ activename + "活动发送成功！],请求资源:" + params.toString());
				} else {
					logger.error("[" + activename + "发送失败]:服务器返回错误代码[ " + callbackValue + " ]请求资源:" + params.toString());
				}
			}else{
				logger.error("[" + activename + "发送失败]:服务器返回Null,请求资源:" + params.toString());
			}
		} catch (GMConnectException e) {
			logger.error("[" + activename + "发送失败]：WebService连接异常,请求资源:" + params.toString() + "此条消息将返回消息队列...", e);
			throw e;
		} catch (Exception e) {
			logger.error("[" + activename + "发送失败]：其他异常信息",e);
		}
	}
}
