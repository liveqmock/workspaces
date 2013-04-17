/**      
 * PolicyTask.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.worker.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.killer.context.Context;
import com.iwgame.xengine.xtask.killer.filters.LevelFilter;
import com.iwgame.xengine.xtask.killer.filters.LoginDataMatchResult;
import com.iwgame.xengine.xtask.killer.filters.PaidFilter;
import com.iwgame.xengine.xtask.killer.model.KillEvent;
import com.iwgame.xengine.xtask.killer.model.LoginMessageData;
import com.iwgame.xengine.xtask.killer.model.Policy;
import com.iwgame.xengine.xtask.killer.pipe.AndFilter;
import com.iwgame.xengine.xtask.killer.pipe.Pipe;
import com.iwgame.xengine.xtask.killer.service.AccountService;
import com.iwgame.xengine.xtask.killer.service.ContextService;
import com.iwgame.xengine.xtask.killer.service.KillEventPool;

/**
 * @ClassName: PolicyTask
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-11 下午03:02:35
 * @Version 1.0
 * 
 */
public class LoginTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(LoginTask.class);

	private final Pipe<LoginMessageData> pipe;

	private ContextService contextService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	private AccountService accountService;

	@Autowired
	public void setAccountService(final AccountService accountService) {
		this.accountService = accountService;
	}

	private KillEventPool killEventPool;

	@Autowired
	public void setKillEventPool(final KillEventPool killEventPool) {
		this.killEventPool = killEventPool;
	}

	private final SimpleDateFormat sdt;

	/**
	 * 
	 */
	public LoginTask() {
		AndFilter<LoginMessageData> filter = new AndFilter<LoginMessageData>();
		filter.addFilter(new PaidFilter());
		filter.addFilter(new LevelFilter());
		pipe = new Pipe<LoginMessageData>(filter);
		sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public void handleMessage(final String text) {
		try {
			logger.info("监听到登录数据：" + text);

			JSONObject json = JSONObject.fromObject(text);
			logger.debug("解析JSON：" + json);
			String eventMsg = json.getString("eventMsg");
			JSONObject msg = JSONObject.fromObject(eventMsg);
			logger.info("解析EventMsg：" + msg);
			// JSONObject params = json.getJSONObject("dynamicParameter");
			// logger.debug("解析dynamicParameter：" + params);

			LoginMessageData bean = (LoginMessageData) JSONObject.toBean(msg, LoginMessageData.class);

			if (StringUtils.isBlank(bean.getMac()) || StringUtils.isBlank(bean.getName())) {
				logger.error("【帐号封杀】，登录数据格式不对，抛弃数据！");
				return;
			}

			Policy policy = Context.get().getPolicy(bean.getMac());
			if (policy != null) {

				if ((policy.getRule().getAccounts() > 0)
						&& (contextService.getCounter(policy.getMac()) > policy.getRule().getAccounts())) {
					logger.info("今天封杀帐号总数已达限额" + policy.getRule().getAccounts() + "个，不再封杀");
					return;
				}

				bean.setPolicy(policy);
				Integer maxLevel = accountService.getMaxLevel(bean.getName());
				logger.info("最大等级是" + maxLevel);
				bean.setMaxLevel(maxLevel == null ? 0 : maxLevel);
				Integer totalPaid = accountService.getTotalPaid(bean.getName());
				logger.info("累计消费金额是" + totalPaid);
				bean.setTotalPaid(totalPaid == null ? 0 : totalPaid);
				pipe.setTarget(bean);
				LoginDataMatchResult result = (LoginDataMatchResult) pipe.match();
				if (result.isMatch() && !result.isIgnore()) {
					logger.info("符合过滤条件，不封杀！");
					// 不封杀
					return;
				} else {
					// 封杀
					KillEvent killEvent = new KillEvent();
					killEvent.setAccountName(bean.getName());
					killEvent.setContent(policy.getContent());
					killEvent.setTitle(policy.getTitle());
					killEvent.setMac(policy.getMac());
					killEvent.setDays(policy.getRule().getDays());
					killEvent.setReason(policy.getRule().getReason());
					killEvent.setZone(bean.getZone());
					if (policy.getRule().getDelay() == -1) {
						killEvent.setKillDate(new Date());
					} else {
						DateTime dt;
						try {
							dt = new DateTime(sdt.parse(bean.getAddtime()));
						} catch (ParseException e) {
							dt = new DateTime();
						}
						Random random = new Random();
						int prolong = random.nextInt(policy.getRule().getDelay());
						logger.info("延时" + prolong + "秒后封杀帐号" + bean.getName());
						killEvent.setKillDate(dt.plusSeconds(prolong).toDate());
					}
					logger.info("封杀帐号！\n" + killEvent);
					killEventPool.addKillEvent(killEvent);
					contextService.counter(policy.getMac());
				}
			} else {
				logger.debug("帐号的MAC地址不在封杀策略中，数据被抛弃！");
			}
		} catch (Exception e) {
			logger.error("【帐号封杀】，登录数据计算失败，抛弃数据！", e);
		}
	}
}
