/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： RoleInfoGridView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.query.client.ui.xdialog;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityRegisInfoQueryService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
import com.iwgame.ui.panel.client.box.XDialogBox;
/**
 * 类说明
 * @简述： 账号加载角色信息
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-28 下午05:58:28
 */
public class RoleInfoDialogBox extends XDialogBox implements SchemaGridViewPresenter{
	private VerticalPanel vPanel = new VerticalPanel();
	private SchemaGridView view = new SchemaGridView(-1);
	private String accountId;
	private String pid;

	public RoleInfoDialogBox(String accountId,String pid){
		this.accountId = accountId;
		this.pid = pid;
		this.initView();
		vPanel.add(view);
		setButtons(CANCEL);
		getButtonByItemId(CANCEL).setText("关闭");
		setHideOnButtonClick(true);
		initBox("角色信息", vPanel);
	}

	public void initView(){
		view.setPresenter(this);
		view.setWidth(500);
		view.setHeight(300);
		view.setSchemaJson("{\"table\":{\"key\":\"dbid\",\"columns\":["
				+ "{\"index\":\"dbid\",\"header\":\"DBID\",\"width\":200}"
				+ ",{\"index\":\"svr\",\"header\":\"区组\",\"width\":100}"
				+ ",{\"index\":\"name\",\"header\":\"角色\",\"width\":150}"
				+ ",{\"index\":\"level\",\"header\":\"等级\",\"width\":50}"
				+ "]}}");
		
		view.load(true);
	}
	
	@Override
	public void loadData(Object loadConfig, 
			AsyncCallbackEx<String> callback) {
		BaseFilterPagingLoadConfig config = (BaseFilterPagingLoadConfig) loadConfig;
		config.set("accountId", accountId);
		config.set("pid",pid);
		SecurityRegisInfoQueryService.Util.get().loadRoles(config, callback);
	}
	
	@Override
	public void goTo(Place place) {}
}
