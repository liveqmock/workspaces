/****************************************************************
 *  系统名称  ： '业务日志监控-预警任务系统'
 *  文件名    ： SMSMessageCache.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.task.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类说明
 * @简述： 短信发送缓存信息
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-10-9 下午9:13:45
 */
public class SMSMessageCache {

	private static Map<String, Integer> msgHashMap = new ConcurrentHashMap<String, Integer>();
	
	public static void add2MsgHashMap(String id,int count) {
		msgHashMap.put(id, count);
	}
	
	public static int getCountById(String id) {
		try {
			if (msgHashMap.containsKey(id)) {
				return msgHashMap.get(id);
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static void clearMsgHashMap() {
		msgHashMap.clear();
	}
}
