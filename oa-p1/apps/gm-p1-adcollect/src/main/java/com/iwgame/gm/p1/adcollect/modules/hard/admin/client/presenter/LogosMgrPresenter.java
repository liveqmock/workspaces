/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LogosMgrPresenter.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * 类说明
 * 
 * @简述： LOGOS
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午03:04:15
 */
public interface LogosMgrPresenter extends SchemaGridViewPresenter {

	void deleteLogos(final int id, final AsyncCallback<Integer> callback);
}
