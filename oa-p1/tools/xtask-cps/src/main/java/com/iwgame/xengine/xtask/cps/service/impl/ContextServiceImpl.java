/**      
 * ContextServiceImpl.java Create on 2012-5-16     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.service.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.iwgame.xengine.xtask.cps.context.CRL;
import com.iwgame.xengine.xtask.cps.context.CRL.Type;
import com.iwgame.xengine.xtask.cps.context.Context;
import com.iwgame.xengine.xtask.cps.context.ProfitSharingPolicy;
import com.iwgame.xengine.xtask.cps.model.CacheUser;
import com.iwgame.xengine.xtask.cps.model.Config;
import com.iwgame.xengine.xtask.cps.model.IPCRL;
import com.iwgame.xengine.xtask.cps.model.UserCRL;
import com.iwgame.xengine.xtask.cps.service.BaseService;
import com.iwgame.xengine.xtask.cps.service.ContextService;

/**
 * @ClassName: ContextServiceImpl
 * @Description: 上下文环境服务实现
 * @author 吴江晖
 * @date 2012-5-16 上午08:53:08
 * @Version 1.0
 * 
 */
public class ContextServiceImpl extends BaseService implements ContextService {

	private static Logger logger = Logger.getLogger(ContextServiceImpl.class);

	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	private StringRedisTemplate cpsRedisTemplate;

	@Autowired
	public void setCpsRedisTemplate(final StringRedisTemplate cpsRedisTemplate) {
		this.cpsRedisTemplate = cpsRedisTemplate;
	}

	private static final String CACHE_USER_KEY = "uid:{0}:info";
	private final MessageFormat keyFormat;
	private final SimpleDateFormat dateFormat;

	public ContextServiceImpl() {
		super();
		keyFormat = new MessageFormat(CACHE_USER_KEY);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public synchronized void initializeCRLs(final CRL crl) {
		List<UserCRL> userCrls = selectList(productId, NS + "getUserCRLs");
		List<IPCRL> ipCrls = selectList(productId, NS + "getIPCRLs");
		crl.setUserCRLs(userCrls);
		crl.setIpCRLs(ipCrls);
	}

	@Override
	public void updateCRL(final Type type) {
		logger.info("更新黑名单");
		switch (type) {
		case USER:
			List<UserCRL> userCrls = selectList(productId, NS + "getUserCRLs");
			Context.get().getCRL().setUserCRLs(userCrls);
			break;
		case IP:
			List<IPCRL> ipCrls = selectList(productId, NS + "getIPCRLs");
			Context.get().getCRL().setIpCRLs(ipCrls);
			break;
		}
	}

	@Override
	public void initializeCache() {
		logger.info("从数据库中查询所有的CPS用户");
		List<CacheUser> cacheUsers = selectList(productId, NS + "getCacheUsers");
		logger.info("查询到CPS用户" + cacheUsers.size() + "名");
		Set<String> keys = cpsRedisTemplate.keys("uid:*:info");
		if ((keys != null) && !keys.isEmpty()) {
			cpsRedisTemplate.delete(cpsRedisTemplate.keys("uid:*:info"));
		}
		logger.info("将查询到CPS用户加载到缓存中");
		for (CacheUser cacheUser : cacheUsers) {
			addCacheUser(cacheUser);
		}
	}

	@Override
	public void addCacheUser(final CacheUser cacheUser) {
		HashOperations<String, String, String> hashOpt = cpsRedisTemplate.opsForHash();
		String key = keyFormat.format(new String[] { Long.toString(cacheUser.getAccountId()) });
		hashOpt.put(key, "name", cacheUser.getName());
		hashOpt.put(key, "media_id", Integer.toString(cacheUser.getMediaId()));
		hashOpt.put(key, "link_id", Integer.toString(cacheUser.getLinkId()));
		hashOpt.put(key, "atime", dateFormat.format(cacheUser.getCreateTime()));
		// hashOpt.put(key, "bonus", Double.toString(cacheUser.getBonus()));
		if (cacheUser.getLoginTime() != null) {
			hashOpt.put(key, "ltime", dateFormat.format(cacheUser.getLoginTime()));
		} else {
			hashOpt.put(key, "ltime", "");
		}

	}

	@Override
	public void updateCacheUserBonus(final long accountId, final double bonus) {
		HashOperations<String, String, String> hashOpt = cpsRedisTemplate.opsForHash();
		String key = keyFormat.format(new String[] { Long.toString(accountId) });
		double _bonus = Double.valueOf(hashOpt.get(key, "bonus"));
		hashOpt.put(key, "bonus", Double.toString(_bonus + bonus));
	}

	@Override
	public CacheUser getCacheUser(final long accountId) {
		HashOperations<String, String, String> hashOpt = cpsRedisTemplate.opsForHash();
		String key = keyFormat.format(new String[] { Long.toString(accountId) });
		Map<String, String> entries = hashOpt.entries(key);
		if ((entries != null) && !entries.isEmpty()) {
			CacheUser user = new CacheUser();
			user.setAccountId(accountId);
			user.setName(entries.get("name").toString());
			Integer mediaId = Integer.valueOf(entries.get("media_id"));
			user.setMediaId(mediaId);
			user.setLinkId(Integer.valueOf(entries.get("link_id")));
			try {
				user.setCreateTime(dateFormat.parse(entries.get("atime")));
			} catch (ParseException e) {
				logger.info("[没有用户创建时间，atime]" + e.getMessage());
				user.setCreateTime(null);
			}
			try {
				user.setLoginTime(dateFormat.parse(entries.get("ltime")));
			} catch (ParseException e) {
				logger.info("[没有用户登录时间，ltime]" + e.getMessage());
				user.setLoginTime(null);
			}
			user.setBlocked(Context.get().getCRL().blockUser(accountId));
			ProfitSharingPolicy policy = Context.get().getProfitSharingPolicy(mediaId);
			user.setProfitSharingPolicy(policy);
			logger.info("获取CPS用户：" + user);
			return user;
		} else {
			return null;
		}
	}

	@Override
	public boolean isCpsUser(final long accountId) {
		Set<String> keys = cpsRedisTemplate.keys(keyFormat.format(new String[] { Long.toString(accountId) }));
		return ((keys != null) && !keys.isEmpty() && (keys.size() == 1));
	}

	@Override
	public void initializeProfitSharingPolicyTable() {
		logger.info("更新媒体的分成方案");
		List<Map<String, Object>> schemas = selectList(productId, NS + "getUsedSchemasByMedia");
		for (Map<String, Object> schema : schemas) {
			Integer mediaId = (Integer) schema.get("mediaId");
			ProfitSharingPolicy psp = new ProfitSharingPolicy();
			String bounsPolicy = (String) schema.get("bonusPolicy");
			String awardPolicy = (String) schema.get("awardPolicy");
			JSONArray arrayBounsPolicy = JSONArray.fromObject(bounsPolicy);
			for (int i = 0; i < arrayBounsPolicy.size(); i++) {
				JSONObject object = arrayBounsPolicy.getJSONObject(i);
				psp.addBounsPolicy(object.getInt("min"), object.getInt("max"), object.getDouble("props"),
						object.getDouble("quota"));
			}
			JSONArray arrayAwardPolicy = JSONArray.fromObject(awardPolicy);
			for (int i = 0; i < arrayAwardPolicy.size(); i++) {
				JSONObject object = arrayAwardPolicy.getJSONObject(i);
				psp.addAwardPolicy(object.getDouble("consume"), object.getDouble("award"));
			}
			Context.get().updateProfitSharingPolicyTable(mediaId, psp);
			logger.info(">>>>[XTASK-CPS]-添加媒体" + mediaId + "分成方案");
			logger.info(">>>>>>>>>>" + bounsPolicy);
			logger.info(">>>>>>>>>>" + awardPolicy);
		}
	}

	@Override
	public void updateProfitSharingPolicy(final int mediaId) {
		logger.info("更新媒体" + mediaId + "的分成方案");
		Map<String, Object> schema = selectOne(productId, NS + "getUsedSchemaByMedia", mediaId);
		ProfitSharingPolicy psp = new ProfitSharingPolicy();
		String bounsPolicy = (String) schema.get("bonusPolicy");
		String awardPolicy = (String) schema.get("awardPolicy");
		JSONArray arrayBounsPolicy = JSONArray.fromObject(bounsPolicy);
		for (int i = 0; i < arrayBounsPolicy.size(); i++) {
			JSONObject object = arrayBounsPolicy.getJSONObject(i);
			psp.addBounsPolicy(object.getInt("min"), object.getInt("max"), object.getDouble("props"),
					object.getDouble("quota"));
		}
		JSONArray arrayAwardPolicy = JSONArray.fromObject(awardPolicy);
		for (int i = 0; i < arrayAwardPolicy.size(); i++) {
			JSONObject object = arrayAwardPolicy.getJSONObject(i);
			psp.addAwardPolicy(object.getDouble("consume"), object.getDouble("award"));
		}
		Context.get().updateProfitSharingPolicyTable(mediaId, psp);
	}

	@Override
	public void initializeGlobalConfig() {
		logger.info("初始化全局配置");
		Config config = selectOne(productId, NS + "getGlobalConfig");
		Context context = Context.get();
		if (config != null) {
			context.setMaxBonus(config.getMaxBonus());
			logger.info(">>>>>>>>>>MaxBonus=" + config.getMaxBonus());
			context.setMaxDays(config.getMaxDays());
			logger.info(">>>>>>>>>>MaxDays=" + config.getMaxDays());
		}
	}

	@Override
	public void initializeDisableMedias() {
		logger.info("初始化禁用推广媒体");
		Context ctx = Context.get();
		List<Integer> results = selectList(productId, NS + "getDisableMedias");
		if ((results != null) && !results.isEmpty()) {
			for (Integer mediaId : results) {
				ctx.addDisableMedia(mediaId);
			}
		}
	}

	@Override
	public void initializeDisableLinks() {
		logger.info("初始化禁用推广链接");
		Context ctx = Context.get();
		List<Integer> results = selectList(productId, NS + "getDisableLinks");
		if ((results != null) && !results.isEmpty()) {
			for (Integer linkId : results) {
				ctx.addDisableLink(linkId);
			}
		}
	}

	@Override
	public void updateDisableMedias(final Integer mediaId, final boolean remove) {
		if (remove) {
			logger.info("移除禁用推广媒体");
			Context.get().removeDisableMedia(mediaId);
		} else {
			logger.info("添加禁用推广媒体");
			Context.get().addDisableMedia(mediaId);
		}
	}

	@Override
	public void updateDisableLinks(final Integer linkId, final boolean remove) {
		if (remove) {
			logger.info("移除禁用推广链接");
			Context.get().removeDisableLink(linkId);
		} else {
			logger.info("添加禁用推广链接");
			Context.get().addDisableLink(linkId);
		}
	}

	@Override
	public void updateCacheUserLoginTime(final Long accountId, final Date loginTime) {
		logger.info("更新缓存用户" + accountId + "登录时间");
		HashOperations<String, String, String> hashOpt = cpsRedisTemplate.opsForHash();
		String key = keyFormat.format(new String[] { Long.toString(accountId) });
		hashOpt.put(key, "ltime", dateFormat.format(loginTime));
	}

}
