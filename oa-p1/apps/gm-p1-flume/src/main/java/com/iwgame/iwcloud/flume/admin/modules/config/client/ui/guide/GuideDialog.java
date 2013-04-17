/**      
 * ChannelDialog.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.iwcloud.flume.admin.modules.config.client.ui.guide;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.events.ChannelSelectedEvent;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeChannel;
import com.iwgame.ui.client.widgets.panel.wizard.ViewValidator;
import com.iwgame.ui.client.widgets.panel.wizard.WizardPanel;
import com.iwgame.ui.client.widgets.panel.wizard.WizardViewListener;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.xmvp.client.utils.AppUtils;

/**
 * 
 * @ClassName: ChannelDialog
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-2 上午09:00:12
 * @Version 1.0
 * 
 */
public class GuideDialog extends XDialogBox {


	private final SchemaGridView view;
	
	private final VerticalPanel vp = new VerticalPanel();
	
	private WizardPanel wizard;
	
	private GuideAddView guideAddView;
	
	private OldGuideDialog oldGuideDialog;
	
	public GuideDialog(final SchemaGridView view, final FlumeChannel channel) {
		
		this.view = view;
		
		wizard = new WizardPanel();
		wizard.setWidth("560px");
		wizard.setHeight("350px");
		
		
		wizard.addPanel("向导新增", guideAddView = new GuideAddView());
		wizard.addPanel("通道基本信息",oldGuideDialog = new OldGuideDialog(this.view,null));
		
		wizard.addViewListener("通道基本信息", new WizardViewListener() {
			@Override
			public void onViewSwitch(WizardPanel wizard, Widget widget) {
				AppUtils.EVENT_BUS.fireEvent(new ChannelSelectedEvent(guideAddView.getGenerateFlowName(),guideAddView.getGenerateFlowExecSource(),guideAddView.getCollectRoomSelectIndexOf()));
			}
			@Override
			public boolean onBeforeViewSwitch(WizardPanel wizard, Widget widget) {
				return true;
			}
		});
		
		//向导新增页面
		wizard.addViewValidator("向导新增", new ViewValidator() {
			@Override
			public boolean validate(WizardPanel wizard, Widget widget) {
				GuideAddView guideAddView = (GuideAddView)widget;
				return guideAddView.validateFieldValue()&&guideAddView.getFormpanel().validate();
			}
		});
		
		//取消按钮事件
		wizard.getCancelButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		//完成事件
		wizard.getFinishButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(oldGuideDialog.onFilsh()){
					hide();
				}
			}
		});
		
		vp.add(wizard);
		initBox("", vp);
		setButtons("");
	}
}
