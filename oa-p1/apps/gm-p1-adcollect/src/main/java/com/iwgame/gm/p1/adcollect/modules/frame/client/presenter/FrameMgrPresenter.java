/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： FrameMarPresenter.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.client.presenter;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.FrameDataBase;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * @Description: 框架管理接口类
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-13 下午12:31:08
 */
public interface FrameMgrPresenter extends SchemaGridViewPresenter {

	public void getMediaType(int type, AsyncCallback<List<DropDownListData>> callback);

	public void getMedia(int type, AsyncCallback<List<DropDownListData>> callback);

	public void addFrame(FrameDataBase newBase, AsyncCallback<Integer> callback);

	public void updateFrame(FrameDataBase newBase, AsyncCallback<Integer> callback);

	public void checkFrameName(String name, AsyncCallback<Boolean> callback);

	public void checkFrameTime(Map<String, Object> parameter, AsyncCallback<Integer> callback);

	public void delFrameByIds(String ids, AsyncCallback<Integer> callback);
}
