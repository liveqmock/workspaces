/**      
 * AccountMgrView.java Create on 2012-10-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.modules.baidu.account.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.iwgame.ui.grid.client.cell.actions.Action;
import com.iwgame.ui.grid.client.cell.actions.ActionsCell;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * @ClassName: AccountMgrView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-10-11 下午03:29:21
 * @Version 1.0
 * 
 */
public class AccountMgrView extends SchemaGridView {

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public AccountMgrView() {
		super(-1);
		getPanel().setHeader("百度帐号管理");

		final ButtonToolItem btnViewer = new ButtonToolItem("查看");

		btnViewer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				getPanel().reload();
			}
		});
		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(btnViewer);
		getPanel().setTopToolBar(topToolbar);

		final Action<BaseModelData> passwordModifyAction = new Action<BaseModelData>() {

			@Override
			public String getText(final BaseModelData data) {
				return "修改";
			}

			@Override
			public String getStyleName(final BaseModelData data) {
				return Styles.style().edit();
			}

			@Override
			public void execute(final BaseModelData data) {
				new ModifyDialog(data, AccountMgrView.this).center();
			}
		};

		getGrid().addActionsCell(new ActionsCell<BaseModelData>(passwordModifyAction));
	}

}
