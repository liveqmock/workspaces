/**      
* KilledCause.java Create on 2012-11-15     
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
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityKilledCauseView;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityKilledCauseView.KilledCausePresenter;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityKilledCauseViewImpl;
import com.iwgame.gm.p1.security.modules.manage.shared.rpc.SecurityKilledCauseService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.xportal.common.client.ApplicationContext;
/** 
 * @简述: 封杀原因操作实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 */
public class SecurityKilledCauseActivity extends AbstractActivity implements KilledCausePresenter{

	private final int initPageSize = 25;
	private final boolean isLoadOnAttach=true;
	private  String productId = ApplicationContext.getCurrentProductId();
	private SecurityKilledCauseView killedCauseView=null;
	
	@Inject
	public SecurityKilledCauseActivity(){
	};
	
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.killedCauseView = new SecurityKilledCauseViewImpl(this, initPageSize, isLoadOnAttach,productId);
		panel.setWidget(killedCauseView);
	}

	@Override
	public void goTo(Place place) {
		
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		SecurityKilledCauseService.Util.get().loadKilledCauseData(productId,(BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void onClickAdd(KilledCauseConfig killedCause) {
		SecurityKilledCauseService.Util.get().addKilledCause(productId,killedCause, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				handlerResult(result,"新增封杀原因");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("新增封杀原因失败,异常:"+caught.getMessage());
			}
		});
	}


	@Override
	public void onAttachUpdateBox(Integer id) {
		SecurityKilledCauseService.Util.get().getKilledCauseById(productId,id, new AsyncCallback<KilledCauseConfig>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("获取封杀原因失败,异常:"+caught.getMessage());
			}

			@Override
			public void onSuccess(KilledCauseConfig result) {
				if (result!=null) {
					killedCauseView.doAttachUpdateBox(result);
				}else {
					MessageBox.alert("未找到该封杀原因");
					return;
				}
			}
		});
	}
	
	@Override
	public void onClickUpdate(KilledCauseConfig killedCause) {
		SecurityKilledCauseService.Util.get().updateKilledCause(productId,killedCause, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("修改封杀原因失败,异常:"+caught.getMessage());
			}

			@Override
			public void onSuccess(Boolean result) {
				handlerResult(result,"修改封杀原因");
			}
		});
	}
	
	@Override
	public void onClickDel(List<Integer> ids) {
		SecurityKilledCauseService.Util.get().deleteKilledCause(productId,ids, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("删除封杀原因失败,异常:"+caught.getMessage());
			}

			@Override
			public void onSuccess(Boolean result) {
				handlerResult(result,"删除封杀原因");
			}
		});
	}
	/**
	 * 
	 */
	protected void reloadGridView() {
		killedCauseView.getsSchemaGridView().getPanel().getGrid().load();
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
	 * @see com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityKilledCauseView.KilledCausePresenter#getProductId()
	 */
	@Override
	public String getProductId() {
		// TODO Auto-generated method stub
		return productId;
	}


}
