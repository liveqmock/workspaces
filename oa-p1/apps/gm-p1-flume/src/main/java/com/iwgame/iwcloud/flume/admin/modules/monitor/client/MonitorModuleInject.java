package com.iwgame.iwcloud.flume.admin.modules.monitor.client;

import com.google.gwt.inject.client.GinModules;
import com.iwgame.xmvp.client.ioc.XMVPAppGinContainer;
import com.iwgame.xmvp.client.module.XMVPModuleInject;

@GinModules(value = { XMVPAppGinContainer.class,MonitorGinContainer.class})
public interface MonitorModuleInject extends XMVPModuleInject {
	MonitorModule getModule();
}