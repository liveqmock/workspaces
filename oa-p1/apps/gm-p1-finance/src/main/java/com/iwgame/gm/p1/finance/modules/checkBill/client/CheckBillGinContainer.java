/****************************************************************
 *  系统名称  ： '[finance]'
 *  文件名    ： CheckBillGinContainer.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.finance.modules.checkBill.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

/**
 * 
 * @简述： 
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class CheckBillGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(CheckBillModule.class).to(CheckBillModuleImpl.class).in(Singleton.class);
	}

	public static class CheckBillModuleImpl implements CheckBillModule {

		private final CheckBillActivityMapper mapper;

		@Inject
		public CheckBillModuleImpl(final CheckBillActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "checkBill";
		}

		@Override
		public CheckBillActivityMapper getActivityMapper() {
			return mapper;
		}
	}
}
