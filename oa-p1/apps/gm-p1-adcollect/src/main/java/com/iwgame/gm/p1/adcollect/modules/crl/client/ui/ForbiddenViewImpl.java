/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ForbiddenView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.crl.client.ui;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.LinkColumnRenderer;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-9-24 上午09:32:23
 */
public class ForbiddenViewImpl extends Composite implements ForbiddenView {
	private Presenter listener;
	public SchemaGridView schemaGridView = new SchemaGridView(50);

	private final VerticalPanel vPanel = new VerticalPanel(); // 总面板
	private final String productId = ApplicationContext.getCurrentProductId();

	public ForbiddenViewImpl() {

		vPanel.add(schemaGridView);
		initWidget(vPanel);

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

		// 修改历史
		schemaGridView.setColumnRenderer("operate", new ButtonColumnRenderer<BaseModelData>() {

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
				return "操作";
			}

		});

		// id
		schemaGridView.setColumnRenderer("count", new LinkColumnRenderer<BaseModelData>() {

			@Override
			public String getHref(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "#adcollect/crl/list?id=" + rowData.get("id");
			}

			@Override
			public String getTarget() {
				return "_self";
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
		schemaGridView.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"ID\",\"width\":100,\"visiable\":false}"
				+ ",{\"index\":\"project\",\"header\":\"项目\",\"width\":100}"
				+ ",{\"index\":\"count\",\"header\":\"数量\",\"width\":100,\"type\":\"link\"}"
				+ ",{\"index\":\"status\",\"header\":\"状态\",\"width\":100}"
				+ ",{\"index\":\"operate\",\"header\":\"操作\",\"type\":\"button\",\"width\":100}]}}");
		return schemaGridView;

	}

	// 修改信息弹出框
	class UpdateDialog extends XDialogBox {

		String id = "";
		RadioButtonGroup status = null;
		XFormPanel updatePanel = null;

		public UpdateDialog(final BaseModelData object) {
			id = object.get("id");

			updatePanel = new XFormPanel();
			final FormLayout updatelayout = new FormLayout();
			updatePanel.setLayout(updatelayout);

			final String statusValue = object.get("status");
			final boolean a = ("1").equals(statusValue) ? true : false;
			status = new RadioButtonGroup("状态", "status", Direction.Horizontal);
			status.addRadioButton("禁用", "0", !a);
			status.addRadioButton("启用", "1", a);
			updatelayout.add(status);

			updatePanel.setWidth("600px");
			initBox("修改状态", updatePanel);
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
				listener.updateStatus(productId, id, status.getValue());
				hide();
			} else if (button == getButtonByItemId(CANCEL)) {
				hide();
			}
			super.onButtonPressed(button);
		}
	}

	@Override
	public void updateResult(String result) {
		if (Integer.valueOf(result) > 0) {
			MessageBox.info("修改状态成功！");
			schemaGridView.getPanel().getGrid().load();
		} else {
			MessageBox.info("修改失败！");
		}
	}

}
