package com.iwgame.iwcloud.flume.admin.modules.history.client;

import com.iwgame.xmvp.client.module.XMVPModule;

public interface HistoryModule extends XMVPModule {
	
	public HistoryActivityMapper getActivityMapper();
	
}
