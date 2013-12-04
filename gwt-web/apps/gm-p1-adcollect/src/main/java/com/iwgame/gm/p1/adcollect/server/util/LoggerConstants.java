/****************************************************************
 *  系统名称  ： '石器部落GM平台-[gm-wg1-player]'
 *  文件名    ： LoggerConstants.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.util;

/**
 * 类说明
 * 
 * @简述： 日志相关定义
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-14 上午09:16:24
 */
public class LoggerConstants {

	public static String ACTION_QUERYADINFO = "查询广告信息列表";
	public static String ACTION_INSERTADINFO = "增加广告信息列表";
	public static String ACTION_UPDATEADINFO = "修改广告信息列表";
	public static String ACTION_ADDLOG = "增加广告日志";
	public static String ACTION_GETLOG = "查询广告日志";
	public static String ACTION_GETMEDIA = "得到媒体列表";
	public static String ACTION_DELADINFO = "删除关键字";
	public static String ACTION_KEYS_EXPORT = "导出关键字";
	public static String ACTION_READCSVFILE = "解析csv文件";

	public static String ACTION_GETBLACKLISTRULES = "得到黑名单规则列表";
	public static String ACTION_UPDATESTATUS = "修改黑名单规则状态成功";
	public static String ACTION_GETACCOUNTLIST = "得到黑名单账户列表";
	public static String ACTION_GETBLACKMANAGELIST = "得到黑名单管理列表成功";
	public static String ACTION_INSERTBLACKMANAGE = "增加黑名单成功";

	public static String ACTION_ADMIN_MGR_ADD_MEDIA = "添加媒体";
	public static String ACTION_ADMIN_MGR_UPDATE_MEDIA = "修改媒体";
	public static String ACTION_ADMIN_MGR_ADD_CONTRACT = "添加合同";
	public static String ACTION_ADMIN_MGR_UPDATE_CONTRACT = "修改合同";
	public static String ACTION_ADMIN_MGR_ADD_POSTION = "添加加广告位";
	public static String ACTION_ADMIN_MGR_UPDATE_POSTION = "修改广告位";
	public static String ACTION_ADMIN_MGR_ADD_NETBAR_IP = "添加媒体对应ip";
	public static String ACTION_ADMIN_MGR_DEL_NETBAR_IP = "删除媒体对应ip";
	public static String ACTION_ADMIN_MGR_ADD_CONTRACT_PAYABLE = "添加合同抬头";
	public static String ACTION_ADMIN_MGR_UPDATE_CONTRACT_PAYABLE = "修改合同抬头";
	public static String ACTION_ADMIN_MGR_DEL_CONTRACT_PAYABLE = "删除合同抬头";

	public static String ACTION_ADMIN_MGR_DEL_CONTRACT_LOGO = "删除合同Logo";
	public static String ACTION_ADMIN_MGR_ADD_CONTRACT_LOGO = "添加合同Logo";
	public static String ACTION_ADMIN_MGR_UPDATE_CONTRACT_LOGO = "修改合同Logo";

	public static String ACTION_GETCUSTOMREPORTLIST= "查询定制报表列表";
	public static String ACTION_DELCUSTOMREPORT = "删除定制报表";
	public static String ACTION_ADDCUSTOMREPORT = "增加定制报表";
    
	public static String ACTION_CRLBATCHDEL = "批量删除黑名单列表";
	public static String ACTION_CRLACCOUNTBATCHINPUT = "批量导入黑名单账号";
	public static String ACTION_DELACCOUNT = "删除黑名单账号";

	// appUnitName
	public static String APPUNITNAME_KEYS = "Keys"; // 关键字
	public static String APPUNITNAME_CRL = "Crl"; // 黑名单
	public static String APPUNITNAME_ADMIN_MGR = "Admin"; // 硬广
	public static String APPUNITNAME_UPLOAD_POS= "UploadPos"; // 上传广告位
	public static String APPUNITNAME_UPLOAD_MATERIAL= "UploadMaterial"; // 上传素材
	public static String APPUNITNAME_UPLOAD_CONTRACT= "UploadContract"; // 上传广告位
	public static String APPUNITNAME_NETBAR = "Netbar"; // 关键字
	public static String APPUNITNAME_UPLOAD_IP = "UploadNetbarIp"; // 关键字
	public static String APPUNITNAME_ADMIN_MGR_EXPORT = "ExportData"; //
	public static String APPUNITNAME_FRAME_MGR = "Frame"; // 框架管理
	public static String APPUNITNAME_EXPLAIN_MGR = "Explain"; // 说明

	public static String ACTION_HARD_ADMIN_EXPORT_MEDIS = "导出媒体"; //导出硬广数据
	public static String ACTION_HARD_ADMIN_EXPORT_POS = "导出广告位"; //导出硬广数据
	public static String ACTION_HARD_ADMIN_EXPORT_SHEDULE = "导出广告排期"; //导出硬广数据
	
	// haedAdmin
	public static String APPUNITNAME_UPLOAD_IP_ADD_BATCH ="批量添加IP";
	public static String APPUNITNAME_UPLOAD_MATERIAL_ADD ="添加素材";
	public static String ACTION_HARD_ADMIN_GET_TYPE ="获取类型分类";
	public static String ACTION_HARD_ADMIN_GET_MATERIAL_LIST ="获取素材列表";
	public static String ACTION_HARD_ADMIN_GET_MEDIA_LIST ="获取媒体列表";
	public static String ACTION_HARD_ADMIN_GET_MEDIA ="获取硬广媒体列表";
	public static String ACTION_HARD_ADMIN_CHECK_GROUP_NAME ="检查组名";
	public static String ACTION_HARD_ADMIN_ADD_GROUP ="添加广告组";
	public static String ACTION_HARD_ADMIN_GET_GROUP_LIST ="获取广告组列表";
	public static String ACTION_HARD_ADMIN_GET_POS_LIST ="获取广告位列表";
	public static String ACTION_HARD_ADMIN_CHECK_POS_NAME ="检查广告位名";
	public static String ACTION_HARD_ADMIN_GET_PAYABLE_LIST ="获取合同抬头列表";
	public static String ACTION_HARD_ADMIN_GET_LOGO_LIST ="获取合同logo列表";
	public static String ACTION_HARD_ADMIN_GET_CONTRACT_LIST ="获取合同列表";
	public static String ACTION_HARD_ADMIN_GET_LOGO ="获取LOGO";
	public static String ACTION_HARD_ADMIN_GET_PAYABLE ="获取抬头";
	public static String ACTION_HARD_ADMIN_CHECK_ITEMNO ="验证合同编号";
	public static String ACTION_HARD_ADMIN_ADD_SHEDULE ="添加广告排期";
	public static String ACTION_HARD_ADMIN_GET_SHEDULE_LIST ="获取广告排期列表";
	public static String ACTION_HARD_ADMIN_UPDATE_SHEDULE="修改广告排期";
	public static String ACTION_HARD_ADMIN_CHECK_SHEDULEID="验证广告排期";
	public static String ACTION_HARD_ADMIN_CHECK_MATERIALID="验证素材";
	public static String ACTION_HARD_ADMIN_CHECK_GROUPID="验证广告组";
	public static String ACTION_HARD_ADMIN_CHECK_CONTRACTID ="验证合同";
	public static String ACTION_HARD_ADMIN_GET_POS_DORP ="获取广告位下拉列表";
	public static String ACTION_HARD_ADMIN_CHECK_AD_URL ="验证广告url";
	public static String ACTION_HARD_ADMIN_GET_NETBAR_IP_LIST ="获取网吧对应ip列表";
	public static String ACTION_HARD_ADMIN_GET_CONTACT_LIST_DORP ="合同编号下拉列表";
	public static String ACTION_HARD_ADMIN_GET_NETBAR_CLIENT_LIST ="获取客户端识别码列表";
	public static String ACTION_HARD_ADMIN_ADD_CLENT ="添加客户端识别码";
	public static String ACTION_HARD_ADMIN_UPDATE_CLENT ="修改客户端识别码";
	public static String ACTION_HARD_ADMIN_GET_UNITS_IS_NETBAR  ="获取网吧的售卖类型";
	
	public static String ACTION_HARD_FRAME_ADD_FRAME ="添加框架";
	public static String ACTION_HARD_FRAME_ADD_USEFRAME ="添加使用框架";
	public static String ACTION_HARD_FRAME_MGR_GET_LIST ="查询框架列表";
	public static String ACTION_HARD_FRAME_MGR_GET0_USE_LIST ="获取使用框架列表";
	public static String ACTION_HARD_FRAME_DATA_USE_EXPORT ="导出使用框架数据";
	public static String ACTION_HARD_FRAME_DATA_EXPORT ="导出框架数据";
	public static String ACTION_HARD_FRAME_UPDATE ="修改框架";
	public static String ACTION_HARD_FRAME_DEL ="删除框架";
	public static String ACTION_HARD_FRAME_DEL_USE ="删除使用框架";
	
	public static String APPUNITNAME_UPLOAD_DICTIONARY = "UploadDictionary"; // 关键字
	public static String APPUNITNAME_UPLOAD_DICTIONARY_EXPORT  = "数据字典上传"; // 关键字
	public static String ACTION_HARD_ADMIN_GET_DICTIONARY_LIST ="获取数据字典列表";
	public static String ACTION_HARD_ADMIN_GET_DICTIONARY_DEL ="删除数据字典";
	
	// appName
	public static String APPNAME = "ADCollect";
}
