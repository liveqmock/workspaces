/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： PayableMgrListView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.PayableMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.OperationPayableDialog;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.SchemaGrid.SelectMode;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.MessageBoxEvent;
import com.iwgame.ui.panel.client.box.MessageBoxHandler;
import com.iwgame.ui.panel.client.box.XButton;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;

/**
 * 类说明
 * 
 * @简述：合同抬头列表视图
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午12:01:02
 */
public class PayableMgrListView extends SchemaGridView {

	private PayableMgrPresenter presenter;

	/**
	 * @return 获取 presenter
	 */
	public PayableMgrPresenter getPresenter() {
		return presenter;
	}

	/**
	 * @param presenter
	 *            设置 presenter
	 */
	public void setPresenter(final PayableMgrPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private final XFormPanel formPanel;
	private final FormLayout layout;

	public PayableMgrListView() {
		super(25);
		getPanel().getGrid().setHasCheckBoxColumn(true);
		//  设置 Select 只能选一个
		setSelectMode(SelectMode.Single);
		formPanel = new XFormPanel("合同抬头列表");
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(4);
		formPanel.setLayout(layout);

		final ButtonToolItem addBtn = new ButtonToolItem("添加");
		addBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				// 调用添加的Dialog
				new OperationPayableDialog(true, PayableMgrListView.this).center();
			}
		});
		final ButtonToolItem updateBtn = new ButtonToolItem("修改");
		updateBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if ((getPanel().getGrid().getSelected() == null)
						|| getPanel().getGrid().getSelected().isEmpty()) {
					MessageBox.alert("请选择一个合同抬头！");
				} else if (getPanel().getGrid().getSelected().size() > 1) {
					MessageBox.alert("只能选择一个合同抬头修改信息！");
				} else {
					// 调用修改的 Dialog
					new OperationPayableDialog(false, PayableMgrListView.this).center();
				}
			}
		});
		final ButtonToolItem deleteBtn = new ButtonToolItem("删除");
		deleteBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if ((getPanel().getGrid().getSelected() == null)
						|| getPanel().getGrid().getSelected().isEmpty()) {
					MessageBox.alert("请选择一个合同抬头！");
				} else if (getPanel().getGrid().getSelected().size() > 1) {
					MessageBox.alert("只能选择一个合同抬头删除信息！");
				} else {

					MessageBox.confirm("确认删除"
							+ getGrid().getSelected().get(0).get("title").toString(),
							new MessageBoxHandler() {

								@Override
								public void onClose(final MessageBoxEvent event) {

									final XButton button = event.getButton();
									if (button.getItemId().equals(XDialogBox.YES)) {
										final Double d = getGrid().getSelected().get(0).get("id");
										presenter.deletePayable(d.intValue(),
												new AsyncCallbackEx<Integer>() {

													@Override
													public void onSuccess(final Integer result) {
														if (result == 1) {
															getGrid().load();
														} else {
															MessageBox.alert("删除失败");
														}
													}
												});
									}
								}
							});

				}
			}
		});

		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(addBtn);
		topToolbar.addItem(updateBtn);
		topToolbar.addItem(deleteBtn);
		getPanel().setTopToolBar(topToolbar);
	}
}
