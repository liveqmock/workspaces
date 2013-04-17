package com.iwgame.iwcloud.flume.admin.modules.monitor.client;

import com.iwgame.xmvp.client.module.XMVPModule;

public interface MonitorModule extends XMVPModule {
	
	public MonitorActivityMapper getActivityMapper();
	
}
