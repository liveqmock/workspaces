package com.iwgame.iwcloud.flume.admin.modules.config.client;

import com.google.gwt.inject.client.GinModules;
import com.iwgame.xmvp.client.ioc.XMVPAppGinContainer;
import com.iwgame.xmvp.client.module.XMVPModuleInject;

@GinModules(value = { XMVPAppGinContainer.class,ConfigGinContainer.class})
public interface ConfigModuleInject extends XMVPModuleInject {
	ConfigModule getModule();
}