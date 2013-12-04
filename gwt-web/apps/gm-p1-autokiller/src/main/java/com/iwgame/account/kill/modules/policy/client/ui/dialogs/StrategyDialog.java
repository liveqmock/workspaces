/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： StrategyDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui.dialogs;

import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RadioButton;
import com.iwgame.account.kill.modules.policy.client.presenter.StrategyListPresenter;
import com.iwgame.account.kill.modules.policy.client.ui.widget.MACField;
import com.iwgame.account.kill.modules.policy.client.ui.widget.SelectNumberField;
import com.iwgame.account.kill.modules.policy.shared.model.DropDownListData;
import com.iwgame.account.kill.modules.policy.shared.model.KillPolicy;
import com.iwgame.account.kill.modules.policy.shared.model.Policy;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.SchemaGrid;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector.Delegate;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup.RadioSelectedAction;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 添加修改策略的弹出框
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-3 下午12:02:37
 */
public class StrategyDialog extends XDialogBox {

	private final StrategyListPresenter slPresenter;
	private final boolean temptype;

	private final KillPolicy killPolicy;

	private final SchemaGrid<BaseModelData> griddata;
	private final XFormPanel formpanel;
	private final FormLayout layout;

	private SimpleSelector objectIdField;
	private MACField objectField; // MAC地址
	// 点选按钮
	private RadioButtonGroup isDelayField; // 封停延迟
	private PlainObjectSelector<DropDownListData> whyField; // 封停原因
	private RadioButtonGroup daysField; // 封停天数
	private RadioButtonGroup isPaidField; // 是否有充值记录
	private NumberField paidField; // 冲值限额
	private NumberField accNumField; // 每天最大充值数
	private SelectNumberField levelField; // 等级过滤
	private TextField nameField; // 配置策略名

	/**
	 * 添加或者修改
	 * 
	 * @param temptype
	 *            true 添加 false 修改
	 */
	public StrategyDialog(final boolean type, final SchemaGrid<BaseModelData> grid,
			final StrategyListPresenter presenter, final KillPolicy killp) {
		temptype = type;
		killPolicy = killp;
		slPresenter = presenter;
		griddata = grid;
		formpanel = new XFormPanel();
		formpanel.setButtonAlign(HasHorizontalAlignment.ALIGN_RIGHT);
		formpanel.setWidth("1000px");
		layout = new FormLayout();
		// 加载视图
		initfp();
		// 加载数据

		setButtons(XDialogBox.YESNOCANCEL);
		getButtonByItemId(XDialogBox.YES).setText("延时");
		getButtonByItemId(YES).setVisible(false);
		getButtonByItemId(XDialogBox.NO).setText("确定");
		getButtonByItemId(XDialogBox.CANCEL).setText("取消");
		if (type) {
			initBox("添加", formpanel);
		} else {
			initBox("修改：" + killPolicy.getTitle(), formpanel);
		}
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (!temptype) {
			initData();
			if (killPolicy.getStatus() == 1) {
				getButtonByItemId(YES).setVisible(true);
			}
			if (killPolicy.getStatus() == 1) {
				objectField.setReadOnlyMac(true);
			}
		}
	}

	/**
	 * 加载视图
	 * */
	public void initfp() {
		layout.setColumn(2);
		formpanel.setLayout(layout);

		objectIdField = new SimpleSelector("数据模型") {

			@Override
			public void initItems() {
				addItem("MAC地址", "MAC");
			}
		};

		layout.add(objectIdField);

		objectField = new MACField("MAC地址");
		objectField.setAllowBlank(false);
		objectField.setValidateOnBlur(true);
		layout.add(2, objectField);

		isDelayField = new RadioButtonGroup("封杀延迟", "isDelay", Direction.Horizontal);
		isDelayField.setColSpan(2);
		isDelayField.addRadioButton("帐号登入后，立即自动停封", "-1", false);
		isDelayField.addRadioButton("帐号登入后，在系统随机时间内自动停封", "1", false);
		isDelayField.setValue("1");
		layout.add(isDelayField);

		whyField = new PlainObjectSelector<DropDownListData>("封杀原因") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId() + "";
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getReason();
			}
		};
		whyField.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (whyField.getValue().equals("-1")) {
					return "请选择封杀原因";
				}
				return null;
			}
		});
		whyField.setDelegate(new Delegate<DropDownListData>() {

			@Override
			public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
				slPresenter.getWhyDropDownListValue(callback);
			}
		});
		whyField.getWidget().setWidth("675px");
		whyField.setValidateOnBlur(true);
		whyField.setColSpan(2);
		layout.add(whyField);

		daysField = new RadioButtonGroup("封杀天数", "days", Direction.Horizontal);
		daysField.addRadioButton("1天", "1", false);
		daysField.addRadioButton("3天", "3", false);
		daysField.addRadioButton("7天", "7", false);
		daysField.addRadioButton("30天", "30", false);
		daysField.addRadioButton("永久", "5000", false);
		daysField.setValue("7");
		daysField.setColSpan(2);

		layout.add(daysField);
		// *************************************
		isPaidField = new RadioButtonGroup("是否有充值记录", "isPaidField", Direction.Horizontal);
		isPaidField.addRadioButton("没有限制", "-1", false);
		isPaidField.addRadioButton("有限制请填写充值限额", "1", false);
		isPaidField.setValue("-1");

		layout.add(isPaidField);

		isPaidField.setRadioSelectedAction(new RadioSelectedAction() {

			@Override
			public void execute(final RadioButton rb) {
				paidField.getWidget().setEnabled(!rb.getFormValue().equals("-1"));
				if (rb.getFormValue().equals("-1")) {
					paidField.setValue(0);
				} else if (rb.getFormValue().equals("1")) {
					paidField.getWidget().setFocus(true);
				}
			}
		});
		paidField = new NumberField("充值限额（以上）");
		paidField.getWidget().setEnabled(false);
		paidField.setValue(0);
		layout.add(2, paidField);

		// *************************************

		levelField = new SelectNumberField("等级过滤", "temptype", "condition") {

			@Override
			public void initItems() {
				addItem("大于等于", "gt");
				addItem("小于", "lt");
				addItem("无限制", "-1");
			}
		};
		levelField.setValue("-1", "");
		levelField.getWidget().getNumbox().setVisible(false);
		levelField.getWidget().getNumbox().setMaxLength(3);
		levelField.setColSpan(2);

		layout.add(levelField);

		accNumField = new NumberField("封杀账号数/（天）");
		accNumField.setValue(10000);
		accNumField.setColSpan(2);
		layout.add(accNumField);

		nameField = new TextField("策略名称");
		nameField.setAllowBlank(false);
		nameField.setValidateOnBlur(true);
		nameField.setColSpan(2);
		layout.add(nameField);

		nameField.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				slPresenter.checkTitle(nameField.getValue(), new AsyncCallbackEx<Boolean>() {

					@Override
					public void onSuccess(final Boolean result) {
						if (!result) {
							MessageBox.alert("策略名称已经存在请换一个");
							nameField.setValue("");
						}
					}
				});
			}
		});

	}

	/**
	 * 修改加载数据
	 * */
	private void initData() {
		objectIdField.setValue(killPolicy.getObjectId());
		objectField.setValue(killPolicy.getObject());
		if (killPolicy.getPolicy().getDelay() == -1) {
			isDelayField.setValue("-1");
		} else {
			isDelayField.setValue("1");
		}
		whyField.setValue(killPolicy.getPolicy().getReason() + "");
		daysField.setValue(killPolicy.getPolicy().getDays() + "");
		if (killPolicy.getPolicy().getPaid() == -1) {
			isPaidField.setValue("-1");
		} else {
			isPaidField.setValue("1");
			paidField.setValue(killPolicy.getPolicy().getPaid());
			paidField.getWidget().setEnabled(true);
		}
		if (!killPolicy.getPolicy().getLevelOpt().equals("-1")) {
			levelField.getWidget().getNumbox().setVisible(true);
			levelField.setValue(killPolicy.getPolicy().getLevelOpt(), killPolicy.getPolicy().getLevel() + "");
		} else {
			levelField.setValue(killPolicy.getPolicy().getLevelOpt());
			levelField.getWidget().getNumbox().setVisible(false);
		}

		accNumField.setValue(killPolicy.getPolicy().getAccounts());
		nameField.setValue(killPolicy.getTitle());
	}

	@Override
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(XDialogBox.YES)) {
			slPresenter.delay(killPolicy.getId(), ApplicationContext.getCurrrentUser().getUsername(),
					killPolicy.getObject(), new AsyncCallbackEx<Integer>() {

						@Override
						public void onSuccess(final Integer result) {
							hide();
							new HoldTipInfo("延时操作", griddata).center();
						}
					});
		}

		if (button == getButtonByItemId(XDialogBox.NO)) {
			if (formpanel.validate()) {
				getButtonByItemId(XDialogBox.NO).setEnabled(false);
				final KillPolicy kp = new KillPolicy();
				Policy p = new Policy();
				kp.setPolicy(p);
				kp.setObject(objectField.getValue());
				kp.setTitle(nameField.getValue());
				kp.getPolicy().setDelay(Integer.parseInt(isDelayField.getValue()));
				kp.getPolicy().setReason(Integer.parseInt(whyField.getValue()));
				kp.getPolicy().setDays(Integer.parseInt(daysField.getValue()));
				if (isPaidField.getValue().equals("-1")) {
					kp.getPolicy().setPaid(-1);
				} else {
					kp.getPolicy().setPaid(paidField.getValue().intValue());
				}
				if (levelField.getValue().get("temptype").equals("-1")) {
					kp.getPolicy().setLevelOpt("-1");
				} else {
					kp.getPolicy().setLevel(Integer.parseInt(levelField.getValue().get("condition").toString()));
					kp.getPolicy().setLevelOpt(levelField.getValue().get("temptype").toString());
				}
				kp.getPolicy().setAccounts(Integer.parseInt(accNumField.getValue().toString()));
				kp.setObjectId(objectIdField.getValue());
				String username = ApplicationContext.getCurrrentUser().getUsername();
				kp.setModifier(username);
				if (temptype) {// 添加
					kp.setCreater(username);
					slPresenter.addPolicy(kp, new AsyncCallbackEx<Integer>() {

						@Override
						public void onSuccess(final Integer result) {
							if (result == 1) {
								hide();
								griddata.load();
							} else {
								MessageBox.alert("添加失败");
								getButtonByItemId(XDialogBox.NO).setEnabled(true);
							}
						}
					});
				} else { // 修改
					slPresenter.checkIsUpdateMac(kp.getObject(), new AsyncCallbackEx<Boolean>() {

						@Override
						public void onSuccess(final Boolean result) {
							if (!result) {
								MessageBox.alert("此MAC地址已经启用了");
								getButtonByItemId(XDialogBox.NO).setEnabled(true);
							} else {
								kp.setId(killPolicy.getId());
								kp.setStatus(killPolicy.getStatus());
								slPresenter.updateKillPolicy(kp, new AsyncCallbackEx<Integer>() {

									@Override
									public void onSuccess(final Integer result) {
										if (result == 1) {
											hide();
											griddata.load();
										} else {
											MessageBox.alert("修改失败");
											getButtonByItemId(XDialogBox.NO).setEnabled(true);
										}
									}
								});
							}
						}
					});

				}
			}

		}

		if (button == getButtonByItemId(XDialogBox.CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
	}
}
