/**      
 * ContextServiceImpl.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.iwgame.xengine.xtask.killer.context.Context;
import com.iwgame.xengine.xtask.killer.model.Policy;
import com.iwgame.xengine.xtask.killer.model.PolicyMeta;
import com.iwgame.xengine.xtask.killer.service.BaseService;
import com.iwgame.xengine.xtask.killer.service.ContextService;

/**
 * @ClassName: ContextServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-11 下午12:29:41
 * @Version 1.0
 * 
 */
public class ContextServiceImpl extends BaseService implements ContextService {

	private static final Logger logger4j = Logger.getLogger(ContextServiceImpl.class);

	private String productId;

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	private StringRedisTemplate killerRedisTemplate;

	@Autowired
	public void setKillerRedisTemplate(final StringRedisTemplate killerRedisTemplate) {
		this.killerRedisTemplate = killerRedisTemplate;
	}

	@Override
	public void loadActivedKillPolicies() {
		List<Policy> policies = selectList(productId, "killer.xtask.queryPolicies");
		for (Policy policy : policies) {
			String reason = selectOne(productId, "killer.xtask.queryReason", policy.getRule().getReasonId());
			policy.getRule().setReason(reason);
			DateTime startDate = new DateTime(policy.getStartDate());
			policy.setEndDate(startDate.plusDays(policy.getRule().getDueDays()).toDate());
		}
		Context.get().addPolicies(policies);
	}

	@Override
	public void loadActivedKillPolicy(final PolicyMeta meta) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", meta.getId());
		params.put("modifier", meta.getModifier());
		params.put("startDate", new Date());
		int i = update(productId, "killer.xtask.activePolicy", params);
		if (i > 0) {
			Policy policy = selectOne(productId, "killer.xtask.queryPolicy", params);
			if (policy != null) {
				String reason = selectOne(productId, "killer.xtask.queryReason", policy.getRule().getReasonId());
				policy.getRule().setReason(reason);
				DateTime startDate = new DateTime(policy.getStartDate());
				policy.setEndDate(startDate.plusDays(policy.getRule().getDueDays()).toDate());
				Context.get().addPolicy(policy);
			}
		}
	}

	@Override
	public void prolongActivedKillPolicy(final PolicyMeta meta) {
		logger4j.info("对封杀策略【id:" + meta.getId() + "】延长有限期");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", meta.getId());
		params.put("modifier", meta.getModifier());
		params.put("startDate", new Date());
		int i = update(productId, "killer.xtask.prolongPolicy", params);
		if (i > 0) {
			loadActivedKillPolicy(meta);
		}
	}

	@Override
	public void deactiveKillPolicy(final PolicyMeta meta) {
		logger4j.info("停用封杀策略【id:" + meta.getId() + "】");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", meta.getId());
		params.put("modifier", meta.getModifier());
		int i = update(productId, "killer.xtask.deactivePolicy", params);
		if (i > 0) {
			Context.get().removePolicy(meta.getMac());
		}
	}

	@Override
	public void counter(final String mac) {
		logger4j.info("针对" + mac + "的封杀计数器加一");
		killerRedisTemplate.boundValueOps(mac.toLowerCase() + ":counter").increment(1);
	}

	@Override
	public void cleanCounter() {
		logger4j.info("封杀计数器全部清零");
		killerRedisTemplate.delete(killerRedisTemplate.keys("*:counter"));
	}

	@Override
	public void cleanCounter(final String mac) {
		logger4j.info("针对" + mac + "的封杀计数器清零");
		killerRedisTemplate.delete(mac.toLowerCase() + ":counter");

	}

	@Override
	public Integer getCounter(final String mac) {
		logger4j.info("获取" + mac + "的封杀计数器计数");
		String v = killerRedisTemplate.boundValueOps(mac.toLowerCase() + ":counter").get();
		return v == null ? 0 : Integer.valueOf(v);
	}

}
