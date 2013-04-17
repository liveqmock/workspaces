/****************************************************************
 *  文件名     ： TimeScopeSelectorHandler.java
 *  日期         :  2012-10-23
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @ClassName: TimeScopeSelectorHandler
 * @Description: TODO(...)
 * @author: 吴君杰
 * @email: wujunjie@iwgame.com
 * @date: 2012-10-23下午12:26:55
 * @Version: 1.0
 */
public interface TimeScopeSelectorHandler extends EventHandler {
	
	void onSelected(TimeScopeSelectorEvent event);

}
