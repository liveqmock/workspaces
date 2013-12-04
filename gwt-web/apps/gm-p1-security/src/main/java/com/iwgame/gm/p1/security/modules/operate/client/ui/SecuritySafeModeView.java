/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecuritySafeModeView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.SafeModeBean;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * 类说明
 * @简述： 添加安全模式UI接口
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午09:39:54
 */
public interface SecuritySafeModeView extends IsWidget{

	public interface SecuritySafeModePresenter extends SchemaGridViewPresenter{
		public void onClickSubmit(SafeModeBean bean,String operator);
	}
	
	public void doClickSubmit(SafeModeBean bean,String operator);
}
