/**      
* SecuritySafeModeCauseActivity.java Create on 2012-11-16     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.client.activity;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecuritySafeModeCauseView;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecuritySafeModeCauseView.SafeModeCausePresenter;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecuritySafeModeCauseViewImpl;
import com.iwgame.gm.p1.security.modules.manage.shared.rpc.SecuritySafeModeCauseService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.xportal.common.client.ApplicationContext;

/** 
 * @简述: 安全模式备注操作实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-16 下午04:20:19 
 * 
 */
public class SecuritySafeModeCauseActivity extends AbstractActivity implements SafeModeCausePresenter{
	private final int initPageSize = 25;
	private final boolean isLoadOnAttach=true;
	private  String productId = ApplicationContext.getCurrentProductId();
	private SecuritySafeModeCauseView safeModeCauseView=null;
	
	@Inject
	public SecuritySafeModeCauseActivity(){
	};
	
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.safeModeCauseView = new SecuritySafeModeCauseViewImpl(this, initPageSize, isLoadOnAttach,productId);
		panel.setWidget(safeModeCauseView);
	}

	@Override
	public void goTo(Place place) {
		
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		SecuritySafeModeCauseService.Util.get().loadSafeModeCauseData(productId,(BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void onClickAdd(SafeModeCauseConfig safeModeCause) {
		SecuritySafeModeCauseService.Util.get().addSafeModeCause(productId,safeModeCause, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				handlerResult(result,"新增安全模式备注");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("新增安全模式备注失败,异常:"+caught.getMessage());
			}
		});
	}


	@Override
	public void onAttachUpdateBox(Integer id) {
		SecuritySafeModeCauseService.Util.get().getSafeModeCauseById(productId,id, new AsyncCallback<SafeModeCauseConfig>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("获取安全模式备注失败,异常:"+caught.getMessage());
			}

			@Override
			public void onSuccess(SafeModeCauseConfig result) {
				if (result!=null) {
					safeModeCauseView.doAttachUpdateBox(result);
				}else {
					MessageBox.alert("未找到该安全模式备注");
					return;
				}
			}
		});
	}
	
	@Override
	public void onClickUpdate(SafeModeCauseConfig safeModeCause) {
		SecuritySafeModeCauseService.Util.get().updateSafeModeCause(productId,safeModeCause, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("修改安全模式备注失败,异常:"+caught.getMessage());
			}

			@Override
			public void onSuccess(Boolean result) {
				handlerResult(result,"修改安全模式备注");
			}
		});
	}
	
	@Override
	public void onClickDel(List<Integer> ids) {
		SecuritySafeModeCauseService.Util.get().deleteSafeModeCause(productId,ids, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("删除安全模式备注失败,异常:"+caught.getMessage());
			}

			@Override
			public void onSuccess(Boolean result) {
				handlerResult(result,"删除安全模式备注");
			}
		});
	}
	/**
	 * 
	 */
	protected void reloadGridView() {
		safeModeCauseView.getsSchemaGridView().getPanel().getGrid().load();
	}

	/**
	 * @param result
	 * @param string
	 */
	protected void handlerResult(Boolean result, String string) {
		if (result) {
			MessageBox.info(string+"成功");
		}else {
			MessageBox.alert(string+"失败");
		}
		reloadGridView();
	}


	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.security.modules.manage.client.ui.SecuritySafeModeCauseView.SafeModeCausePresenter#getProductId()
	 */
	@Override
	public String getProductId() {
		// TODO Auto-generated method stub
		return productId;
	}


}
