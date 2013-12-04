/**      
* BizLoggerServiceImpl.java Create on 2012-11-26     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.common.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwgame.gm.p1.common.server.log.BusinessLogger;
import com.iwgame.gm.p1.security.common.shared.rpc.BizLoggerService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @简述: 操作日志记录服务实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-26 下午06:53:22 
 */
@Service
public class BizLoggerServiceImpl implements BizLoggerService{

	@Resource(name="businessLogger")
	private BusinessLogger logger ;
	
	
	@Override
	public String getLogs(BaseFilterPagingLoadConfig loadConfig)
			throws Exception {
		
		return logger.getLogs(loadConfig);
	}
	
}
