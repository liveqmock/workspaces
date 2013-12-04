/****************************************************************
 *  文件名     ： XhttpserviceModuleInject.java
 *  日期         :  2012-10-18
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client;

import com.google.gwt.inject.client.GinModules;
import com.iwgame.xmvp.client.ioc.XMVPAppGinContainer;
import com.iwgame.xmvp.client.module.XMVPModuleInject;

/**
 * @ClassName: XhttpserviceModuleInject
 * @Description: TODO(...)
 * @author: 吴君杰
 * @email: wujunjie@iwgame.com
 * @date: 2012-10-18下午02:52:15
 * @Version: 1.0
 */
@GinModules(value = { XMVPAppGinContainer.class, XhttpserviceGinContainer.class })
public interface XhttpserviceModuleInject extends XMVPModuleInject {
	@Override
	XhttpserviceModule getModule();
}
