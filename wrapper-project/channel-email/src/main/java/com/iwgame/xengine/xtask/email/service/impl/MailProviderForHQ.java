package com.iwgame.xengine.xtask.email.service.impl;

/****************************************************************
 *  文件名     ： HQMailSendImpl.java
 *  日期         :  2012-12-27
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/

import org.apache.log4j.Logger;
import org.codehaus.xfire.XFireRuntimeException;
import org.springframework.stereotype.Service;

import com.hanqinet.redm.client.model.mail.BasalMailData;
import com.hanqinet.redm.client.model.mail.ObjectFactory;
import com.hanqinet.redm.client.services.mail.AddEmailServiceClient;
import com.hanqinet.redm.client.services.mail.AddEmailServicePortType;
import com.iwgame.xengine.xtask.email.model.Mail;
import com.iwgame.xengine.xtask.email.service.MailProvider;
import com.iwgame.xengine.xtask.email.tools.exception.MailException;
import com.iwgame.xengine.xtask.email.tools.utils.ProperUtils;

/**
 * @类名: HQMailSendImpl
 * @描述: 汉启邮件发送实现
 * 
 * @作者: 吴君杰
 * @邮件: wujunjie@iwgame.com
 * @日期: 2012-12-27下午6:40:26
 * @版本: 1.0
 */
@Service
public class MailProviderForHQ implements MailProvider {

	private final Logger logger = Logger.getLogger(MailProviderForHQ.class);

	/**
	 * 汉启邮件发送实现
	 * 
	 * @see
	 */
	@Override
	public String sendMailHandler(Mail mail) {
		try {
			ObjectFactory factory = new ObjectFactory();
			BasalMailData basalMailData = factory.createBasalMailData();

			// 模板ID
			basalMailData.setTemplateId(factory.createBasalMailDataTemplateId(mail.getTemplateId()));

			// Email地址
			basalMailData.setEmail(factory.createBasalMailDataEmail(mail.getEmailAddress()));

			// 用户昵称
			basalMailData.setNickname(factory.createBasalMailDataNickname(mail.getEmailAddress()));

			// 模板动态参数
			StringBuffer template_params = new StringBuffer();
			template_params.append(mail.getFNAME());
			template_params.append(",");
			template_params.append(mail.getLNAME());
			template_params.append(",");
			template_params.append(mail.getAparam());
			template_params.append(",");
			template_params.append(mail.getBparam());
			template_params.append(",");
			template_params.append(mail.getCparam());

			basalMailData.setData(factory.createBasalMailDataData(template_params.toString()));
			basalMailData.setDataSplit(factory.createBasalMailDataDataSplit(","));
			basalMailData.setTest(false);

			// webservice url
			String ws_wsdl = ProperUtils.getString("hq_ws_wsdl");

			// username
			String ws_username = ProperUtils.getString("hq_ws_username");

			// password
			String ws_password = ProperUtils.getString("hq_ws_password");

			// client
			AddEmailServiceClient client = new AddEmailServiceClient(ws_wsdl);
			final AddEmailServicePortType service = client.getAddEmailServiceHttpPort();
			Integer ret = service.addEmail(ws_username, ws_password, basalMailData);

			// handler result
			return resultHandler(ret, mail.toString());
		} catch (XFireRuntimeException e) {
			throw new MailException(e.getMessage());
		} catch (Exception e) {
			logger.error("邮件发送失败,忽略,请求:" + mail.toString());
			return "other error";
		}
	}

	/**
	 * 邮件发送结果处理
	 */
	@Override
	public String resultHandler(Object reslut, String source) {
		if (null != reslut) {
			Integer ret = Integer.valueOf(reslut.toString());
			if (ret != null && ret >= 1) {
				return "OK";
			} else {
				switch (ret) {
				case -1:
					logger.error("返回值:[" + ret + "],邮件地址不合法,忽略,请求:" + reslut);
					return "邮件地址不合法!";
				case -2:
					logger.error("返回值:[" + ret + "],邮件模板的默认发送设置组(不存在),忽略,请求:" + source);
					return "邮件模板的默认发送设置组(不存在)";
				case -3:
					logger.error("返回值:[" + ret + "],邮件模板id错误(不存在),忽略,请求:" + source);
					return "邮件模板id错误(不存在)";
				case -4:
					logger.error("返回值:[" + ret + "],第三方邮件系统异常,请求:" + source);
					throw new MailException("邮件发送失败,第三方邮件系统异常,稍后重试!");
				case -5:
					logger.error("返回值:[" + ret + "],非法ws用户名或密码,请求:" + source);
					return "非法ws用户名或密码";
				case -6:
					logger.error("返回值:[" + ret + "],非信任ip，请将调用者ip通过web管理端加入信任ip,忽略请求" + reslut);
					return "非信任ip，请将调用者ip通过web管理端加入信任ip";
				case -7:
					logger.error("返回值:[" + ret + "],email 自定义数据(data)最大长度超限,忽略请求:" + reslut);
					return "email 自定义数据(data)最大长度超限";
				case -8:
					logger.error("返回值:[" + ret + "],email 自定义数据分隔符(datasplit) 最大长度超限,忽略请求:" + reslut);
					return "email 自定义数据分隔符(datasplit) 最大长度超限";
				case -10:
					logger.error("返回值:[" + ret + "],第三方邮件系统异常,请求:" + source);
					throw new MailException("邮件发送失败,第三方邮件系统异常,稍后重试!");
				case -11:
					logger.error("返回值:[" + ret + "],超出最大发送数量（可在关于里面查询详情）,请求:" + source);
					throw new MailException("超出最大发送数量（可在关于里面查询详情）,稍后重试!");
				case -12:
					logger.error("返回值:[" + ret + "],超出有效期（可在关于里面查询详情）,请求:" + source);
					throw new MailException("超出有效期（可在关于里面查询详情）,稍后重试!");
				case -13:
					logger.error("返回值:[" + ret + "],汉启邮件系统错误，请与管理员联系,请求:" + source);
					throw new MailException("邮件发送失败,汉启邮件系统错误，请与管理员联系,稍后重试!");
				case -14:
					logger.error("返回值:[" + ret + "],Base64解码错误，请与管理员联系,请求:" + source);
					throw new MailException("邮件发送失败,Base64解码错误，请与管理员联系,稍后重试!");
				case -99:
					logger.error("返回值:[" + ret + "],超出队列最大长度,请稍后再试.(队列最大长度通过web端设置),请求:" + source);
					throw new MailException("邮件发送失败,超出队列最大长度,请稍后再试.(队列最大长度通过web端设置),稍后重试!");
				default:
					logger.error("其他未知返回值,不做处理!");
					return "其他未知返回值,不做处理!";
				}
			}
		} else {
			logger.error("返回值:[ null ],第三方邮件系统异常,请求值:" + source);
			throw new MailException("邮件发送失败,第三方邮件系统异常,稍后重试!");
		}
	}

}
