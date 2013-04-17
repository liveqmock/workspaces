/****************************************************************
 *  文件名     ： XhttpserviceGinContainer.java
 *  日期         :  2012-10-18
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

/** 
 * @ClassName:    XhttpserviceGinContainer 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-18下午02:53:34
 * @Version:      1.0 
 */
public class XhttpserviceGinContainer extends XMVPModuleGinContainer{

	@Override
	protected void configure() {
		super.configure();
		bind(XhttpserviceModule.class).to(XhttpserviceModuleImpl.class).in(Singleton.class);
	}
	
	public static class XhttpserviceModuleImpl implements XhttpserviceModule {

		private final XhttpserviceActivityMapper mapper;

		@Inject
		public XhttpserviceModuleImpl(final XhttpserviceActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "xhttpservice";
		}

		@Override
		public XhttpserviceActivityMapper getActivityMapper() {
			return mapper;
		}
	}
}
