/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： UseMgrPresenter.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.client.presenter;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.UseFrameDataBase;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * @Description: 使用框架接口
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-17 下午12:04:54
 */
public interface UseMgrPresenter extends SchemaGridViewPresenter {

	public void getMediaType(int type, AsyncCallback<List<DropDownListData>> callback);

	public void getMedia(int type, AsyncCallback<List<DropDownListData>> callback);

	public void delUseFrameByIds(String ids, AsyncCallback<Integer> callback);
	
	public void getFrameName(String mediaId, AsyncCallback<List<DropDownListData>> callback);
	
	public void checkContractId(String id, AsyncCallback<String> callback);
	
	public void autoContractId(String query, AsyncCallback<List<DropDownListData>> callback);

	public void addUseFrame(UseFrameDataBase useframe, AsyncCallback<Integer> callback);
}

