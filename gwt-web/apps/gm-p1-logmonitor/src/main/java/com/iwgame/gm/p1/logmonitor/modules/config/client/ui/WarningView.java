/**      
 * WarningView.java Create on 2012-9-5     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.config.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.gm.p1.logmonitor.modules.config.client.ui.dialogs.ChangeDialog;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * @ClassName: WarningView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-5 下午12:10:16
 * @Version 1.0
 * 
 */
public class WarningView extends SchemaGridView {

	private final XFormPanel formPanel;
	private final FormLayout layout;

	/**
	 * 
	 */
	public WarningView() {
		super(15);

		getPanel().getGrid().setHasCheckBoxColumn(true);

		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		formPanel.setLayout(layout);

		ButtonToolItem btnRefresh = new ButtonToolItem("查询");
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				reload();
			}
		});

		formPanel.addButton(btnRefresh);

		ButtonToolItem btnUpdate = new ButtonToolItem("修改");
		btnUpdate.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {

				if (getGrid().getSelected().isEmpty()) {
					MessageBox.alert("请选择一个需要修改的策略");
				} else if (getGrid().getSelected().size() > 1) {
					MessageBox.alert("一次只能选择一个策略来进行修改");
				} else {
					new ChangeDialog(WarningView.this).center();
				}
			}
		});

		formPanel.addButton(btnUpdate);

		getPanel().getToolbar().addItem(formPanel);

		getGrid().setColumnRenderer("status", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				int s = object.<Double> get(config.getIndex()).intValue();
				if (s == 0) {
					sb.append(SafeHtmlUtils.fromString("启用"));
				} else {
					sb.append(SafeHtmlUtils.fromString("停用"));
				}

			}
		});
	}
}
