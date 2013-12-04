/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： OperationUseDialog.java
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.frame.client.presenter.UseMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.frame.client.ui.UseMgrView;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.UseFrameDataBase;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestField;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestField.AsyncValidator;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestOracle;
import com.iwgame.ui.panel.client.form.field.DateField;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextAreaField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.ui.panel.client.form.field.events.CascadingListEvent;
import com.iwgame.ui.panel.client.form.field.events.CascadingListEvent.CascadingListHandler;

/**
 * @Description: 添加使用框架
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-17 下午3:30:58
 */
public class OperationUseDialog extends XDialogBox {

	private UseMgrPresenter presenter;
	private UseMgrView view;
	
	private XFormPanel formPanel;
	private FormLayout layout;
	
	/** // 媒体分类 true */
	private XLinkageSelector mediaType;
	/** // 媒体 true */
	private XLinkageSelector media;
	/** // 框架信息 */
	private XLinkageSelector frameInfo;
	/** // 所选的框架名 */
	private Map<String, Object> frameMap;
	/** // 所选的框架名 */
	private Map<String, Object> nameMap;
	/** // 变更日期 */
	private DateField lastDate;
	/** // 合同编码 */
	private AsyncSuggestField<DropDownListData> contractId;
	// 变更前余额
	private TextField balanceBefore;
	/** // 变更金额 */
	private NumberField changeAmount;
	/**　 消费详细　*/
	private TextAreaField usedDetails;
	/**　 消费详细　*/
	private TextAreaField changes;
	/**　 效果追踪 　*/
	private TextAreaField effectTracking;
	
	/** 合同数据  */
	private List<DropDownListData> contractdate;
	public List<DropDownListData> getContractdate() {
		return contractdate;
	}
	public void setContractdate(List<DropDownListData> contractdate) {
		this.contractdate = contractdate;
	}

	public OperationUseDialog(final UseMgrView mgrView){
		this.presenter = mgrView.getPresenter();
		this.view = mgrView;
		
		formPanel = new XFormPanel();
		formPanel.setWidth("600px");
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);

		
		balanceBefore = new TextField("变更前余额");
		balanceBefore.getWidget().setEnabled(false);
		balanceBefore.setEmptyText("只能做为参考");
		
		mediaType = new XLinkageSelector("媒体分类", "mediaType");
		presenter.getMediaType(1, new AsyncCallbackEx<List<DropDownListData>>() {

			@Override
			public void onSuccess(final List<DropDownListData> result) {
				mediaType.getWidget().clear();
				mediaType.addItem("---选择媒体分类---", "-1");
				mediaType.addItems(result);
			}
		});

		mediaType.setAllowBlank(false);
		mediaType.setValidateOnBlur(true);
		mediaType.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (mediaType.getValue().equals("-1")) {
					return "请选择媒体分类";
				}
				return null;
			}
		});
		media = new XLinkageSelector("媒体", "mediaId") {

			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		media.addItem("---请先选择媒体类别---", "-1");
		media.addCascadingListHandler(new CascadingListHandler() {

			@Override
			public void onCascading(final CascadingListEvent event) {
				String typeId = event.getValue().toString();
				Integer iTypeId = Integer.parseInt(typeId);
				presenter.getMedia(iTypeId, new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						media.getWidget().clear();
						media.addItem("---选择媒体---", "-1");
						media.addItems(result);
					}
				});
			}
		});
		media.setEnablePlugin(true);
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
		
		frameInfo = new XLinkageSelector("框架名称", "position") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		frameInfo.addItem("---请先选择媒体---", "-1");
		frameInfo.addCascadingListHandler(new CascadingListHandler() {

			@Override
			public void onCascading(final CascadingListEvent event) {
				String mediaId = event.getValue().toString();
				presenter.getFrameName(mediaId, new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						frameInfo.getWidget().clear();
						frameInfo.addItem("---选择框架名称---", "-1");
						if(null != result){
							frameInfo.addItems(result);
							frameMap = new HashMap<String, Object>();
							nameMap = new HashMap<String, Object>();
							for (DropDownListData d : result) {
								frameMap.put(d.getId(), d.getName());
								nameMap.put(d.getId(),d.getGenerate());
							}
						}
					}
				});
			}
		});
		frameInfo.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (frameInfo.getValue().equals("-1")) {
					return "请选择框架名称";
				}
				return null;
			}
		});
		frameInfo.setAllowBlank(false);
		frameInfo.setValidateOnBlur(true);
		frameInfo.setColSpan(2);
		frameInfo.getWidget().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				balanceBefore.setValue(nameMap.get(frameInfo.getValue()).toString());
			}
		});
		mediaType.addCascadeSelector(media);
		media.addCascadeSelector(frameInfo);
		layout.add(1, mediaType);
		layout.add(2, media);
		layout.add(1,  frameInfo);
		
		lastDate = new DateField("变更日期", "lastDate");
		lastDate.setAllowBlank(false);
		lastDate.setValidateOnBlur(true);
		lastDate.setValue(new Date());
		layout.add(1,  lastDate);
		
		contractId = new AsyncSuggestField<DropDownListData>("合同", new AsyncSuggestOracle<DropDownListData>() {

			@Override
			public void getCandidatesFromQuery(final String query, final AsyncCallback<List<DropDownListData>> callback) {
				presenter.autoContractId(query, new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(List<DropDownListData> result) {
						setContractdate(result);
						callback.onSuccess(result);
					}
				});
			}

			@Override
			public String buildDisplayString(final DropDownListData candidate) {
				return "<span><strong>ID：</strong>" + candidate.getId() + "<span> " + "<span><strong>编码：</strong>"
						+ candidate.getGenerate() + "<span> ";
			}

			@Override
			public String buildReplaceString(final DropDownListData candidate) {
				return candidate.getId();
			}
		});
		contractId.setAllowBlank(false);
		contractId.setValidateOnBlur(true);
		layout.add(2, contractId);
		contractId.setAsyncValidator(new AsyncValidator() {

			@Override
			public void validate(final String value, final AsyncCallbackEx<String> callback) {
				presenter.checkContractId(contractId.getValue(), new AsyncCallbackEx<String>() {

					@Override
					public void onSuccess(final String result) {
						callback.onSuccess(result);
					}
				});
			}
		});

		layout.add(1, balanceBefore);
		
		changeAmount = new NumberField("变更金额");
		changeAmount.setMax(999999999);
		changeAmount.setDecimal(isModal());
		changeAmount.setPrecision(2);
		changeAmount.setAllowBlank(false);
		changeAmount.setValidateOnBlur(true);
		layout.add(2,changeAmount);
		
		changes = new TextAreaField("变更情况");
		changes.getWidget().setHeight("60px");
		changes.setAllowBlank(false);
		changes.setValidateOnBlur(true);
		changes.setColSpan(2);
		layout.add(changes);

		usedDetails = new TextAreaField("消费详细");
		usedDetails.getWidget().setHeight("60px");
		usedDetails.setColSpan(2);
		usedDetails.setAllowBlank(false);
		usedDetails.setValidateOnBlur(true);
		layout.add(usedDetails);
		
		effectTracking = new TextAreaField("相关排期");
		effectTracking.getWidget().setHeight("60px");
		effectTracking.setColSpan(2);
		effectTracking.setAllowBlank(false);
		effectTracking.setValidateOnBlur(true);
		layout.add(effectTracking);
		
		String title = "使用框架";
		initBox(title, formPanel);
		setButtons(OKCANCEL);		
		
	}
	
	@Override
	protected void onButtonPressed(final Button button) {
		super.onButtonPressed(button);
		if (button == getButtonByItemId(OK)) {
			if (formPanel.validate()) {
				UseFrameDataBase useFrame = new UseFrameDataBase();
				useFrame.setMediaId(media.getValue());
				useFrame.setMediaName(media.getWidget().getItemText(media.getWidget().getSelectedIndex()));
				useFrame.setFrameId(Integer.parseInt(frameInfo.getValue()));
				useFrame.setFrameName(frameInfo.getWidget().getItemText(frameInfo.getWidget().getSelectedIndex()));
				useFrame.setChanges(changes.getValue());
				
				useFrame.setChangeAmount(changeAmount.getValue().doubleValue());
				useFrame.setContractId(Integer.parseInt(contractId.getValue()));
				List<DropDownListData> ci = getContractdate();
				for (DropDownListData d : ci) {
					if(d.getId().equals(contractId.getValue())){
						useFrame.setContractItmo(d.getGenerate());
						break;
					}
				}
				useFrame.setUsedDetails(usedDetails.getValue());
				useFrame.setEffectTracking(effectTracking.getValue());
				//调用  使用的方法
				presenter.addUseFrame(useFrame, new AsyncCallbackEx<Integer>() {

					@Override
					public void onSuccess(Integer result) {
						if (result.intValue() == 0) {
							view.getGrid().load();
							hide();
						} else {
							MessageBox.alert("添加失败");
						}
					}
				});
			}
		}
		if (button == getButtonByItemId(CANCEL)) {
			hide();
		}
	}

	public class XLinkageSelector extends PlainObjectSelector<DropDownListData> {

		public XLinkageSelector(final String label, final String fieldName) {
			super(label);
			setFieldName(fieldName);
		}

		@Override
		protected String getValue(final DropDownListData t) {
			return t.getId();
		}

		@Override
		protected String getLabel(final DropDownListData t) {
			return t.getName();
		}

	}
}

