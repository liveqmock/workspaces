/**
 * ManagerUserDialog.java Create on 2012-1-19
 * 
 * Copyright (c) 2012 by GreenShore Network Company: 上海绿岸网络科技有限公司(Shanghai
 * GreenShore Network Technology Co.,Ltd.)
 * 
 */

package com.iwgame.iwcloud.flume.admin.client.dialogs;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.place.shared.Place;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.iwgame.iwcloud.flume.admin.client.i18n.Messages;
import com.iwgame.ui.grid.client.SchemaGrid;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * @ClassName: ChannelDialog
 * @Description: 时段监控数据对话框
 * @author 吴江晖
 * @date 2012-1-19 下午3:30:26
 * @Version 1.0
 * 
 */
public abstract class TimeDataDialog extends XDialogBox implements SchemaGridViewPresenter {

	Messages messages = GWT.create(Messages.class);

	@Override
	public void goTo(final Place place) {
		// 没有操作
	}

	/**
	 * 获取对话框标题
	 */
	public abstract String getDialogTitle();

	public TimeDataDialog() {
		super();

		SchemaGridView appview = new SchemaGridView(-1);// 构造一个没有分页的Grid
		appview.setPresenter(this);

		appview.setWidth(600);
		appview.setHeight(300);
		appview.getPanel().getGrid().setHasCheckBoxColumn(false);

		appview.setSchemaJson(getSchemaJson());

		SchemaGrid<BaseModelData> grid = appview.getPanel().getGrid();

		// 为计数栏位加渲染器
		ColumnRenderer<BaseModelData> renderer = buildCountRenderer();
		grid.setColumnRenderer("fetcherCount", renderer);
		grid.setColumnRenderer("sinkCount", renderer);

		// 为时间栏位加渲染器
		grid.setColumnRenderer("time", buildTimeRenderer());

		initBox(getDialogTitle(), appview);

		setHideOnButtonClick(true);
		setButtons(XDialogBox.CLOSE);
		appview.load();
	}

	/**
	 * Grid Schema
	 */
	protected String getSchemaJson() {
		return "{\"table\":{\"key\":\"time\",\"columns\":["
				+ "{\"index\":\"time\",\"header\":\"时间段\",\"width\":100,\"type\":\"date\",\"format\":\"HH:mm\"},"
				+ "{\"index\":\"fetcherCount\",\"header\":\"采集计数（条）\",\"width\":150,\"type\":\"number\"},"
				+ "{\"index\":\"sinkCount\",\"header\":\"存储计数（条）\",\"width\":150,\"type\":\"number\"}" + "]}}";
	}

	/**
	 * 时间段渲染器
	 */
	protected ColumnRenderer<BaseModelData> buildTimeRenderer() {
		return new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				// 构造出8：00 - 9：00这样的格式
				Date date = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(
						object.<String> get(config.getIndex()));
				DateWrapper dw = new DateWrapper(date);
				StringBuffer buffer = new StringBuffer();
				buffer.append(DateTimeFormat.getFormat(config.getFormat()).format(dw.asDate())).append("-");
				dw = dw.addHours(1);
				buffer.append(DateTimeFormat.getFormat(config.getFormat()).format(dw.asDate()));

				if (object.getInt("fetcherCount") != object.getInt("sinkCount")) {
					// 错误格式，红色加粗提示
					sb.append(SafeHtmlUtils.fromTrustedString(messages.timeDialogErrorField(buffer.toString())));
				} else {
					sb.append(SafeHtmlUtils.fromString(buffer.toString()));
				}

			}
		};
	}

	/**
	 * 汇总数据渲染器
	 */
	protected ColumnRenderer<BaseModelData> buildCountRenderer() {
		return new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {

				StringBuffer buffer = new StringBuffer();
				// 取出值，该值为Double型，要转成Integer型
				buffer.append(object.<Double> get(config.getIndex()).intValue());
				if (object.getInt("fetcherCount") != object.getInt("sinkCount")) {
					// 错误样式，红色加粗提示
					sb.append(SafeHtmlUtils.fromTrustedString(messages.timeDialogErrorField(buffer.toString())));
				} else {
					sb.append(SafeHtmlUtils.fromString(buffer.toString()));
				}

			}
		};
	}
}
