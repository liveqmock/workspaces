/**      
 * AccountServiceAsync.java Create on 2012-10-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.modules.baidu.account.shared.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @ClassName: AccountServiceAsync
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-10-11 下午03:25:23
 * @Version 1.0
 * 
 */
public interface BaiduAccountServiceAsync {

	void getAccounts(String productId, AsyncCallback<String> callback);

	void modifyPassword(Integer id, String username, String token, String inputPassword, String oldPassword,
			String newPassword, String modifier, AsyncCallback<String> callback);

}
