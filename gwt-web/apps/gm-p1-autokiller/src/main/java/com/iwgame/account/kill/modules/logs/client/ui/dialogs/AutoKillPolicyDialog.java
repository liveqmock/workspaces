/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： AutoKillPolicyDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.logs.client.ui.dialogs;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.account.kill.modules.policy.shared.model.Policy;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.LabelField;

/**
 * 类说明
 * 
 * @简述： 自动封停策略
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-12 下午04:23:49
 */
public class AutoKillPolicyDialog extends XDialogBox {

	private final XFormPanel formpanel;
	private final FormLayout layout;

	public AutoKillPolicyDialog(String title, final Policy p) {
		formpanel = new XFormPanel();
		formpanel.setButtonAlign(HasHorizontalAlignment.ALIGN_RIGHT);
		formpanel.setWidth("500px");
		layout = new FormLayout();
		layout.setColumn(2);
		formpanel.setLayout(layout);

		LabelField paid = new LabelField("是否有充值记录");
		paid.setHiddenLabel(false);
		if (p.getPaid() < 0) {
			paid.setValue("无限制");
		} else if (p.getPaid() == 0) {
			paid.setValue("无");
		} else if (p.getPaid() > 0) {
			paid.setValue(p.getPaid() + "");
		} else {
			paid.setValue(" ");
		}
		layout.add(paid);
		LabelField delay = new LabelField("是否有延时");
		delay.setHiddenLabel(false);
		if(p.getDelay()==-1){
			delay.setValue("即时");
		}else{
			delay.setValue("延时");
		}
		layout.add(2, delay);
		LabelField level = new LabelField("等级过滤");
		level.setHiddenLabel(false);
		if(p.getLevelOpt().equals("-1")){
			level.setValue("无限制");
		}else if(p.getLevelOpt().equals("gt")){
			level.setValue("大于"+p.getLevel());
		}else if(p.getLevelOpt().equals("lt")){
			level.setValue("小于"+p.getLevel());
		}
		layout.add(level);
		LabelField days = new LabelField("封杀天数");
		days.setHiddenLabel(false);
		switch (p.getDays()) {
		case 1:
			days.setValue("1天");
			break;
		case 3:
			days.setValue("3天");
			break;
		case 7:
			days.setValue("7天");
			break;
		case 30:
			days.setValue("30天");
			break;
		case 5000:
			days.setValue("永久");
			break;
		default:
			days.setValue(" ");
			break;
		}
		layout.add(2, days);
		LabelField accounts = new LabelField("最多封用户数");
		accounts.setHiddenLabel(false);
		switch (p.getDays()) {
		case 0:
			accounts.setValue("无限制");
			break;
		default:
			accounts.setValue(p.getAccounts()+"");
			break;
		}
		layout.add(accounts);
		LabelField dueDays = new LabelField("策略有效期");
		dueDays.setHiddenLabel(false);
		dueDays.setValue(p.getDueDays()+"天");
		layout.add(2, dueDays);
		LabelField reason = new LabelField("原因ID");
		reason.setHiddenLabel(false);
		reason.setValue(p.getReason()+"");
		reason.setColSpan(2);
		layout.add(reason);

		initBox(title, formpanel);
		setButtons(XDialogBox.CLOSE);
	}

	@Override
	protected void onButtonPressed(Button button) {
		if (button == getButtonByItemId(XDialogBox.CLOSE)) {
			hide();
		}
		super.onButtonPressed(button);
	}
}
