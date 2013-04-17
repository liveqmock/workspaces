/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： XtaskService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.service;

import com.iwgame.xengine.xtask.wg1.model.RewardEquipment;
import com.iwgame.xengine.xtask.wg1.model.RewardGemstone;
import com.iwgame.xengine.xtask.wg1.model.RewardPet;
import com.iwgame.xengine.xtask.wg1.model.UserPoints;
import com.iwgame.xengine.xtask.wg1.model.UserResouce;


/**
 * 类说明
 * @简述： XtaskService接口
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午05:58:53
 */
public interface XtaskService {

	/**
	 * 给游戏中的玩家实现发放资源奖品
	 * @param userResouce 	资源奖品对象
	 * @return
	 */
	public void giveGift(UserResouce userResouce);
	
	/**
	 * 把发奖信息放到备用发奖通道中
	 * @param message
	 */
	public void send2RewardBackupMQ(String message);
	
	/**
	 * 给游戏中的玩家实现发放宝石
	 * @param rewardGemstone 	宝石对象
	 * @return
	 */
	public void giveRewardGemstone(RewardGemstone rewardGemstone);
	
	/**
	 * 把发宝石信息放到备用通道中
	 * @param message
	 */
	public void send2RewardGemstoneBackupMQ(String message);
	
	/**
	 * 给游戏中的玩家实现发放装备
	 * @param rewardEquipment 	装备对象
	 * @return
	 */
	public void giveRewardEquipment(RewardEquipment rewardEquipment);
	
	/**
	 * 把发装备信息放到备用通道中
	 * @param message
	 */
	public void send2RewardEquipmentBackupMQ(String message);
	
	
	/**
	 * 把发宠物信息放到备用通道中
	 * @param message
	 */
	public void send2RewardPetBackupMQ(String message);
	
	/**
	 * 给游戏中的玩家实现赠送宠物
	 * @param rewardPet 宠物对象
	 * @return
	 */
	public void giveRewardPet(RewardPet rewardPet);
	
	
	/**
	 * 给玩家实现发放积分
	 * @param userPoints 	积分对象
	 * @return
	 */
	public void givePoints(UserPoints userPoints);
	
	/**
	 * 服务器人数设置
	 * @param guid			大区编号
	 * @param maxUserCounts 人数
	 * @throws Exception
	 */
	public void setServerParameters(String guid,String maxUserCounts);
	
	
	/**
	 * 给游戏中的玩家发信
	 * 
	 * @param guid       大区编号
	 * @param scope      发放范围 0：所有用户 1：自定义用户 2：导入用户
	 * @param username   用户名
	 * @param mailtitle  邮件标题
	 * @param mailcontent邮件内容
	 * @param operater   操作者
	 * @param scope      范围
	 * @return
	 */
	public void sendMail(String guid,String username,String mailtitle,String mailcontent,String operater,int scope); 
	
	/**
	 * 给游戏中发送公告
	 * @param guid	(特别说明:ALL表示为所有大区列表)
	 * @param type
	 * @param content
	 * @param operater
	 */
	public void sendNotice(String guid, int type, String content,String operater);
	
	/**
	 * 人民币充值发送
	 * @param message
	 */
	public void sendRmbCZMQMessage(String message);
	
	/**
	 * 踢人
	 * @param guid
	 * @param type
	 * @param username
	 */
	public void kickUser(String guid, int type, String username); 
	
	
	/**
	 * 玩家管理，目前支持封禁账号/解封账号
	 * @param username 用户名
	 * @param operator 操作：1=封禁；2=解封
	 * @param content 封禁理由
	 * @param minute 封禁时间
	 * @return
	 */
	public void AccountManager(String username,int operator,String content,int minute,String memo);
	
	/**
	 * 禁言管理，支持禁言、解除禁言
	 * @param guid 目标大区ID
	 * @param username 用户名
	 * @param operate 操作：1=禁言；2=解禁
	 * @param validtime 禁言的有效期，单位秒，至少1分钟。
	 * @param msg 禁言内容
	 */
	public void TalkManager(String guid,String username,int operate,int validtime,String msg,String memo);
	
	/**
	 * 封禁IP管理，支持封禁IP，解除封禁IP
	 * @param guid 目标大区
	 * @param ip 用户IP
	 * @param operate 操作类型(0:封禁;1:解封)
	 * @param validtime 封禁时间(分钟,0为永久封禁)
	 * @param memo 日志说明
	 */
	public void LockIPManager(String guid,String ip,int operate,int validtime,String memo);
	
	/**
	 * 封禁玩家角色管理，支持封禁玩家角色，解禁玩家角色
	 * @param guid 目标大区ID
	 * @param username 用户名
	 * @param operate 1 = 封停,4 = 解除异常状态（封停、冻结)
	 * @param validtime 封停、冻结有效期，单位秒，当time=0时，表示永久封停、冻结
	 * @param memo 日志说明
	 */
	public void LockUserManager(String guid,String username,int operate,int validtime,String memo);
}
