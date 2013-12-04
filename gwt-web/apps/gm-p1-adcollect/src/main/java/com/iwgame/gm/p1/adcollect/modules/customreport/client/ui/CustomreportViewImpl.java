/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： CustomreportView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.customreport.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.adcollect.modules.customreport.client.ui.dialog.ReportDialog;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.LinkColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.ComboBoxField;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述： 自动报表界面
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-11-12 下午02:44:45
 */
public class CustomreportViewImpl extends Composite implements CustomreportView{
	private Presenter listener;
	public SchemaGridView schemaGridView = new SchemaGridView(15);
	private final SchemaGridView detailsView = new SchemaGridView(10);

	private final VerticalPanel vPanel = new VerticalPanel(); // 总面板
	private final String productId = ApplicationContext.getCurrentProductId();

	private XFormPanel formPanel = null;
	private FormLayout layout = null;

	private DateRangeField date = null;
	private TextField name = null;
	private TextField operator = null;
	private ComboBoxField status = null; // 状态0：待处理   1：处理中  2：已完成

	private ButtonToolItem query = null;
	private ButtonToolItem add = null;

	public CustomreportViewImpl() {

		formPanel = new XFormPanel();
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);

		//时间
		date = new DateRangeField("时间");
		date.setColSpan(2);
		layout.add(date);
		
		//名称
		name = new TextField("报表名称");
		name.setColSpan(1);
		layout.add(name);
		
		//提交人
		operator = new TextField("提交人");
		operator.setColSpan(1);
		layout.add(2,operator);
		
		//状态  0：待处理   1：处理中  2：已完成
		status  = new ComboBoxField("状态");
		status.addItem("所有", "");
		status.addItem("待处理", "0");
		status.addItem("处理中", "1");
		status.addItem("已完成", "2");
		layout.add(status);
		
		query = new ButtonToolItem("查询");
		add = new ButtonToolItem("添加");
		
		formPanel.addButton(query);
		formPanel.addButton(add);

		vPanel.add(formPanel);
		vPanel.add(schemaGridView);
		initWidget(vPanel);
		
		query.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (formPanel.validate()) {
					final BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
					if (null != date.getStartValue()) {
						loadConfig.set("beginDate", DateWrapper.format(date.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						loadConfig.set("beginDate", null);
					}
					if (null != date.getEndValue()) {
						final DateWrapper dateWrapper = new DateWrapper(date.getEndValue());
						loadConfig.set("endDate",
								DateWrapper.format(dateWrapper.clearTime().addDays(1).asDate(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						loadConfig.set("endDate", null);
					}
					loadConfig.set("name",name.getValue().trim());
					loadConfig.set("operator",operator.getValue().trim());
					loadConfig.set("status",status.getValue());
					// 重新加载数据
					schemaGridView.getPanel().getGrid().load(loadConfig);
				}

			}
		});
		
		
		add.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				new ReportDialog(CustomreportViewImpl.this).center();
				
			}
		});
		
		// 说明
		schemaGridView.setColumnRenderer("remark", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						MessageBox.info(object.get("remark").toString());
					} 
				};
			}
			
			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
					return "说明";
				}
		});
		
		// 状态  0：待处理   1：处理中  2：已完成
		schemaGridView.setColumnRenderer("status", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				if (object.get("status").equals("2")) {
					sb.appendHtmlConstant("<font color='green'>已完成</font>");
				} else if (object.get("status").equals("1")) {
					sb.appendHtmlConstant("<font color='blue'>处理中</font>");
     			} else if (object.get("status").equals("0")) {
		    		sb.appendHtmlConstant("<font color='red'>待处理</font>");
			    } else if (object.get("status").equals("3")) {
				    sb.appendHtmlConstant("<font color='gray'>无数据</font>");
			    } else if (object.get("status").equals("4")) {
				    sb.appendHtmlConstant("<font color='black'>任务失败</font>");
			}
			}
		});
		
		// 修改
		schemaGridView.setColumnRenderer("download", new LinkColumnRenderer<BaseModelData>() {
			

			@Override
            public String getHref(int column, int row,
                    BaseModelData rowData, ColumnConfig config) {
				if (rowData.get("status").equals("2")) {
					return GWT.getHostPageBaseURL() + "gsvc/__download__?beanname=ad.download&filename="+rowData.get("name")+"&fileurl="+rowData.get("filepath")+"&contentType=vnd.ms-excel";
				}else {
					return "";
				}
            }
            
			@Override
            public String getText(int column, int row,
                    BaseModelData rowData, ColumnConfig config) {
               if (rowData.get("status").equals("2")) {
					return "下载";
				}else {
					return "";
				}
            }
			
			@Override
			public String getTarget() {
				return "blank";
			}

		});

		// 删除
		schemaGridView.setColumnRenderer("delete", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						 if (!object.get("status").equals("1")){
							 Map<String, String> paramMap = new  HashMap<String, String>();
							 paramMap.put("id", object.get("id").toString());
						     listener.delTask(productId, paramMap);    
						 } 
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
                if (!rowData.get("status").equals("1")) {
					return "删除";
				}else {
					return "";
				}
			}
		});
	}
    
	
	@Override
	public void setPresenter(final Presenter presenter) {
		listener = presenter;
	}

	@Override
	public void onFailureQuery(final String errorMsg) {
		// TODO Auto-generated method stub

	}
  
	@Override
	public SchemaGridView getSchemaGridView() {
		schemaGridView
				.setSchemaJson("{\"table\":{\"key\":\"manageId\",\"columns\":["
						+ "{\"index\":\"id\",\"header\":\"ID\",\"width\":100,\"visiable\":false}"
						+ ",{\"index\":\"name\",\"header\":\"报表名称\",\"width\":250}"
						+ ",{\"index\":\"remark\",\"header\":\"说明\",\"type\":\"button\",\"width\":100}"
						+ ",{\"index\":\"submitTime\",\"header\":\"提交时间\",\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"width\":180}"
						+ ",{\"index\":\"finishTime\",\"header\":\"完成时间\",\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"width\":180}"
						+ ",{\"index\":\"operatior\",\"header\":\"提交人\",\"width\":100}"
						+ ",{\"index\":\"status\",\"header\":\"状态\",\"width\":100}"
						+ ",{\"index\":\"download\",\"header\":\"下载\",\"type\":\"link\",\"width\":100}"
						+ ",{\"index\":\"filepath\",\"header\":\"路径\",\"visiable\":false,\"width\":100}"
						+ ",{\"index\":\"delete\",\"header\":\"删除\",\"type\":\"button\",\"width\":100}]}}");
		return schemaGridView;
	}

	@Override
	public void addResult(String result) {
		if (result!=null) {
			if (Integer.valueOf(result) > 0) {
				MessageBox.info("增加定时报表成功！");
				schemaGridView.getPanel().getGrid().load();
			} else {
				MessageBox.info("增加定时报表失败！");
			}
		}
	}

	@Override
	public void delResult(String result) {
		if (result!=null) {
			if (Integer.valueOf(result) > 0) {
				MessageBox.info("删除成功！");
				schemaGridView.getPanel().getGrid().load();
			} else {
				MessageBox.info("删除失败！");
			}
		}
	}

	@Override
	public Presenter getPresenter() {
		return this.listener;
	}

	
}
