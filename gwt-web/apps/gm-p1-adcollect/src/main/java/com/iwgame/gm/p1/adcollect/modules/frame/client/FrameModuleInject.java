/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ForceOutMgrModuleImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.client;

import com.google.gwt.inject.client.GinModules;
import com.iwgame.xmvp.client.ioc.XMVPAppGinContainer;
import com.iwgame.xmvp.client.module.XMVPModuleInject;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 2.1
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-2 下午05:55:38
 */
@GinModules(value = { XMVPAppGinContainer.class, FrameGinContainer.class })
public interface FrameModuleInject extends XMVPModuleInject {

	@Override
	FrameModule getModule();
}
