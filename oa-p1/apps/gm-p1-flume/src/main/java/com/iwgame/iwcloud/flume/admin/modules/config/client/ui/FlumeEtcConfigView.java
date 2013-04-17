/**      
 * DetailsView.java Create on 2012-3-14     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.config.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * @ClassName: FlumeEtcConfigView
 * @Description: 组历史监控数据
 * @author 吴江晖
 * @date 2012-3-14 上午10:08:02
 * @Version 1.0
 * 
 */
public class FlumeEtcConfigView extends SchemaGridView {

	/**
	 * 
	 */
	public FlumeEtcConfigView() {
		super(-1);
		getPanel().getGrid().setHasCheckBoxColumn(false);

		ButtonToolItem btn = new ButtonToolItem("立即刷新");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				reload();
			}
		});
		
		
		getPanel().getGrid().setColumnRenderer("t_status", new ColumnRenderer<BaseModelData>(){

			@Override
			public void render(int column, int row, BaseModelData object, ColumnConfig config, SafeHtmlBuilder sb) {
				String status = object.<String> get("t_status");
				if ("0".equals(status)) {
					sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:green;'>正常</span>"));
				} else {
					sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:red;'>停用</span>"));
				}
			}
			
		});
		
		getPanel().getGrid().setColumnRenderer("t_zone", new ColumnRenderer<BaseModelData>(){

			@Override
			public void render(int column, int row, BaseModelData object, ColumnConfig config, SafeHtmlBuilder sb) {
				String status = object.<String> get("t_zone");
				if ("0".equals(status)) {
					sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:green;'>有</span>"));
				} else {
					sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:red;'>无</span>"));
				}
			}
			
		});
		
		getPanel().getGrid().setColumnRenderer("t_group", new ColumnRenderer<BaseModelData>(){

			@Override
			public void render(int column, int row, BaseModelData object, ColumnConfig config, SafeHtmlBuilder sb) {
				String status = object.<String> get("t_group");
				if ("0".equals(status)) {
					sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:green;'>有</span>"));
				} else {
					sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:red;'>无</span>"));
				}
			}
			
		});
		
		getPanel().getToolbar().addItem(btn);

	}

	private static FlumeEtcConfigView instance;

	public static FlumeEtcConfigView getInstance() {
		if (FlumeEtcConfigView.instance == null) {
			FlumeEtcConfigView.instance = new FlumeEtcConfigView();
		}
		return FlumeEtcConfigView.instance;
	}

}
