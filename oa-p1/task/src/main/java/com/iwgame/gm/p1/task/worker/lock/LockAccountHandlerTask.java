/****************************************************************
 *  文件名     ： AccountHandlerTask.java
 *  日期         :  2012-11-16
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.worker.lock;

import javax.annotation.Resource;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.iwgame.gm.p1.task.bean.SecurityAccount;
import com.iwgame.gm.p1.task.bean.SecurityConfig;
import com.iwgame.gm.p1.task.bean.SecurityMail;
import com.iwgame.gm.p1.task.security.dao.sm.SmAccountDataStoreDao;
import com.iwgame.gm.p1.task.security.dao.zxy.ZxyAccountDataStoreDao;
import com.iwgame.gm.p1.task.service.WebsgsLoginService;
import com.iwgame.gm.p1.task.service.XhttpService;
import com.iwgame.gm.p1.task.service.XhttpSolrService;
import com.iwgame.gm.p1.task.util.Constant;

/**
 * @描述: 冻结,封杀
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-16下午02:56:40
 * @版本: v1.0
 */
@Component
public class LockAccountHandlerTask {

	@Resource
	private XhttpService xhttpService;

	@Resource
	private XhttpSolrService xhttpSolrService;

	@Resource
	private WebsgsLoginService websgsLoginService;

	@Resource
	private SmAccountDataStoreDao smAccountDataStoreDao;

	@Resource
	private ZxyAccountDataStoreDao zxyAccountDataStoreDao;

	@Resource
	private SecurityConfig securityConfig;

	private final Logger logger = Logger.getLogger(Constant.LOG_OA_SECURITY);

	public void handleMessage(String source) throws Exception {

		logger.info("收到封杀,冻结请求:" + source);

		try {
			SecurityAccount account = new SecurityAccount(source);

			// 冻结 1.封杀 2.冻结
			if (account.getOptype() == 2) {
				// 处理冻结,给小戴的一张表里写数据
				try {
					if ("p-p1".equalsIgnoreCase(account.getPid())) {
						int reslut = smAccountDataStoreDao.addAccountByUserNameToUnLook(account.getUsername());
						if (reslut > 0) {
							logger.info("帐号 [" + account.getUsername() + "] 冻结写入前台自助解冻表  [m_remove_user] 成功!");
						}
					} else if ("p-p1.5".equalsIgnoreCase(account.getPid())) {
						// 醉逍遥帐号处理
						String username = account.getUsername();
						if (!username.endsWith("_zxy")) {
							username = username + "_zxy";
						}
						int reslut = zxyAccountDataStoreDao.addAccountByUserNameToUnLook(username);
						if (reslut > 0) {
							logger.info("帐号 [" + account.getUsername() + "] 冻结写入前台自助解冻表  [m_remove_user] 成功!");
						}
					}

				} catch (DuplicateKeyException e) {
					logger.info("帐号冻结-此帐号已经存在表中,无需插入!");
				} catch (Exception e) {
					throw e;
				}
			}

			// 封杀,冻结
			int reslut = xhttpService.sendLockAccountByHttpService(account);
			if (reslut == 0) {
				logger.info("帐号 [" + account.getUsername() + "]  封杀,冻结,发送成功!");
			} else {
				logger.error(account.getUsername() + " 封杀,冻结,发送失败! 返回值:" + reslut);
			}

			// 禁止社区登录
			if (account.getSqlogin() == 1) {
				try {
					int res = websgsLoginService.disableLogin(account.getUsername(), account.getPid());
					if (res == 1) {
						logger.info("帐号 [" + account.getUsername() + "]  禁止社区登录成功!");
					} else {
						logger.error("帐号 [" + account.getUsername() + "]  禁止社区登录失败! 返回值:" + res);
					}
				} catch (Exception e) {
					logger.error(account.getUsername() + " 禁止社区登录失败!,异常信息:" + e.getMessage());
				}
			}

			// 邮件通知开关
			if ("on".equals(securityConfig.getMailswitch())) {
				// 邮件通知 0.不通知 1.通知
				if (account.getMailnotice() == 1) {
					try {
						// 根据帐号查询Email地址
						String email = xhttpSolrService.getEmailAddressByAccountId(account);
						if (!"".equals(email)) {
							SecurityMail mail = new SecurityMail();
							// 蜀门邮件模板
							if ("p-p1".equalsIgnoreCase(account.getPid())) {
								mail.setTemplateId(198250);
								mail.setEmailAddress(email);
								mail.setLNAME("帐号:" + account.getUsername() + ", " + account.getCausenote());
							} else {// 醉逍遥邮件模板
								mail.setTemplateId(198315);
								mail.setEmailAddress(email);
								mail.setLNAME("帐号:" + account.getUsername() + ", " + account.getCausenote());
							}
							// 发送邮件
							xhttpService.sendMailByHttpService(mail);
							logger.info("帐号 [" + account.getUsername() + "]  邮件通知发送成功!");
						} else {
							logger.info("帐号 [" + account.getUsername() + "] , 获取email为空, 忽略发送邮件请求....");
						}
					} catch (Exception e) {
						logger.error(account.getUsername() + " 邮件发送失败!,异常信息:" + e.getMessage());
					}
				}
			} else {
				logger.info("邮件开关为关闭状态,忽略邮件请求!");
			}
		} catch (JSONException e) {
			logger.error("忽略,参数异常!请求:" + source, e);
		} catch (Exception e) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
			}
			logger.error("封杀冻结帐号失败,重试...", e);
			throw e;
		}
	}
}
