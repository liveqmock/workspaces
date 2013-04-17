/**      
 * UpdateCRLTask.java Create on 2012-5-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.worker.service;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.cps.service.XtaskService;

/**
 * @ClassName: UpdateCRLTask
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-11 下午03:43:58
 * @Version 1.0
 * 
 */
public class ClickCountTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(ClickCountTask.class);

	private XtaskService xtaskService;

	@Autowired
	public void setCpsTaskService(final XtaskService xtaskService) {
		this.xtaskService = xtaskService;
	}

	public void handleMessage(final String text) {
		logger.info("从MQ中取得一条CPS点击数消息:" + text);
		JSONObject json = JSONObject.fromObject(text);
		xtaskService.countClick(json.getString("gameId"),
				json.getInt("mediaId"), json.getInt("linkId"),
				json.getLong("atime"));
	}
}
