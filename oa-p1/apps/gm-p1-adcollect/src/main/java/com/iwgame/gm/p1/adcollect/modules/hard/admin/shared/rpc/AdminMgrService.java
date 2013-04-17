/****************************************************************
// *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： AdminMgrService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.SheduleDataBase;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * 类说明
 * 
 * @简述： 硬广的服务接口
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-24 下午05:22:49
 */
public interface AdminMgrService extends RemoteService {

	/**
	 * 类型分类
	 * 
	 * @param productId
	 * @param type
	 * <br/>
	 *            1: 为媒体分类，2：媒体类型，3：素材分类，4：排期类型，5：售卖单位，6：广告位类型
	 * @param callback
	 */
	List<DropDownListData> getType(String productId, int type) throws AccessDeniedException;

	/**
	 * 素材列表
	 */
	String getMaterialList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/**
	 * 媒体列表
	 */
	String getMediaList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/**
	 * 添加媒体
	 * 
	 * @param parameter
	 * @return
	 */
	int addMedia(String productId, Map<String, Object> parameter) throws AccessDeniedException;

	/**
	 * 修改媒体
	 * 
	 * @param parameter
	 *            修改目标
	 * @param oldBase
	 *            修改以前的
	 * @return
	 */
	int updateMedia(String productId, Map<String, Object> parameter, Map<String, Object> oldBase) throws AccessDeniedException;

	/**
	 * 硬广媒体
	 * 
	 * @param callback
	 */
	List<DropDownListData> getMedia(String productId, int type) throws AccessDeniedException;
	/**
	 * 网吧的售卖类型
	 * 
	 * @param callback
	 */
	List<DropDownListData> getUnitsIsNetBar(String productId) throws AccessDeniedException;

	/** 检查组名 */
	boolean checkGroupName(String productId, String name) throws AccessDeniedException;

	/** 添加组 */
	int addGroup(String productId, String name, String mediaId) throws AccessDeniedException;

	/**
	 * 广告组列表
	 */
	String getGroupList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/**
	 * 广告位列表
	 */
	String getPositionList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/** 检查广告位的名字 */
	Boolean checkPositionName(String productId, String name) throws AccessDeniedException;

	/**
	 * 获取日志
	 * 
	 * @param loadConfig
	 * @return
	 * @throws AccessDeniedException
	 */
	public String getLog(BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/**
	 * 新增合同抬头
	 */
	public Integer addPayable(String productId, String title) throws AccessDeniedException;

	/**
	 * 合同抬头列表
	 */
	String getPayableList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/**
	 * 修改合同抬头
	 */
	public Integer updatePayable(String productId, int id, String title) throws AccessDeniedException;

	/**
	 * 删除合同抬头
	 */
	public Integer deletePayable(String productId, int id) throws AccessDeniedException;

	/**
	 * LOGOS列表
	 */
	String getLogosList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/**
	 * 删除LOGOS
	 */
	int deleteLogos(String productId, int id) throws AccessDeniedException;

	/**
	 * LOGOS列表
	 */
	String getContractList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/** Logo List */
	List<DropDownListData> getLogoList(String productId) throws AccessDeniedException;

	/** Payable List */
	List<DropDownListData> getPayableList(String productId) throws AccessDeniedException;

	/** 检查合同编号 */
	Boolean checkItemno(String productId, String itemno) throws AccessDeniedException;

	/**
	 * 广告位下拉框的list
	 */
	List<DropDownListData> getPositionListDorp(String productId, String mediaId) throws AccessDeniedException;

	/**
	 * 合同编号下拉框的list
	 */
	List<DropDownListData> getContactListDorp(String productId, String mediaId) throws AccessDeniedException;

	/**
	 * 自动补全素材ID
	 */
	List<DropDownListData> autoMaterialId(String productId, String query) throws AccessDeniedException;

	/**
	 * 自动补全合同
	 */
	List<DropDownListData> autoContractIdAndName(String productId, String query) throws AccessDeniedException;

	/**
	 * 自动补全广告组
	 */
	List<DropDownListData> autoGroupIdAndName(String productId, String query) throws AccessDeniedException;

	/**
	 * 添加广告排期
	 */
	int addShedule(final String productId, final SheduleDataBase newDateBase) throws AccessDeniedException;

	/**
	 * 排期列表
	 */
	String getSheduleList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/**
	 * 添加广告排期
	 */
	int updateShedule(final String productId, final SheduleDataBase newDateBase, final SheduleDataBase oldDateBase) throws AccessDeniedException;

	/**
	 * 验证排期码
	 */
	boolean checkSheduleId(String productId, String id) throws AccessDeniedException;

	/**
	 * 验证素材
	 */
	String checkMaterialId(String productId, String id) throws AccessDeniedException;

	/**
	 * 验证广告组
	 */
	String checkGroupId(String productId, String id) throws AccessDeniedException;

	/**
	 * 验证合同
	 */
	String checkContractId(String productId, String id) throws AccessDeniedException;

	/**
	 * 自动补全广告URL
	 */
	List<DropDownListData> getAutoAdUrlListDorp(String productId, String query) throws AccessDeniedException;

	/**
	 * 验证广告URL
	 */
	String checkAdUrl(String productId, String id) throws AccessDeniedException;

	/**
	 * 验证广告位添加权限
	 */
	void chenkAddPosAuthority() throws AccessDeniedException;

	/**
	 * 验证广告位修改权限
	 */
	void chenkUpdatePosAuthority() throws AccessDeniedException;

	/**
	 * 验证素材添加权限
	 */
	void chenkAddMaterialAuthority() throws AccessDeniedException;

	/**
	 * 验证合同添加权限
	 */
	void chenkAddContractAuthority() throws AccessDeniedException;
	
	/**
	 * 验证合同修改权限
	 */
	void chenkUpdateContractAuthority() throws AccessDeniedException;
	
	/**
	 * 根据广告位的ID获取类型名
	 */
	String getPosNameById(String productId, String id) throws AccessDeniedException;
	
	/**
	 * 获取网吧对应ip列表
	 */
	String getNetbarIpList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/**
	 * 添加网吧对应ip
	 */
	int addNetbarIp(String productId, String mediaId, String ip) throws AccessDeniedException;
	
	/**
	 * 删除网吧对应ip
	 */
	int delNetbarIps(String productId, String ids) throws AccessDeniedException;
	
	/**
	 * 客户端识别码列表
	 */
	String getNetbarClientList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;
	
	/**
	 * 添加客户端识别码
	 */
	int addClent(String productId, String mediaId, String version, String code) throws AccessDeniedException;

	/**
	 * 修改客户端识别码
	 */
	int updateClent(String productId, String mediaId, String version, String code, int id) throws AccessDeniedException;
	
	/**
	 * 验证批量添加网吧IP
	 */
	void chenkbatchAddNetbarIpAuthority() throws AccessDeniedException;
	
	public static class Util {
		private static AdminMgrServiceAsync instance;

		public static AdminMgrServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(AdminMgrService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
