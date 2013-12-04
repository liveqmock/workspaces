/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： DictionaryMgrView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.explain.client.ui;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.iwgame.gm.p1.adcollect.modules.explain.client.presenter.DictionaryMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.explain.client.ui.dialog.DownLoadDialog;
import com.iwgame.gm.p1.adcollect.modules.explain.client.ui.dialog.UploadDictionary;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
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
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * @Description: 数字字典视图类
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-20 上午10:15:17
 */
public class DictionaryMgrView extends SchemaGridView {

	private DictionaryMgrPresenter presenter;

	public DictionaryMgrPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(DictionaryMgrPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	private ButtonToolItem upload;
	private ButtonToolItem delBtn;

	public DictionaryMgrView() {
		// 加载选择框
		getPanel().getGrid().setHasCheckBoxColumn(true);

		formPanel = new XFormPanel("使用管理");
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);

		upload = new ButtonToolItem("上传");
		upload.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.chenkUploadAuthority(new AsyncCallbackEx<Void>() {

					@Override
					public void onSuccess(Void result) {
						new UploadDictionary(DictionaryMgrView.this).center();
					}
				});
			}
		});
		delBtn = new ButtonToolItem("删除");
		delBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (!getGrid().getSelected().isEmpty()) {
					MessageBox.confirm("您确定需要删除所选的用户吗？", new MessageBoxHandler() {

						@Override
						public void onClose(MessageBoxEvent event) {
							XButton btn = event.getButton();
							if (btn.getItemId().equals(XDialogBox.YES)) {
								StringBuilder sb = new StringBuilder();
								for (BaseModelData data : getGrid().getSelected()) {
									Double id = data.get("id");
									sb.append(id.intValue()).append(",");
								}

								sb.deleteCharAt(sb.length() - 1);
								presenter.delDictionaryByIds(sb.toString(), new AsyncCallbackEx<Integer>() {

									@Override
									public void onSuccess(Integer result) {
										if (result == getGrid().getSelected().size()) {
											getGrid().load();
										} else {
											MessageBox.alert("删除出现错误");
										}
									}
								});
							}
						}
					});
				} else {
					MessageBox.alert("请选择需要删除的用户！");
				}
			}
		});

		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(upload);
		topToolbar.addItem(delBtn);
		getPanel().setTopToolBar(topToolbar);

		getPanel().setColumnRenderer("pathShow", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						presenter.chenkDownLoadAuthority(new AsyncCallbackEx<Void>() {

							@Override
							public void onSuccess(Void result) {
								new DownLoadDialog(object.get("name").toString() + "信息", object.get("path").toString())
										.center();
							}
						});
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "下载";
			}
		});
	}

}
