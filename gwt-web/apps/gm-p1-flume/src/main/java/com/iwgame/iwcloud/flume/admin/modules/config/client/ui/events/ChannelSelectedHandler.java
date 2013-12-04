/****************************************************************
 *  文件名     ： ChannelSelectedHandler.java
 *  日期         :  2012-8-22
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.modules.config.client.ui.events;

import com.google.gwt.event.shared.EventHandler;

/** 
 * @ClassName:    ChannelSelectedHandler 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-8-22上午10:31:57
 * @Version:      1.0 
 */
public interface ChannelSelectedHandler extends EventHandler {

	void buildChannelInfo(ChannelSelectedEvent event);
}
