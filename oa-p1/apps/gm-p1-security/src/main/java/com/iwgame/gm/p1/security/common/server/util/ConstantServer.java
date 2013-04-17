/**      
* KilledCause.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/
package com.iwgame.gm.p1.security.common.server.util;
/** 
 * @简述: 公用常量类
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 */
public class ConstantServer {
	// 需要改动 配置数据源
	public static final String TARGET_SUBFIX = "-security";
	
	public static final String PREFIX_SOLR="-solr";
	/**
	 * 封杀原因配置
	 */
	public static final String SQL_MAPPER_IW_RESULT_TRACK_DAO = "security.IwResultTrack.";
	
	public static final String SQL_MAPPER_KILLED_RECORD_DAO = "security.KilledRecord.";
	
	public static final String SQL_MAPPER_SAFE_MODE_RECORD_DAO = "security.SafeModeRecord.";
	
	public static final String SQL_MAPPER_KILLED_CAUSE_DAO = "security.KilledCause.";
	
	public static final String SQL_MAPPER_SAFE_MODE_CAUSE_DAO = "security.SafeModeCauseMap.";
	
	public static final String SQL_MAPPER_DANGER_ID_CARD_DAO ="security.DangerIdCardMap.";
	
	public static final String SQL_MAPPER_LOGIN_PASS_MODIFY_RECORD_DAO="security.LoginPassModifyMap.";

	/****************************
	 * SQL ID
	 ****************************/
	public static final String SQL_ID_BASE_SELECT = "select";
	public static final String SQL_ID_BASE_SELECT_COUNT = "selectCount";
	public static final String SQL_ID_BASE_UPDATE = "update";
	public static final String SQL_ID_BASE_INSERT = "insert";
	public static final String SQL_ID_BASE_DELETE = "delete";
	public static final String SQL_ID_BASE_COUNT = "count";
	
	/**
	 * 醉逍遥产品ID
	 * */
	public static final String PRODUCT_ID_ZXY = "P-P1.5";
	
	/**
	 * 获取角色精确查询地址
	 * @param productId
	 * @param dbid
	 * @return
	 * String
	 */
	public static String getRoleBydbidSolrURL(String productId,String dbid) {
		return "/xquery/service/role/"+productId+"/"+dbid+"/getRoleBydbid";
	}
	
	/**
	 * 获取角色模糊查询地址
	 * @param productId
	 * @param dbid
	 * @return
	 * String
	 */
	public static String getRoleByParamSolrURL(String productId) {
		return "/xquery/service/role/"+productId+"/getRoles";
	}
	
	/**
	 * 获取账号模糊查询地址
	 * @param productId
	 * @param dbid
	 * @return
	 * String
	 */
	public static String getAccountByParamSolrURL(String productId) {
		return "/xquery/service/account/"+productId+"/getAccounts";
		//return "/service/account/"+productId+"/getAccounts";
	}
	
	/**
	 * 获取账号精确查询地址
	 * @param productId
	 * @param dbid
	 * @return
	 * String
	 */
	public static String getAccountByAccountIdSolrURL(String productId,String accountId) {
		return "/xquery/service/account/"+productId+"/"+accountId+"/getAccountByAccountId";
	}
}
