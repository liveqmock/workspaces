/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： XtaskServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.exeception.GMConnectException;
import com.iwgame.xconnector.service.GMRestHttpServiceConnection;
import com.iwgame.xconnector.service.GMWebServiceConnection;
import com.iwgame.xengine.xtask.wg1.dao.MMailDao;
import com.iwgame.xengine.xtask.wg1.dao.MNoticeDao;
import com.iwgame.xengine.xtask.wg1.dao.RewardDao;
import com.iwgame.xengine.xtask.wg1.exception.RMBCSPropertiesNotFoundException;
import com.iwgame.xengine.xtask.wg1.model.GemConfig;
import com.iwgame.xengine.xtask.wg1.model.MQRMBGemstoneMessage;
import com.iwgame.xengine.xtask.wg1.model.MQRMBResourcesMessage;
import com.iwgame.xengine.xtask.wg1.model.Mail;
import com.iwgame.xengine.xtask.wg1.model.PropertyConfig;
import com.iwgame.xengine.xtask.wg1.model.RewardEquipment;
import com.iwgame.xengine.xtask.wg1.model.RewardGemstone;
import com.iwgame.xengine.xtask.wg1.model.RewardPet;
import com.iwgame.xengine.xtask.wg1.model.Torch;
import com.iwgame.xengine.xtask.wg1.model.UserPoints;
import com.iwgame.xengine.xtask.wg1.model.UserResouce;
import com.iwgame.xengine.xtask.wg1.service.XtaskService;
import com.iwgame.xengine.xtask.wg1.util.Constant;
import com.iwgame.xengine.xtask.wg1.util.TaskProperties;
import com.iwgame.xengine.xtask.wg1.util.XtaskUtils;
import com.iwgame.xengine.xtask.wg1.worker.reward.RewardGivingTask;

/**
 * 类说明
 * 
 * @简述： XtaskService实现类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午06:01:16
 */
@Service
public class XtaskServiceImpl implements XtaskService {
	private Logger logger = Logger.getLogger(XtaskServiceImpl.class);
	private Logger rmbczLogger = Logger.getLogger("rmbcz");
	private Logger rewardLogger = Logger.getLogger(RewardGivingTask.class);

	private TaskProperties  taskProperties;
	private RabbitTemplate rabbitTemplate;
	private GMRestHttpServiceConnection restHttpServiceConnectorConnection;
	private GMWebServiceConnection webserviceConnectorConnection;
	private RewardDao rewardDao;
	private MMailDao mMailDao;
	private MNoticeDao gmNoticeDao;

	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Autowired
	public void setmMailDao(MMailDao mMailDao) {
		this.mMailDao = mMailDao;
	}

	@Autowired
	public void setGmNoticeDao(MNoticeDao gmNoticeDao) {
		this.gmNoticeDao = gmNoticeDao;
	}

	@Autowired
	public void setRewardDao(RewardDao rewardDao) {
		this.rewardDao = rewardDao;
	}

	@Autowired
	public void setWebserviceConnectorConnection(GMWebServiceConnection webserviceConnectorConnection) {
		this.webserviceConnectorConnection = webserviceConnectorConnection;
	}

	@Autowired
	public void setRestHttpServiceConnectorConnection(GMRestHttpServiceConnection restHttpServiceConnectorConnection) {
		this.restHttpServiceConnectorConnection = restHttpServiceConnectorConnection;
	}
	
	@Autowired
	public void setTaskProperties(TaskProperties taskProperties) {
		this.taskProperties = taskProperties;
	}

	@Override
	public void giveGift(UserResouce userResouce) {
		try {
			Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
			paramMap.put("guid", userResouce.getGuid());
			paramMap.put("scope", Integer.valueOf(userResouce.getScope()));
			paramMap.put("type", Integer.valueOf(userResouce.getResourceType()));
			paramMap.put("users", userResouce.getUsername());
			paramMap.put("content", userResouce.getResouces());

			String rewardHttpService = "/webservice/http-gm/" + userResouce.getGuid() +"/giveGift";
			String rewardReturnValue = restHttpServiceConnectorConnection.post(TargetType.GAME, Constant.PID, userResouce.getGuid(),
					rewardHttpService, paramMap);
			
			rewardLogger.info(rewardReturnValue);
			JSONObject jsonObject = JSONObject.fromObject(rewardReturnValue);
			int rc = jsonObject.getInt("rc");
			String content = "";
			if (jsonObject.has("content")) {
				content = jsonObject.getString("content");
			}

			// 成功
			if (rc == 0) {
				rewardLogger.info("[result]发奖成功!code:" + rc + ",content:" + content);
				// 记录发奖记录
				saveRewardResouceLog(userResouce, getFailedUsers(content), 0);
			} else {
				rewardLogger.info("[result]发奖失败!code:" + rc + ",content:" + content);
				// 记录发奖记录[失败]
				saveRewardResouceLog(userResouce, getFailedUsers(content), 1);
			}
		} catch (GMConnectException e) {
			// 因为连接原因出错,则强制消息重发,抛出异常后,MQ会自动把消息设置为Ready状态
			rewardLogger.error("[发奖出错了]连接原因:" + e.getMessage() + ", 资源:" + userResouce.toString());
			throw e;
		} catch (Exception e) {
			rewardLogger.error("[发奖出错了]其他原因:" + e);
		}
	}

//  webservice 方式
//	public void giveGift(UserResouce userResouce) {
//		try {
//			GameResponse gameResponse = null;
//			String webServiceURL = "/webservice/gm?wsdl";
//			// 参数
//			Map<String, Object> param = new LinkedHashMap<String, Object>();
//			param.put("guid", userResouce.getGuid());
//			param.put("scope", Integer.valueOf(userResouce.getScope()));
//			param.put("type", Integer.valueOf(userResouce.getResourceType()));
//			param.put("users", userResouce.getUsername());
//			param.put("content", userResouce.getResouces());
//
//			Object[] objResult = webserviceConnectorConnection.invoke(TargetType.GAME, Constant.PID, userResouce.getGuid(),
//					webServiceURL, "giveGift", param);
//
//			gameResponse = XtaskUtils.object2GameResponse(objResult);
//
//			// 成功
//			if (gameResponse != null && gameResponse.getReturnCode() == 0) {
//				rewardLogger.info("[result]发奖成功!type: " + gameResponse.getContentType() + ",code:" + gameResponse.getReturnCode() + ",content:" + gameResponse.getContent());
//				// 记录发奖记录
//				saveRewardResouceLog(userResouce, getFailedUsers(gameResponse.getContent()), 0);
//			} else {
//				rewardLogger.info("[result]发奖失败!,type: " + gameResponse.getContentType() + ",code:" + gameResponse.getReturnCode() + ",content:" + gameResponse.getContent());
//				// 记录发奖记录[失败]
//				saveRewardResouceLog(userResouce, getFailedUsers(gameResponse.getContent()), 1);
//			}
//		} catch (GMConnectException e) {
//			// 因为连接原因出错,则强制消息重发,抛出异常后,MQ会自动把消息设置为Ready状态
//			rewardLogger.error("[发奖出错了]连接原因:" + e.getMessage() + ", 资源:" + userResouce.toString());
//			throw e;
//		} catch (Exception e) {
//			rewardLogger.error("[发奖出错了]其他原因:" + e);
//		}
//
//	}

	/**
	 * 取得失败的用户列表
	 * 
	 * @param content
	 * @return
	 */
	private List<String> getFailedUsers(String content) {
		List<String> faildUsers = new ArrayList<String>();
		try {
			if (content != null && content.length() > 0) {
				String[] arr = content.split("\\;");
				for(String user : arr) {
					faildUsers.add(user);
				}
			}
		} catch (Exception e) {

		}
		return faildUsers;
	}

	/**
	 * 保存资源发放日志记录
	 * @param userResouce  资源
	 * @param failedUsers  失败用户列表
	 * @param status       状态
	 */
	private void saveRewardResouceLog(UserResouce userResouce, List<String> failedUsers, int status) {
		try {
			String[] users = userResouce.getUsername().split("\\;");
			// 构建参数
			Map<String, Object> paramMap = null;
			for (int i = 0; i < users.length; i++) {
				paramMap = new HashMap<String, Object>();
				paramMap.put("status", status);
				paramMap.put("g_batchid", userResouce.getBatchid());
				paramMap.put("g_reward_type", 0);//表示类型为 资源
				paramMap.put("g_username", users[i]);
				paramMap.put("g_guid", userResouce.getGuid());
				paramMap.put("g_resource_type", userResouce.getResourceType());
				paramMap.put("g_resouces", userResouce.getTranlatedResouces());//翻译后的资源属性
				paramMap.put("g_operater", userResouce.getOperater());
				paramMap.put("g_source", userResouce.getSource());
				paramMap.put("g_note", userResouce.getDesc());//备注信息

				// 失败用户
				if (failedUsers != null && failedUsers.contains(users[i])) {
					paramMap.put("status", 1);
				} else {
					//邮件提醒
					add2MailNotice(userResouce.getGuid(),users[i],"系统资源发放",userResouce.getTranlatedResouces());
				
				}

				rewardDao.saveResouceLog(paramMap);
				rewardLogger.info("保存[" + users[i] + "]发奖日志记录成功！");
			}
		} catch (Exception e) {
			rewardLogger.error("保存发奖日志记录失败！" + e);
		}
	}
	
	/**
	 * 保存宝石发放日志记录
	 * @param rewardGemstone 宝石对象
	 * @param status  状态
	 */
	private void saveRewardGemstoneLog(RewardGemstone rewardGemstone, int status) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("status", status);
			paramMap.put("g_batchid", System.currentTimeMillis());
			paramMap.put("g_reward_type", 1);//表示类型为 宝石
			paramMap.put("g_username", rewardGemstone.getUsername());
			paramMap.put("g_guid", rewardGemstone.getGuid());
			paramMap.put("g_resource_type", 0);
			paramMap.put("g_resouces", rewardGemstone.getTranslatedContent());
			paramMap.put("g_operater", rewardGemstone.getOperater());
			paramMap.put("g_source", rewardGemstone.getSource());
			paramMap.put("g_note", rewardGemstone.getDesc());//备注信息

			rewardDao.saveResouceLog(paramMap);
			rewardLogger.info("保存[" + rewardGemstone.getUsername() + "]发宝石日志记录成功！");
		} catch (Exception e) {
			rewardLogger.error("保存发宝石日志记录失败！" + e);
		}
	}

	/**
	 * 保存装备发放日志记录
	 * @param rewardEquipment 装备对象
	 * @param status  状态
	 */
	private void saveRewardEquipmentLog(RewardEquipment rewardEquipment, int status) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("status", status);
			paramMap.put("g_batchid", System.currentTimeMillis());
			paramMap.put("g_reward_type", 2);//表示类型为 装备
			paramMap.put("g_username", rewardEquipment.getUsername());
			paramMap.put("g_guid", rewardEquipment.getGuid());
			paramMap.put("g_resource_type", 0);
			paramMap.put("g_resouces", rewardEquipment.getTranslatedContent());
			paramMap.put("g_operater", rewardEquipment.getOperater());
			paramMap.put("g_source", rewardEquipment.getSource());
			paramMap.put("g_note", rewardEquipment.getDesc());//备注信息

			rewardDao.saveResouceLog(paramMap);
			rewardLogger.info("保存[" + rewardEquipment.getUsername() + "]发装备日志记录成功！");
		} catch (Exception e) {
			rewardLogger.error("保存发装备日志记录失败！" + e);
		}
	}
	
	/**
	 * 保存积分发放日志记录
	 * @param UserPoints 积分对象
	 * @param status  状态
	 */
	private void saveRewardUserPointsLog(UserPoints userPoints, int status) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("status", status);
			paramMap.put("g_batchid", System.currentTimeMillis());
			paramMap.put("g_reward_type", 4);//表示类型为积分
			paramMap.put("g_username", userPoints.getUsername());
			paramMap.put("g_guid", "-");
			paramMap.put("g_resource_type", 0);
			paramMap.put("g_resouces", userPoints.getTranslatedContent());
			paramMap.put("g_operater", userPoints.getOperation());
			paramMap.put("g_source", userPoints.getSource());
			paramMap.put("g_note", userPoints.getDesc());//备注信息

			rewardDao.saveResouceLog(paramMap);
			rewardLogger.info("保存[" + userPoints.getUsername() + "]发积分日志记录成功！");
		} catch (Exception e) {
			rewardLogger.error("保存发积分日志记录失败！" + e);
		}
	}
	
	
	@Override
	public void setServerParameters(String guid, String maxUserCounts) {
		try {

			String HttpServiceURL = "/webservice/http-gm/" + guid + "/setting";
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			param.put("guid", guid);
			param.put("maxUserCounts", maxUserCounts);

			String responseResult = restHttpServiceConnectorConnection.post(TargetType.GAME, Constant.PID, guid, HttpServiceURL,
					param);

			JSONObject responseObject = JSONObject.fromObject(responseResult);
			int resultMsg = XtaskUtils.object2int(responseObject.get("rc"));
			if (resultMsg == 0) {
				logger.info("设置成功！");
			}

		} catch (Exception e) {
			logger.error("[配置修改出错]" + e);
		}
	}

	/**
	 * 保存邮件发送记录
	 * 
	 * @param mail
	 * @param status
	 */
	private void saveMailLog(Mail mail, int status) {
		try {
			String[] users = mail.getUsername().split("\\;");
			// 构建参数
			Map<String, Object> paramMap = null;
			for (int i = 0; i < users.length; i++) {
				paramMap = new HashMap<String, Object>();
				paramMap.put("status", status);
				paramMap.put("g_guid", mail.getGuid());
				paramMap.put("g_username", users[i]);
				paramMap.put("g_mailtitle", mail.getMailtitle());
				paramMap.put("g_mailcontent", mail.getMailcontent());
				paramMap.put("g_operater", mail.getOperater());

				mMailDao.saveMailLog(paramMap);
				logger.info("保存[" + users[i] + "]邮件发送日志记录成功！");
			}
		} catch (Exception e) {
			logger.error("保存邮件发送日志记录失败！" + e);
		}
	}

	@Override
	public void sendMail(String guid, String username, String mailtitle, String mailcontent, String operater, int scope) {
		int result = -4;
		try {
			Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
			paramMap.put("guid", guid);
			paramMap.put("scope", scope);
			paramMap.put("users", username);
			paramMap.put("title", mailtitle);
			paramMap.put("content", mailcontent);

			String mailHttpService = "/webservice/http-gm/" + guid +"/sendMail";
			String mailReturnValue = restHttpServiceConnectorConnection.post(TargetType.GAME, Constant.PID, guid,
					mailHttpService, paramMap);
			
			logger.info("[mail]result:" + mailReturnValue);
			
			JSONObject jsonObject = JSONObject.fromObject(mailReturnValue);
			result = jsonObject.getInt("rc");

			logger.info("邮件发送完成！");
		} catch (Exception e) {
			logger.error("[邮件发送出错]," + e.getMessage());
		}

		// 保存发信记录
		Mail mail = new Mail(guid, username, mailtitle, mailcontent, operater);
		saveMailLog(mail, result);
	}
	
//	webservice
//	public void sendMail(String guid, String username, String mailtitle, String mailcontent, String operater, int scope) {
//		int result = -4;
//		try {
//			String webServiceURL = "/webservice/gm?wsdl";
//			Map<String, Object> param = new LinkedHashMap<String, Object>();
//			param.put("guid", guid);
//			param.put("scope", scope);
//			param.put("users", username);
//			param.put("title", mailtitle);
//			param.put("content", mailcontent);
//
//			Object[] objResult = webserviceConnectorConnection.invoke(TargetType.GAME, Constant.PID, guid, webServiceURL,
//					"sendMail", param);
//
//			GameResponse gameResponse = XtaskUtils.object2GameResponse(objResult);
//			if (gameResponse != null) {
//				result = gameResponse.getReturnCode();
//			}
//			logger.info("邮件发送完成！");
//		} catch (Exception e) {
//			logger.error("[邮件发送出错]," + e.getMessage());
//		}
//
//		// 保存发信记录
//		Mail mail = new Mail(guid, username, mailtitle, mailcontent, operater);
//		saveMailLog(mail, result);
//	}

	@Override
	public void sendNotice(String guid, int type, String content, String operater) {
		int status = 1;
		try {
			String webServiceURL = "/webservice/gm?wsdl";
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			param.put("guid", guid);
			param.put("type", type);
			param.put("content", content);

			Object[] objResult = webserviceConnectorConnection.invoke(TargetType.GAME, Constant.PID, guid, webServiceURL,
					"notice", param);
			if (objResult.length == 1) {
				status = XtaskUtils.object2int(objResult[0]);
				if (status != 0) {
					status = 1;
				}
			}
			logger.info("[" + guid + "]公告发送完成！");
		} catch (Exception e) {
			logger.error("[" + guid + "]公告发送出错," + e.getMessage());
		}
		// 添加发送公告日志
		saveNoticeLog(guid, content, operater, status, "");
	}

	/**
	 * 添加公告发送日志
	 * 
	 * @param guid
	 * @param content
	 * @param operater
	 * @param updatetime
	 * @param note
	 * @return
	 */
	private void saveNoticeLog(String guid, String content, String operater, int status, String note) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("guid", guid);
			param.put("content", content);
			param.put("operater", operater);
			param.put("status", status);
			param.put("note", note);

			gmNoticeDao.addNoticeLog(param);
			logger.info("保存大区[" + guid + "]公告发送日志记录成功！");
		} catch (Exception e) {
			logger.error("保存公告发送日志记录失败！" + e);
		}
	}

	@Override
	public void kickUser(String guid, int type, String username) {
		try {
			String webServiceURL = "/webservice/gm?wsdl";
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			param.put("guid", guid);
			param.put("type", type);
			param.put("username", username);

			Map<String, Object> sginParam = new LinkedHashMap<String, Object>();
			sginParam.put("type", type);
			sginParam.put("username", username);

			Object[] objResult = webserviceConnectorConnection.invoke(TargetType.GAME, Constant.PID, guid, webServiceURL, "kick",
					param, sginParam);
			if (objResult.length == 1) {
				int rv = (Integer) objResult[0];
				if (rv == 0) {
					logger.info(guid + "[踢人操作]" + username + "成功!");
				} else {
					logger.info(guid + "[踢人操作]" + username + "失败, 返回代码:" + rv);
				}
			}

		} catch (Exception e) {
			logger.error("[踢人出错]" + username + " , " + e);
		}
	}

	@Override
	public void AccountManager(String username, int operator, String content, int minute, String memo) {
		try {
			String webServiceURL = "/Main.asmx?wsdl";
			Map<String, Object> accountManagerParam = new LinkedHashMap<String, Object>();
			accountManagerParam.put("userName", username);
			accountManagerParam.put("lockTime", minute);
			accountManagerParam.put("reason", content);
			accountManagerParam.put("type", operator);

			Object[] objResult = webserviceConnectorConnection.invoke(TargetType.UCENTER, Constant.PID, Constant.WG1_MEMBERWS,
					webServiceURL, "LockOperation", accountManagerParam);
			if (objResult.length == 1) {
				String resultMsg = objResult[0].toString();
				if (resultMsg.startsWith("success")) {
					logger.info("[" + memo + "]，账号:" + username + "成功");
				} else {
					logger.info("[" + memo + "]，账号:" + username + "失败，返回代码:" + resultMsg);
				}
			} else {
				logger.info("[" + memo + "]，账号:" + username + "失败");
			}
		} catch (Exception e) {
			logger.error("[" + memo + "]，operator:" + operator + ",账号:" + username + "失败，msg:" + e);
		}

	}

	@Override
	public void TalkManager(String guid, String username, int operate, int validtime, String msg, String memo) {
		try {
			Map<String, Object> talkManagerParam = new LinkedHashMap<String, Object>();
			talkManagerParam.put("guid", guid);
			talkManagerParam.put("username", username);
			talkManagerParam.put("operate", operate);
			talkManagerParam.put("validtime", validtime);
			talkManagerParam.put("msg", msg);

			String ipHttpService = "/webservice/http-gm/" + guid + "/bannedUsers";
			String lockTalkReturnValue = restHttpServiceConnectorConnection.post(TargetType.GAME, Constant.PID, guid,
					ipHttpService, talkManagerParam);
			JSONObject jsonLockIpReturnValue = JSONObject.fromObject(lockTalkReturnValue);
			int rc = jsonLockIpReturnValue.getInt("rc");
			if (rc == 0) {
				logger.info("[" + memo + "],玩家:" + username + "成功");
			} else {
				logger.info("[" + memo + "],玩家:" + username + "失败,返回代码:" + rc);
			}
		} catch (Exception e) {
			logger.error("[" + memo + "],玩家:" + username + "失败，msg:" + e);
		}
	}

	@Override
	public void LockIPManager(String guid, String ip, int operate, int validtime, String memo) {
		try {
			String ipHttpService = "/webservice/http-gm/" + guid + "/lockIP";
			Map<String, Object> ipparam = new LinkedHashMap<String, Object>();
			ipparam.put("guid", guid);
			ipparam.put("operate", operate);
			ipparam.put("ip", ip);
			ipparam.put("validtime", validtime);

			String lockIpReturnValue = restHttpServiceConnectorConnection.post(TargetType.GAME, Constant.PID, guid,
					ipHttpService, ipparam);
			JSONObject jsonLockIpReturnValue = JSONObject.fromObject(lockIpReturnValue);
			int rc = jsonLockIpReturnValue.getInt("rc");
			if (rc == 0) {
				logger.info("[" + memo + "],ip:" + ip + "成功");
			} else {
				logger.info("[" + memo + "],ip:" + ip + "失败,返回代码:" + rc);
			}
		} catch (Exception e) {
			logger.error("[" + memo + "],ip:" + ip + "失败，msg:" + e);
		}
	}

	@Override
	public void LockUserManager(String guid, String username, int operate, int validtime, String memo) {
		try {
			String webServiceURL = "/webservice/gm?wsdl";
			Map<String, Object> lockUserparam = new LinkedHashMap<String, Object>();
			lockUserparam.put("guid", guid);
			lockUserparam.put("username", username);
			lockUserparam.put("operate", operate);
			lockUserparam.put("validtime", validtime);

			Object[] objResult = webserviceConnectorConnection.invoke(TargetType.GAME, Constant.PID, guid, webServiceURL, "lock",
					lockUserparam);
			if (objResult.length == 1) {
				String resultMsg = objResult[0].toString();
				if (resultMsg.equals("0")) {
					logger.info("[" + memo + "],玩家：" + username + "成功");
				} else {
					logger.info("[" + memo + "],玩家：" + username + "失败,返回代码:" + resultMsg);
				}
			} else {
				logger.info("[" + memo + "],玩家：" + username + "失败");
			}
		} catch (Exception e) {
			logger.error("[" + memo + "],玩家：" + username + "失败，msg:" + e);
		}

	}

	@Override
	public void givePoints(UserPoints userPoints) {
		try {

			String HttpServiceURL = "/service/" + userPoints.getPointtype() + "/" + userPoints.getUsername() + "/gpay";
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			param.put("pointtype", userPoints.getPointtype());
			param.put("username", userPoints.getUsername());
			param.put("amount", userPoints.getAmount());
			param.put("source", userPoints.getSource());
			param.put("operation", userPoints.getOperation());
			param.put("location", userPoints.getLocation());
			param.put("orderNo", userPoints.getOrderNo());

			String responseResult = restHttpServiceConnectorConnection.post(TargetType.UCENTER, Constant.PID,
					Constant.WG1_POINTS, HttpServiceURL, param);

			JSONObject responseObject = JSONObject.fromObject(responseResult);
			int resultMsg = XtaskUtils.object2int(responseObject.get("rc"));
			if (resultMsg == 0) {
				//发放积分成功
				saveRewardUserPointsLog(userPoints,0);
				logger.info("给玩家积分加点成功！info:" + userPoints.toString());
			} else {
				logger.info("给玩家积分加点失败！code:" + resultMsg);
			}

		} catch (Exception e) {
			logger.error("[给玩家积分加点出错]" + e + ", info:" + userPoints.toString());
		}
	}

	/**
	 * 发送rmb充值赠送消息（支持 资源&宝石）
	 */
	public void sendRmbCZMQMessage(String message) {
		try {
			String sourceFlag = "充值赠送";
			// 取得信息
			JSONObject jsonObject = JSONObject.fromObject(message);
			String guid = jsonObject.getString("guid");
			String username = jsonObject.getString("userName");
			String amount = jsonObject.getString("amount");
			String isFristPay = XtaskUtils.getJsonString(jsonObject, "isFirstPay");
			
			//RMB首次充值，赠送金币
			if (StringUtils.isNotBlank(isFristPay) && isFristPay.equalsIgnoreCase("1")) {
				if ("true".equalsIgnoreCase(taskProperties.getFirstRMBPayment())) {
					try {
						String resouces = getMQFristRMBResourceMessage(amount);
						logger.info("首次RMB充值，赠送资源:" + resouces);
						// 提交到发奖通道
						MQRMBResourcesMessage mqRMBResourceMessage = new MQRMBResourcesMessage(username, guid, resouces,sourceFlag,"首次RMB充值奖励！");
						rabbitTemplate.convertAndSend("wg1.reward.exchange", "reward", mqRMBResourceMessage.toJsonString());
						rmbczLogger.info("[RMB首次充值赠送金币成功]" + message);
					} catch (RMBCSPropertiesNotFoundException e) {
						rmbczLogger.info("[RMB首次充值赠送金币失败]" + e + ", [message]:" + message);
					} catch (Exception e) {
						rmbczLogger.error("[RMB首次充值赠送金币失败]" + e + ", [message]:" + message);
					}
				} else {
					logger.info("task未开启首次充值赠送开关，故此不发送首次充值赠送！");
				}
			}
			
			
			// 资源发送
			try {
				String resouces = getMQRMBResourceMessage(amount);
				if (resouces != null) {
					// 提交到发奖通道
					MQRMBResourcesMessage mqRMBResourceMessage = new MQRMBResourcesMessage(username, guid, resouces,sourceFlag,"");
					rabbitTemplate.convertAndSend("wg1.reward.exchange", "reward", mqRMBResourceMessage.toJsonString());
					rmbczLogger.info("[RMB充值赠送资源成功]" + message);
				} else {
					rmbczLogger.info("[提示]各项资源设置均为0，忽略发送。" + message);
				}
			} catch (RMBCSPropertiesNotFoundException e) {
				rmbczLogger.info("[RMB充值赠送资源失败]" + e + ", [message]:" + message);
			} catch (Exception e) {
				rmbczLogger.error("[RMB充值赠送资源失败]" + e + ", [message]:" + message);
			}

			// 宝石发送
			try {
				String resouces = getMQRMBGemstoneMessage(amount);
				// 提交到发宝石通道
				MQRMBGemstoneMessage mqRMBGemstoneMessage = new MQRMBGemstoneMessage(username, guid, resouces,sourceFlag,"");
				rabbitTemplate.convertAndSend("wg1.reward.exchange", "rewardgemstone", mqRMBGemstoneMessage.toJsonString());
				rmbczLogger.info("[RMB充值赠送宝石成功]" + message);
			} catch (RMBCSPropertiesNotFoundException e) {
				rmbczLogger.info("[RMB充值赠送宝石失败]" + e + ", [message]:" + message);
			} catch (Exception e) {
				rmbczLogger.error("[RMB充值赠送宝石失败]" + e + ", [message]:" + message);
			}
			
			//发放积分
			List<Torch> resouces = getMQRMBPointMessage(amount);
			if (resouces != null) {
		    	for (int i = 0; i < resouces.size(); i++) {
		    		try {
		    			Torch torch = resouces.get(i);
		    			UserPoints userPoints = new UserPoints();
		    		     userPoints.setAmount(torch.getTorch_num());
		    		     userPoints.setLocation("127.0.0.1");
		    		     userPoints.setOperation("Xtask");
		    		     userPoints.setOrderNo(String.valueOf(new Date().getTime()));
		    		     userPoints.setPointtype(torch.getTorch_id());
		    		     userPoints.setSource("充值赠送");
		    		     userPoints.setUsername(username);
		    			
		    			// 提交到发积分通道
		    		    JSONObject userPoint = JSONObject.fromObject(userPoints);
		    			rabbitTemplate.convertAndSend("wg1.points.exchange", "points",userPoint.toString());
		    			rmbczLogger.info("[RMB充值赠送积分成功]" + message);
		    		} catch (RMBCSPropertiesNotFoundException e) {
		    			rmbczLogger.info("[RMB充值赠送积分失败]" + e + ", [message]:" + message);
		    		} catch (Exception e) {
		    			rmbczLogger.error("[RMB充值赠送积分失败]" + e + ", [message]:" + message);
		    		}
					
				}
			} else {
				logger.info("未配置送积分策略!");
			}
			
		} catch (Exception e) {
			rmbczLogger.error	("[RMB充值赠送失败]" + e + ", [message]:" + message);
		}
	}
	/**
	 * 获取RMB充值宝石赠送策略
	 * @param amount
	 * @return
	 * @throws RMBCSPropertiesNotFoundException
	 */
	private String getMQRMBGemstoneMessage(String amount) throws RMBCSPropertiesNotFoundException {
		try {
			int pay_amount = Integer.valueOf(amount);
			int g_pay_amount = pay_amount / 10;
			// 构建参数&查询
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("g_pay_amount", g_pay_amount);

			List<GemConfig> gemConfigList = rewardDao.getGemConfigList(parameter);
			if (gemConfigList == null || gemConfigList.size() <= 0) {
				throw new RMBCSPropertiesNotFoundException("未找到对应的宝石赠送策略文件，充值档位：" + g_pay_amount);
			} else {
				return getGemstoneContent(gemConfigList);
			}
		} catch (Exception e) {
			throw new RMBCSPropertiesNotFoundException("获取RMB充值宝石赠送策略出错," + e.getMessage());
		}
	}
	
	/**
	 * 获取RMB充值积分赠送策略
	 * @param amount
	 * @return
	 * @throws RMBCSPropertiesNotFoundException
	 */
	private List<Torch> getMQRMBPointMessage(String amount) throws RMBCSPropertiesNotFoundException {
		try {
			int pay_amount = Integer.valueOf(amount);
			int g_pay_amount = pay_amount / 10;
			// 构建参数&查询
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("g_pay_amount", g_pay_amount);

			List<Torch> torchList = rewardDao.getTorchList(parameter);
			if (torchList == null || torchList.size() <= 0) {
				throw new RMBCSPropertiesNotFoundException("未找到对应的积分发放赠送策略文件，充值档位：" + g_pay_amount);
			} else {
				return  torchList;
			}
		} catch (Exception e) {
			throw new RMBCSPropertiesNotFoundException("获取RMB充值积分赠送策略出错," + e.getMessage());
		}
	}

	/**
	 * 格式化资源消息
	 * @param lmoney   	金币
	 * @param money		银币
	 * @param food		食物
	 * @param wood		木材
	 * @param renown	荣誉
	 * @param glory		声望
	 * @return
	 */
	private String getResouceContent(int lmoney, int money, int food, int wood, int renown, int glory) {
		if (lmoney <= 0 && money <= 0 && food <= 0 && wood <= 0 && renown <= 0 && glory <= 0) {
			return null;
		}
		
		StringBuilder resouceBuilder = new StringBuilder();
		resouceBuilder.append("{");
		resouceBuilder.append("\"lmoney\":").append(lmoney).append(",");
		resouceBuilder.append("\"money\":").append(money).append(",");
		resouceBuilder.append("\"food\":").append(food).append(",");
		resouceBuilder.append("\"wood\":").append(wood).append(",");
		resouceBuilder.append("\"renown\":").append(renown).append(",");
		resouceBuilder.append("\"glory\":").append(glory);
		resouceBuilder.append("}");
		return resouceBuilder.toString();
	}
	
	/**
	 * 格式化宝石消息
	 * @param gemConfigList
	 * @return
	 */
	private String getGemstoneContent(List<GemConfig> gemConfigList) {
		List<TempGemStone> list = new ArrayList<TempGemStone>();
		TempGemStone tempGemStone = new TempGemStone();
		for (GemConfig gemConfig : gemConfigList) {
			//循环数据
			tempGemStone = new TempGemStone();
			tempGemStone.setId(gemConfig.getBase_id());
			tempGemStone.setName(gemConfig.getBase_name());
			tempGemStone.setNum(gemConfig.getBase_num());
			list.add(tempGemStone);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		StringBuilder resouceBuilder = new StringBuilder();
		resouceBuilder.append("{");
		resouceBuilder.append("\"content\":").append(jsonArray.toString());
		resouceBuilder.append("}");
		return resouceBuilder.toString();
	}
	
	/**
	 * 获取RMB首次充值赠送金币，规则是： 冲多少，送多少，最多为500
	 * @param amount
	 * @return
	 */
	private String getMQFristRMBResourceMessage(String amount) {
		int pay_amount = Integer.valueOf(amount);
		if (pay_amount > 500) {
			pay_amount = 500;
		}
		
		return getResouceContent(pay_amount, 0, 0, 0, 0,0);
	}
	
	/**
	 * 获取RMB充值宝石赠送策略
	 * @param amount
	 * @return
	 * @throws RMBCSPropertiesNotFoundException
	 */
	private String getMQRMBResourceMessage(String amount) throws RMBCSPropertiesNotFoundException {
		try {
			int pay_amount = Integer.valueOf(amount);
			int g_pay_amount = pay_amount / 10;
			// 构建参数&查询
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("g_pay_amount", g_pay_amount);

			List<PropertyConfig> propertyConfigList = rewardDao.getPropertyList(parameter);
			if (propertyConfigList == null || propertyConfigList.size() <= 0) {
				throw new RMBCSPropertiesNotFoundException("未找到对应的资源赠送策略文件，充值档位：" + g_pay_amount);
			} else {
				PropertyConfig propertyConfig = propertyConfigList.get(0);
				if (propertyConfig == null) {
					throw new RMBCSPropertiesNotFoundException("未找到对应的资源赠送策略文件，充值档位：" + g_pay_amount);
				} else {
					return getResouceContent(Integer.valueOf(propertyConfig.getG_reward_gold()), Integer.valueOf(propertyConfig.getG_silver()),
							Integer.valueOf(propertyConfig.getG_food()), Integer.valueOf(propertyConfig.getG_wood()), Integer.valueOf(propertyConfig.getG_renown()),
									Integer.valueOf(propertyConfig.getG_glory()));
				}
			}
		} catch (Exception e) {
			throw new RMBCSPropertiesNotFoundException("获取RMB充值资源赠送策略出错," + e.getMessage());
		}
	}

	@Override
	public void send2RewardBackupMQ(String message) {
		//资源发放信息转入到备用通道
		rabbitTemplate.convertAndSend("wg1.reward-backup.exchange", "reward-backup", message);
	}

	@Override
	public void giveRewardGemstone(RewardGemstone rewardGemstone) {
		try {
			Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
			paramMap.put("guid", rewardGemstone.getGuid());
			paramMap.put("username", rewardGemstone.getUsername());
			paramMap.put("content", rewardGemstone.getContent());

			String gemStoneHttpService = "/webservice/http-gm/" + rewardGemstone.getGuid() + "/" + rewardGemstone.getUsername() +"/addGem";
			String rewardGemstoneReturnValue = restHttpServiceConnectorConnection.post(TargetType.GAME, Constant.PID, rewardGemstone.getGuid(),
					gemStoneHttpService, paramMap);
			
			JSONObject jsonObject = JSONObject.fromObject(rewardGemstoneReturnValue);
			int rc = jsonObject.getInt("rc");

			// 成功
			if (rc == 0) {
				rewardLogger.info("[result]发宝石成功,返回值code:" + rc);
				//邮件提醒
				add2MailNotice(rewardGemstone.getGuid(),rewardGemstone.getUsername(),"系统资源发放",rewardGemstone.getTranslatedContent());
			
				// 记录发宝石记录
				saveRewardGemstoneLog(rewardGemstone, 0);
			} else {
				rewardLogger.info("[result]发宝石失败,返回值code:" + rc);
				// 记录发宝石记录[失败]
				saveRewardGemstoneLog(rewardGemstone, 1);
			}
		} catch (GMConnectException e) {
			// 因为连接原因出错,则强制消息重发,抛出异常后,MQ会自动把消息设置为Ready状态
			rewardLogger.error("[发宝石出错了]连接原因:" + e.getMessage() + ", 资源:" + rewardGemstone.toString());
			throw e;
		} catch (Exception e) {
			rewardLogger.error("[发宝石出错了]其他原因:" + e);
		}
	}

	@Override
	public void send2RewardGemstoneBackupMQ(String message) {
		//宝石发放转入到备用通道
		rabbitTemplate.convertAndSend("wg1.reward-backup.exchange", "rewardgemstone-backup", message);
	}

	@Override
	public void giveRewardEquipment(RewardEquipment rewardEquipment) {
		try {
			Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
			paramMap.put("guid", rewardEquipment.getGuid());
			paramMap.put("username", rewardEquipment.getUsername());
			paramMap.put("content", rewardEquipment.getContent());
			paramMap.put("isWhole", rewardEquipment.getIsWhole());

			String equipmentHttpService = "/webservice/http-gm/" + rewardEquipment.getGuid() + "/" + rewardEquipment.getUsername() +"/addEquipment";
			String rewardEquipmentReturnValue = restHttpServiceConnectorConnection.post(TargetType.GAME, Constant.PID, rewardEquipment.getGuid(),
					equipmentHttpService, paramMap);
			
			JSONObject jsonObject = JSONObject.fromObject(rewardEquipmentReturnValue);
			int rc = jsonObject.getInt("rc");

			// 成功
			if (rc == 0) {
				rewardLogger.info("[result]发装备成功,返回值code:" + rc);
				//邮件提醒
				add2MailNotice(rewardEquipment.getGuid(),rewardEquipment.getUsername(),"系统资源发放",rewardEquipment.getTranslatedContent());
				
				// 记录发装备记录
				saveRewardEquipmentLog(rewardEquipment, 0);
			} else {
				rewardLogger.info("[result]发装备失败,返回值code:" + rc + ",msg:" + jsonObject.getString("msg"));
				// 记录发装备记录[失败]
				saveRewardEquipmentLog(rewardEquipment, 1);
			}
		} catch (GMConnectException e) {
			// 因为连接原因出错,则强制消息重发,抛出异常后,MQ会自动把消息设置为Ready状态
			rewardLogger.error("[发装备出错了]连接原因:" + e.getMessage() + ", 资源:" + rewardEquipment.toString());
			throw e;
		} catch (Exception e) {
			rewardLogger.error("[发装备出错了]其他原因:" + e);
		}
	}

	@Override
	public void send2RewardEquipmentBackupMQ(String message) {
		//装备发放转入到备用通道
		rabbitTemplate.convertAndSend("wg1.reward-backup.exchange", "rewardequipment-backup", message);
	}
	
	
	@SuppressWarnings("deprecation")
	private void add2MailNotice(String guid,String username,String title,String content) {
		try {
			StringBuffer message = new StringBuffer();
			message.append("{");
			message.append("\"users\":\""+username+"\"").append(",");
			message.append("\"scope\":2").append(",");
			message.append("\"guid\":\""+guid+"\"").append(",");
			message.append("\"title\":\""+title+"\"").append(",");
			message.append("\"content\":\"恭喜您获得了"+content+" 请您注意查收，如有问题，请通过论坛、在线QQ等方式与我们联系，谢谢！\"").append(",");
			message.append("\"operater\":\"系统\"").append(",");
			message.append("\"uptime\":\""+new Date().toLocaleString()+"\"").append(",");
			message.append("}");
			
			rabbitTemplate.convertAndSend("wg1.mail.exchange", "mail", message.toString());
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	
	
	public class TempGemStone {
		private String id = "";
		private String name= "";
		private String num = "";
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
	}
	

	@Override
	public void send2RewardPetBackupMQ(String message) {
		//宠物赠送转入到备用通道
		rabbitTemplate.convertAndSend("wg1.reward-backup.exchange", "rewardpet-backup", message);
	}

	@Override
	public void giveRewardPet(RewardPet rewardPet) {

		try {
			Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
			paramMap.put("guid", rewardPet.getGuid());
			paramMap.put("username", rewardPet.getUsername());
			paramMap.put("content", rewardPet.getContent());

			String petHttpService = "/webservice/http-gm/" + rewardPet.getGuid() + "/" + rewardPet.getUsername() +"/addPet";
			String rewardPetReturnValue = restHttpServiceConnectorConnection.post(TargetType.GAME, Constant.PID, rewardPet.getGuid(),petHttpService, paramMap);
			
			JSONObject jsonObject = JSONObject.fromObject(rewardPetReturnValue);
			int rc = jsonObject.getInt("rc");

			// 成功
			if (rc == 0) {
				rewardLogger.info("[result]赠送宠物成功,返回值code:" + rc);
				//邮件提醒
//				add2MailNotice(rewardPet.getGuid(),rewardPet.getUsername(),"宠物赠送通知","恭喜您获得游戏内的资源奖励，奖励明细如下：" + rewardPet.getTranslatedContent());
			
				// 记录发宠物记录
				saveRewardPetLog(rewardPet, 0);
			} else {
				rewardLogger.info("[result]发宠物失败,返回值code:" + rc);
				// 记录发宠物记录[失败]
				saveRewardPetLog(rewardPet, 1);
			}
		} catch (GMConnectException e) {
			// 因为连接原因出错,则强制消息重发,抛出异常后,MQ会自动把消息设置为Ready状态
			rewardLogger.error("[发宠物出错了]连接原因:" + e.getMessage() + ", 资源:" + rewardPet.toString());
			throw e;
		} catch (Exception e) {
			rewardLogger.error("[发宠物出错了]其他原因:" + e);
		}
	
	}
	
	/**
	 * 保存宠物发放日志记录
	 * @param rewardPet 宠物对象
	 * @param status  状态
	 */
	private void saveRewardPetLog(RewardPet  rewardPet, int status) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("status", status);
			paramMap.put("g_batchid", System.currentTimeMillis());
			paramMap.put("g_reward_type", 3);//表示类型为 宠物
			paramMap.put("g_username", rewardPet.getUsername());
			paramMap.put("g_guid", rewardPet.getGuid());
			paramMap.put("g_resource_type", 0);
			paramMap.put("g_resouces", rewardPet.getTranslatedContent());
			paramMap.put("g_operater", rewardPet.getOperater());
			paramMap.put("g_source", rewardPet.getSource());
			paramMap.put("g_note", rewardPet.getDesc());//备注信息

			rewardDao.saveResouceLog(paramMap);
			rewardLogger.info("保存[" + rewardPet.getUsername() + "]发宠物日志记录成功！");
		} catch (Exception e) {
			rewardLogger.error("保存发宠物日志记录失败！" + e);
		}
	}

}


