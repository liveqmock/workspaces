/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LandingView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.landing.client.ui;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.iwgame.gm.p1.adcollect.client.wiget.dialog.OperLogListDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.InfoDialog;
import com.iwgame.gm.p1.adcollect.modules.landing.client.presenter.LandingPresenter;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.dialog.OperDialog;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * 类说明
 * 
 * @简述： 到达页视图
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-21 下午04:18:29
 */
public class LandingView extends SchemaGridView {

	private LandingPresenter presenter;

	/**
	 * @return the presenter
	 */
	public LandingPresenter getPresenter() {
		return presenter;
	}

	/**
	 * @param presenter
	 *            the presenter to set
	 */
	public void setPresenter(final LandingPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;

	public LandingView() {
		super(25);

		formPanel = new XFormPanel("广告到达页管理");
		getPanel().getGrid().setHasCheckBoxColumn(true);

		final ButtonToolItem addBtn = new ButtonToolItem("添加");

		addBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				new OperDialog(true, LandingView.this).center();
			}
		});
		final ButtonToolItem updateBtn = new ButtonToolItem("修改");

		updateBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if ((getPanel().getGrid().getSelected() == null) || getPanel().getGrid().getSelected().isEmpty()) {
					MessageBox.alert("请选择一个到达页！");
				} else if (getPanel().getGrid().getSelected().size() > 1) {
					MessageBox.alert("只能选择一个到达页修改信息！");
				} else {
					new OperDialog(false, LandingView.this).center();
				}
			}
		});

		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(addBtn);
		topToolbar.addItem(updateBtn);
		getPanel().setTopToolBar(topToolbar);

		// 渲染状态
		getPanel().setColumnRenderer("statsShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				final Double stats = object.get("status");
				if (stats.intValue() == 1) {
					sb.appendHtmlConstant("使用");
				} else if (stats.intValue() == 0) {
					sb.appendHtmlConstant("暂停");
				}
			}
		});

		getGrid().setColumnRenderer("logger", new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						final Double id = object.get("id");
						new OperLogListDialog((id.intValue() + "").trim(), "LANDING", 60).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
			}

		});
		
		getGrid().setColumnRenderer("adUrlShow", new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						final String link = object.get("link").toString();
						new InfoDialog(true,object.get("name")+ "的广告链接信息", link).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
			}
		});

	}
}
