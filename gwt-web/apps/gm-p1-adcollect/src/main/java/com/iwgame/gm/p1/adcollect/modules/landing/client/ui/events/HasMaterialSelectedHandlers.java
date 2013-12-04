/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MaterialHasMenuSelectedHandlers.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.landing.client.ui.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-25 下午05:43:31
 */
public interface HasMaterialSelectedHandlers extends HasHandlers {

	HandlerRegistration addMaterialSelectedHandlers(MaterialSelectedHandler handler);
}
