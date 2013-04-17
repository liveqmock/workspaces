/**      
 * Context.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.killer.model.Policy;

/**
 * @ClassName: Context
 * @Description: 环境对象
 * @author 吴江晖
 * @date 2012-7-11 下午12:21:57
 * @Version 1.0
 * 
 */
public class Context {

	private static final Logger logger4j = Logger.getLogger(Context.class);

	private final Map<String, Policy> policies;// 缓存的活跃封杀策略列表

	private static Context instance;

	protected Context() {
		policies = new HashMap<String, Policy>();
	}

	public static Context get() {
		if (instance == null) {
			instance = new Context();
		}
		return instance;
	}

	/**
	 * 添加活跃封杀策略
	 * 
	 * @param policies
	 *            活跃封杀策略列表
	 */
	public void addPolicies(final List<Policy> policies) {
		for (Policy policy : policies) {
			addPolicy(policy);
		}
	}

	/**
	 * 添加一条活跃封杀策略
	 * 
	 * @param policy
	 *            活跃封杀策略
	 */
	public void addPolicy(final Policy policy) {
		logger4j.info("缓存活跃封杀策略：【" + policy + "】");
		policies.put(policy.getMac().toLowerCase(), policy);
	}

	/**
	 * 根据MAC地址取得缓存的活跃封杀策略
	 * 
	 * @param mac
	 *            MAC地址
	 */
	public Policy getPolicy(final String mac) {
		return policies.get(mac.toLowerCase());
	}

	/**
	 * 根据MAC地址移除缓存的活跃封杀策略
	 * 
	 * @param mac
	 *            MAC地址
	 */
	public void removePolicy(final String mac) {
		logger4j.info("从缓存中移除封杀策略：【MAC=" + mac + "】");
		policies.remove(mac.toLowerCase());
	}

	/**
	 * 取得缓存的活跃封杀策略列表
	 */
	public List<Policy> getPolicies() {
		ArrayList<Policy> result = new ArrayList<Policy>();
		for (Map.Entry<String, Policy> entry : policies.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
	}
}
