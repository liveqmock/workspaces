package com.iwgame.iwcloud.flume.admin.modules.history.client;

import com.google.gwt.inject.client.GinModules;
import com.iwgame.xmvp.client.ioc.XMVPAppGinContainer;
import com.iwgame.xmvp.client.module.XMVPModuleInject;

@GinModules(value = { XMVPAppGinContainer.class,HistoryGinContainer.class})
public interface HistoryModuleInject extends XMVPModuleInject {
	HistoryModule getModule();
}