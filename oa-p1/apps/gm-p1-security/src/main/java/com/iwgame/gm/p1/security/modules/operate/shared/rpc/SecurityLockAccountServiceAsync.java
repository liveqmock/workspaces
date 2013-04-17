/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityLockAccountServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.LockAccountBean;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-15 上午10:16:51
 */
public interface SecurityLockAccountServiceAsync {

	public void getKilledCausesByCauseType(String productId,Integer causeType,AsyncCallback<List<KilledCauseConfig>> callback);
	public void insert(LockAccountBean bean,String operator,AsyncCallback<String> callback);
	void getKilledCausesByCauseTypeAndKilledType(String productId,
			Integer causeType, Integer killedType,
			AsyncCallback<List<KilledCauseConfig>> callback);
	
	void getGlobalResource(AsyncCallback<Map<String, Object>> callback);
}
