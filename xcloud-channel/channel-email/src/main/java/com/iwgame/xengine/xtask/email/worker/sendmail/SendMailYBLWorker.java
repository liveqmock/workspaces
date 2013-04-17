/**************************************************************** *  文件名     ： SendMailHQWorker.java *  日期         :  2012-12-28 *  Company: 上海绿岸网络科技有限公司 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012 *           		All Rights Reserved. *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发 ****************************************************************/package com.iwgame.xengine.xtask.email.worker.sendmail;import javax.annotation.Resource;import org.apache.log4j.Logger;import org.springframework.amqp.rabbit.core.RabbitTemplate;import com.iwgame.xengine.xtask.email.model.Mail;import com.iwgame.xengine.xtask.email.service.MailProvider;import com.iwgame.xengine.xtask.email.tools.exception.MailException;import com.iwgame.xengine.xtask.email.tools.utils.Constant;import com.iwgame.xengine.xtask.email.tools.utils.EmailUtils;import com.iwgame.xengine.xtask.email.tools.utils.ProperUtils;import com.iwgame.xtask.support.model.SupportLog;import com.iwgame.xtask.support.tools.XtaskTools;/** * @类名: SendMailHQWorker * @描述: TODO(...) *  * @作者: 吴君杰 * @邮件: wujunjie@iwgame.com * @日期: 2012-12-28下午7:12:51 * @版本: 1.0 */public class SendMailYBLWorker {	private final Logger logger = Logger.getLogger(Constant.LOG4J_YBL);	@Resource	private XtaskTools xtaskTools;	@Resource	private RabbitTemplate rabbitTemplate;	@Resource	private MailProvider mailProviderForYBL;	public void handleMessage(String source) {		logger.info("益百利邮件服务,收到邮件请求:" + source);		Mail mail = null;		SupportLog monitorlog = new SupportLog();		monitorlog.setBizcode(11);		monitorlog.setResource(source);		monitorlog.setProductid("COMMON");		try {			mail = new Mail(source);			boolean checkEmailAddress = EmailUtils.regularExpCommon(ProperUtils.getString("regEmail"), mail.getEmailAddress());			monitorlog.setAppname(mail.getAppname());			monitorlog.setVal1(mail.getEmailAddress());			monitorlog.setVal2(mail.getTemplateId().toString());			monitorlog.setVal3(mail.getChannelid().toString());			monitorlog.setVal4(mail.getProvider());			if (checkEmailAddress) {				String result = mailProviderForYBL.sendMailHandler(mail);				if ("OK".equalsIgnoreCase(result)) {					logger.info("益百利邮件服务,发送邮件成功: " + mail.getEmailAddress());					monitorlog.setLogtype(0);					monitorlog.setLognote("益百利邮件发送成功!");				} else {					monitorlog.setLognote("益百利邮件发送失败! 原因:" + result);				}				// 存入日志				xtaskTools.saveXtaskLogInfo(monitorlog);			} else {				logger.error("Email地址不合法!忽略请求:" + mail.getEmailAddress());			}		} catch (MailException e) {			mail.setFailed("failed");			mail.setTemplateId(mail.getChannelid());			rabbitTemplate.convertAndSend(Constant.EXCHANGE_MAIL, "sendemail-dispatch", mail.toString());		} catch (Throwable e) {			logger.error("汉启邮件发送其他异常", e);			// 存入日志			try {				monitorlog.setLognote("益百利邮件发送失败! 原因:" + e.getMessage());				xtaskTools.saveXtaskLogInfo(monitorlog);			} catch (Throwable ex) {			}		}	}}