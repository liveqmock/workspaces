/****************************************************************
 *  文件名     ： TestView.java
 *  日期         :  2012-10-18
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.AppSource;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.BizDict;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc.CommonChannelService;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector.Delegate;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;

/** 
 * @ClassName:    TestView 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-18下午03:25:08
 * @Version:      1.0 
 */
public class CommonChannelReportView extends SchemaGridView {
	
	private final XFormPanel formPanel;
	private final FormLayout layout;
	private final PlainObjectSelector<BizDict> typeSelector;
	private final PlainObjectSelector<AppSource> sourceSelector;
	
	private final TimeScopeSelector timeScope;

	public CommonChannelReportView() {
		super(-1);
		getPanel().getGrid().setHasCheckBoxColumn(false);
		getPanel().setHeader("通道日志报表");
		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		layout.setColumn(3);
		formPanel.setLayout(layout);

		timeScope = new TimeScopeSelector("统计维度");
		timeScope.setColSpan(3);
		layout.add(timeScope);
		
		getPanel().setColumnRenderer("success", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				int s = object.<Double> get(config.getIndex()).intValue();
				if (s >= 0) {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:green;font-weight:bolder;'>"+object.getInt("success")+"</sapn>"));
				} else {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='font-weight:bolder;'>"+object.getInt("success")+"</sapn>"));
				}
			}
		});
		
		getPanel().setColumnRenderer("failure", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				int s = object.<Double> get(config.getIndex()).intValue();
				if (s > 0) {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:red;font-weight:bolder;'>"+object.getInt("failure")+"</sapn>"));
				} else {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='font-weight:bolder;'>"+object.getInt("failure")+"</sapn>"));
				}
			}
		});
		
		sourceSelector = new PlainObjectSelector<AppSource>("业务来源统计","") {

			@Override
			protected String getValue(AppSource t) {
				return t.getAppName();
			}

			@Override
			protected String getLabel(AppSource t) {
				return t.getAliasName();
			}

		};
		
		sourceSelector.setDelegate(new Delegate<AppSource>(){

			@Override
			public void loadItem(AsyncCallback<List<AppSource>> callback) {
				CommonChannelService.Util.get().retrieveAppSource(callback);
			}
			
		});



		typeSelector = new PlainObjectSelector<BizDict>("业务统计统计","") {
			@Override
			protected String getValue(BizDict t) {
				// TODO Auto-generated method stub
				return String.valueOf(t.getId());
			}
			@Override
			protected String getLabel(BizDict t) {
				return t.getBizname();
			}
		};
		
		typeSelector.setDelegate(new Delegate<BizDict>() {
			@Override
			public void loadItem(AsyncCallback<List<BizDict>> callback) {
				CommonChannelService.Util.get().retrieveBizDict(callback);
			}
			
		});
		
		layout.add(1, sourceSelector);
		layout.add(2, typeSelector);

		ButtonToolItem btnRefresh = new ButtonToolItem("查询");
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if(validateTime()){
					getQueryParams();
					reload();
				}else{
					MessageBox.alert("请选择正确的日期!");
				}
			}
		});
		

		formPanel.addButton(btnRefresh);
		getPanel().getToolbar().addItem(formPanel);
	}

	public void getQueryParams() {
		
		int index = Integer.valueOf(timeScope.getValue());
		
		int startDay = timeScope.getPlugin().getStartDay();
		int startMonth = timeScope.getPlugin().getStartMonth();
		int startYear = timeScope.getPlugin().getStartYear();
		
		int endDay = timeScope.getPlugin().getEndDay();
		int endMonth = timeScope.getPlugin().getEndMonth();
		int endYear = timeScope.getPlugin().getEndYear();
		if (!"-1".equals(sourceSelector.getValue())) {
			getGrid().getLoadConfig().set("appname", sourceSelector.getValue());
		} else {
			getGrid().getLoadConfig().remove("appname");
		}
		if (!"-1".equals(typeSelector.getValue())) {
			getGrid().getLoadConfig().set("bizcode", typeSelector.getValue());
		} else {
			getGrid().getLoadConfig().remove("bizcode");
		}
		
		if (validateTime()) {
			if(index == 1){
				getGrid().getLoadConfig().set("startDate", DateWrapper.format(new DateWrapper(startYear, startMonth-1, 1).asDate(), "yyyy-MM"));
			}else if(index == 2){
				getGrid().getLoadConfig().set("startDate", DateWrapper.format(new DateWrapper(startYear, 0, 1).asDate(), "yyyy"));
			}else{
				getGrid().getLoadConfig().set("startDate", DateWrapper.format(new DateWrapper(startYear, startMonth-1, startDay).asDate(), "yyyy-MM-dd"));
			}
		} else {
			getGrid().getLoadConfig().remove("startDate");
		}
		if (validateTime()) {
			if(index == 1){
				getGrid().getLoadConfig().set("endDate", DateWrapper.format(new DateWrapper(endYear, endMonth-1, 1).asDate(), "yyyy-MM"));
			}else if(index == 2){
				getGrid().getLoadConfig().set("endDate", DateWrapper.format(new DateWrapper(endYear, 0, 1).asDate(), "yyyy"));
			}else{
				getGrid().getLoadConfig().set("endDate", DateWrapper.format(new DateWrapper(endYear, endMonth-1, endDay).asDate(), "yyyy-MM-dd"));
			}
		} else {
			getGrid().getLoadConfig().remove("endDate");
		}
		
		if(index >= 0 ){
			getGrid().getLoadConfig().set("model",index);
		}else{
			getGrid().getLoadConfig().remove("model");
		}
	}
	
	public boolean validateTime(){
		int index = Integer.valueOf(timeScope.getValue());
		
		int startDay = timeScope.getPlugin().getStartDay();
		int startMonth = timeScope.getPlugin().getStartMonth();
		int startYear = timeScope.getPlugin().getStartYear();
		
		int endDay = timeScope.getPlugin().getEndDay();
		int endMonth = timeScope.getPlugin().getEndMonth();
		int endYear = timeScope.getPlugin().getEndYear();
		switch (index) {
			case 0:
				return (startDay > -1 && startMonth > -1 && startYear > -1 && endDay > -1 && startMonth > -1 && endYear > -1);
			case 1:
				return (startMonth > -1 && startYear > -1 && endMonth > -1 && endYear > -1);
			case 2:
				return (startYear > -1 && endYear > -1);
			default:
				return false;
		}
	}

}
