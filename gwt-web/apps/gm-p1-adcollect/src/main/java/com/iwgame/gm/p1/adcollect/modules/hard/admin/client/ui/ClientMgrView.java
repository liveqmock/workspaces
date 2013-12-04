/**      
 * ClientMgrView.java Create on 2012-11-12 上午9:44:16    
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.ClientMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.OperationClientDialog;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.SchemaGrid.SelectMode;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;

/**
 * @ClassName: 客户端识别码管理视图
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-12 上午9:44:16
 * @Version 1.0
 * 
 */
public class ClientMgrView extends SchemaGridView {

    private ClientMgrPresenter presenter;

    public ClientMgrPresenter getPresenter() {
	return presenter;
    }

    public void setPresenter(ClientMgrPresenter presenter) {
	super.setPresenter(presenter);
	this.presenter = presenter;
    }
    
    private final XFormPanel formPanel;
    private final FormLayout layout;

    private PlainObjectSelector<DropDownListData> media;
    private TextField version;
    private TextField code;

    
    public ClientMgrView(){
	super(25);
	getPanel().getGrid().setHasCheckBoxColumn(true);
	//  设置 Select 只能选一个
	setSelectMode(SelectMode.Single);
	formPanel = new XFormPanel("客户端识别码");
	formPanel.setWidth("100%");
	layout = new FormLayout();
	layout.setColumn(3);
	formPanel.setLayout(layout);
	
	media = new PlainObjectSelector<DropDownListData>("网吧媒体") {

	    @Override
	    protected String getValue(final DropDownListData t) {
		return t.getId();
	    }

	    @Override
	    protected String getLabel(final DropDownListData t) {
		return t.getName();
	    }
	};

	media.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

	    @Override
	    public void loadItem(
		    final AsyncCallback<List<DropDownListData>> callback) {
		presenter.getMedia(2, callback);
	    }
	});

	media.addValidator(new Validator() {

	    @Override
	    public String validate(final Field<?, ?> field) {
		if (media.getValue().equals("-1")) {
		    return "请选择媒体";
		}
		return null;
	    }
	});

	layout.add(1, media);
	
	version = new TextField("游戏客户端版本");
	layout.add(2, version);

	code = new TextField("游戏客户识别码");
	layout.add(3, code);
	
	final ButtonToolItem query = new ButtonToolItem("查询");
	query.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(final ClickEvent event) {
		BaseFilterPagingLoadConfig loadConfig = getGrid()
			.getLoadConfig();

		loadConfig.set("mediaId", media.getValue());

		loadConfig.set("version", version.getValue());

		loadConfig.set("code", code.getValue());

		getGrid().load();
	    }
	});

	final ButtonToolItem addBtn = new ButtonToolItem("录入");
	addBtn.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(final ClickEvent event) {
		// 调用添加的Dialog
		new OperationClientDialog(true, ClientMgrView.this).center();
	    }
	});
	final ButtonToolItem updateBtn = new ButtonToolItem("修改");
	updateBtn.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(final ClickEvent event) {
		if ((getPanel().getGrid().getSelected() == null)
			|| getPanel().getGrid().getSelected().isEmpty()) {
		    MessageBox.alert("请选择一个广告位！");
        	} else if (getPanel().getGrid().getSelected().size() > 1) {
        	    MessageBox.alert("只能选择一个广告位修改信息！");
        	} else {
    		    // 调用修改的 Dialog
		    new OperationClientDialog(false, ClientMgrView.this)
			    .center();
        	}
		
	    }
	});

	final Toolbar topToolbar = new Toolbar();
	topToolbar.addItem(formPanel);
	topToolbar.addItem(query);
	topToolbar.addItem(addBtn);
	topToolbar.addItem(updateBtn);
	getPanel().setTopToolBar(topToolbar);
    }

}
