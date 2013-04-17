/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： KillMgrGinContainer.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 2.1
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-2 下午05:52:41
 */
public class FrameGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(FrameModule.class).to(KeysModuleImpl.class).in(Singleton.class);
	}

	public static class KeysModuleImpl implements FrameModule {

		private final FrameActivityMapper mapper;

		@Inject
		public KeysModuleImpl(final FrameActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "frame";
		}

		@Override
		public FrameActivityMapper getActivityMapper() {
			return mapper;
		}
	}
}
