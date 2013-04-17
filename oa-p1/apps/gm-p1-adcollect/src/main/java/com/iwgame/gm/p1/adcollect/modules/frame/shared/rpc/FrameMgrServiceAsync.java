/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： FrameMgrServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.FrameDataBase;
import com.iwgame.gm.p1.adcollect.shared.model.UseFrameDataBase;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-13 下午2:09:03
 */
public interface FrameMgrServiceAsync {

	void addFrame(String productId, FrameDataBase newBase, AsyncCallback<Integer> callback);

	void getFrameList(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void checkFrameName(String productId, String name, AsyncCallback<Boolean> callback);

	void checkFrameTime(String productId, Map<String, Object> parameter, AsyncCallback<Integer> callback);

	void updateFrame(String productId, FrameDataBase newBase, AsyncCallback<Integer> callback);

	void delFrameByIds(String productId, String ids, AsyncCallback<Integer> callback);

	void getFrameName(String productId, String mediaId, AsyncCallback<List<DropDownListData>> callback);

	void addUseFrame(String productId, UseFrameDataBase newBase, AsyncCallback<Integer> callback);

	void getUseFrameList(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void delUseFrameByIds(String productId, String ids, AsyncCallback<Integer> callback);

}

