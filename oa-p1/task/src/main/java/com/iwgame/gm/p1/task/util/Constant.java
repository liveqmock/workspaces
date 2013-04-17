/****************************************************************
 *  系统名称  ： '广告系统任务处理'
 *  文件名    ： Constant.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.util;

/**
 * 类说明
 * 
 * @简述： 常量定义
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-4-18 上午11:41:48
 */
public class Constant {

	// 上次发送短信时间戳
	public static long lastSendSMSTimeStamp = 0;

	// 日志handler
	public static final String LOG_XPORTAL_TASK = "com.iwgame.gm.p1.task.xportaltask";
	public static final String LOG_IP_TRANSLATE = "com.iwgame.gm.p1.task.iptranslate";
	public static final String LOG_CUSTOM_REPORT = "com.iwgame.gm.p1.task.customreport";

	// 安全
	public static final String LOG_OA_SECURITY = "oasecurity";
	public static final String LOG_SAFE_MODEL = "safemodel";
}
