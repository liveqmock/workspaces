/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MediaMgrPresenter.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-26 下午05:03:42
 */
public interface MediaMgrPresenter extends SchemaGridViewPresenter {

	public void getType(int type, AsyncCallback<List<DropDownListData>> callback);

	public void addMedia(Map<String, Object> parameter, AsyncCallback<Integer> callback);

	public void updateMedia(Map<String, Object> parameter, Map<String, Object> oldBase, AsyncCallback<Integer> callback);
}
