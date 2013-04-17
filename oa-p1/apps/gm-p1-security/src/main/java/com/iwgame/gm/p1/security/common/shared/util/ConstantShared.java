/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： Constant.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.util;
/**
 * 类说明
 * @简述：常量类定义
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-20 上午09:38:33
 */
public class ConstantShared {

	/**
	 * 成功事件
	 */
	public final static String EVENT_ALL_SUCCESS = "10001";
	/**
	 * 失败事件
	 */
	public final static String EVENT_ALL_FAILURE = "10002";
	/**
	 * 系统内部错误事件
	 */
	public final static String EVENT_SYSTEM_INNER_ERROR = "10003";

	/**
	 * 重复事件
	 */
	public final static String EVENT_CUSTOM_DUPLICATE = "10110";
	/**
	 * 参数错误事件
	 */
	public final static String EVENT_PARAMETER_ERROR = "10122";
	/**
	 * 验证码错误事件
	 */
	public final static String EVENT_AUTHCODE_ERROR = "10132";

	/**
	 * 未登录事件
	 */
	public final static String EVENT_CUSTOM_UNLOGIN = "10142";
	/**
	 * 不存在事件
	 */
	public final static String EVENT_CUSTOM_NO_EXIST = "10170";
	
	
	/**
	 * model对应的数据库表名,记录操作日志调用
	 */
	public final static String TABLE_DANGER_ID_CARD="danger_id_card";
	
	public final static String TABLE_KILLED_CAUSE_CONFIG="killed_cause_config";
	
	public final static String TABLE_SAFE_MODE_CAUSE_CONFIG="safe_mode_cause_config";
	
	public static String getIpQueryUrl(){
		return "http://www.ip138.com/ips138.asp?ip=$ip";
	}
	
	public static String getIdcardQueryUrl(){
		return "http://qq.ip138.com/idsearch/index.asp?action=idcard&userid=$idcard";
	}
}
