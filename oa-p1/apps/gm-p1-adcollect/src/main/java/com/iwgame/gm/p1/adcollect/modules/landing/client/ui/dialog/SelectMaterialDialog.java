/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： SelectMaterialDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.landing.client.ui.dialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.events.MaterialSelectedEvent;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.SchemaGrid.SelectMode;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.AppUtils;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 选择素材
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-25 下午03:52:58
 */
public class SelectMaterialDialog extends XDialogBox implements SelectMaterialPresenter {

	private final SchemaGridView materialView = new SchemaGridView(25);

	public SelectMaterialDialog() {
		materialView.setPresenter(this);
		XFormPanel formPanel;
		FormLayout layout;
		formPanel = new XFormPanel();
		formPanel.setWidth("100%");
		layout = new FormLayout();
		formPanel.setLayout(layout);

		final TextField name = new TextField("素材名称");
		layout.add(name);

		final ButtonToolItem querybtn = new ButtonToolItem("查询");
		querybtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				final BaseFilterPagingLoadConfig loadConfig = materialView.getPanel().getGrid()
						.getLoadConfig();
				if (!name.getValue().equals("")) {
					loadConfig.set("name", name.getValue());
				} else {
					loadConfig.set("name", null);
				}
				materialView.getGrid().load();
			}
		});

		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(querybtn);

		materialView.getPanel().setTopToolBar(topToolbar);
		// materialView.getGrid().setSelectionModel(new SingleSelectionModel());
		materialView
				.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
						+ "{\"index\":\"id\",\"header\":\"ID\",\"width\":100},"
						+ "{\"index\":\"name\",\"header\":\"素材名\",\"width\":120},"
						+ "{\"index\":\"type\",\"header\":\"素材类型\",\"width\":100},"
						+ "{\"index\":\"media\",\"header\":\"素材\",\"width\":60,\"type\":\"image\",\"downloadbean\":\"ad.download\"},"
						+ "{\"index\":\"width\",\"header\":\"宽度\",\"width\":60,\"type\":\"number\"},"
						+ "{\"index\":\"height\",\"header\":\"高度\",\"width\":60,\"type\":\"number\"},"
						+ "{\"index\":\"capacity\",\"header\":\"素材容量\",\"width\":80,\"type\":\"number\"},"
						+ "{\"index\":\"note\",\"header\":\"素材说明\",\"width\":100},"
						+ "{\"index\":\"addTime\",\"header\":\"添加日期\",\"width\":130,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm\"}"
						+ "]}}");

		materialView.getGrid().setHasCheckBoxColumn(true);
		materialView.setSelectMode(SelectMode.Single);
		materialView.load();
		materialView.setWidth(850);
		materialView.setHeight(450);

		initBox("选择素材", materialView);
		setButtons(OKCANCEL);
	}

	/**
	 * 重写XDialog的 按钮方法
	 * 
	 * 
	 */
	@Override
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(OK)) {
			if ((materialView.getGrid().getSelected() == null)
					|| materialView.getGrid().getSelected().isEmpty()) {
				MessageBox.alert("请选择一个素材！");
			} else if (materialView.getGrid().getSelected().size() > 1) {
				MessageBox.alert("只能选择一个素材！");
			} else {
				final String name = materialView.getGrid().getSelected().get(0).get("name")
						.toString();
				final String id = materialView.getGrid().getSelected().get(0).get("id").toString();
				AppUtils.EVENT_BUS.fireEvent(new MaterialSelectedEvent(name, id));
				hide();
			}

		} else if (button == getButtonByItemId(CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
	}

	@Override
	public void goTo(final Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		AdminMgrService.Util.get().getMaterialList(ApplicationContext.getCurrentProductId(),
				(BaseFilterPagingLoadConfig) loadConfig, callback);
	}
}

interface SelectMaterialPresenter extends SchemaGridViewPresenter {

}