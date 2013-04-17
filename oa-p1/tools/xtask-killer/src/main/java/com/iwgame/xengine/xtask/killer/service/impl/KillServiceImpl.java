/**      
 * KillServiceImpl.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.pconf.model.GGameGroupNode;
import com.iwgame.pconf.service.ProductConfigurationService;
import com.iwgame.xengine.xtask.killer.model.KillEvent;
import com.iwgame.xengine.xtask.killer.service.BaseService;
import com.iwgame.xengine.xtask.killer.service.KillEventPool;
import com.iwgame.xengine.xtask.killer.service.KillService;

/**
 * @ClassName: KillServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-11 下午06:17:31
 * @Version 1.0
 * 
 */
public class KillServiceImpl extends BaseService implements KillService {

	private static final Logger logger4j = Logger.getLogger(KillServiceImpl.class);

	private String productId;

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	private ProductConfigurationService productConfigurationService;

	@Autowired
	public void setProductConfigurationService(final ProductConfigurationService productConfigurationService) {
		this.productConfigurationService = productConfigurationService;
	}

	private KillEventPool killEventPool;

	@Autowired
	public void setKillEventPool(final KillEventPool killEventPool) {
		this.killEventPool = killEventPool;
	}

	private RabbitTemplate killerServiceRabbitTemplate;

	@Autowired
	public void setKillerServiceRabbitTemplate(final RabbitTemplate killerServiceRabbitTemplate) {
		this.killerServiceRabbitTemplate = killerServiceRabbitTemplate;
	}

	private String exchange;

	private String routeKey;

	public void setExchange(final String exchange) {
		this.exchange = exchange;
	}

	public void setRouteKey(final String routeKey) {
		this.routeKey = routeKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.killer.service.KillService#kill(java.util.Date)
	 */
	@Override
	public void kill(final Date date) {
		List<GGameGroupNode> areas = productConfigurationService.getAreaByProduct(productId.toUpperCase());

		List<KillEvent> events = killEventPool.locateKillEvent(date);
		if (events.size() > 0) {
			logger4j.info("从封杀事件池中检索到" + events.size() + "条符合封杀时间的封杀事件");
		} else {
			logger4j.debug("从封杀事件池中检索到" + events.size() + "条符合封杀时间的封杀事件");
		}
		for (KillEvent event : events) {

			// {
			// "guid":"大区ID",
			// "sealtime":10,
			// "username":"帐号名",
			// "rolename":"角色名字"
			// "note":"封禁,解封,原因，说明"
			// }
			int sealtime = event.getDays() * 1440;
			for (GGameGroupNode node : areas) {
				String zone = node.getId().substring(node.getId().lastIndexOf("-") + 1);
				String eventMsg = "{\"guid\":\"" + zone + "\",\"sealtime\":\"" + sealtime + "\",\"username\":\""
						+ event.getAccountName() + "\",\"rolename\":\"\",\"note\":\"" + event.getReason() + "\"}";
				logger4j.debug("发送封杀消息：" + eventMsg);
				logger4j.debug("封杀消息目的地：" + exchange + "[" + routeKey + "]");
				killerServiceRabbitTemplate.convertAndSend(exchange, routeKey, eventMsg);
			}

			logger4j.debug("记录封杀事件日志" + event);
			insert(productId, "killer.xtask.writeKillLog", event);
		}
	}

}
