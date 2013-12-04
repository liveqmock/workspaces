/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityUnsafeModeServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.shared.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.SafeModeBean;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午11:01:07
 */
public interface SecurityUnsafeModeServiceAsync {

	public void insert(SafeModeBean bean,String operator,AsyncCallback<String> callback);
}
