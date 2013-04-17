/**      
 * DetailsView.java Create on 2012-3-14     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.config.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;

/**
 * @ClassName: FlumeNodeView
 * @Description: 组历史监控数据
 * @author 吴江晖
 * @date 2012-3-14 上午10:08:02
 * @Version 1.0
 * 
 */
public class FlumeNodeView extends SchemaGridView {

	/**
	 * 
	 */
	public FlumeNodeView() {
		super(-1);
		getPanel().getGrid().setHasCheckBoxColumn(false);

		ButtonToolItem btn = new ButtonToolItem("立即刷新");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				reload();
			}
		});
		getPanel().getToolbar().addItem(btn);

	}

	private static FlumeNodeView instance;

	public static FlumeNodeView getInstance() {
		if (FlumeNodeView.instance == null) {
			FlumeNodeView.instance = new FlumeNodeView();
		}
		return FlumeNodeView.instance;
	}

}
