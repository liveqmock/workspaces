/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FlumeManageService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.sever.service;

/**
 * 类说明
 * 
 * @简述： Flume管理相关接口类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-25 下午8:41:30
 */
public interface FlumeManageService {
	// 所有的方法均返回字符串消息，前端接受并显示给客户端
	// configJson: {"anodeid":"","flowId":"","source":"","sink":""}

	/**
	 * 关闭Anode配置
	 * 
	 * @param configJson
	 */
	public String unconfigAnode(String configJson);

	/**
	 * 配置Anode(新配置，或者错误情况下的重置）
	 * 
	 * @param configJson
	 */
	public String configAnode(String configJson);

	/**
	 * 关闭Cnode配置
	 * 
	 * @param configJson
	 */
	public String unconfigCnode(String configJson);

	/**
	 * 配置Cnode(新配置，或者错误情况下的重置）
	 * 
	 * @param configJson
	 */
	public String configCnode(String configJson);

	/**
	 * 开启通道
	 * 
	 * @param channelId
	 * @param physicalAnode
	 * @param physicalCnode
	 */
	public String openChannel(String channelId, String physicalAnode, String physicalCnode);

	/**
	 * 关闭通道，（一般合服的时候使用， 在redis中删除key，执行unconfig操作，数据库更改通道状态)
	 * 
	 * @param channelId
	 * @param physicalAnode
	 * @param physicalCnode
	 */
	public String closeChannel(String channelId, String physicalAnode, String physicalCnode);

	/**
	 * 获取下一个推荐的端口号
	 * 
	 * @param productId
	 * @param channelId
	 * @return
	 */
	public int getNextRecommendPort(String productId, String channelId);

	/**
	 * 确认数据一致
	 * 
	 * @param channelId
	 *            通道编号
	 * @param date
	 *            日期（YYYYMMDD）
	 */
	public void confirmDataIntegrity(String channelId, String date);
	
	
	/**
	 * 验证通道是否正常
	 * @param productId  产品编号
	 */
	public void validateChannel(String productId);
	
	/**
	 * 解锁通道组警告
	 * @param groupId 组编号
	 */
	public void unlockChannelGroupWarning(String groupId);
	
	/**
	 * 解锁通道警告
	 * @param channelId 通道编号
	 */
	public void unlockChannelWarning(String channelId);
}
