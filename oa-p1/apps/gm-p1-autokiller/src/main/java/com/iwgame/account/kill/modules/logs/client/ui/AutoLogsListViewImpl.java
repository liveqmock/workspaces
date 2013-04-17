/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： AutoLogsListViewImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.logs.client.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.iwgame.account.kill.modules.logs.client.presenter.AutoLogsListPresenter;
import com.iwgame.account.kill.modules.logs.client.ui.dialogs.AutoKillPolicyDialog;
import com.iwgame.account.kill.modules.logs.client.ui.dialogs.InfoDialog;
import com.iwgame.account.kill.modules.policy.shared.model.Policy;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.ExportButton;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 封停日志视图实现类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-4 下午02:12:46
 */
public class AutoLogsListViewImpl extends SchemaGridView implements AutoLogsListView {

	private AutoLogsListPresenter presenter;

	private ButtonToolItem querybtn;

	private XFormPanel formPanel;
	private FormLayout layout;

	private TextField policy;
	private DateRangeField time;
	private TextField username;
	private NumberField days;

	public AutoLogsListViewImpl() {
		super(15);
		initFormPanel();
		querybtn = new ButtonToolItem("查询");
		querybtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
				loadConfig.set("username", username.getValue());
				loadConfig.set("policy", policy.getValue());
				loadConfig.set("days", Integer.parseInt(days.getValue().toString()));
				if (null != time.getStartValue()) {
					DateWrapper dateWrapper = new DateWrapper(time.getStartValue());
					loadConfig.set("startTime", DateWrapper.format(
							dateWrapper.clearTime().asDate(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					loadConfig.set("startTime", null);
				}
				if (null != time.getEndValue()) {
					DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
					loadConfig.set(
							"endTime",
							DateWrapper.format(dateWrapper.clearTime().addDays(1).addSeconds(-1)
									.asDate(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					loadConfig.set("endTime", null);
				}

				getGrid().load();
			}
		});
		getPanel().getToolbar().addItem(formPanel);
		getPanel().getToolbar().addItem(querybtn);
		getPanel().enableExport("killer-logs-logsservice");
		getPanel().setExportButton(new ExportButton(getPanel()) {

			@Override
			public Map<String, String> getParameters() {
				Map<String, String> map = new HashMap<String, String>();
				map.put("username", username.getValue());
				map.put("policy", policy.getValue());
				map.put("days", days.getValue().toString());
				if (null != time.getStartValue()) {
					DateWrapper dateWrapper = new DateWrapper(time.getStartValue());
					map.put("startTime", DateWrapper.format(
							dateWrapper.clearTime().asDate(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					map.put("startTime", null);
				}
				if (null != time.getEndValue()) {
					DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
					map.put(
							"endTime",
							DateWrapper.format(dateWrapper.clearTime().addDays(1).addSeconds(-1)
									.asDate(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					map.put("endTime", null);
				}
				map.put("productId", ApplicationContext.getCurrentProductId());
				return map;
			}

			@Override
			public Map<String, String> getColumns() {
				Map<String, String> columns = new HashMap<String, String>();
				columns.put("id", "ID");
				columns.put("policyTitle", "策略名称");
				columns.put("policyContent", "策略内容");
				columns.put("accountid", "游戏账号ID");
				columns.put("accountName", "游戏帐号");
				columns.put("killTime", "封杀时间");
				columns.put("remark", "封杀原因");
				columns.put("object", "MAC地址");
				columns.put("killDays", "封杀天数");
				return columns;
			}
		});
		
		// 渲染 策略 policyTitle
		getGrid().setColumnRenderer("policyTitle", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						
						Policy p = new Policy();
						p.setAccounts(((Double) object.get("policy.accounts")).intValue());
						p.setDays(((Double) object.get("policy.days")).intValue());
						p.setDelay(((Double) object.get("policy.delay")).intValue());
						p.setLevel(((Double) object.get("policy.level")).intValue());
						p.setLevelOpt(object.get("policy.levelOpt").toString());
						p.setPaid(((Double) object.get("policy.paid")).intValue());
						p.setReason(((Double) object.get("policy.reason")).intValue());
						p.setDueDays(((Double) object.get("policy.dueDays")).intValue());
						
						new AutoKillPolicyDialog("ID："+((Double)object.get("id")).intValue()+"的封杀策略",p).center();
					}
				};
			}
		});
		
		// 渲染 原因 remarkInfo
		getGrid().setColumnRenderer("remarkInfo", new ButtonColumnRenderer<BaseModelData>() {
			
			@Override
			public boolean isPopupWindow() {
				return true;
			}
			
			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					
					@Override
					public void execute(final BaseModelData object) {
						new InfoDialog("ID："+((Double)object.get("id")).intValue()+"的原因",object.get("remark").toString()).center();
					}
				};
			}
			
			@Override
			public String getText(int column, int row, BaseModelData rowData, ColumnConfig config) {
				String remark = rowData.get("remark").toString();
				if(remark.length()>7){
					remark = remark.substring(0, 5)+"…";
				}
				return remark;
			}
		});
		// 渲染封杀天数
		getPanel().setColumnRenderer("killDaysShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				/* "paid":-1 */
				if (null != object.get("killDays")) {
						Double pd = object.get("killDays");
						if (pd.intValue() == 5000) {
							sb.appendHtmlConstant("永久");
						} else {
							sb.appendHtmlConstant(pd.intValue()+"");
						}
				}
			}
		});
	}

	@Override
	public void setPresenter(AutoLogsListPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private void initFormPanel() {
		formPanel = new XFormPanel("自动封杀记录");
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);
		policy = new TextField("策略名称");
		layout.add(policy);

		time = new DateRangeField("封杀时间");
		time.setEndValue(new Date());
		layout.add(2,time);
		
		username = new TextField("用户名");
		layout.add(username);

		days = new NumberField("封杀天数");
		layout.add(2, days);

	}
}
