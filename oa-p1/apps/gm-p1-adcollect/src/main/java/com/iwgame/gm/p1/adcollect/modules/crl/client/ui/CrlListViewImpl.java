/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： CrlListView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.crl.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.adcollect.modules.crl.client.ui.widget.UploadTypeField;
import com.iwgame.gm.p1.adcollect.server.util.RegexGwtHelper;
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
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.ComboBoxField;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.ui.panel.client.util.IDCard;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 黑名单列表视图实现
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-9-24 上午11:57:39
 */
public class CrlListViewImpl extends Composite implements CrlListView {
	private Presenter listener;
	public SchemaGridView schemaGridView = new SchemaGridView(15);
	private final SchemaGridView detailsView = new SchemaGridView(10);

	private final VerticalPanel vPanel = new VerticalPanel(); // 总面板
	private final String productId = ApplicationContext.getCurrentProductId();

	private XFormPanel formPanel = null;
	private FormLayout layout = null;

	private ComboBoxField type = null;
	private DateRangeField date = null;
	private TextField keyword = null;
	private ButtonToolItem query = null;
	private ButtonToolItem add = null;

	private XUploadFormPanel addPanel = null;
	private FormLayout addlayout = null;
	private ComboBoxField target = null; // 导入目标
	private UploadTypeField addType = null; // 上传类型

	private HiddenField hiddenField_productId = null;

	public CrlListViewImpl(final String id) {

		formPanel = new XFormPanel();
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);

		type = new ComboBoxField("筛选模式");
		type.addItem("邮箱", "1");
		type.addItem("身份证", "2");
		type.setColSpan(1);
		layout.add(type);

		date = new DateRangeField("黑名单录入日期");
		date.setColSpan(1);
		layout.add(2, date);

		keyword = new TextField("检索文本");
		layout.add(keyword);

		query = new ButtonToolItem("查询");
		query.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (formPanel.validate()) {
					final BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
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

		add = new ButtonToolItem("添加黑名单");
		add.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				new addCrlDialog().center();
			}
		});

		formPanel.addButton(query);
		formPanel.addButton(add);

		vPanel.add(formPanel);
		vPanel.add(schemaGridView);
		initWidget(vPanel);

		// 类型
		schemaGridView.setColumnRenderer("manageType", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				if (object.get("manageType").equals("1")) {
					sb.appendHtmlConstant("<font color='green'>邮箱</font>");
				} else if (object.get("manageType").equals("2")) {
					sb.appendHtmlConstant("<font color='red'>身份证</font>");
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
						+ "{\"index\":\"manageId\",\"header\":\"ID\",\"width\":100,\"visiable\":false}"
						+ ",{\"index\":\"manageType\",\"header\":\"类型\",\"width\":100}"
						+ ",{\"index\":\"manageText\",\"header\":\"内容\",\"width\":100}"
						+ ",{\"index\":\"manageInsertTime\",\"header\":\"添加时间\",\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"width\":100}]}}");
		return schemaGridView;
	}

	// 黑名单添加弹出框
	class addCrlDialog extends XDialogBox {

		public addCrlDialog() {

			addPanel = new XUploadFormPanel("crlbatchUploadProcessor");
			addlayout = new FormLayout();
			addPanel.setLayout(addlayout);

			addType = new UploadTypeField("导入方式");
			addlayout.add(addType);

			target = new ComboBoxField("导入类型");
			target.addItem("身份证", "2");
			target.addItem("邮箱", "1");
			addlayout.add(target);

			hiddenField_productId = new HiddenField("hiddenField_productId", productId);
			hiddenField_productId.setValue(productId);
			addlayout.add(hiddenField_productId);

			addPanel.setWidth("600px");
			initBox("黑名单录入", addPanel);
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
				// 判断是单个导入还是批量导入 1= 批量导入 ，0=单个录入
				if (("0").equals(addType.getValue())) {
					if (("").equals(addType.getTextKeyword().trim())) {
						MessageBox.info("请输入导入内容");
					} else {
						addType.setValidators(null);
						// 1:邮箱 2:身份证
						if (("1").equals(target.getValue())) {
							if (addType.getTextKeyword().length() != 40) {
								addType.addValidator(new Validator() {

									@Override
									public String validate(final Field<?, ?> field) {
										final RegExp regexp = RegExp.compile(RegexGwtHelper.EMAIL);
										if (regexp.exec(addType.getTextKeyword()) == null) {
											return "Email格式不正确";
										}
										return null;
									}
								});
							} else if (addType.getTextKeyword().contains("@")) {
								addType.addValidator(new Validator() {

									@Override
									public String validate(final Field<?, ?> field) {
										final RegExp regexp = RegExp.compile(RegexGwtHelper.EMAIL);
										if (regexp.exec(addType.getTextKeyword()) == null) {
											return "Email格式不正确";
										}
										return null;
									}
								});
							}
						} else if (("2").equals(target.getValue()) && (addType.getTextKeyword().length() != 40)) {
							addType.addValidator(new Validator() {

								@Override
								public String validate(final Field<?, ?> field) {
									final RegExp regexp = RegExp.compile(RegexGwtHelper.IDCARD);
									if (regexp.exec(addType.getTextKeyword()) == null) {
										return "身份证格式不正确";
									} else {
										if (IDCard.Verify(addType.getTextKeyword())) {
											return null;
										} else {
											return "身份证无效";
										}
									}
								}
							});
						}

						if (addType.validate()) {
							final Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("type", target.getValue());
							paramMap.put("keyword", addType.getTextKeyword().trim());
							listener.insertBlackManage(productId, paramMap);
							hide();
						}
					}
				} else if (("1").equals(addType.getValue())) {
					if (addType.getUploadField().getValue().length() == 0) {
						MessageBox.alert("请选择你要上传的CSV文件!");
					} else if (!addType.getUploadField().getValue().endsWith(".csv")) {
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
				}

			} else if (button == getButtonByItemId(CANCEL)) {
				hide();
			}
			super.onButtonPressed(button);
		}
	}

	@Override
	public void insertResult(final String result) {
		if (Integer.valueOf(result) > 0) {
			MessageBox.info("增加黑名单成功！");
			final BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
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
			schemaGridView.getPanel().getGrid().load(loadConfig);
		} else if (Integer.valueOf(result) == 0) {
			MessageBox.info("黑名信息已存在！");
		} else {
			MessageBox.info("增加黑名单失败！");
		}
	}

}
