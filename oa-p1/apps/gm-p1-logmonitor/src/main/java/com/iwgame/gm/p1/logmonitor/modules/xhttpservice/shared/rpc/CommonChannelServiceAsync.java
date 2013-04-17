/****************************************************************
 *  文件名     ： CommonChannelServiceAsync.java
 *  日期         :  2012-10-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.AppSource;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.BizDict;
import com.iwgame.ui.core.client.data.PagingLoadConfig;

/** 
 * @ClassName:    CommonChannelServiceAsync 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-19上午11:07:06
 * @Version:      1.0 
 */
public interface CommonChannelServiceAsync {

	void retrieveAppSource(AsyncCallback<List<AppSource>> callback);

	void retrieveBizDict(AsyncCallback<List<BizDict>> callback);

	void getAllLogData(String prodouctid,PagingLoadConfig loadConfig,AsyncCallback<String> callback);

	void getLogreport(String prodouctid, PagingLoadConfig loadConfig, AsyncCallback<String> callback);
	
}
