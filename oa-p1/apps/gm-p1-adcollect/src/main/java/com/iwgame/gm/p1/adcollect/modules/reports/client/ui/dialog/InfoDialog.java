/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： InfoDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.client.ui.dialog;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.ui.panel.client.box.XDialogBox;

/**
 * 类说明
 * 
 * @简述： 广告信息弹出框
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-23 上午11:37:33
 */
public class InfoDialog extends XDialogBox {

	private final Grid grid;

	public InfoDialog(List<AdvertisementInfo> result) {

		grid = new Grid(result.size() + 1, 4);
		// Add images to the grid
		for (int row = 0; row < grid.getRowCount(); row++) {
			if (row == 0) {
				grid.setStyleName("iwgame-gm-table");
				grid.setWidget(row, 0, new HTML("关键字"));
				grid.setWidget(row, 1, new HTML("广告ID"));
				grid.setWidget(row, 2, new HTML("类型"));
				grid.setWidget(row, 3, new HTML("广告链接"));
				grid.getRowFormatter().addStyleName(row, "iwgame-gm-table-row");
				grid.getCellFormatter().addStyleName(row, 0, "iwgame-gm-table-line-width1");
				grid.getCellFormatter().addStyleName(row, 1, "iwgame-gm-table-line-width2");
				grid.getCellFormatter().addStyleName(row, 2, "iwgame-gm-table-line-width3");
				grid.getCellFormatter().addStyleName(row, 3, "iwgame-gm-table-line-width4");
			} else {
				grid.setWidget(row, 0, new HTML(result.get(row - 1).getKeyword()));
				grid.setWidget(row, 1, new HTML(result.get(row - 1).getId()));
				grid.setWidget(row, 2, new HTML(result.get(row - 1).getType()));
				grid.setWidget(row, 3, new HTML(result.get(row - 1).getUrl()));
				grid.getCellFormatter().addStyleName(row, 0, "iwgame-gm-table-line-width1");
				grid.getCellFormatter().addStyleName(row, 1, "iwgame-gm-table-line-width2");
				grid.getCellFormatter().addStyleName(row, 2, "iwgame-gm-table-line-width3");
				grid.getCellFormatter().addStyleName(row, 3, "iwgame-gm-table-line-width4");
			}
		}
		initBox(result.get(0).getKeyword(), grid);
		setButtons(OK);
	}

	@Override
	protected void onButtonPressed(Button button) {
		if (button == getButtonByItemId(OK)) {
			hide();
		}
		super.onButtonPressed(button);
	}
}
