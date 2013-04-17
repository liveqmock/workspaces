/**      
 * KillEventPoolImpl.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.core.query.SortQueryBuilder;

import com.iwgame.xengine.xtask.killer.model.KillEvent;
import com.iwgame.xengine.xtask.killer.service.KillEventPool;

/**
 * @ClassName: KillEventPoolImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-11 下午04:24:06
 * @Version 1.0
 * 
 */
public class KillEventPoolImpl implements KillEventPool {

	private StringRedisTemplate killerRedisTemplate;

	@Autowired
	public void setKillerRedisTemplate(final StringRedisTemplate killerRedisTemplate) {
		this.killerRedisTemplate = killerRedisTemplate;
	}

	private static final String KILLDATE_KEY = "killdates:set";
	private static final String ACCOUNT_LIST_KEY = "{0}:account:list";
	private static final String ACCOUNT_KEY = "account:{0}:info";

	private final MessageFormat accountListKeyFormat;
	private final MessageFormat accountKeyFormat;

	/**
	 * 
	 */
	public KillEventPoolImpl() {
		super();
		accountListKeyFormat = new MessageFormat(ACCOUNT_LIST_KEY);
		accountKeyFormat = new MessageFormat(ACCOUNT_KEY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.killer.service.KillEventPool#addKillEvent(com
	 * .iwgame.xengine.xtask.killer.model.KillEvent)
	 */
	@Override
	public void addKillEvent(final KillEvent event) {

		SetOperations<String, String> setOpt = killerRedisTemplate.opsForSet();
		setOpt.add(KILLDATE_KEY, Long.toString(event.getKillDate().getTime()));

		ListOperations<String, String> listOpt = killerRedisTemplate.opsForList();
		String listKey = accountListKeyFormat.format(new Object[] { Long.toString(event.getKillDate().getTime()) });
		listOpt.leftPush(listKey, event.getAccountName());

		HashOperations<String, String, String> hashOpt = killerRedisTemplate.opsForHash();
		String key = accountKeyFormat.format(new Object[] { event.getAccountName() });
		hashOpt.put(key, "days", Integer.toString(event.getDays()));
		hashOpt.put(key, "reason", event.getReason());
		hashOpt.put(key, "content", event.getContent());
		hashOpt.put(key, "mac", event.getMac());
		hashOpt.put(key, "title", event.getTitle());
		hashOpt.put(key, "zone", event.getZone());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.killer.service.KillEventPool#removeKillEvent
	 * (com.iwgame.xengine.xtask.killer.model.KillEvent)
	 */
	@Override
	public void removeKillEvent(final KillEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.killer.service.KillEventPool#locateKillEvent
	 * (java.util.Date)
	 */
	@Override
	public List<KillEvent> locateKillEvent(final Date date) {

		List<KillEvent> events = new ArrayList<KillEvent>();

		SetOperations<String, String> setOpt = killerRedisTemplate.opsForSet();
		Set<String> killdates = setOpt.members(KILLDATE_KEY);

		long time = date.getTime();

		for (String killdate : killdates) {
			long killdateTime = Long.valueOf(killdate);
			if (time >= killdateTime) {
				String listKey = accountListKeyFormat.format(new Object[] { killdate });
				SortQuery<String> sortQuery = SortQueryBuilder.sort(listKey).get("#").get("account:*:info->days")
						.get("account:*:info->reason").get("account:*:info->content").get("account:*:info->mac")
						.get("account:*:info->title").get("account:*:info->zone").build();
				List<String> accounts = killerRedisTemplate.sort(sortQuery);
				for (int index = 0; index < accounts.size();) {
					KillEvent event = new KillEvent();
					event.setKillDate(new Date(killdateTime));
					event.setAccountName(accounts.get(index++));
					event.setDays(Integer.valueOf(accounts.get(index++)));
					event.setReason(accounts.get(index++));
					event.setContent(accounts.get(index++));
					event.setMac(accounts.get(index++));
					event.setTitle(accounts.get(index++));
					event.setZone(accounts.get(index++));
					events.add(event);
				}
				killerRedisTemplate.delete(listKey);
				setOpt.remove(KILLDATE_KEY, killdate);
			}
		}

		return events;
	}

}
