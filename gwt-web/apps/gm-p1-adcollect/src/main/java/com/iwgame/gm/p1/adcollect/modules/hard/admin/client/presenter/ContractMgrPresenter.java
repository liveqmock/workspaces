/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ContractMgrPresenter.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter;

import java.util.List;

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
 * @创建时间：2012-10-10 下午05:21:45
 */
public interface ContractMgrPresenter extends SchemaGridViewPresenter {

	public void getMediaType(int type, AsyncCallback<List<DropDownListData>> callback);

	public void getMedia(int type, AsyncCallback<List<DropDownListData>> callback);

	public void getlogoList(AsyncCallback<List<DropDownListData>> callback);

	public void getPayableList(AsyncCallback<List<DropDownListData>> callback);

	public void checkItemno(String itemno, AsyncCallback<Boolean> callback);
	
	public void chenkAddContractAuthority(AsyncCallback<Void> callback);

	public void chenkUpdateContractAuthority(AsyncCallback<Void> callback);
}
