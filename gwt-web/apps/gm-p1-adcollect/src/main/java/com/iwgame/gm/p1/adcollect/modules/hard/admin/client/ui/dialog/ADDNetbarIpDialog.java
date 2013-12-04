/**      
 * ADDNetbarIpDialog.java Create on 2012-11-8 下午5:25:02    
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.IpMarPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.IpMarView;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.client.widgets.form.IPField;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;

/**
 * @ClassName: 添加网吧媒体对应IP Dialog
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-8 下午5:25:02
 * @Version 1.0
 * 
 */
public class ADDNetbarIpDialog extends XDialogBox {

    private IpMarPresenter presenter;
    private IpMarView view;

    private XFormPanel formPanel;
    private FormLayout layout;

    private PlainObjectSelector<DropDownListData> media;
    private IPField ip;

    public ADDNetbarIpDialog(final IpMarPresenter presenter,
	    final IpMarView view) {
	this.presenter = presenter;
	this.view = view;
	formPanel = new XFormPanel();
	formPanel.setWidth("600px");
	layout = new FormLayout();
	layout.setColumn(2);
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
	media.setAllowBlank(false);
	media.setValidateOnBlur(true);
	layout.add(1, media);

	ip = new IPField("IP");
	ip.setAllowBlank(false);
	ip.setValidateOnBlur(true);
	layout.add(2, ip);

	setButtons(OKCANCEL);
	initBox("录入媒体对应ip", formPanel);

    }

    @Override
    protected void onButtonPressed(final Button button) {
	super.onButtonPressed(button);
	if (button == getButtonByItemId(OK)) {
	    if (formPanel.validate()) {
		presenter.addNetbarIp(media.getValue(), ip.getValue(),
			new AsyncCallbackEx<Integer>() {

			    @Override
			    public void onSuccess(Integer result) {
				if (result == 1) {
				    view.getGrid().load();
				    hide();
				} else {
				    hide();
				}
			    }

			});
	    }
	}
	if (button == getButtonByItemId(CANCEL)) {
	    hide();
	}
    }
}
