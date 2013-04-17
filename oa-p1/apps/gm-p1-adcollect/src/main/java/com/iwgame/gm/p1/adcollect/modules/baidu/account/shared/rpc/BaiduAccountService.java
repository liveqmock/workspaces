/**      
 * AccountService.java Create on 2012-10-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.modules.baidu.account.shared.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @ClassName: BaiduAccountService
 * @Description: 百度帐号服务
 * @author 吴江晖
 * @date 2012-10-11 下午03:25:17
 * @Version 1.0
 * 
 */
public interface BaiduAccountService extends RemoteService {

	/**
	 * 获取不同产品的百度帐号列表
	 * 
	 * @param productId
	 *            产品ID
	 */
	String getAccounts(String productId);

	/**
	 * 修改百度帐号
	 * 
	 * @param id
	 *            ID
	 * @param username
	 *            帐号名
	 * @param token
	 *            TOKEN令牌
	 * @param inputPassword
	 *            输入密码
	 * @param oldPassword
	 *            原密码
	 * @param newPassword
	 *            新密码
	 * @param modifier
	 *            修改人
	 */
	String modifyPassword(Integer id, String username, String token,
			String inputPassword, String oldPassword, String newPassword,
			String modifier);

	public static class Util {
		private static BaiduAccountServiceAsync instance;

		public static BaiduAccountServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(BaiduAccountService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
