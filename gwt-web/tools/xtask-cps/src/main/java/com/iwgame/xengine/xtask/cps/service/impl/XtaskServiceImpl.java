/**      
 * XtaskServiceImpl.java Create on 2012-5-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.cps.model.User;
import com.iwgame.xengine.xtask.cps.service.BaseService;
import com.iwgame.xengine.xtask.cps.service.XtaskService;

/**
 * @ClassName: XtaskServiceImpl
 * @Description: xtask服务实现
 * @see XtaskService
 * @author 吴江晖
 * @date 2012-5-11 下午03:50:02
 * @Version 1.0
 * 
 */
public class XtaskServiceImpl extends BaseService implements XtaskService {

	private static final Logger logger = Logger.getLogger(XtaskServiceImpl.class);

	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.cps.service.XtaskService#countClick(java.lang
	 * .String, int, int)
	 */
	@Override
	public int countClick(final String gameId, final int mediaId, final int linkId, final long atime) {
		logger.info("将点击数入库【gameId=" + gameId + "，mediaId=" + mediaId + "，linkId=" + linkId + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gameId", gameId);
		params.put("mediaId", mediaId);
		params.put("linkId", linkId);
		params.put("time", new Date(atime));
		return insert(productId, NS + "countClick", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.cps.service.XtaskService#registeUser(java.lang
	 * .String, int, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public int registeUser(final String gameId, final int mediaId, final int linkId, final long accountId,
			final String username, final String ip, final long atime) {
		try {
			logger.info("将新用户入库【gameId=" + gameId + "，mediaId=" + mediaId + "，linkId=" + linkId + "，accountId="
					+ accountId + "，username=" + username + ",ip=" + ip + "】；【产品ID：" + productId + "】");
			User user = new User();
			user.setAccountId(accountId);
			user.setMediaId(mediaId);
			user.setLinkId(linkId);
			user.setName(username);
			user.setRegisteTime(new Date(atime));
			user.setIp(ip);
			return insert(productId, NS + "registeUser", user);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public int writeConsumeDetail(final Map<String, Object> params) {
		logger.info("记录计算后的消费数据【" + params + "】；【产品ID：" + productId + "】");
		return insert(productId, NS + "writeConsumeDetail", params);
	}

	@Override
	public int updateBonusOfMedia(final double bonus, final int mediaId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bonus", bonus);
		params.put("mediaId", mediaId);
		return update(productId, NS + "updateBonusOfMedia", params);
	}

	@Override
	public int updateConsumeDataOfAccount(final double bonus, final double money, final long accountid) {
		logger.info("更新帐号" + accountid + "的消费信息：【消费金额：" + money + "】【分成：" + bonus + "】；【产品ID：" + productId + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bonus", bonus);
		params.put("money", money);
		params.put("accountId", accountid);
		return update(productId, NS + "updateConsumeDataOfAccount", params);
	}

	@Override
	public int writeCheckDayData(final Map<String, Object> consumeDetail) {

		return 0;
	}

	@Override
	public int writeUserLoginTimeAndIp(final Long accountId, final Date loginTime, final String ip) {
		logger.info("更新帐号" + accountId + "的登录信息：【登录时间：" + loginTime + "】【登录IP：" + ip + "】；【产品ID：" + productId + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("loginTime", loginTime);
		params.put("ip", ip);
		return update(productId, NS + "writeUserLoginTimeAndIp", params);
	}

	@Override
	public double getTotalBonusOfUserBetweenLevels(final Long accountId, final int minLevel, final int maxLevel) {
		logger.info("获取帐号" + accountId + "在等级【" + minLevel + "," + maxLevel + "】之间的总消费额；【产品ID：" + productId + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("minLevel", minLevel);
		params.put("maxLevel", maxLevel);
		Double quota = selectOne(productId, NS + "getTotalBonusOfUserBetweenLevels", params);
		if (quota == null) {
			quota = 0.0;
		}
		logger.info("【总消费额=" + quota + "】");
		return quota;
	}

	@Override
	public double getTotalBonusOfUser(final Long accountId) {
		logger.info("获取帐号" + accountId + "的总消费额；【产品ID：" + productId + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		Double quota = selectOne(productId, NS + "getTotalBonusOfUser", params);
		if (quota == null) {
			quota = 0.0;
		}
		logger.info("【总消费额=" + quota + "】");
		return quota;
	}

}
