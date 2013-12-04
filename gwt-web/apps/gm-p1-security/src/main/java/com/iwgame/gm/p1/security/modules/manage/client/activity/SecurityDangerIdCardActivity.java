/**      
* SecurityDangerIdCardActivity.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.iwgame.gm.p1.security.common.shared.model.DangerIdCard;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityDangerIdCardView;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityDangerIdCardView.DangerIdCardPresenter;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityDangerIdCardViewImpl;
import com.iwgame.gm.p1.security.modules.manage.shared.rpc.SecurityDangerIdCardService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.xportal.common.client.ApplicationContext;

/** 
 * @简述: 高危身份证操作活动
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期： 2012-11-19 下午03:02:29 
 * 
 */
public class SecurityDangerIdCardActivity extends AbstractActivity implements DangerIdCardPresenter{

	private final int initPageSize = 25;
	private final boolean isLoadOnAttach=true;
	private  String productId = ApplicationContext.getCurrentProductId();
	private SecurityDangerIdCardView dangerIdCardView;
	@Inject
	public SecurityDangerIdCardActivity()
	{
		
	}
	
	@Override
	public void goTo(Place place) {
		
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		SecurityDangerIdCardService.Util.get().loadDangerIdCardData(productId,(BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.dangerIdCardView = new SecurityDangerIdCardViewImpl(this, initPageSize, isLoadOnAttach,productId);
		panel.setWidget(dangerIdCardView);
	}

	@Override
	public void onClickAdd(DangerIdCard idCard) {
		List<DangerIdCard> idCards = new ArrayList<DangerIdCard>();
		idCards.add(idCard);
		SecurityDangerIdCardService.Util.get().addDangerIdCard(productId,idCards, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				handlerResult(result,"新增高危身份证");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("新增高危身份证失败,异常:"+caught.getMessage());
			}
		});
	}

	@Override
	public void onAttachUpdateBox(Integer id) {
		SecurityDangerIdCardService.Util.get().getDangerIdCardById(productId,id, new AsyncCallback<DangerIdCard>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("获取高危身份证失败,异常:"+caught.getMessage());
			}

			@Override
			public void onSuccess(DangerIdCard result) {
				if (result!=null) {
					dangerIdCardView.doAttachUpdateBox(result);
				}else {
					MessageBox.alert("未找到该高位身份证");
					return;
				}
			}
		});
	}

	@Override
	public void onClickUpdate(DangerIdCard idCard) {
		SecurityDangerIdCardService.Util.get().updateDangerIdCard(productId,idCard, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("修改高危身份证失败,异常:"+caught.getMessage());
			}

			@Override
			public void onSuccess(Boolean result) {
				handlerResult(result,"修改高危身份证");
			}
		});
	}

	@Override
	public void onClickDel(List<Integer> ids) {
		SecurityDangerIdCardService.Util.get().deleteDangerIdCard(productId,ids, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("删除高危身份证失败,异常:"+caught.getMessage());
			}

			@Override
			public void onSuccess(Boolean result) {
				handlerResult(result,"删除高危身份证");
			}
		});
	}
	protected void reloadGridView() {
		dangerIdCardView.getsSchemaGridView().getPanel().getGrid().load();
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
	 * @see com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityDangerIdCardView.DangerIdCardPresenter#getProductId()
	 */
	@Override
	public String getProductId() {
		// TODO Auto-generated method stub
		return productId;
	}
}
