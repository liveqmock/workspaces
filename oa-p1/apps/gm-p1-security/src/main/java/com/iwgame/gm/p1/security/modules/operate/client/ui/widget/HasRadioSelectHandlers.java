/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： HasRadioSelectHandlers.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client.ui.widget;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-23 下午03:18:51
 */
public interface HasRadioSelectHandlers extends HasHandlers{

	HandlerRegistration addRadioSelectHandler(RadioSelectHandler handler);
}
