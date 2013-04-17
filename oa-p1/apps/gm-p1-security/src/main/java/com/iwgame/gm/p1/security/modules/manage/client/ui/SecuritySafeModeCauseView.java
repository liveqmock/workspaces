/**      
* SecuritySafeModeCauseView.java Create on 2012-11-16     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/** 
 * @简述: 安全模式备注视图接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-16 下午04:20:53 
 * 
 */
public interface SecuritySafeModeCauseView extends IsWidget{
	// 主view-------- 新增，修改，删除，启用、禁用，加载数据
	// 新增窗口----取消,保存
	
	public interface SafeModeCausePresenter extends SchemaGridViewPresenter{
		
		public String getProductId();
		
		void onClickAdd(SafeModeCauseConfig killedCause);

		/**
		 * @param id
		 */
		void onAttachUpdateBox(Integer id);

		/**
		 * @param killedCause
		 */
		void onClickUpdate(SafeModeCauseConfig killedCause);

		/**
		 * @param ids
		 */
		void onClickDel(List<Integer> ids);
	}
	
	public SafeModeCausePresenter getPresenter();
	
	public void setPresenter(SafeModeCausePresenter presenter);
	
	public SchemaGridView getsSchemaGridView();
	
	public void doOpenAddWin();
	
	void doOpenUpdateWin(Integer id);
	
	void doAttachUpdateBox(SafeModeCauseConfig safeModeCause);
	
	public String getProductId();
}
