/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityUnlockAccountService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.shared.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.LockAccountBean;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-15 下午04:28:17
 */
public interface SecurityUnlockAccountService extends RemoteService{

	public String insert(LockAccountBean bean,String operator)throws Exception;
	
	public static class Util{
		private static SecurityUnlockAccountServiceAsync instance;
		
		public static SecurityUnlockAccountServiceAsync get(){
			if(instance==null){
				instance = GWT.create(SecurityUnlockAccountService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
