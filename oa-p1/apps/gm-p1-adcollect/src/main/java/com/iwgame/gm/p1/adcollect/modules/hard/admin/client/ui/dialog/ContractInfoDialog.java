/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ContractInfoDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget.LoadAdjunctField;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.HTMLField;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * 类说明
 * 
 * @简述： 合同详细信息
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-15 上午09:09:22
 */
public class ContractInfoDialog extends XDialogBox {

	private ScrollPanel sp;
	private XFormPanel formPanel;
	private FormLayout layout;

	public ContractInfoDialog(final BaseModelData info) {

		formPanel = new XFormPanel();
		formPanel.setWidth("790px");
		layout = new FormLayout();
		layout.setColumn(3);
		formPanel.setLayout(layout);

		LabelField title = new LabelField("抬头");
		title.setHiddenLabel(false);
		layout.add(1, title);

		LabelField itemno = new LabelField("合同编号");
		itemno.setHiddenLabel(false);
		layout.add(2, itemno);

		LabelField signedString = new LabelField("合同日期");
		signedString.setHiddenLabel(false);
		layout.add(3, signedString);

		LabelField applyman = new LabelField("申请人");
		applyman.setHiddenLabel(false);
		layout.add(1, applyman);

		LabelField section = new LabelField("所属部门");
		section.setHiddenLabel(false);
		layout.add(2, section);

		LabelField level = new LabelField("合同等级");
		level.setHiddenLabel(false);
		layout.add(3, level);

		LabelField media = new LabelField("媒体");
		media.setHiddenLabel(false);
		media.setColSpan(1);
		layout.add(1, media);

		LoadAdjunctField isatt = new LoadAdjunctField("是否有附件");
		isatt.setHiddenLabel(false);
		isatt.setColSpan(2);
		layout.add(2, isatt);

		HTMLField content = new HTMLField("合同主旨");
		content.setHiddenLabel(false);
		content.setColSpan(3);
		layout.add(1, content);

		HTMLField description = new HTMLField("合作概述");
		description.setHiddenLabel(false);
		description.setColSpan(3);
		layout.add(1, description);

		HTMLField note = new HTMLField("合作说明");
		note.setHiddenLabel(false);
		note.setColSpan(3);
		layout.add(1, note);

		HTMLField adsituation = new HTMLField("上次投放情况");
		adsituation.setHiddenLabel(false);
		adsituation.setColSpan(3);
		layout.add(1, adsituation);

		LabelField total = new LabelField("合同金额");
		total.setHiddenLabel(false);
		layout.add(1, total);

		LabelField discount = new LabelField("折扣");
		discount.setHiddenLabel(false);
		layout.add(2, discount);

		LabelField stdDiscount = new LabelField("折扣标准");
		stdDiscount.setHiddenLabel(false);
		layout.add(3, stdDiscount);

		LabelField stdRegcost = new LabelField("注册成本");
		stdRegcost.setHiddenLabel(false);
		layout.add(1, stdRegcost);

		LabelField stdLogincost = new LabelField("登陆成本");
		stdLogincost.setHiddenLabel(false);
		layout.add(2, stdLogincost);

		LabelField payedString = new LabelField("付款日期");
		payedString.setHiddenLabel(false);
		layout.add(3, payedString);

		LabelField adamt = new LabelField("广告数");
		adamt.setHiddenLabel(false);
		adamt.setColSpan(3);
		layout.add(1, adamt);

		// 给各个属性赋值
		if (null != info.get("titleName")) {
			title.setValue(info.get("titleName").toString());
		} else {
			title.setValue("");
		}
		if (null != info.get("itemno")) {
			itemno.setValue(info.get("itemno").toString());
		} else {
			itemno.setValue("");
		}
		if (null != info.get("signed")) {
			signedString.setValue((info.get("signed")).toString().substring(0, 10));
		} else {
			signedString.setValue("");
		}
		if (null != info.get("applyman")) {
			applyman.setValue(info.get("applyman").toString());
		} else {
			applyman.setValue("");
		}
		if (null != info.get("section")) {
			section.setValue(info.get("section").toString());
		} else {
			section.setValue("");
		}
		if (null != info.get("level")) {
			level.setValue(info.get("level").toString());
		} else {
			isatt.setValue("");
		}
		if (null != info.get("mediaName")) {
			media.setValue(info.get("mediaName").toString());
		} else {
			media.setValue("");
		}
		isatt.setLoadAdds(info.get("path").toString());
		if (null != info.get("content")) {
			content.setValue(info.get("content").toString());
		} else {
			content.setValue("");
		}
		if (null != info.get("description")) {
			description.setValue(info.get("description").toString());
		} else {
			description.setValue("");
		}
		if (null != info.get("note")) {
			note.setValue(info.get("note").toString());
		} else {
			note.setValue("");
		}
		if (null != info.get("adsituation")) {
			adsituation.setValue(info.get("adsituation").toString());
		} else {
			adsituation.setValue("");
		}
		if (null != info.get("total")) {
			total.setValue(("￥" + info.get("total")));
		} else {
			total.setValue("");
		}
		if (null != info.get("discount")) {
			discount.setValue((info.get("discount")) + "");
		} else {
			discount.setValue("");
		}
		if (null != info.get("stdDiscount")) {
			stdDiscount.setValue((info.get("stdDiscount")) + "");
		} else {
			stdDiscount.setValue("");
		}
		if (null != info.get("stdRegcost")) {
			stdRegcost.setValue("￥" + (info.get("stdRegcost")));
		} else {
			stdRegcost.setValue("");
		}
		if (null != info.get("stdLogincost")) {
			stdLogincost.setValue("￥" + (info.get("stdLogincost")));
		} else {
			stdLogincost.setValue("");
		}
		if ((null != info.get("payed")) && (info.get("payed").toString().length() > 0)) {
			payedString.setValue((info.get("payed")).toString().substring(0, 10));
		} else {
			payedString.setValue("");
		}
		if (null != info.get("adamt")) {
			adamt.setValue(((Double) (info.get("adamt"))).intValue() + "");
		} else {
			adamt.setValue("");
		}

		sp = new ScrollPanel();
		sp.setHeight("500px");
		sp.setWidth("820px");
		sp.add(formPanel);

		// 设置dialog
		setButtons(CANCEL);
		initBox("合同详细信息", sp);
	}

	@Override
	protected void onButtonPressed(final Button button) {
		super.onButtonPressed(button);
		if (button == getButtonByItemId(CANCEL)) {
			hide();
		}
	}

}
