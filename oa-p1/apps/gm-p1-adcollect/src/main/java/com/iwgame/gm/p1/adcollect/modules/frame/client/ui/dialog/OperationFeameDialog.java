/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： OperationFeameDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.client.ui.dialog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.frame.client.presenter.FrameMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.frame.client.ui.FrameMgrView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedEvent;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget.DropDownField;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.FrameDataBase;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.AppUtils;
import com.iwgame.xmvp.client.utils.DateWrapper;

/**
 * @Description: 添加修改框架
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-13 下午4:13:33
 */
public class OperationFeameDialog extends XDialogBox {

	private final FrameMgrPresenter presenter;
	private final FrameMgrView view;
	private final boolean type;

	private final XFormPanel formPanel;
	private final FormLayout layout;
	/** // 媒体分类 true */
	private PlainObjectSelector<DropDownListData> mediaType;
	/** // 媒体 true */
	private DropDownField media;
	/** // 名称 true */
	private TextField name;
	/** // 框架金额 */
	private NumberField amount;
	/** // 折扣 */
	private final NumberField rebate;
	/** // 折扣 */
	private final NumberField securityDeposit;
	/** // 有效时间 */
	private final DateRangeField time;

	private LabelField mediaTypeStr;
	private LabelField mediaStr;
	private LabelField nameStr;
	private LabelField amountStr;
	private int frameId;

	public OperationFeameDialog(boolean operation, final FrameMgrView mgrView) {
		this.presenter = mgrView.getPresenter();
		this.view = mgrView;
		this.type = operation;

		formPanel = new XFormPanel();
		formPanel.setWidth("600px");
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);

		if (operation) {
			mediaType = new PlainObjectSelector<DropDownListData>("媒体分类") {
				@Override
				protected String getValue(final DropDownListData t) {
					return t.getId();
				}

				@Override
				protected String getLabel(final DropDownListData t) {
					return t.getName();
				}
			};
			mediaType.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

				@Override
				public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
					presenter.getMediaType(1, callback);
				}
			});
			mediaType.addValidator(new Validator() {

				@Override
				public String validate(final Field<?, ?> field) {
					if (mediaType.getValue().equals("-1")) {
						return "请选择媒体分类";
					}
					return null;
				}
			});
			mediaType.setAllowBlank(false);
			mediaType.setValidateOnBlur(true);
			mediaType.getWidget().addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(final ChangeEvent event) {
					int temptype = Integer.parseInt(mediaType.getValue());
					AppUtils.EVENT_BUS.fireEvent(new MediaSelectedEvent(temptype));
				}
			});

			media = new DropDownField("媒体") {
				@Override
				protected String getValue(final DropDownListData t) {
					return t.getId();
				}

				@Override
				protected String getLabel(final DropDownListData t) {
					return t.getName();
				}
			};
			media.addItem("---请先选择媒体分类---", "-1");
			media.setEnablePlugin(true);
			media.addMedisSelectedHandlers(new MediaSelectedHandler() {

				@Override
				public void selectMedia(final MediaSelectedEvent event) {
					presenter.getMedia(event.getType(), new AsyncCallbackEx<List<DropDownListData>>() {

						@Override
						public void onSuccess(final List<DropDownListData> result) {
							media.getWidget().clear();
							media.addItem("--请选择--", "-1");
							for (DropDownListData ddld : result) {
								media.addItem(ddld.getName(), ddld.getId());
							}
						}
					});
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

			name = new TextField("名称");
			name.getWidget().addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					presenter.checkFrameName(name.getValue(), new AsyncCallbackEx<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							if (!result) {
								MessageBox.alert("次名称已经存在请换一个");
								name.setValue("");
							}
						}
					});

				}
			});
			name.setAllowBlank(false);
			name.setValidateOnBlur(true);

			amount = new NumberField("框架金额");
			amount.setMax(999999999);
			amount.setDecimal(isModal());
			amount.setPrecision(2);
			amount.setAllowBlank(false);
			amount.setValidateOnBlur(true);

			layout.add(1, mediaType);
			layout.add(2, media);
			layout.add(1, name);
			layout.add(2, amount);
		} else {
			mediaTypeStr = new LabelField("媒体分类");
			mediaTypeStr.setHiddenLabel(false);
			mediaStr = new LabelField("媒体");
			mediaStr.setHiddenLabel(false);
			nameStr = new LabelField("名称");
			nameStr.setHiddenLabel(false);
			amountStr = new LabelField("框架金额");
			amountStr.setHiddenLabel(false);
			layout.add(1, mediaTypeStr);
			layout.add(2, mediaStr);
			layout.add(1, nameStr);
			layout.add(2, amountStr);
			amountStr.setValue("￥" + view.getGrid().getSelected().get(0).get("amount").toString());
			List<DropDownListData> mediaList = view.getMediaTypeList();
			String mediaId = view.getGrid().getSelected().get(0).get("media").toString();
			mediaTypeStr.setValue(view.getGrid().getSelected().get(0).get("mediaType").toString());
			nameStr.setValue(view.getGrid().getSelected().get(0).get("name").toString());
			mediaStr.setValue(view.getGrid().getSelected().get(0).get("mediaName").toString());
			for (DropDownListData list : mediaList) {
				if (list.getGenerate().equals(mediaId.substring(0, 2))) {
					mediaTypeStr.setValue(list.getName());
					break;
				}
			}
			Double did = view.getGrid().getSelected().get(0).get("id");
			frameId = did.intValue();
		}

		rebate = new NumberField("折扣");
		rebate.setMax(999999999);
		rebate.setDecimal(isModal());
		rebate.setPrecision(4);
		rebate.setAllowBlank(false);
		rebate.setValidateOnBlur(true);
		layout.add(1, rebate);

		securityDeposit = new NumberField("保证金");
		securityDeposit.setMax(999999999);
		securityDeposit.setDecimal(isModal());
		securityDeposit.setPrecision(2);
		securityDeposit.setAllowBlank(false);
		securityDeposit.setValidateOnBlur(true);
		layout.add(2, securityDeposit);

		time = new DateRangeField("有效时间");
		time.setAllowBlank(false);
		time.setValidateOnBlur(true);
		time.setEndValue(new Date());
		time.setColSpan(2);
		layout.add(time);

		String title = "";
		if (operation) {
			title = "添加框架";
		} else {
			title = "修改框架：" + view.getGrid().getSelected().get(0).get("name");
			rebate.setValue((Number) view.getGrid().getSelected().get(0).get("rebate"));
			securityDeposit.setValue((Number) view.getGrid().getSelected().get(0).get("securityDeposit"));
			String ts = view.getGrid().getSelected().get(0).get("startTime").toString();
			Date s = DateTimeFormat.getFormat("yyyy-MM-dd  HH:mm:ss").parse(ts);
			time.setStartValue(s);
			String te = view.getGrid().getSelected().get(0).get("endTime").toString();
			Date e = DateTimeFormat.getFormat("yyyy-MM-dd  HH:mm:ss").parse(te);
			time.setEndValue(e);
		}
		initBox(title, formPanel);
		setButtons(OKCANCEL);
	}

	@Override
	protected void onButtonPressed(final Button button) {
		super.onButtonPressed(button);
		if (button == getButtonByItemId(OK)) {
			if (formPanel.validate()) {
				// 验证 同媒体 的时间
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("start", time.getStartValue());
				parameter.put("end", time.getEndValue());
				if (type) {
					parameter.put("mediaId", media.getValue());
				} else {
					parameter.put("mediaId", view.getGrid().getSelected().get(0).get("media").toString());
				}
				presenter.checkFrameTime(parameter, new AsyncCallbackEx<Integer>() {

					@Override
					public void onSuccess(Integer result) {

						// 在所选的时间段内没有该媒体的框架
						FrameDataBase base = new FrameDataBase();
						base.setRebate(rebate.getValue().doubleValue());
						base.setSecurityDeposit(securityDeposit.getValue().doubleValue());
						base.setStartTime(time.getStartValue());
						final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
						base.setEndTime(dateWrapper.clearTime().asDate());

						if (type) {// 添加
							if (result > 0) {// 新建框架所选的时间已经存在
								MessageBox.alert("框架所选的时间已经存在");
							} else {
								base.setMediaName(media.getWidget().getItemText(media.getWidget().getSelectedIndex()));
								base.setAmount(amount.getValue().doubleValue());
								base.setName(name.getValue());
								base.setMediaType(mediaType.getValue());
								base.setMedia(media.getValue());
								presenter.addFrame(base, new AsyncCallbackEx<Integer>() {

									@Override
									public void onSuccess(Integer result) {
										if (result.intValue() == 1) {
											view.getGrid().load();
											hide();
										} else {
											MessageBox.alert("添加失败");
										}
									}
								});
							}
						} else { // 修改
							if (result > 1) {// 新建框架所选的时间已经存在
								MessageBox.alert("框架所选的时间已经存在");
							} else {
								base.setId(frameId);
								presenter.updateFrame(base, new AsyncCallbackEx<Integer>() {
									
									@Override
									public void onSuccess(Integer result) {
										if (result.intValue() == 1) {
											view.getGrid().load();
											hide();
										} else {
											MessageBox.alert("修改失败");
										}
									}
								});
							}
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
