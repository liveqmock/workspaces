/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： CrlListView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.crl.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.adcollect.modules.crl.client.ui.widget.UploadTypeField;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.MessageBoxEvent;
import com.iwgame.ui.panel.client.box.MessageBoxHandler;
import com.iwgame.ui.panel.client.box.XButton;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.ComboBoxField;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.FileUploadField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 黑名单列账号表视图实现
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-9-24 上午11:57:39
 */
public class CrlAccountListViewImpl extends Composite implements CrlAccountListView {
	private Presenter listener;
	public SchemaGridView schemaGridView = new SchemaGridView(30);
	private final SchemaGridView detailsView = new SchemaGridView(20);

	private final VerticalPanel vPanel = new VerticalPanel(); // 总面板
	private final String productId = ApplicationContext.getCurrentProductId();

	private XFormPanel formPanel = null;
	private FormLayout layout = null;

	private ComboBoxField reason = null;
	private DateRangeField date = null;
	private RadioButtonGroup type = null;
	private TextField keyword = null;
	private ButtonToolItem query = null; //查询
	private ButtonToolItem input = null; //导入
	private ButtonToolItem delete= null; //删除
	private ButtonToolItem batchdel = null; //批量删除
   
	private XUploadFormPanel addPanel = null;
	private FormLayout addlayout = null;
	private ComboBoxField target = null; // 导入目标
	private UploadTypeField addType = null; // 上传类型
    
	private FileUploadField batchField= null;
	private HiddenField hiddenField_productId = null;

	private XUploadFormPanel delPanel = null;   //批量删除上传面板
	private FormLayout dellayout =null; //批量删除布局器
	private FileUploadField batchDelField = null; //批量删除上传器
	private HiddenField hiddenField_del_productId = null; 
	
	
	public CrlAccountListViewImpl() {

		formPanel = new XFormPanel();
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);

		reason = new ComboBoxField("黑名单原因");
		reason.addItem("不限", "");
		// 1为邮箱， 2为身份证，3为导入
		reason.addItem("导入", "3");
		reason.addItem("身份证", "2");
		reason.addItem("邮箱", "1");
		reason.setColSpan(1);
		layout.add(reason);

		date = new DateRangeField("黑名单录入日期");
		date.setColSpan(1);
		layout.add(2, date);

		type = new RadioButtonGroup("检索条件", "type", Direction.Horizontal);
		type.addRadioButton("账号名", "账号名", true);
		type.addRadioButton("身份证", "身份证", false);
		type.addRadioButton("邮箱", "邮箱", false);
		type.setColSpan(1);
		layout.add(type);

		keyword = new TextField("检索文本");
		layout.add(2, keyword);

		query = new ButtonToolItem("查询");
		query.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (formPanel.validate()) {
					final BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
					loadConfig.set("reason", reason.getValue());
					loadConfig.set("type", type.getValue());
					loadConfig.set("keyword", keyword.getValue().trim());
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
					// 重新加载数据
					schemaGridView.getPanel().getGrid().load(loadConfig);
				}
			}
		});

		formPanel.addButton(query);
        
		input = new ButtonToolItem("导入");
		formPanel.addButton(input);
		
		delete = new ButtonToolItem("删除");
		formPanel.addButton(delete);

		batchdel = new ButtonToolItem("批量删除");
		formPanel.addButton(batchdel);
		
		//checkbox
		schemaGridView.getPanel().getGrid().setHasCheckBoxColumn(true);
		
		delete.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (!schemaGridView.getPanel().getGrid().getSelected().isEmpty()) {
				    MessageBox.confirm("您确定需要删除所选的账号吗？",
					    new MessageBoxHandler() {

						@Override
						public void onClose(MessageBoxEvent event) {
						    XButton btn = event.getButton();
						    if (btn.getItemId().equals(XDialogBox.YES)) {
							StringBuilder sb = new StringBuilder();
							for (BaseModelData data : schemaGridView.getPanel().getGrid().getSelected()) {
							    String id = data.get("id");
							    sb.append(id).append(",");
							}

							sb.deleteCharAt(sb.length() - 1);
							listener.delAccount(productId,sb.toString());
						    }
						}
					    });
				} else {
				    MessageBox.alert("请选择需要删除的账号！");
				}
	        }
		});
		
		input.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				new batchInputDialog().center();
			}
		});
         
		
		batchdel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				new batchDelDialog().center();
			}
		});
		
		vPanel.add(formPanel);
		vPanel.add(schemaGridView);
		initWidget(vPanel);

		// 原因
		schemaGridView.setColumnRenderer("reason", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				if (object.get("reason").equals("1")) {
					sb.appendHtmlConstant("<font color='green'>邮箱</font>");
				} else if (object.get("reason").equals("2")) {
					sb.appendHtmlConstant("<font color='red'>身份证</font>");
				}else {
					sb.appendHtmlConstant("<font color='blue'>导入</font>");
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
				.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
						+ "{\"index\":\"id\",\"header\":\"ID\",\"width\":100,\"visiable\":false}"
						+ ",{\"index\":\"userName\",\"header\":\"账号名\",\"width\":200}"
						+ ",{\"index\":\"regTime\",\"header\":\"注册时间\",\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"width\":200}"
						+ ",{\"index\":\"regIp\",\"header\":\"注册IP\",\"width\":100}"
						+ ",{\"index\":\"idCard\",\"header\":\"注册身份证\",\"width\":300}"
						+ ",{\"index\":\"regEmail\",\"header\":\"注册邮箱\",\"width\":300}"
						+ ",{\"index\":\"sourceType\",\"header\":\"来源类型\",\"width\":100}"
						+ ",{\"index\":\"sourceId\",\"header\":\"来源ID\",\"width\":150}"
						+ ",{\"index\":\"loaction\",\"header\":\"来源URL\",\"width\":250}"
						+ ",{\"index\":\"reason\",\"header\":\"黑名单原因\",\"width\":100}]}}");
		return schemaGridView;

	}
	
	/***
	 *
	 *批量删除对话框
	 *
	 */
	class batchDelDialog extends XDialogBox {

		public batchDelDialog() {

			delPanel = new XUploadFormPanel("", "crlBatchDelUploadprocessor");
			dellayout = new FormLayout();
			delPanel.setLayout(dellayout);

			batchDelField = new FileUploadField("导入账号列表", "batch");
			dellayout.add(batchDelField);

			hiddenField_del_productId = new HiddenField("hiddenField_productId", productId);
			hiddenField_del_productId.setValue(productId);
			dellayout.add(hiddenField_del_productId);

			delPanel.setWidth("600px");
			initBox("批量删除黑名单账号", delPanel);
			initBox("批量导入黑名单账号", new HTML("账号列表CSV文件不可以带表头"));
			setButtons(XDialogBox.OKCANCEL);
		}

		/**
		 * 重写XDialog的 按钮方法
		 * 
		 * 
		 */
		@Override
		protected void onButtonPressed(final Button button) {
			if (button == getButtonByItemId(OK)) {
				if (batchDelField.getValue().length() == 0) {
					MessageBox.alert("请选择你要上传的CSV文件!");
				} else if (!batchDelField.getValue().endsWith(".csv")) {
					MessageBox.alert("文件格式错误,请选CSV文件!");
				} else {
					MessageBox.confirm("确认要导入吗?", new MessageBoxHandler() {
						@Override
						public void onClose(final MessageBoxEvent event) {
							final XButton button = event.getButton();
							if (button.getItemId().equals(XDialogBox.YES)) {
								delPanel.submit();
								delPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {

									@Override
									public void onSubmitComplete(final SubmitCompleteEvent event) {
										hide();
									}
								});
							}
						}
					});
				}

				// hiddenField_productId.setValue(productId);
			} else if (button == getButtonByItemId(CANCEL)) {
				hide();
			}
			super.onButtonPressed(button);
		}

	}
	
	
	
	
	
	/***
	 *
	 *批量导入对话框
	 *
	 */
	class batchInputDialog extends XDialogBox {

		public batchInputDialog() {

			addPanel = new XUploadFormPanel("", "crlAccountBatchInputUploadprocessor");
			addlayout = new FormLayout();
			addPanel.setLayout(addlayout);

			batchField = new FileUploadField("导入文本", "batch");
			addlayout.add(batchField);

			hiddenField_productId = new HiddenField("hiddenField_productId", productId);
			hiddenField_productId.setValue(productId);
			addlayout.add(hiddenField_productId);

			addPanel.setWidth("800px");
			initBox("批量导入黑名单账号", addPanel);
			initBox("批量导入黑名单账号", new HTML(getSampleCSVDesc()));
			setButtons(XDialogBox.OKCANCEL);
		}

		/**
		 * 重写XDialog的 按钮方法
		 * 
		 * 
		 */
		@Override
		protected void onButtonPressed(final Button button) {
			if (button == getButtonByItemId(OK)) {
				if (batchField.getValue().length() == 0) {
					MessageBox.alert("请选择你要上传的CSV文件!");
				} else if (!batchField.getValue().endsWith(".csv")) {
					MessageBox.alert("文件格式错误,请选CSV文件!");
				} else {
					MessageBox.confirm("确认要导入吗?", new MessageBoxHandler() {
						@Override
						public void onClose(final MessageBoxEvent event) {
							final XButton button = event.getButton();
							if (button.getItemId().equals(XDialogBox.YES)) {
								addPanel.submit();
								addPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {

									@Override
									public void onSubmitComplete(final SubmitCompleteEvent event) {
										hide();
									}
								});
							}
						}
					});
				}

				// hiddenField_productId.setValue(productId);
			} else if (button == getButtonByItemId(CANCEL)) {
				hide();
			}
			super.onButtonPressed(button);
		}

	}
	
	
	@Override
	public void delAccountResult(Integer result) {
		if (result>0) {
			MessageBox.info("删除 "+result+" 条账号成功!");
			schemaGridView.getPanel().getGrid().load();
		}else {
			MessageBox.info("删除账号失败!");
		}
	}
    
	/**
	 * 取得CSV格式文件样例
	 * 
	 * @return
	 */
	private String getSampleCSVDesc() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<hr/>");
		stringBuilder.append("<bgcolor=red>CSV约定的格式如下(仅限数据,不能带表头,表头仅供参考)：");
		stringBuilder.append("<table border=\"0\"><tbody>");
		stringBuilder.append("<tr>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 账号名 </th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 注册时间</th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 注册IP</th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 注册身份证</th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 注册邮箱</th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 来源类型</th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 来源ID</th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 来源URL</th>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
		stringBuilder.append("<td>test_zxy </td>");
		stringBuilder.append("<td>2010-09-09 12:23:34</td>");
		stringBuilder.append("<td>10.10.10.1</td>");
		stringBuilder.append("<td>341111198911211132</td>");
		stringBuilder.append("<td>test@163.com</td>");
		stringBuilder.append("<td>ad</td>");
		stringBuilder.append("<td>2222</td>");
		stringBuilder.append("<td>http://hd.zuixiaoyao.com/client/test.html</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("</tbody></table>");
		stringBuilder.append("<a href = \"" + GWT.getHostPageBaseURL() + "template/blackAccountInput-sample.csv" + "\">模版下载</a>");

		return stringBuilder.toString();
	}
}
