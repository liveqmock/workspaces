/**      
* KilledCause.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 
package com.iwgame.gm.p1.security.modules.manage.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
/** 
 * @简述: 封杀原因view
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 */
public interface SecurityKilledCauseView extends IsWidget{
	// 主view-------- 新增，修改，删除，启用、禁用，加载数据
	// 新增窗口----取消,保存
	
	public interface KilledCausePresenter extends SchemaGridViewPresenter{
		
		public String getProductId();
		
		void onClickAdd(KilledCauseConfig killedCause);

		/**
		 * @param id
		 */
		void onAttachUpdateBox(Integer id);

		/**
		 * @param killedCause
		 */
		void onClickUpdate(KilledCauseConfig killedCause);

		/**
		 * @param ids
		 */
		void onClickDel(List<Integer> ids);
	}
	
	public KilledCausePresenter getPresenter();
	
	public void setPresenter(KilledCausePresenter presenter);
	
	public SchemaGridView getsSchemaGridView();
	
	public void doOpenAddWin();
	
	void doOpenUpdateWin(Integer id);
	
	void doAttachUpdateBox(KilledCauseConfig killedCause);
	
	public String getProductId();
}
