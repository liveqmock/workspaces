/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： AdminMgrServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.SheduleDataBase;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * 类说明
 * 
 * @简述： 硬广的回调函数
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-24 下午05:22:12
 */
public interface AdminMgrServiceAsync {

    void getType(String productId, int type,
	    AsyncCallback<List<DropDownListData>> callback);

    void getMaterialList(String productId,
	    BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void getMediaList(String productId, BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void addMedia(String productId, Map<String, Object> parameter,
	    AsyncCallback<Integer> callback);

    void updateMedia(String productId, Map<String, Object> parameter,
	    Map<String, Object> oldBase, AsyncCallback<Integer> callback);

    void getMedia(String productId, int type,
	    AsyncCallback<List<DropDownListData>> callback);

    void checkGroupName(String productId, String name,
	    AsyncCallback<Boolean> callback);

    void addGroup(String productId, String name, String mediaId,
	    AsyncCallback<Integer> callback);

    void getGroupList(String productId, BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void getPositionList(String productId,
	    BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void getLog(BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void checkPositionName(String productId, String name,
	    AsyncCallback<Boolean> callback);

    void addPayable(String productId, String title,
	    AsyncCallback<Integer> callback);

    void getPayableList(String productId,
	    BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void updatePayable(String productId, int id, String title,
	    AsyncCallback<Integer> callback);

    void deletePayable(String productId, int id, AsyncCallback<Integer> callback);

    void getLogosList(String productId, BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void deleteLogos(String productId, int id, AsyncCallback<Integer> callback);

    void getContractList(String productId,
	    BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void getLogoList(String productId,
	    AsyncCallback<List<DropDownListData>> callback);

    void getPayableList(String productId,
	    AsyncCallback<List<DropDownListData>> callback);

    void checkItemno(String productId, String itemno,
	    AsyncCallback<Boolean> callback);

    void getPositionListDorp(String productId, String mediaId,
	    AsyncCallback<List<DropDownListData>> callback);

    void getContactListDorp(String productId, String mediaId,
    		AsyncCallback<List<DropDownListData>> callback);

    void autoMaterialId(String productId, String query,
	    AsyncCallback<List<DropDownListData>> callback);

    void autoContractIdAndName(String productId, String query,
	    AsyncCallback<List<DropDownListData>> callback);

    void autoGroupIdAndName(String productId, String query,
	    AsyncCallback<List<DropDownListData>> callback);

    void addShedule(String productId, SheduleDataBase newDateBase,
	    AsyncCallback<Integer> callback);

    void getSheduleList(String productId,
	    BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void updateShedule(String productId, SheduleDataBase newDateBase,
	    SheduleDataBase oldDateBase, AsyncCallback<Integer> callback);

    void checkSheduleId(String productId, String id,
	    AsyncCallback<Boolean> callback);

    void checkContractId(String productId, String id,
	    AsyncCallback<String> callback);

    void checkGroupId(String productId, String id,
	    AsyncCallback<String> callback);

    void checkMaterialId(String productId, String id,
	    AsyncCallback<String> callback);

    void getAutoAdUrlListDorp(String productId, String query,
	    AsyncCallback<List<DropDownListData>> callback);

    void checkAdUrl(String productId, String id, AsyncCallback<String> callback);

    void chenkAddPosAuthority(AsyncCallback<Void> callback);

    void chenkUpdatePosAuthority(AsyncCallback<Void> callback);

    void chenkAddMaterialAuthority(AsyncCallback<Void> callback);

    void chenkAddContractAuthority(AsyncCallback<Void> callback);

    void chenkUpdateContractAuthority(AsyncCallback<Void> callback);

    void getPosNameById(String productId, String id,
	    AsyncCallback<String> callback);

    void getNetbarIpList(String productId,
	    BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void addNetbarIp(String productId, String mediaId, String ip,
	    AsyncCallback<Integer> callback);

    void delNetbarIps(String productId, String ids,
	    AsyncCallback<Integer> callback);

    void getNetbarClientList(String productId,
	    BaseFilterPagingLoadConfig loadConfig,
	    AsyncCallback<String> callback);

    void addClent(String productId, String mediaId, String version,
	    String code, AsyncCallback<Integer> callback);

    void updateClent(String productId, String mediaId, String version,
	    String code, int id, AsyncCallback<Integer> callback);

    void chenkbatchAddNetbarIpAuthority(AsyncCallback<Void> callback);

    void getUnitsIsNetBar(String productId,
	    AsyncCallback<List<DropDownListData>> callback);

}
