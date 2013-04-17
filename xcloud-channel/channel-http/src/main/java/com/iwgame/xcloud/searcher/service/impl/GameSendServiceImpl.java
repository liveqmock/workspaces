/****************************************************************
 *  文件名     ： GameServiceImpl.java
 *  日期         :  2012-9-24
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.iwgame.xcloud.searcher.model.AccountParamBean;
import com.iwgame.xcloud.searcher.model.KickParamBean;
import com.iwgame.xcloud.searcher.model.MQSendResult;
import com.iwgame.xcloud.searcher.model.SafeModelBean;
import com.iwgame.xcloud.searcher.model.SecurityCardParamBean;
import com.iwgame.xcloud.searcher.model.TalkParamBean;
import com.iwgame.xcloud.searcher.service.GameSendService;
import com.iwgame.xcloud.searcher.util.SecurityUtil;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
 * @类名: GameSendServiceImpl
 * @描述: 游戏相关操作,例如(踢人,帐号封杀,解封,密保卡绑定,等等..),发送到MQ消息队列实现
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-24下午01:58:05
 * @版本: 1.0
 */
@Service
public class GameSendServiceImpl implements GameSendService {

	private final Logger logger = Logger.getLogger("game");

	@Resource
	private RabbitTemplate rabbitTemplateSm; // 蜀门

	@Resource
	private RabbitTemplate rabbitTemplateZxy; // 醉逍遥

	/**
	 * 密保卡
	 */
	@Override
	public String sendSecurityCardByMQ(String pid, SecurityCardParamBean securityCardParamBean) {
		logger.info("AppName:[" + securityCardParamBean.getAppname() + "]收到密保卡 (产品ID:" + pid + ") 请求:" + securityCardParamBean.toString());

		MQSendResult result = new MQSendResult();
		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(securityCardParamBean)) {
				// 验证签名
				int rc = SecurityUtil.securityAuthority(pid, securityCardParamBean.getName(), securityCardParamBean.getSign(), securityCardParamBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSm.convertAndSend("p1.securitycard.exchange", "securitycard", securityCardParamBean.toString());
						String virtualHost = rabbitTemplateSm.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + securityCardParamBean.getAppname() + "]发送蜀门密保卡到[(" + virtualHost + ")]成功!请求:" + securityCardParamBean.toString());
						result.setResult(rc);

					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxy.convertAndSend("p1.securitycard.exchange", "securitycard", securityCardParamBean.toString());
						String virtualHost = rabbitTemplateZxy.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + securityCardParamBean.getAppname() + "]发送醉逍遥密保卡到[(" + virtualHost + ")]成功!请求:" + securityCardParamBean.toString());
						result.setResult(rc);

					} else {
						logger.info("密保卡请求错误,未找到产品ID:" + pid);
						result.setResult(-4);

					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + securityCardParamBean.getAppname() + "]密保卡签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [username:" + securityCardParamBean.getName()
							+ "] [sign:" + securityCardParamBean.getSign() + "] [ts:" + securityCardParamBean.getTs() + "]");

				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + securityCardParamBean.getAppname() + "]密保卡参数错误,必要参数为空或NULL!请求:" + securityCardParamBean.toString());
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + securityCardParamBean.getAppname() + "]密保卡其他异常!请求:" + securityCardParamBean.toString(), ex);

		}

		return result.toString();

	}

	/**
	 * 禁言
	 */
	@Override
	public String sendNoTalkByMQ(String pid, TalkParamBean talkParamBean) {
		logger.info("AppName:[" + talkParamBean.getAppname() + "]收到禁言指令 (产品ID:" + pid + ") 请求:" + talkParamBean.toString());

		MQSendResult result = new MQSendResult();

		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(talkParamBean)) {
				// 验证签名
				int rc = SecurityUtil.securityAuthority(pid, talkParamBean.getUsername(), talkParamBean.getSign(), talkParamBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSm.convertAndSend("p1.account.exchange", "notalk", talkParamBean.toString());
						String virtualHost = rabbitTemplateSm.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + talkParamBean.getAppname() + "]发送蜀门禁言指令到[(" + virtualHost + ")]成功!请求:" + talkParamBean.toString());
						result.setResult(rc);
					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxy.convertAndSend("p1.account.exchange", "notalk", talkParamBean.toString());
						String virtualHost = rabbitTemplateZxy.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + talkParamBean.getAppname() + "]发送醉逍遥禁言指令到[(" + virtualHost + ")]成功!请求:" + talkParamBean.toString());
						result.setResult(rc);

					} else {
						logger.info("禁言指令请求错误,未找到产品ID:" + pid);
						result.setResult(-4);
					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + talkParamBean.getAppname() + "]禁言指令签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [Rolename:" + talkParamBean.getUsername()
							+ "] [sign:" + talkParamBean.getSign() + "] [ts:" + talkParamBean.getTs() + "]");
				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + talkParamBean.getAppname() + "]禁言指令参数错误,必要参数为空或NULL!请求:" + talkParamBean.toString());
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + talkParamBean.getAppname() + "]禁言指令其他异常!请求:" + talkParamBean.toString(), ex);
		}
		return result.toString();
	}

	/**
	 * 解除禁言
	 */
	@Override
	public String sendTalkByMQ(String pid, TalkParamBean talkParamBean) {
		logger.info("AppName:[" + talkParamBean.getAppname() + "]收到禁言解除指令 (产品ID:" + pid + ") 请求:" + talkParamBean.toString());

		MQSendResult result = new MQSendResult();

		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(talkParamBean)) {
				// 验证签名
				int rc = SecurityUtil.securityAuthority(pid, talkParamBean.getUsername(), talkParamBean.getSign(), talkParamBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSm.convertAndSend("p1.account.exchange", "talk", talkParamBean.toString());
						String virtualHost = rabbitTemplateSm.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + talkParamBean.getAppname() + "]发送蜀门禁言解除指令到[(" + virtualHost + ")]成功!请求:" + talkParamBean.toString());
						result.setResult(rc);
					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxy.convertAndSend("p1.account.exchange", "talk", talkParamBean.toString());
						String virtualHost = rabbitTemplateZxy.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + talkParamBean.getAppname() + "]发送醉逍遥禁言解除指令到[(" + virtualHost + ")]成功!请求:" + talkParamBean.toString());
						result.setResult(rc);
					} else {
						logger.info("禁言解除指令请求错误,未找到产品ID:" + pid);
						result.setResult(-4);
					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + talkParamBean.getAppname() + "]禁言解除指令签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [Rolename:" + talkParamBean.getUsername()
							+ "] [sign:" + talkParamBean.getSign() + "] [ts:" + talkParamBean.getTs() + "]");
				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + talkParamBean.getAppname() + "]禁言解除指令参数错误,必要参数为空或NULL!请求:" + talkParamBean.toString());

			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + talkParamBean.getAppname() + "]禁言解除指令其他异常!请求:" + talkParamBean.toString(), ex);
		}

		return result.toString();
	}

	/**
	 * 账号封停
	 */
	@Override
	public String sendLockAccountByMQ(String pid, AccountParamBean accountParamBean) {
		logger.info("AppName:[" + accountParamBean.getAppname() + "]收到账号封停指令 (产品ID:" + pid + ") 请求:" + accountParamBean.toString());

		MQSendResult result = new MQSendResult();

		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(accountParamBean)) {
				// 验证签名
				int rc = SecurityUtil.securityAuthority(pid, accountParamBean.getUsername(), accountParamBean.getSign(), accountParamBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSm.convertAndSend("p1.account.exchange", "lockaccount", accountParamBean.toString());
						String virtualHost = rabbitTemplateSm.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + accountParamBean.getAppname() + "]发送蜀门账号封停指令到[(" + virtualHost + ")]成功!请求:" + accountParamBean.toString());
						result.setResult(rc);
					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxy.convertAndSend("p1.account.exchange", "lockaccount", accountParamBean.toString());
						String virtualHost = rabbitTemplateZxy.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + accountParamBean.getAppname() + "]发送醉逍遥账号封停指令到[(" + virtualHost + ")]成功!请求:" + accountParamBean.toString());
						result.setResult(rc);
					} else {
						logger.info("账号封停指令请求错误,未找到产品ID:" + pid);
						result.setResult(-4);
					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + accountParamBean.getAppname() + "]账号封停指令签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [Username:" + accountParamBean.getUsername()
							+ "] [sign:" + accountParamBean.getSign() + "] [ts:" + accountParamBean.getTs() + "]");
				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + accountParamBean.getAppname() + "]账号封停指令参数错误,必要参数为空或NULL!请求:" + accountParamBean.toString());
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + accountParamBean.getAppname() + "]账号封停指令其他异常!请求:" + accountParamBean.toString(), ex);
		}
		return result.toString();
	}

	/***
	 * 账号解封
	 */
	@Override
	public String sendUnLockAccountByMQ(String pid, AccountParamBean accountParamBean) {
		logger.info("AppName:[" + accountParamBean.getAppname() + "]收到账号解封指令 (产品ID:" + pid + ") 请求:" + accountParamBean.toString());

		MQSendResult result = new MQSendResult();

		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(accountParamBean)) {
				// 验证签名
				int rc = SecurityUtil.securityAuthority(pid, accountParamBean.getUsername(), accountParamBean.getSign(), accountParamBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSm.convertAndSend("p1.account.exchange", "unlockaccount", accountParamBean.toString());
						String virtualHost = rabbitTemplateSm.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + accountParamBean.getAppname() + "]发送蜀门账号解封指令到[(" + virtualHost + ")]成功!请求:" + accountParamBean.toString());
						result.setResult(rc);
					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxy.convertAndSend("p1.account.exchange", "unlockaccount", accountParamBean.toString());
						String virtualHost = rabbitTemplateZxy.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + accountParamBean.getAppname() + "]发送醉逍遥账号解封指令到[(" + virtualHost + ")]成功!请求:" + accountParamBean.toString());
						result.setResult(rc);
					} else {
						logger.info("账号解封指令请求错误,未找到产品ID:" + pid);
						result.setResult(-4);
					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + accountParamBean.getAppname() + "]账号解封指令签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [Username:" + accountParamBean.getUsername()
							+ "] [sign:" + accountParamBean.getSign() + "] [ts:" + accountParamBean.getTs() + "]");
				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + accountParamBean.getAppname() + "]账号解封指令参数错误,必要参数为空或NULL!请求:" + accountParamBean.toString());
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + accountParamBean.getAppname() + "]账号解封指令其他异常!请求:" + accountParamBean.toString(), ex);
		}
		return result.toString();
	}

	/**
	 * 踢人
	 */
	@Override
	public String sendKickUserByMQ(String pid, KickParamBean kickParamBean) {
		logger.info("AppName:[" + kickParamBean.getAppname() + "]收到踢人指令 (产品ID:" + pid + ") 请求:" + kickParamBean.toString());

		MQSendResult result = new MQSendResult();

		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(kickParamBean)) {
				// 验证签名
				int rc = SecurityUtil.securityAuthority(pid, kickParamBean.getUsername(), kickParamBean.getSign(), kickParamBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSm.convertAndSend("p1.account.exchange", "kickuser", kickParamBean.toString());
						String virtualHost = rabbitTemplateSm.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + kickParamBean.getAppname() + "]发送蜀门踢人指令到[(" + virtualHost + ")]成功!请求:" + kickParamBean.toString());
						result.setResult(rc);
					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxy.convertAndSend("p1.account.exchange", "kickuser", kickParamBean.toString());
						String virtualHost = rabbitTemplateZxy.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + kickParamBean.getAppname() + "]发送醉逍遥踢人指令到[(" + virtualHost + ")]成功!请求:" + kickParamBean.toString());
						result.setResult(rc);
					} else {
						logger.info("踢人指令请求错误,未找到产品ID:" + pid);
						result.setResult(-4);
					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + kickParamBean.getAppname() + "]踢人指令签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [Username:" + kickParamBean.getUsername()
							+ "] [sign:" + kickParamBean.getSign() + "] [ts:" + kickParamBean.getTs() + "]");
				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + kickParamBean.getAppname() + "]踢人指令参数错误,必要参数为空或NULL!请求:" + kickParamBean.toString());
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + kickParamBean.getAppname() + "]踢人指令其他异常!请求:" + kickParamBean.toString(), ex);

		}
		return result.toString();
	}

	/*
	  * @param pid
	  * @param safeModelBean
	  * @return
	  * @see com.iwgame.xcloud.searcher.service.GameSendService#sendAddSafeModelByMQ(java.lang.String, com.iwgame.xcloud.searcher.model.SafeModelBean)
	  */
	@Override
	public String sendAddSafeModelByMQ(String pid, SafeModelBean safeModelBean) {
		logger.info("AppName:[" + safeModelBean.getAppname() + "]收到添加安全模式指令 (产品ID:" + pid + ") 请求:" + safeModelBean.toString());

		MQSendResult result = new MQSendResult();

		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(safeModelBean)) {
				// 验证签名
				int rc = SecurityUtil.securityAuthority(pid, safeModelBean.getDbid(), safeModelBean.getSign(), safeModelBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSm.convertAndSend("p1.account.exchange", "safemodel", safeModelBean.toString());
						String virtualHost = rabbitTemplateSm.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + safeModelBean.getAppname() + "]发送蜀门添加安全模式指令到[(" + virtualHost + ")]成功!请求:" + safeModelBean.toString());
						result.setResult(rc);
					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxy.convertAndSend("p1.account.exchange", "safemodel", safeModelBean.toString());
						String virtualHost = rabbitTemplateZxy.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + safeModelBean.getAppname() + "]发送醉逍遥添加安全模式指令到[(" + virtualHost + ")]成功!请求:" + safeModelBean.toString());
						result.setResult(rc);
					} else {
						logger.info("添加安全模式指令请求错误,未找到产品ID:" + pid);
						result.setResult(-4);
					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + safeModelBean.getAppname() + "]添加安全模式指令签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [DBID:" + safeModelBean.getDbid()
							+ "] [sign:" + safeModelBean.getSign() + "] [ts:" + safeModelBean.getTs() + "]");
				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + safeModelBean.getAppname() + "]添加安全模式指令参数错误,必要参数为空或NULL!请求:" + safeModelBean.toString());
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + safeModelBean.getAppname() + "]添加安全模式指令其他异常!请求:" + safeModelBean.toString(), ex);

		}
		return result.toString();
	}

	/*
	  * @param pid
	  * @param safeModelBean
	  * @return
	  * @see com.iwgame.xcloud.searcher.service.GameSendService#sendUnSafeModelByMQ(java.lang.String, com.iwgame.xcloud.searcher.model.SafeModelBean)
	  */
	@Override
	public String sendUnSafeModelByMQ(String pid, SafeModelBean safeModelBean) {
		logger.info("AppName:[" + safeModelBean.getAppname() + "]收到解除安全模式指令 (产品ID:" + pid + ") 请求:" + safeModelBean.toString());

		MQSendResult result = new MQSendResult();

		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(safeModelBean)) {
				// 验证签名
				int rc = SecurityUtil.securityAuthority(pid, safeModelBean.getDbid(), safeModelBean.getSign(), safeModelBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSm.convertAndSend("p1.account.exchange", "unsafemodel", safeModelBean.toString());
						String virtualHost = rabbitTemplateSm.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + safeModelBean.getAppname() + "]发送蜀门解除安全模式指令到[(" + virtualHost + ")]成功!请求:" + safeModelBean.toString());
						result.setResult(rc);
					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxy.convertAndSend("p1.account.exchange", "unsafemodel", safeModelBean.toString());
						String virtualHost = rabbitTemplateZxy.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + safeModelBean.getAppname() + "]发送醉逍遥解除安全模式指令到[(" + virtualHost + ")]成功!请求:" + safeModelBean.toString());
						result.setResult(rc);
					} else {
						logger.info("解除安全模式指令请求错误,未找到产品ID:" + pid);
						result.setResult(-4);
					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + safeModelBean.getAppname() + "]解除安全模式指令签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [DBID:" + safeModelBean.getDbid()
							+ "] [sign:" + safeModelBean.getSign() + "] [ts:" + safeModelBean.getTs() + "]");
				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + safeModelBean.getAppname() + "]解除安全模式指令参数错误,必要参数为空或NULL!请求:" + safeModelBean.toString());
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + safeModelBean.getAppname() + "]解除安全模式指令其他异常!请求:" + safeModelBean.toString(), ex);

		}
		return result.toString();
	}

}
