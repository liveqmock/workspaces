/**      
 * ConsumeTask.java Create on 2012-5-16     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.worker.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.cps.context.Context;
import com.iwgame.xengine.xtask.cps.context.ProfitSharingPolicy.BounsPolicy;
import com.iwgame.xengine.xtask.cps.filters.ConsumeDataMatchResult;
import com.iwgame.xengine.xtask.cps.filters.DisableLinkFilter;
import com.iwgame.xengine.xtask.cps.filters.DisableMediaFilter;
import com.iwgame.xengine.xtask.cps.filters.IPCRLFilter;
import com.iwgame.xengine.xtask.cps.filters.OutOfDateFilter;
import com.iwgame.xengine.xtask.cps.filters.OverLevelQuotaFilter;
import com.iwgame.xengine.xtask.cps.filters.OverQuotaFilter;
import com.iwgame.xengine.xtask.cps.filters.UserCRLFilter;
import com.iwgame.xengine.xtask.cps.model.CacheUser;
import com.iwgame.xengine.xtask.cps.model.ConsumeMessageData;
import com.iwgame.xengine.xtask.cps.pipe.AndFilter;
import com.iwgame.xengine.xtask.cps.pipe.Pipe;
import com.iwgame.xengine.xtask.cps.service.ContextService;
import com.iwgame.xengine.xtask.cps.service.XtaskService;
import com.iwgame.xengine.xtask.cps.util.IPTools;

/**
 * @ClassName: ConsumeTask
 * @Description: 消费数据计算处理
 * @author 吴江晖
 * @date 2012-5-16 下午01:43:54
 * @Version 1.0
 * 
 */
public class ConsumeTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(ConsumeTask.class);

	private final Pipe<ConsumeMessageData> pipe;

	private ContextService contextService;

	private XtaskService xtaskService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	@Autowired
	public void setXtaskService(final XtaskService xtaskService) {
		this.xtaskService = xtaskService;
	}

	/**
	 * 
	 */
	public ConsumeTask() {

		AndFilter<ConsumeMessageData> filter = new AndFilter<ConsumeMessageData>();
		filter.addFilter(new UserCRLFilter());
		filter.addFilter(new OutOfDateFilter());
		filter.addFilter(new OverQuotaFilter());
		filter.addFilter(new IPCRLFilter());
		filter.addFilter(new DisableMediaFilter());
		filter.addFilter(new DisableLinkFilter());
		filter.addFilter(new OverLevelQuotaFilter());

		pipe = new Pipe<ConsumeMessageData>(filter);
	}

	/**
	 * 验证消费数据是要抛弃还是计算处理
	 * 
	 * @param data
	 *            验证的消费数据
	 * @return true=处理，false=抛弃
	 */
	protected boolean checkData(final JSONObject json) {
		long accountid = Long.valueOf(json.getString("accountid"));
		boolean needProcess = contextService.isCpsUser(accountid);
		if (needProcess) {
			int type = Integer.valueOf(json.getString("type"));
			needProcess = (type == 0);
			if (needProcess) {
				int optype = Integer.valueOf(json.getString("optype"));
				needProcess = (optype == 1);
				if (!needProcess) {
					logger.info("消费数据中操作类型不是购买物品，数据被抛弃");
				}
			} else {
				logger.info("消费数据中类型不是钻石，数据被抛弃");
			}
		} else {
			logger.info("消费数据中用户不是CPS用户，数据被抛弃");
		}
		return needProcess;
	}

	public synchronized void handleMessage(final String text) {
		logger.info("收到消费数据请求：" + text);

		try {
			JSONObject json = JSONObject.fromObject(text);
			logger.info("解析JSON：" + json);
			String eventMsg = json.getString("eventMsg");
			JSONObject msg = JSONObject.fromObject(eventMsg);
			logger.info("解析EventMsg：" + msg);
			JSONObject params = json.getJSONObject("dynamicParameter");
			logger.info("解析dynamicParameter：" + params);
			if (checkData(msg)) {
				ConsumeMessageData data = new ConsumeMessageData();
				data.setOrderId(Integer.valueOf(msg.getString("idx")));
				data.setAccountId(Long.valueOf(msg.getString("accountid")));
				data.setName(msg.getString("name"));
				data.setGold(Integer.valueOf(msg.getString("gold")));
				DateTime ctime = new DateTime(Long.valueOf(msg.getString("utime")) * 1000L);
				data.setCtime(ctime.toDate());
				data.setOpparam1(msg.getString("opparam1"));
				data.setServeridx(msg.getString("serveridx"));
				data.setActorid(msg.getString("actorid"));

				// 从MQ处获得的角色名为GBK编码
				// 例如：角色名=中文测试test
				// 获得的字符串为：\u00d6\u00d0\u00ce\u00c4\u00b2\u00e2\u00ca\u00d4test
				// 而“中文测试test”的GBK编码为 : \ud6d0\ucec4\ub2e2\ucad4test
				// 它的中文ByteString为
				// ffffffd6,ffffffd0,ffffffce,ffffffc4,ffffffb2,ffffffe2,ffffffca,ffffffd4
				// 所以需要对其进行 和 0xffffff00的或运行以纠正编码
				String actor = msg.getString("actorname");
				byte[] bytes = new byte[actor.length()];
				for (int i = 0; i < actor.length(); i++) {
					bytes[i] = (byte) (actor.charAt(i) | 0xffffff00);
				}
				data.setActor(new String(bytes, "GBK"));

				data.setLevel(Integer.valueOf(msg.getString("actorlev")));
				data.setIp(IPTools.longToIP(Long.valueOf(msg.getString("ip"))));
				data.setArea(params.getString("$zone"));
				CacheUser user = contextService.getCacheUser(data.getAccountId());

				data.setCacheUser(user);
				data.setLinkId(user.getLinkId());
				data.setMediaId(user.getMediaId());

				Map<String, Object> consumeDetail = new HashMap<String, Object>();
				consumeDetail.put("orderId", data.getOrderId());
				consumeDetail.put("area", data.getArea());
				consumeDetail.put("accountId", data.getAccountId());
				consumeDetail.put("name", data.getName());
				consumeDetail.put("linkId", data.getLinkId());
				consumeDetail.put("mediaId", data.getMediaId());
				consumeDetail.put("actor", data.getActor());
				consumeDetail.put("level", data.getLevel());
				double money = data.getGold() / 100.0;
				consumeDetail.put("money", money);
				consumeDetail.put("ctime", data.getCtime());
				consumeDetail.put("ip", data.getIp());
				consumeDetail.put("itemId", Integer.valueOf(data.getOpparam1()));

				Date processDate = new Date();
				double bonus = 0.0;
				double proportion = 0.0;
				double levelQuota = 0;
				int minLevel = 0, maxLevel = 0;
				logger.info("开始计算分成数据");
				for (BounsPolicy policy : user.getProfitSharingPolicy().getBounsPolicies()) {
					if ((data.getLevel() <= policy.getMax()) && (data.getLevel() >= policy.getMin())) {
						proportion = policy.getProps();
						levelQuota = policy.getQuota();
						bonus = money * (policy.getProps() / 100.0);
						minLevel = policy.getMin();
						maxLevel = policy.getMax();
						break;
					}
				}

				user.setBonus(xtaskService.getTotalBonusOfUser(data.getAccountId()));
				user.setLevelTotalBonus(xtaskService.getTotalBonusOfUserBetweenLevels(data.getAccountId(), minLevel,
						maxLevel));
				user.setBonusTheory(bonus);
				user.setLevelQuota(levelQuota);

				consumeDetail.put("proportion", proportion);
				consumeDetail.put("bonusTheory", bonus);

				pipe.setTarget(data);
				ConsumeDataMatchResult result = (ConsumeDataMatchResult) pipe.match();
				if (result.isMatch()) {
					logger.info("消费数据符合过滤条件，记录分成数据; 状态码=0");

					consumeDetail.put("bonusActual", bonus);

					consumeDetail.put("atime", processDate);
					consumeDetail.put("status", 0);

					// contextService.updateCacheUserBonus(data.getAccountId(),
					// bonus);
				} else {
					logger.info("消费数据不符合过滤条件，数据被忽略; 状态码=" + result.getStatusCode());
					double bonusActual = 0;
					int status = result.getStatusCode();
					if (result.getStatusCode() == 5) {
						bonusActual = levelQuota - user.getLevelTotalBonus();
						if (bonusActual > 0) {
							status = 0;
						} else {
							bonusActual = 0;
						}
						logger.info("状态码=5[超过区间限额],实际分成为" + bonusActual);
					}
					if (result.getStatusCode() == 3) {
						bonusActual = Context.get().getMaxBonus() - user.getBonus();
						if (bonusActual > 0) {
							status = 0;
						} else {
							bonusActual = 0;
						}
						logger.info("状态码=3[超过总限额],实际分成为" + bonusActual);
					}
					consumeDetail.put("bonusActual", bonusActual);
					consumeDetail.put("atime", processDate);
					consumeDetail.put("mtime", processDate);
					consumeDetail.put("status", status);

					// contextService.updateCacheUserBonus(data.getAccountId(),
					// bonusActual);

					bonus = bonusActual;
				}

				// 更新消费明细
				xtaskService.writeConsumeDetail(consumeDetail);

				// if (bonus != 0) {
				// // 更新媒体的分成总额
				// xtaskService.updateBonusOfMedia(bonus, user.getMediaId());
				// }

				// 更新用户的分成总额，消费金额，消费次数
				xtaskService.updateConsumeDataOfAccount(bonus, money, user.getAccountId());

				logger.info("消费数据写入数据库");
			}
		} catch (Exception e) {
			logger.error("消费数据计算错误。" + e.getMessage(), e);
		}

	}
}
