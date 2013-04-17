/**      
 * XtaskService.java Create on 2012-5-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.service;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: XtaskService
 * @Description: xtask服务接口
 *               <ol>
 *               <li>记录点击数</li>
 *               <li>记录注册用户</li>
 *               <li>记录计算后消费数据</li>
 *               </ol>
 * @author 吴江晖
 * @date 2012-5-11 下午03:46:44
 * @Version 1.0
 * 
 */
public interface XtaskService {

	/**
	 * 记录点击数
	 * 
	 * @param gameId
	 *            游戏ID
	 * @param mediaId
	 *            媒体ID
	 * @param linkId
	 *            链接ID
	 * @param atime
	 *            点击时间
	 */
	public int countClick(String gameId, int mediaId, int linkId, long atime);

	/**
	 * 记录注册用户
	 * 
	 * @param gameId
	 *            游戏ID
	 * @param mediaId
	 *            媒体ID
	 * @param linkId
	 *            链接ID
	 * @param accountId
	 *            用户帐号ID
	 * @param username
	 *            用户名
	 * @param ip
	 *            用户IP
	 * @param atime
	 *            注册时间
	 */
	public int registeUser(String gameId, int mediaId, int linkId,
			long accountId, String username, String ip, long atime);

	/**
	 * 记录消费数据
	 * 
	 * @param consumeDetail
	 *            消费数据
	 */
	public int writeConsumeDetail(Map<String, Object> consumeDetail);

	public int writeCheckDayData(Map<String, Object> consumeDetail);

	public int updateBonusOfMedia(double bonus, int mediaId);

	public int updateConsumeDataOfAccount(double bonus, double money,
			long accountid);

	/**
	 * 记录用户首次登陆时间和IP
	 * 
	 * @param accountId
	 * @param loginTime
	 * @param ip
	 * @return
	 */
	public int writeUserLoginTimeAndIp(Long accountId, Date loginTime, String ip);

	public double getTotalBonusOfUserBetweenLevels(Long accountId,
			int minLevel, int maxLevel);

	public double getTotalBonusOfUser(Long accountId);
}
