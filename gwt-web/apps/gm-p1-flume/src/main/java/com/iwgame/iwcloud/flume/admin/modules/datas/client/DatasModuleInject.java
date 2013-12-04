package com.iwgame.iwcloud.flume.admin.modules.datas.client;

import com.google.gwt.inject.client.GinModules;
import com.iwgame.xmvp.client.ioc.XMVPAppGinContainer;
import com.iwgame.xmvp.client.module.XMVPModuleInject;

@GinModules(value = { XMVPAppGinContainer.class,DatasGinContainer.class})
public interface DatasModuleInject extends XMVPModuleInject {
	DatasModule getModule();
}