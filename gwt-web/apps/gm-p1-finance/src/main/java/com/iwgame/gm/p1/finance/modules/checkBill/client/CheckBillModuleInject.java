/****************************************************************
 *  系统名称  ： '[finance]'
 *  文件名    ： CheckBillModuleInject.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.finance.modules.checkBill.client;

import com.google.gwt.inject.client.GinModules;
import com.iwgame.xmvp.client.ioc.XMVPAppGinContainer;
import com.iwgame.xmvp.client.module.XMVPModuleInject;

/**
 * 
 * @简述： 
 * @作者： Maven自动生成
 * @版本： 1.0
 */
@GinModules(value = { XMVPAppGinContainer.class, CheckBillGinContainer.class })
public interface CheckBillModuleInject extends XMVPModuleInject {
	
	CheckBillModule getModule();
}
