/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： DictionaryMgrPresenter.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.explain.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * @Description: 数据字典接口类
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-20 上午10:15:59
 */
public interface DictionaryMgrPresenter  extends SchemaGridViewPresenter {

	public void delDictionaryByIds(String ids, AsyncCallback<Integer> callback);
	
	void chenkUploadAuthority(AsyncCallback<Void> callback);

	void chenkDownLoadAuthority(AsyncCallback<Void> callback);
}

