/****************************************************************
 *  文件名     ： HasTimeScopeSelectorHandler.java
 *  日期         :  2012-10-23
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/** 
 * @类名:   HasTimeScopeSelectorHandler 
 * @描述:  	TODO(...) 
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-23下午12:32:44
 * @版本:   1.0 
 */
public interface HasTimeScopeSelectorHandler extends HasHandlers{
	
	HandlerRegistration addTimeScopeSelectorHandler(TimeScopeSelectorHandler handler);

}
