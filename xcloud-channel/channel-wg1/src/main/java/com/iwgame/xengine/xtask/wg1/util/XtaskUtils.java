/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： XtaskUtils.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.util;

import net.sf.json.JSONObject;

import com.jjl.sq.webservice.impl.GameResponse;

/**
 * 类说明
 * @简述： Xtask工具类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午06:07:30
 */
public class XtaskUtils {

	/**
	 * 对象转换
	 * 
	 * @param object
	 * @return
	 */
	public static GameResponse object2GameResponse(Object[] object) {
		GameResponse gameResponse = null;
		try {
			if (object != null) {
				gameResponse = new GameResponse();
				gameResponse = (GameResponse) object[0];
			}
		} catch (Exception e) {
			gameResponse = null;
		}
		return gameResponse;
	}
	
	public static int object2int(Object object) {
		try {
			return Integer.valueOf(object.toString());
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String getJsonString(JSONObject jsonObject ,String key) {
		try {
			return jsonObject.getString(key);
		} catch (Exception e) {
			return "";
		}
	}
	
	
}
