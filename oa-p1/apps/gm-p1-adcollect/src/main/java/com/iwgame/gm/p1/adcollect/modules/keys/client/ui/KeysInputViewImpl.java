/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： KeysInputViewImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.keys.client.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.adcollect.client.wiget.MediaField;
import com.iwgame.gm.p1.adcollect.modules.keys.client.ui.widget.KeywordTypeField;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.LinkColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.ExportButton;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
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
import com.iwgame.ui.panel.client.form.field.CheckBoxGroup;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.FileUploadField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述： 关键字录入视图
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-12-11 上午10:52:12
 */
public class KeysInputViewImpl  extends Composite implements KeysInputView{
	private Presenter listener;
	public SchemaGridView schemaGridView = new SchemaGridView(20);
	private final SchemaGridView detailsView = new SchemaGridView(10);

	private final VerticalPanel vPanel = new VerticalPanel(); // 总面板
	private final String productId = ApplicationContext.getCurrentProductId();
	private XFormPanel formPanel = null; // 玩家列表
	private FormLayout layout = null;

	private KeywordTypeField keyField = null;

	private InputAdDialog inputAdDialog = null; // 插入对话框

	private MediaField media = null; // 媒体
	private CheckBoxGroup keywordType = null; // 关键词类型
	private CheckBoxGroup keywordSign = null; // 关键词标识
    
	private FileUploadField batchField = null;
	private HiddenField hiddenField_productId = null;

	//批量上传
	private XUploadFormPanel modifyPanel = null; //批量修改上传panle
	private FormLayout modifylayout = null; //批量修改布局器
	private FileUploadField modifyField = null; //批量修改布局器
	private HiddenField modifyHiddenField_productId = null;

	// 添加弹出框的元素
	private XFormPanel addPanel = null; // 玩家列表
	private FormLayout addlayout = null;
	private MediaField media2 = null; // 媒体
	private TextField keyword2 = null; // 关键词
	private RadioButtonGroup keywordType2 = null; // 关键词类型
	private RadioButtonGroup keywordSign2 = null; // 关键词标识

	private RadioButtonGroup inputType = null; // 关键词录入类型
	private TextField link = null; // 关键词标识

	private ButtonToolItem query = null;// 查询
	private ButtonToolItem add = null;// 添加
	private ButtonToolItem batchModify = null;// 批量修改

	public KeysInputViewImpl() {
		formPanel = new XFormPanel("百度关键字列表管理");
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);

		media = new MediaField("媒体");
		layout.add(media);

		keywordType = new CheckBoxGroup("关键词类型", "type", Direction.Horizontal);

		keywordType.addCheckBox("A类品牌词", "A", true);
		keywordType.addCheckBox("B类竞品词", "B", true);
		keywordType.addCheckBox("C类通用词", "C", true);
		keywordType.addCheckBox("D类无关词", "D", true);
		keywordType.setAllowBlank(false);
		layout.add(2, keywordType);

		keyField = new KeywordTypeField("查询类型");
		keyField.setValidateOnBlur(true);
		layout.add(keyField);

		keywordSign = new CheckBoxGroup("关键字标识", "sign", Direction.Horizontal);
		keywordSign.addCheckBox("关键字", "关键字", true);
		keywordSign.addCheckBox("蹊径", "蹊径", true);
		keywordSign.setAllowBlank(false);

		layout.add(2, keywordSign);
         
		inputType = new RadioButtonGroup("录入类型","inputType",Direction.Horizontal);
		inputType.addRadioButton("单个录入", "0", true);
		inputType.addRadioButton("批量录入", "1", false);
		layout.add(inputType);
		
		query = new ButtonToolItem("查询");

		add = new ButtonToolItem("添加");
		batchModify = new ButtonToolItem("批量修改");

//		formPanel.addButton(query);
//		formPanel.addButton(add);
//		formPanel.addButton(batchModify);
		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(query);
		topToolbar.addItem(add);
		topToolbar.addItem(batchModify);
		schemaGridView.getPanel().setTopToolBar(topToolbar);

		vPanel.add(formPanel);
		vPanel.add(schemaGridView);
		initWidget(vPanel);

		query.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (formPanel.validate()) {
					final BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
					loadConfig.set("media", media.getValue());
					loadConfig.set("keyword", keyField.getTextKeyword().trim());
					// 精确0,模糊 1
					loadConfig.set("level", keyField.getChecked().trim());
					loadConfig.set("adid", keyField.getIdKeyword().trim());
					loadConfig.set("queryType", keyField.getType());
					loadConfig.set("type", keywordType.getValue());
					loadConfig.set("mark", keywordSign.getValue());
					loadConfig.set("productId", productId);
					// 重新加载数据
					schemaGridView.getPanel().getGrid().load(loadConfig);
				}
			}
		});

		add.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if ("0".equals(inputType.getValue())) {
					inputAdDialog = new InputAdDialog();
					inputAdDialog.center();
				}else {
					new batchAdDialog().center();
				}
			}
		});
		
		batchModify.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				new batchModifyDialog().center();
			}
		});

		// 修改
		schemaGridView.setColumnRenderer("update", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						new UpdateDialog(object).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "修改";
			}

		});

		// 状态
		schemaGridView.setColumnRenderer("status", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				if (object.get("status").equals("1")) {
					sb.appendHtmlConstant("<font color='green'>启用</font>");
				} else if (object.get("status").equals("0")) {
					sb.appendHtmlConstant("<font color='red'>禁用</font>");
				}
			}
		});

		// id
		schemaGridView.setColumnRenderer("id", new LinkColumnRenderer<BaseModelData>() {

			@Override
			public String getHref(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "#adcollect/reports/addata?adid=" + rowData.get("id");
			}

			@Override
			public String getTarget() {
				return "_self";
			}

		});

		// 修改历史
		schemaGridView.setColumnRenderer("updatehis", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {

						final String adid = object.get("id").toString();
						// 将输将玩家ip构造成LoadConfig
						final BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
						loadConfig.set("adid", adid);
						loadConfig.set("productId", productId);
						loadConfig.set("flag", "flag");
						// 重新加载数据
						detailsView.getPanel().getGrid().load(loadConfig);
						detailsView.setWidth(550);
						detailsView.setHeight(400);
						final HistoryDialog historyDialog = new HistoryDialog(loadConfig);
						historyDialog.center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看修改历史";
			}

		});
		
		
		// 导出数据
		schemaGridView.getPanel().enableExport(true);
		schemaGridView.getPanel().setExportButton(new ExportButton(schemaGridView.getPanel()) {

			/** 导出条件 */
			@Override
			public Map<String, String> getParameters() {
				final Map<String, String> parameters = new HashMap<String, String>();
				if (formPanel.validate()) {

					parameters.put("media", media.getValue());
						parameters.put("keyword", keyField.getTextKeyword().trim());
						// 精确0,模糊 1
						parameters.put("level", keyField.getChecked().trim());
						parameters.put("adid", keyField.getIdKeyword().trim());
						parameters.put("queryType", keyField.getType());
						parameters.put("type", keywordType.getValue().toString());
						parameters.put("mark", keywordSign.getValue().toString());
						parameters.put("productId", productId);
				}
				return parameters;
			}

			/** 指定到出的setvice */
			@Override
			public String getExportBean() {
				return "gm-p1-ad-reports-KeysExportService";
			}

			/** 导出数据列名的映射 */
			@Override
			public Map<String, String> getColumns() {
				final Map<String, String> columns = new LinkedHashMap<String, String>();
				columns.put("addtime", "添加日期");
				columns.put("type", "分类");
				columns.put("keyword", "关键字");
				columns.put("id", "广告ID");
				columns.put("mark", "标识");
				columns.put("status", "状态");
				columns.put("url", "广告链接");
				return columns;
			}

			/** 生成报名名成 */
			@Override
			public String getFilename() {
				final StringBuilder sb = new StringBuilder();
				sb.append(productId.equals("1.5")?"醉逍遥":"蜀门");
				sb.append("关键字_");
				sb.append(DateTimeFormat.getFormat("yyyyMMddHHmmss").format(new Date()));// 导出时间
				return sb.toString();
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
		schemaGridView.setSchemaJson("{\"table\":{\"key\":\"addtime\",\"columns\":["
				+ "{\"index\":\"addtime\",\"header\":\"添加日期\",\"width\":100}"
				+ ",{\"index\":\"type\",\"header\":\"分类\",\"width\":100}"
				+ ",{\"index\":\"keyword\",\"header\":\"关键字\",\"width\":150}"
				+ ",{\"index\":\"id\",\"header\":\"广告ID\",\"width\":100,\"type\":\"link\"}"
				+ ",{\"index\":\"mark\",\"header\":\"标识\",\"width\":100}"
				+ ",{\"index\":\"status\",\"header\":\"状态\",\"width\":100}"
				+ ",{\"index\":\"url\",\"header\":\"广告链接\",\"width\":250}"
				+ ",{\"index\":\"update\",\"header\":\"修改\",\"type\":\"button\",\"width\":100}"
				+ ",{\"index\":\"updatehis\",\"header\":\"查看历史\",\"type\":\"button\",\"width\":100}]}}");
		return schemaGridView;

	}

	// 录入增加的弹出框
	// 内部类 弹出的对话框
	class InputAdDialog extends XDialogBox {

		public InputAdDialog() {

			addPanel = new XFormPanel();
			addlayout = new FormLayout();
			addPanel.setLayout(addlayout);

			media2 = new MediaField("请选择媒体");
			addlayout.add(media2);

			keyword2 = new TextField("请输入关键词文本");
			addlayout.add(keyword2);

			keywordType2 = new RadioButtonGroup("关键词类型", "type", Direction.Horizontal);
			keywordType2.addRadioButton("A类品牌词", "A", true);
			keywordType2.addRadioButton("B类竞品词", "B", false);
			keywordType2.addRadioButton("C类通用词", "C", false);
			keywordType2.addRadioButton("D类无关词", "D", false);
			addlayout.add(keywordType2);

			keywordSign2 = new RadioButtonGroup("关键字标识", "sign", Direction.Horizontal);
			keywordSign2.addRadioButton("关键字", "关键字", true);
			keywordSign2.addRadioButton("蹊径", "蹊径", false);
			addlayout.add(keywordSign2);

			link = new TextField("请输入链接");
			link.setWidth("450px");
			addlayout.add(link);
			addPanel.setWidth("600px");
			initBox("关键字录入", addPanel);
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
				if (("").equals(keyword2.getValue().trim())) {
					MessageBox.alert("关键字不能为空");
				} else if (("").equals(link.getValue().trim())) {
					MessageBox.alert("链接不能为空");
				} else {
					final Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("keyword", keyword2.getValue());
					paramMap.put("type", keywordType2.getValue());
					paramMap.put("mark", keywordSign2.getValue());
					paramMap.put("media", media2.getValue());
					paramMap.put("link", link.getValue().trim());
					paramMap.put("productId", productId);
					// 重新加载数据
					listener.insertAdInfo(paramMap);
				}
			} else if (button == getButtonByItemId(CANCEL)) {
				hide();
			}
			super.onButtonPressed(button);
		}
	}
    
	// 录入增加的弹出框
	// 内部类 弹出的对话框
	class batchAdDialog extends XDialogBox {

		public batchAdDialog() {

			addPanel = new XUploadFormPanel("", "batchUploadProcessor");
			addlayout = new FormLayout();
			addPanel.setLayout(addlayout);

			batchField = new FileUploadField("导入文本", "batch");
			addlayout.add(batchField);

			hiddenField_productId = new HiddenField("hiddenField_productId", productId);
			hiddenField_productId.setValue(productId);
			addlayout.add(hiddenField_productId);

			addPanel.setWidth("600px");
			initBox("批量导入关键字", addPanel);
			initBox("批量导入关键字", new HTML(getSampleCSVDesc()));
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
	
	
	/**
	 * 批量修改类弹出框
	 * 
	 * @author Administrator
	 */
	class batchModifyDialog extends XDialogBox {

		public batchModifyDialog() {
			modifyPanel = new XUploadFormPanel("", "batchModifyProcessor");
			modifylayout = new FormLayout();
			modifyPanel.setLayout(modifylayout);

			modifyField = new FileUploadField("导入文本", "batch");
			modifylayout.add(modifyField);

			modifyHiddenField_productId = new HiddenField("hiddenField_productId", productId);
			modifyHiddenField_productId.setValue(productId);
			modifylayout.add(modifyHiddenField_productId);

			modifyPanel.setWidth("600px");
			initBox("批量修改百度关键字", modifyPanel);
			initBox("批量修改百度关键字", new HTML(getModifyCSVDesc()));
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
				if (modifyField.getValue().length() == 0) {
					MessageBox.alert("请选择你要上传的CSV文件!");
				} else if (!modifyField.getValue().endsWith(".csv")) {
					MessageBox.alert("文件格式错误,请选CSV文件!");
				} else {
					MessageBox.confirm("确认要导入吗?", new MessageBoxHandler() {
						@Override
						public void onClose(final MessageBoxEvent event) {
							final XButton button = event.getButton();
							if (button.getItemId().equals(XDialogBox.YES)) {
								modifyPanel.submit();
								modifyPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {

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
	
	
	
	// 修改信息弹出框
	class UpdateDialog extends XDialogBox {

		RadioButtonGroup updateType = null;
		RadioButtonGroup updateMark = null;
		TextField updateLink = null; // 原始URL
		TextField newLink = null; // 新URL
		TextField newLinkPreview = null; // 新URL预览
		RadioButtonGroup status = null;
		String adid = "";
		XFormPanel updatePanel = null;

		public UpdateDialog(final BaseModelData object) {

			adid = object.get("id").toString();

			updatePanel = new XFormPanel();
			final FormLayout updatelayout = new FormLayout();
			updatePanel.setLayout(updatelayout);

			updateType = new RadioButtonGroup("分类", "updateType", Direction.Horizontal);
			final String updateTypeVale = object.get("type");

			if (updateTypeVale.equals("A")) {
				updateType.addRadioButton("A", "A", true);
				updateType.addRadioButton("B", "B", false);
				updateType.addRadioButton("C", "C", false);
				updateType.addRadioButton("D", "D", false);
			} else if (updateTypeVale.equals("B")) {
				updateType.addRadioButton("A", "A", false);
				updateType.addRadioButton("B", "B", true);
				updateType.addRadioButton("C", "C", false);
				updateType.addRadioButton("D", "D", false);
			} else if (updateTypeVale.equals("C")) {
				updateType.addRadioButton("A", "A", false);
				updateType.addRadioButton("B", "B", false);
				updateType.addRadioButton("C", "C", true);
				updateType.addRadioButton("D", "D", false);
			} else if (updateTypeVale.equals("D")) {
				updateType.addRadioButton("A", "A", false);
				updateType.addRadioButton("B", "B", false);
				updateType.addRadioButton("C", "C", false);
				updateType.addRadioButton("D", "D", true);
			}

			updateType.setAllowBlank(false);
			updatelayout.add(updateType);

			updateMark = new RadioButtonGroup("标识 ", "updateMark", Direction.Horizontal);
			final String updateMarkValue = object.get("mark");
			final boolean b = ("关键字").equals(updateMarkValue) ? true : false;
			updateMark.addRadioButton("关键字", "关键字", b);
			updateMark.addRadioButton("蹊径", "蹊径", !b);
			updateMark.setAllowBlank(false);
			updatelayout.add(updateMark);

			updateLink = new TextField("原始URL");
			updateLink.getWidget().setReadOnly(true);
			updateLink.setWidth("450px");
			updateLink.setValue(object.get("url").toString());
			updatelayout.add(updateLink);

			newLink = new TextField("请输入新链接");
			newLink.setWidth("450px");
			newLink.getWidget().addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(final ChangeEvent event) {
					newLinkPreview.setValue(getNewLinkPreview(updateLink.getValue().trim(), newLink.getValue().trim()));
				}
			});
			updatelayout.add(newLink);

			newLinkPreview = new TextField("新URL预览");
			newLinkPreview.setWidth("450px");
			newLinkPreview.getWidget().setReadOnly(true);
			updatelayout.add(newLinkPreview);

			status = new RadioButtonGroup("状态", "status", Direction.Horizontal);

			final String statusValue = object.get("status");
			final boolean a = ("1").equals(statusValue) ? true : false;
			status.addRadioButton("禁用", "0", !a);
			status.addRadioButton("启用", "1", a);
			updatelayout.add(status);

			updatePanel.setWidth("600px");
			initBox("修改信息", updatePanel);
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
				if (updatePanel.validate()) {
					final Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("type", updateType.getValue().trim().toUpperCase());
					paramMap.put("mark", updateMark.getValue().trim());
					paramMap.put("link", newLinkPreview.getValue().trim());
					paramMap.put("status", status.getValue());
					paramMap.put("adid", adid);
					paramMap.put("productId", productId);
					// 重新加载数据
					listener.updateAdInfo(paramMap);
					hide();
				}
			} else if (button == getButtonByItemId(CANCEL)) {
				hide();
			}
			super.onButtonPressed(button);
		}

		public String getNewLinkPreview(final String oldURL, String newURL) {
			String adid = "";
			if (oldURL.indexOf("?stat") != -1) {
				adid = oldURL.substring(oldURL.indexOf("?stat"));
			}
			String newURLPreview = "";
			if (!("").equals(newURL)) {
				// 如果以http:// 开头 或者 /?stat="" 结尾
				if (newURL.startsWith("http://")) {
					newURL = newURL.substring(7);
				}
				if (newURL.indexOf("?stat") != -1) {
					newURL = newURL.substring(0, newURL.indexOf("?stat"));
				}
				newURLPreview = "http://" + newURL + adid;
			} else {
				newURLPreview = oldURL;
			}
			return newURLPreview;
		}
	}

	// 修改历史
	class HistoryDialog extends XDialogBox {
		public HistoryDialog(final BaseModelData object) {

			initBox("修改历史记录,广告ID是" + object.get("adid").toString(), detailsView);

			setButtons(XDialogBox.CANCEL);
		}

		/**
		 * 重写XDialog的 按钮方法
		 * 
		 * 
		 */
		@Override
		protected void onButtonPressed(final Button button) {
			if (button == getButtonByItemId(OK)) {
			} else if (button == getButtonByItemId(CANCEL)) {
				hide();
			}
			super.onButtonPressed(button);
		}
	}

	@Override
	public void insertAdInfoResult(final Integer result) {
		if (result > 0) {
			inputAdDialog.hide();
			MessageBox.info("增加广告信息成功！");
			final BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
			loadConfig.set("media", media.getValue());
			loadConfig.set("keyword", keyField.getTextKeyword().trim());
			// 精确0,模糊 1
			loadConfig.set("level", keyField.getChecked().trim());
			loadConfig.set("adid", keyField.getIdKeyword().trim());
			loadConfig.set("queryType", keyField.getType());
			loadConfig.set("type", keywordType.getValue());
			loadConfig.set("mark", keywordSign.getValue());
			loadConfig.set("productId", productId);
			schemaGridView.getPanel().getGrid().load(loadConfig);
		} else {
			MessageBox.info("增加广告信息失败！");
		}
	}

	@Override
	public void updateAdInfoResult(final Integer result) {
		if (result > 0) {
			MessageBox.info("修改广告信息成功！");
			schemaGridView.getPanel().getGrid().load();
		} else {
			MessageBox.info("修改失败！");
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
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 媒体名 </th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 关键字文本 </th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 关键词类型 </th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 关键词标识 </th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 关键词链接 </th>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
		stringBuilder.append("<td> 醉逍遥百度关键字 </td>");
		stringBuilder.append("<td> 百度 </td>");
		stringBuilder.append("<td>  A</td>");
		stringBuilder.append("<td> 关键字 </td>");
		stringBuilder.append("<td> www.baidu.com </td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("</tbody></table>");
		stringBuilder.append("<a href = \"" + GWT.getHostPageBaseURL() + "template/adbatch-sample.csv" + "\">模版下载</a>");

		return stringBuilder.toString();
	}

	/**
	 * 取得CSV格式文件样例
	 * 
	 * @return
	 */
	private String getModifyCSVDesc() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<hr/>");
		stringBuilder.append("<bgcolor=red>CSV约定的格式如下(仅限数据,不能带表头,表头仅供参考)：");
		stringBuilder.append("<table border=\"0\"><tbody>");
		stringBuilder.append("<tr>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 媒体名</th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 分类 </th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 关键字</th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 广告ID </th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 标识 </th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 状态(0:禁用,1:启用)</th>");
		stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 广告链接</th>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
		stringBuilder.append("<td> 醉逍遥百度关键字 </td>");
		stringBuilder.append("<td> D</td>");
		stringBuilder.append("<td> 测试</td>");
		stringBuilder.append("<td> 123111111</td>");
		stringBuilder.append("<td> 关键字 </td>");
		stringBuilder.append("<td> 1 </td>");
		stringBuilder.append("<td> www.baidu.com </td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("</tbody></table>");
		stringBuilder.append("<a href = \"" + GWT.getHostPageBaseURL() + "template/batchModifyBaiduKeyword-sample.csv" + "\">模版下载</a>");

		return stringBuilder.toString();
	}

	
	
	@Override
	public SchemaGridView getDetailsView() {
		detailsView.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				// + "{\"index\":\"relId\",\"header\":\"媒体ID\",\"width\":120}"
				+ "{\"index\":\"note\",\"header\":\"修改内容\",\"width\":300}"
				+ ",{\"index\":\"insertTime\",\"header\":\"修改时间\",\"width\":150}]}}");
		return detailsView;
	}

}
