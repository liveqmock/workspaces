/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： OperLogListDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.client.ui;


import com.google.gwt.place.shared.Place;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.security.common.shared.rpc.BizLoggerService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 操作日志列表 弹出框
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-21 上午10:06:22
 */
public class OperLogListDialog extends XDialogBox implements SchemaGridViewPresenter {

	private final SchemaGridView detailsView = new SchemaGridView(10);

	private final String relId;
	private final String types;

	/**
	 * 操作日志列表
	 */
	public OperLogListDialog(final String relId, final String types, final int relIdWidth) {
		this.relId = relId;
		this.types = types;
		detailsView.setWidth(700);
		detailsView.setHeight(300);
		detailsView.setPresenter(this);
		detailsView.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"relId\",\"header\":\"ID\",\"width\":" + relIdWidth + "}"
				+ ",{\"index\":\"note\",\"header\":\"修改内容\",\"width\":340}"
				+ ",{\"index\":\"insertTimeShow\",\"header\":\"修改时间\",\"width\":130}]}}");
		initBox("修改历史记录", detailsView);
		setButtons(XDialogBox.CANCEL);
		
		detailsView.getPanel().setColumnRenderer("insertTimeShow",
				new ColumnRenderer<BaseModelData>() {
					@Override
					public void render(final int column, final int row, final BaseModelData object,
							final ColumnConfig config, final SafeHtmlBuilder sb) {
						String type = object.get("insertTime").toString();
						sb.appendHtmlConstant(type.substring(0, type.length() - 2));
					}
				});
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		detailsView.load();
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

	@Override
	public void goTo(final Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		final BaseFilterPagingLoadConfig _loadConfig = (BaseFilterPagingLoadConfig) loadConfig;
		_loadConfig.set("relId", relId);
		_loadConfig.set("types", types);
		_loadConfig.set("productId", ApplicationContext.getCurrentProductId());
		BizLoggerService.Util.get().getLogs(_loadConfig, callback);
	}

}

interface OperLogPresenter extends SchemaGridViewPresenter {

}
