/****************************************************************
 *  文件名     ： TestView.java
 *  日期         :  2012-10-18
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.ui;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.gm.p1.logmonitor.modules.business.client.ui.BusinessLogDetailsView;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.AppSource;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.BizDict;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc.CommonChannelService;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector.Delegate;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.log.XMVPLogger;
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
public class CommonChannelLogDetailView extends SchemaGridView {
	
	private static final XMVPLogger logger = new XMVPLogger(BusinessLogDetailsView.class);

	private final XFormPanel formPanel;
	private final FormLayout layout;
	private final PlainObjectSelector<BizDict> typeSelector;
	private final PlainObjectSelector<AppSource> sourceSelector;
	private final SimpleSelector statusSelector;
	private final TextField keywordsField;

	private final DateRangeField dates;

	@SuppressWarnings("deprecation")
	public CommonChannelLogDetailView() {
		getPanel().getGrid().setHasCheckBoxColumn(false);
		getPanel().setHeader("通道监控日志明细");
		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		layout.setColumn(3);
		formPanel.setLayout(layout);

		Date curDate = new Date();
		
		dates = new DateRangeField(true, "查询时间");
		dates.setStartValue(new DateWrapper(curDate.getYear()+1900,curDate.getMonth(),curDate.getDate()).asDate());
		dates.setEndValue(curDate);
		layout.add(dates);

		statusSelector = new SimpleSelector("状态") {

			@Override
			public void initItems() {
				addItem("— 所有 —", "-1");
				addItem("成功", "0");
				addItem("失败", "1");
			}
		};
		layout.add(2, statusSelector);
		
		getPanel().setColumnRenderer("logtype", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				int s = object.<Double> get(config.getIndex()).intValue();
				if (s == 0) {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:green;font-weight:bolder;'>成功</sapn>"));
				} else {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:red;font-weight:bolder;'>失败</sapn>"));
				}
			}
		});
		
		getPanel().setColumnRenderer("resourcebtn", new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
			}

			@Override
			public com.google.gwt.cell.client.ActionCell.Delegate<BaseModelData> getDelegate() {
				// TODO Auto-generated method stub
				return new com.google.gwt.cell.client.ActionCell.Delegate<BaseModelData>(){

					@Override
					public void execute(BaseModelData object) {
						String resource = object.<String> get("resource");
						MessageBox.info(resource);
					}
					
				};
			}

			
			
			
			
		});

		sourceSelector = new PlainObjectSelector<AppSource>("业务来源","") {

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
		

		typeSelector = new PlainObjectSelector<BizDict>("业务类型","") {
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
		
		layout.add(sourceSelector);

		layout.add(2, typeSelector);

		keywordsField = new TextField("关键字", "keywords");
		layout.add(3, keywordsField);

		ButtonToolItem btnRefresh = new ButtonToolItem("查询");
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				getQueryParams();
				reload();
			}
		});

		formPanel.addButton(btnRefresh);

		getPanel().getToolbar().addItem(formPanel);
	}

	public void getQueryParams() {
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
		if (!"-1".equals(statusSelector.getValue())) {
			getGrid().getLoadConfig().set("logtype", statusSelector.getValue());
		} else {
			getGrid().getLoadConfig().remove("logtype");
		}
		if (dates.getStartValue() != null) {
			getGrid().getLoadConfig().set("startDate", DateWrapper.format(dates.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
		} else {
			getGrid().getLoadConfig().remove("startDate");
		}
		if (dates.getEndValue() != null) {
			getGrid().getLoadConfig().set("endDate", DateWrapper.format(dates.getEndValue(), "yyyy-MM-dd HH")+":59:59");
		} else {
			getGrid().getLoadConfig().remove("endDate");
		}
		if (!"".equals(keywordsField.getValue())) {
			getGrid().getLoadConfig().set("keywords", keywordsField.getValue());
		} else {
			getGrid().getLoadConfig().remove("keywords");
		}
		logger.info("查询参数为 【日志状态=" + statusSelector.getValue() + "】【业务来源=" + sourceSelector.getValue() + "】【业务类型="
				+ typeSelector.getValue() + "】【 关键字=" + keywordsField.getValue() + "】");
	}

	public void setUsername(final String username) {
		keywordsField.setValue(username);
	}

	public void setSource(final String source) {
		sourceSelector.setValue(source);
	}

	public void setStartDate(final Date date) {
		dates.setStartValue(date);
	}

	public void setEndDate(final Date date) {
		dates.setEndValue(date);
	}

}
