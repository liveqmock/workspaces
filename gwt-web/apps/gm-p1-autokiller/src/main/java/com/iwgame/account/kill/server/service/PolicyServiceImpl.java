/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： KillServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.iwgame.account.kill.modules.policy.shared.model.DropDownListData;
import com.iwgame.account.kill.modules.policy.shared.model.KillPolicy;
import com.iwgame.account.kill.modules.policy.shared.rpc.PolicyService;
import com.iwgame.account.kill.server.common.BaseService;
import com.iwgame.account.kill.server.mq.Properties;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： 自动封停策略服务
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-4 上午10:45:34
 */
@NeedAuthorization
public class PolicyServiceImpl extends BaseService implements PolicyService {
	private static final long serialVersionUID = -1042146003543983271L;

	private static final Logger logger4j = Logger.getLogger(PolicyServiceImpl.class);

	/**
	 * 最大启动策略数
	 * */
	private String maxPolicyNum;
	/**
	 * 默认封停天数
	 * */
	private String dueDays;
	/**
	 * 默认最大封停时间
	 * */
	private String delayTime;

	public void setMaxPolicyNum(final String maxPolicyNum) {
		this.maxPolicyNum = maxPolicyNum;
	}

	public void setDueDays(final String dueDays) {
		this.dueDays = dueDays;
	}

	public void setDelayTime(final String delayTime) {
		this.delayTime = delayTime;
	}

	private RabbitTemplate rabbitTemplate;

	private Properties mqProperties;

	public void setRabbitTemplate(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void setMqProperties(final Properties mqProperties) {
		this.mqProperties = mqProperties;
	}

	private static final String KP = "kill.policy.";
	private static final String KR = "kill.reason.";

	@Override
	public List<DropDownListData> getWhyDropDownListValue(final String productId) {
		List<DropDownListData> list = selectList(productId, KR + "getAllUnsettledDatas");
		return list;
	}

	@Override
	@AccessResource(name = "kill-policy-mean-querylist")
	public String getPolicyListData(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		try {
			// 分页&排序参数
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			parameter.put("statustype", loadConfig.<Integer> get("statustype"));
			Integer total = selectOne(productId, KP + "getAllPolicyCount", parameter);
			List<KillPolicy> list = selectList(productId, KP + "getAllPolicy", parameter);
			PagingLoadResult<KillPolicy> result = new PagingLoadResult<KillPolicy>();
			result.setRows(list);
			result.setOffset(loadConfig.<Integer> get("offset"));
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal(total);
			returnData = GridHelper.buildResults(result);
		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "kill-policy-mean-add")
	public int addKillPolicy(final String productId, final KillPolicy killPolicy) {
		int resulr = 0;
		try {
			killPolicy.getPolicy().setDueDays(Integer.parseInt(dueDays));
			if (killPolicy.getPolicy().getDelay() == 1) {
				killPolicy.getPolicy().setDelay(Integer.parseInt(delayTime));
			}
			killPolicy.setContent(JSONObject.fromObject(killPolicy.getPolicy()).toString());
			resulr = insert(productId, KP + "addKillPolicy", killPolicy);
		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return resulr;
	}

	@Override
	@AccessResource(name = "kill-policy-mean-isenabled-policy")
	public boolean checkIsEnabled(final String productId, final String mac) {
		boolean result = false;
		try {
			Integer enabledCount = selectOne(productId, KP + "verifyEnabledCount");
			Integer macCount = selectOne(productId, KP + "verifyEnabledMACCount", mac);
			if ((enabledCount < Integer.parseInt(maxPolicyNum)) && (macCount == 0)) {
				result = true;
			}
		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "kill-policy-mean-stop-policy")
	public int stop(final String productId, final int id, final String username, final String mac) {
		int result = 0;
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("modifier", username);
			parameter.put("id", id);
			// result = update(productId, KP + "stopPolicyById", parameter);
			parameter.put("mac", mac);
			parameter.put("type", 3);
			String msg = JSONObject.fromObject(parameter).toString();
			rabbitTemplate.convertAndSend(mqProperties.getExchange().replace("{0}", productId.toLowerCase()),
					mqProperties.getRoutingUpdatePolicy().replace("{0}", productId.toLowerCase()), msg);
		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "kill-policy-mean-enabled-policy")
	public int enabled(final String productId, final int id, final String username, final String mac) {
		int result = 0;
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("modifier", username);
			parameter.put("id", id);
			// result = update(productId, KP + "enabledPolicyById", parameter);
			parameter.put("mac", mac);
			parameter.put("type", 1);
			String msg = JSONObject.fromObject(parameter).toString();
			rabbitTemplate.convertAndSend(mqProperties.getExchange().replace("{0}", productId.toLowerCase()),
					mqProperties.getRoutingUpdatePolicy().replace("{0}", productId.toLowerCase()), msg);
		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public boolean checkTitle(final String productId, final String title) {
		boolean result = false;
		try {
			Integer r = selectOne(productId, KP + "erifyTitle", title);
			if (r == 0) {
				result = true;
			}
		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "kill-policy-mean-delay-policy")
	public int delay(final String productId, final int id, final String username, final String mac) {
		int result = 0;
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("modifier", username);
			parameter.put("id", id);
			// result = update(productId, KP + "delayPolicy", parameter);
			parameter.put("mac", mac);
			parameter.put("type", 2);
			String msg = JSONObject.fromObject(parameter).toString();
			rabbitTemplate.convertAndSend(mqProperties.getExchange().replace("{0}", productId.toLowerCase()),
					mqProperties.getRoutingUpdatePolicy().replace("{0}", productId.toLowerCase()), msg);
		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "kill-policy-mean-update-policy")
	public int updateKillPolicy(final String productId, final KillPolicy killPolicy) {
		int result = 0;
		try {
			killPolicy.getPolicy().setDueDays(Integer.parseInt(dueDays));
			if (killPolicy.getPolicy().getDelay() == 1) {
				killPolicy.getPolicy().setDelay(Integer.parseInt(delayTime));
			}
			killPolicy.setContent(JSONObject.fromObject(killPolicy.getPolicy()).toString());
			result = update(productId, KP + "updateKillPolicy", killPolicy);

			Map<String, Object> parameter = new HashMap<String, Object>();
			if (killPolicy.getStatus() == 1) {
				parameter.put("modifier", killPolicy.getModifier());
				parameter.put("id", killPolicy.getId());
				parameter.put("mac", killPolicy.getObject());
				parameter.put("type", 2);
				String msg = JSONObject.fromObject(parameter).toString();
				rabbitTemplate.convertAndSend(mqProperties.getExchange().replace("{0}", productId.toLowerCase()),
						mqProperties.getRoutingUpdatePolicy().replace("{0}", productId.toLowerCase()), msg);
			}
		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public boolean checkIsUpdateMac(String productId, String mac) {
		Integer macCount = selectOne(productId, KP + "verifyEnabledMACCount", mac);
		if (macCount > 1)
			return false;
		else
			return true;
	}
}
