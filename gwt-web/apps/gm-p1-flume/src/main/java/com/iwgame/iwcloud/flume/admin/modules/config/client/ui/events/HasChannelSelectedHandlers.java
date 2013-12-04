/****************************************************************
 *  文件名     ： HasChannelSelectedHandlers.java
 *  日期         :  2012-8-22
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.modules.config.client.ui.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/** 
 * @ClassName:    HasChannelSelectedHandlers 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-8-22上午10:35:00
 * @Version:      1.0 
 */
public interface HasChannelSelectedHandlers extends HasHandlers {

	HandlerRegistration addChannelSelectedHandler(ChannelSelectedHandler handler);
}
