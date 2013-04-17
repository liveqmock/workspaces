/**      
 * ChannelPortBox.java Create on 2012-6-2     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.config.client.ui.guide;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.iwgame.ui.panel.client.form.field.NumberBox;

/**
 * @ClassName: ChannelPortBox
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-2 下午12:32:07
 * @Version 1.0
 * 
 */
public class GuidePortBox extends Composite {

	private final FlowPanel holder;

	private final NumberBox portBox;

	private final Button btnRecommendPort;

	private final CheckBox checkbox;

	/**
	 * 
	 */
	public GuidePortBox() {
		holder = new FlowPanel();
		portBox = new NumberBox();
		portBox.setDecimal(false);
		btnRecommendPort = new Button("获取推荐端口");
		checkbox = new CheckBox("修改端口");
		holder.add(portBox);
		holder.add(btnRecommendPort);
		holder.add(checkbox);
		checkbox.setVisible(false);
		initWidget(holder);
	}

	public NumberBox getPortBox() {
		return portBox;
	}

	public Button getBtnRecommendPort() {
		return btnRecommendPort;
	}

	public CheckBox getCheckbox() {
		return checkbox;
	}

	public void modifyStatus() {
		portBox.setReadOnly(true);
		btnRecommendPort.setEnabled(false);
		checkbox.setVisible(true);
		checkbox.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (checkbox.getValue()) {
					portBox.setReadOnly(false);
					btnRecommendPort.setEnabled(true);
				} else {
					portBox.setReadOnly(true);
					btnRecommendPort.setEnabled(false);
				}
			}
		});
	}
}
