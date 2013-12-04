/****************************************************************
 * 文件名 ： DataHandlerJob.java
 * 日期 : 2013-1-17
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.channel.data.handler.worker;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.iwgame.channel.data.handler.core.DataHandler;

/**
 * @类名: DataHandlerJob
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮件: wujunjie@iwgame.com
 * @日期: 2013-1-17下午5:15:56
 * @版本: 1.0
 */
public class DataHandlerJob {

	private final Logger logger = Logger.getLogger(DataHandlerJob.class);

	@Resource
	private DataHandler dataHandler;

	public void working() {
		long time = System.currentTimeMillis();
		dataHandler.handlerData();
		logger.info("耗时:" + (System.currentTimeMillis() - time) + "\n");
	}

}
