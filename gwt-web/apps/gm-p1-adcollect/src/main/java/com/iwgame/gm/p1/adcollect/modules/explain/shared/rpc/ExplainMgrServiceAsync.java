/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ExplainMgrServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.explain.shared.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-20 下午3:00:46
 */
public interface ExplainMgrServiceAsync {

	void getDictionaryLIst(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void delDictionaryByIds(String productId, String ids, AsyncCallback<Integer> callback);

	void chenkUploadAuthority(AsyncCallback<Void> callback);

	void chenkDownLoadAuthority(AsyncCallback<Void> callback);

}

