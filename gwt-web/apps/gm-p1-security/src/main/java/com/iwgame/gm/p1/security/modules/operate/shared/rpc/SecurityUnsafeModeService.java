/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityUnsafeModeService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.shared.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.SafeModeBean;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午11:00:09
 */
public interface SecurityUnsafeModeService extends RemoteService{

	public String insert(SafeModeBean bean,String operator)throws Exception;
	public static class Util{
		private static SecurityUnsafeModeServiceAsync instance;
		
		public static SecurityUnsafeModeServiceAsync get(){
			if(instance==null){
				instance = GWT.create(SecurityUnsafeModeService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
