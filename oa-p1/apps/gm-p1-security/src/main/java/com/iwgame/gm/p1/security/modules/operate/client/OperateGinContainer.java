/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： OperateGinContainer.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.gm.p1.security.modules.operate.client.OperateActivityMapper;
import com.iwgame.gm.p1.security.modules.operate.client.OperateModule;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

/**
 * 
 * @简述： 
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class OperateGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(OperateModule.class).to(OperateModuleImpl.class).in(Singleton.class);
	}

	public static class OperateModuleImpl implements OperateModule {

		private final OperateActivityMapper mapper;

		@Inject
		public OperateModuleImpl(final OperateActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "operate";
		}

		@Override
		public OperateActivityMapper getActivityMapper() {
			return mapper;
		}
	}
}
