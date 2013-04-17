/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： SheduleMgrPresenter.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.SheduleDataBase;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * 类说明
 * 
 * @简述：广告排期
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 上午09:57:36
 */
public interface SheduleMgrPresenter extends SchemaGridViewPresenter {

	public void getMediaType(int type, AsyncCallback<List<DropDownListData>> callback);

	public void getMedia(int type, AsyncCallback<List<DropDownListData>> callback);

	public void getPosition(String mediaId, AsyncCallback<List<DropDownListData>> callback);

	public void autoMaterialId(String query, AsyncCallback<List<DropDownListData>> callback);

	public void autoContractId(String query, AsyncCallback<List<DropDownListData>> callback);

	public void autoGroupId(String query, AsyncCallback<List<DropDownListData>> callback);

	public void addShedule(SheduleDataBase newDateBase, AsyncCallback<Integer> callback);

	public void checkSheduleId(String id, AsyncCallback<Boolean> callback);

	public void updateShedule(SheduleDataBase newDateBase, SheduleDataBase oldDateBase,
			AsyncCallback<Integer> callback);

	public void checkMaterialId(String id, AsyncCallback<String> callback);

	public void checkGroupId(String id, AsyncCallback<String> callback);

	public void checkContractId(String id, AsyncCallback<String> callback);
	
	public void autoAdUrl(String query, AsyncCallback<List<DropDownListData>> callback);

	public void checkAdUrl(String id, AsyncCallback<String> callback);
	
	public void getPosNameById(String id, AsyncCallback<String> callback);
	
	public void getUnitsIsNetBar(AsyncCallback<List<DropDownListData>> callback);
}
