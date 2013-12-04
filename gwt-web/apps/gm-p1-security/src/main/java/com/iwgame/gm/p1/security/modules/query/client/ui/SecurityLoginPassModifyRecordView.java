/**      
* SecurityDangerIdCardView.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.client.ui;


import com.google.gwt.user.client.ui.IsWidget;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/** 
 * @简述: 改密记录查询视图接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-20 下午18:46:45 
 */
public interface SecurityLoginPassModifyRecordView extends IsWidget{
	
	public interface LoginPassModifyRecordPresenter extends SchemaGridViewPresenter{
		
		public String getProductId();
	}
	public LoginPassModifyRecordPresenter getPresenter();
	
	public void setPresenter(LoginPassModifyRecordPresenter presenter);
	
	public SchemaGridView getsSchemaGridView();
}
