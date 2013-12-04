/**      
* AccountDataGridView.java Create on 2012-11-27     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.client.ui.grid;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.iwgame.gm.p1.security.common.shared.util.ConstantShared;
import com.iwgame.gm.p1.security.common.shared.util.PathBuilder;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityRegisInfoQueryView.SecurityRegisInfoQueryPresenter;
import com.iwgame.gm.p1.security.modules.query.client.ui.xdialog.RoleInfoDialogBox;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;
/** 
 * @简述: 账户资料表格
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-27 下午12:20:56 
 */
public class AccountDataGridView extends SchemaGridView{

	public AccountDataGridView() {
		super();
	}
	public AccountDataGridView(int pageSize,SecurityRegisInfoQueryPresenter presenter) {
		super(pageSize);
		setPresenter(presenter);
		setSchemaJson(PathBuilder
				.joinLine(
						"{\"table\":{\"key\":\"accountId\",\"columns\":[",
						"{\"index\":\"accountId\",\"header\":\"账号ID\",\"width\":50,\"visiable\":false}",
						",{\"index\":\"userName\",\"header\":\"账号\",\"type\":\"button\",\"width\":200}",
						",{\"index\":\"accountStatus\",\"header\":\"账号状态\",\"width\":100,\"sortable\":false}",
						",{\"index\":\"registerTime\",\"header\":\"注册时间\",\"width\":150,\"sortable\":true}",
						",{\"index\":\"registerIp\",\"header\":\"注册IP\",\"width\":150,\"sortable\":true}",
						",{\"index\":\"registerSourcetype\",\"header\":\"注册来源\",\"width\":100}",
						",{\"index\":\"realName\",\"header\":\"注册姓名\",\"width\":100}",
						",{\"index\":\"idcard\",\"header\":\"身份证\",\"width\":200}",
						",{\"index\":\"idcardStatus\",\"header\":\"身份证状态\",\"width\":100}",
						",{\"index\":\"userEmail\",\"header\":\"注册邮箱\",\"width\":200}",
						",{\"index\":\"maxLevel\",\"header\":\"最高等级\",\"width\":100,\"type\":\"number\",\"sortable\":true}",
						",{\"index\":\"totalPaid\",\"header\":\"充值钻石\",\"width\":100,\"type\":\"number\",\"sortable\":true}",
						",{\"index\":\"password4\",\"header\":\"四位密码\",\"width\":350}",
						"]}}"));
		this.getPanel().enableExport("accountDataExportQuery");
		
		this.setColumnRenderer("registerIp", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
				String ip = object.get("registerIp");
				if(!"".equals(ip)){
					String url = ConstantShared.getIpQueryUrl().replace("$ip", ip);
					sb.appendHtmlConstant("<a href='"+url+"' target='_blank'>"+ip+"</a>");
				}
			}
		});
		
		this.setColumnRenderer("idcard", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
				String idcard = object.get("idcard");
				if(!"".equals(idcard)){
					String url = ConstantShared.getIdcardQueryUrl().replace("$idcard", idcard);
					sb.appendHtmlConstant("<a href='"+url+"' target='_blank'>"+idcard+"</a>");
				}
			}
		});

		this.setColumnRenderer("accountStatus", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
				String status = object.get("accountStatus").toString();
				if("0".equals(status)){
					sb.appendEscaped("未激活");
				}else if("1".equals(status)){
					sb.appendEscaped("已激活");
				}
			}
		});
		
		this.setColumnRenderer("idcardStatus", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
				String idcardStatus = object.get("idcardStatus").toString();
				if("高危".equals(idcardStatus)){
					sb.appendHtmlConstant("<font style='color:red'>高危</font>");
				}else{
					sb.appendEscaped("正常");
				}
			}
		});
		
		this.setColumnRenderer("userName", 
				new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isAutoWidth() {
				return false;
			}
	    	@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>(){
					@Override
					public void execute(BaseModelData object) {
						RoleInfoDialogBox box = new RoleInfoDialogBox(object.get("accountId").toString(),
								ApplicationContext.getCurrentProductId());
		                box.setWidth(500);
						box.setHeight(300);
						box.center();
					}					
				};
			}
		});
	}
	
}
