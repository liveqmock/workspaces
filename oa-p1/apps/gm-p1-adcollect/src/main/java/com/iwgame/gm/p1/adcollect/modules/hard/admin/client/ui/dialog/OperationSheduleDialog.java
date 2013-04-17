/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： OperationSheduleDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.SheduleMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.SheduleMgrListView;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.SheduleDataBase;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestField;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestField.AsyncValidator;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestOracle;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.ui.panel.client.form.field.TextAreaField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.ui.panel.client.form.field.events.CascadingListEvent;
import com.iwgame.ui.panel.client.form.field.events.CascadingListEvent.CascadingListHandler;
import com.iwgame.xmvp.client.log.XMVPLogger;
import com.iwgame.xmvp.client.utils.DateWrapper;

/**
 * 类说明
 * 
 * @简述： 添加广告排期Dialog
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-15 下午02:04:48
 */
public class OperationSheduleDialog extends XDialogBox {

	private final XMVPLogger logger = new XMVPLogger(OperationSheduleDialog.class);

	private final SheduleMgrListView view;
	private XFormPanel formpanel;
	private FormLayout layout;
	private SheduleMgrPresenter presenter;

	private XLinkageSelector mediaType;
	private XLinkageSelector media;
	private XLinkageSelector position;// 广告位
	private SimpleSelector type;
	private AsyncSuggestField<DropDownListData> materialId;
	private AsyncSuggestField<DropDownListData> contractId;
	private AsyncSuggestField<DropDownListData> groupId;
	private XLinkageSelector units;
	private NumberField rebate;
	private NumberField generalPrice;
	private NumberField specialPrice;
	private DateRangeField time;
	private AsyncSuggestField<DropDownListData> url;
	private SimpleSelector usedType;
	private TextAreaField source;
	private NumberField distribuRatio;
	private TextField name;

	private Map<String, Object> posMap;

	public OperationSheduleDialog(final SheduleMgrListView view) {
		this.view = view;
		presenter = view.getPresenter();
		formpanel = new XFormPanel();
		formpanel.setWidth("600px");
		layout = new FormLayout();
		layout.setColumn(2);
		formpanel.setLayout(layout);

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

		mediaType.addCascadeSelector(media);
		layout.add(1, mediaType);
		layout.add(2, media);

		name = new TextField("排期名称");
		name.setMaxLength(25);
		name.setAllowBlank(false);
		name.setValidateOnBlur(true);
		layout.add(1, name);

		position = new XLinkageSelector("广告位", "position") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		position.addItem("---请先选择媒体---", "-1");
		position.addCascadingListHandler(new CascadingListHandler() {

			@Override
			public void onCascading(final CascadingListEvent event) {
				String mediaId = event.getValue().toString();
				presenter.getPosition(mediaId, new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						position.getWidget().clear();
						position.addItem("---选择广告位---", "-1");
						position.addItems(result);
						posMap = new HashMap<String, Object>();
						for (DropDownListData d : result) {
							posMap.put(d.getId(), d.getGenerate());
						}
					}
				});
			}
		});
		position.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (position.getValue().equals("-1")) {
					return "请选择广告位";
				}
				return null;
			}
		});
		position.setAllowBlank(false);
		position.setValidateOnBlur(true);
		media.addCascadeSelector(position);
		layout.add(2, position);

		time = new DateRangeField("投放时间");
		time.setAllowBlank(false);
		time.setValidateOnBlur(true);
		// time.getWidget().setEndDateChangeLinks();
		time.setEndValue(new Date());
		layout.add(1, time);

		final LabelField lf = new LabelField("广告位类型");
		lf.setHiddenLabel(false);
		layout.add(2, lf);
		lf.setValue("");

		position.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				String pos = position.getValue();
				presenter.getPosNameById(pos, new AsyncCallbackEx<String>() {

					@Override
					public void onSuccess(String result) {
						lf.setValue(result);
					}
				});
			}
		});

		type = new SimpleSelector("广告类型") {

			@Override
			public void initItems() {
				addItem("---请选择---", "-1");
				addItem("正式", "1");
				addItem("赠送", "2");
				addItem("补量", "3");
				addItem("测试", "4");
			}
		};
		type.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (type.getValue().equals("-1")) {
					return "请选择广告类型";
				}
				return null;
			}
		});
		type.setAllowBlank(false);
		type.setValidateOnBlur(true);
		layout.add(1, type);

		materialId = new AsyncSuggestField<DropDownListData>("素材ID", new AsyncSuggestOracle<DropDownListData>() {

			@Override
			public void getCandidatesFromQuery(final String query, final AsyncCallback<List<DropDownListData>> callback) {
				presenter.autoMaterialId(query, callback);
			}

			@Override
			public String buildDisplayString(final DropDownListData candidate) {
				return "<span><strong>ID：</strong>" + candidate.getId() + "<span> " + "<span><strong>名称：</strong>"
						+ candidate.getName() + "<span> ";
			}

			@Override
			public String buildReplaceString(final DropDownListData candidate) {
				return candidate.getId();
			}
		});
		layout.add(2, materialId);

		materialId.setAsyncValidator(new AsyncValidator() {
			@Override
			public void validate(final String value, final AsyncCallbackEx<String> callback) {
				if ("".equals(value) && materialId.isAllowBlank()) {
					materialId.setHasError(false);
					return;
				}
				presenter.checkMaterialId(materialId.getValue(), new AsyncCallbackEx<String>() {
					@Override
					public void onSuccess(final String result) {
						callback.onSuccess(result);
					}
				});
			}
		});

		contractId = new AsyncSuggestField<DropDownListData>("合同", new AsyncSuggestOracle<DropDownListData>() {

			@Override
			public void getCandidatesFromQuery(final String query, final AsyncCallback<List<DropDownListData>> callback) {
				presenter.autoContractId(query, callback);
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
		layout.add(1, contractId);
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
		groupId = new AsyncSuggestField<DropDownListData>("广告组ID", new AsyncSuggestOracle<DropDownListData>() {

			@Override
			public void getCandidatesFromQuery(final String query, final AsyncCallback<List<DropDownListData>> callback) {
				presenter.autoGroupId(query, callback);
			}

			@Override
			public String buildDisplayString(final DropDownListData candidate) {
				return "<span><strong>ID：</strong>" + candidate.getId() + "<span> " + "<span><strong>名称：</strong>"
						+ candidate.getName() + "<span> ";
			}

			@Override
			public String buildReplaceString(final DropDownListData candidate) {
				return candidate.getId();
			}
		});
		layout.add(2, groupId);
		groupId.setAsyncValidator(new AsyncValidator() {

			@Override
			public void validate(final String value, final AsyncCallbackEx<String> callback) {
				if ("".equals(value) && groupId.isAllowBlank()) {
					groupId.setHasError(false);
					return;
				}
				presenter.checkGroupId(groupId.getValue(), new AsyncCallbackEx<String>() {

					@Override
					public void onSuccess(final String result) {
						callback.onSuccess(result);
					}
				});
			}
		});

		units = new XLinkageSelector("售卖单位", "units") {

			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		units.setFieldName("units");
		units.addItem("---请先选择媒体---", "-1");
		units.addCascadingListHandler(new CascadingListHandler() {

			@Override
			public void onCascading(final CascadingListEvent event) {
				String mediaId = event.getValue().toString();
				if (mediaId.substring(0, 2).equals("02")) { // 网吧媒体
					presenter.getUnitsIsNetBar(new AsyncCallbackEx<List<DropDownListData>>() {

						@Override
						public void onSuccess(final List<DropDownListData> result) {
							units.getWidget().clear();
							units.addItem("--请选择--", "-1");
							for (final DropDownListData ddld : result) {
								units.addItem(ddld.getName(), ddld.getId());
							}
						}
					});
				} else { // 其他媒体
					presenter.getMediaType(5, new AsyncCallbackEx<List<DropDownListData>>() {

						@Override
						public void onSuccess(final List<DropDownListData> result) {
							units.getWidget().clear();
							units.addItem("--请选择--", "-1");
							for (final DropDownListData ddld : result) {
								units.addItem(ddld.getName(), ddld.getId());
							}
						}
					});
				}
			}
		});
		units.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (units.getValue().equals("-1")) {
					return "请选择售卖单位";
				}
				return null;
			}
		});
		units.setAllowBlank(false);
		units.setValidateOnBlur(true);
		media.addCascadeSelector(units);
		layout.add(1, units);

		rebate = new NumberField("折扣");
		rebate.setDecimal(isModal());
		rebate.setPrecision(2);
		rebate.setValue(1);
		rebate.setValue(0.89);
		layout.add(2, rebate);

		generalPrice = new NumberField("普通日价格");
		generalPrice.setAllowBlank(false);
		generalPrice.setValidateOnBlur(true);
		generalPrice.setDecimal(isModal());
		generalPrice.setPrecision(2);
		generalPrice.setValue(0);
		generalPrice.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if ((generalPrice.getValue() + "").trim().length() <= 0) {
					return "普通日价格不能为空";
				}
				return null;
			}
		});
		layout.add(1, generalPrice);

		specialPrice = new NumberField("特殊日价格");
		specialPrice.setAllowBlank(false);
		specialPrice.setValidateOnBlur(true);
		specialPrice.setDecimal(isModal());
		specialPrice.setPrecision(2);
		specialPrice.setValue(0);
		specialPrice.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if ((generalPrice.getValue() + "").trim().length() <= 0) {
					return "特殊日价格不能为空";
				}
				return null;
			}
		});
		layout.add(2, specialPrice);

		url = new AsyncSuggestField<DropDownListData>("广告链接", new AsyncSuggestOracle<DropDownListData>() {

			@Override
			public void getCandidatesFromQuery(final String query, final AsyncCallback<List<DropDownListData>> callback) {
				presenter.autoAdUrl(query, callback);
			}

			@Override
			public String buildDisplayString(final DropDownListData candidate) {
				return "<span><strong>名称：</strong>" + candidate.getName() + "<span><br/> "
						+ "<span><strong>URL：</strong>" + candidate.getGenerate() + "<span> ";
			}

			@Override
			public String buildReplaceString(final DropDownListData candidate) {
				return candidate.getGenerate();
			}
		});
		url.setValue("http://");
		url.setAllowBlank(false);
		url.setValidateOnBlur(true);
		url.setColSpan(2);
		layout.add(1, url);
		url.setAsyncValidator(new AsyncValidator() {

			@Override
			public void validate(final String value, final AsyncCallbackEx<String> callback) {
				presenter.checkAdUrl(url.getValue(), new AsyncCallbackEx<String>() {

					@Override
					public void onSuccess(final String result) {
						callback.onSuccess(null);
					}
				});
			}
		});
		url.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				String v = url.getValue();
				if (!url.isAllowBlank() && ("http://".equals(v) || "".equals(v))) {
					return "广告链接不能为空";
				}
				if (v.length() > 90) {
					return "你输入的太长了";
				}
				RegExp regexp = RegExp
						.compile("^((https|http)?://)+[A-Za-z0-9]+\\.[A-Za-z0-9]+[/=\\?%\\-&_~`@[\\]\\':+!]*([^<>\"\"])*$");
				if (regexp.exec(v) == null) {
					return "格式不正确";
				}
				return null;
			}
		});

		usedType = new SimpleSelector("消费类型") {

			@Override
			public void initItems() {
				addItem("---请选择---", "-1");
				addItem("购买", "1");
				addItem("配送", "2");
			}
		};
		usedType.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {

				if (usedType.getValue().equals("-1")) {
					return "请选择消费类型";
				}
				return null;
			}
		});

		usedType.setAllowBlank(false);
		usedType.setValidateOnBlur(true);
		layout.add(1, usedType);

		distribuRatio = new NumberField("配送比");
		distribuRatio.setDecimal(isModal());
		distribuRatio.setPrecision(2);
		distribuRatio.setAllowBlank(false);
		distribuRatio.setValidateOnBlur(true);
		distribuRatio.setValue(0.56);
		layout.add(2, distribuRatio);

		source = new TextAreaField("配送来源");
		source.setMaxLength(50);
		source.getWidget().setHeight("60px");
		source.setColSpan(2);
		layout.add(source);
		usedType.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				source.getWidget().setEnabled(usedType.getValue().equals("2"));
			}
		});

		initBox("添加广告排期", formpanel);
		setButtons(OKCANCEL);
	}

	@Override
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(OK)) {
			// logger.info("//////////////=========媒体=="+media.getValue()) ;
			// logger.info("//////////////=========广告位=="+position.getValue()) ;
			// logger.info("//////////////====看看posMap：==" +
			// posMap.get(position.getValue())) ;
			// 调用添加方法
			if (formpanel.validate()) {
				if (usedType.getValue().equals("2") && (source.getValue().length() == 0)) {
					MessageBox.alert("请填写配送来源");
					return;
				}
				final SheduleDataBase newDateBase = new SheduleDataBase();
				newDateBase.setMediaId(media.getValue());
				newDateBase.setPosId(position.getValue());
				if (!type.getValue().equals("")) {
					newDateBase.setTypes(Integer.parseInt(type.getValue()));
				}
				if (!materialId.getValue().equals("")) {
					newDateBase.setMaterialId(materialId.getValue());
				}
				newDateBase.setContractId(contractId.getValue());
				if (!groupId.getValue().equals("")) {
					newDateBase.setAdGroup(Integer.parseInt(groupId.getValue()));
				}
				if (!units.getValue().equals("")) {
					newDateBase.setUnits(Integer.parseInt(units.getValue()));
				}
				newDateBase
						.setRebate(Double.parseDouble((rebate.getValue().equals("") ? "0" : rebate.getValue() + "")));
				newDateBase.setGeneralPrice(Double.parseDouble((generalPrice.getValue().equals("") ? "0" : generalPrice
						.getValue() + "")));
				newDateBase.setSpecialPrice(Double.parseDouble((specialPrice.getValue().equals("") ? "0" : specialPrice
						.getValue() + "")));
				newDateBase.setStartTimeString(DateWrapper.format(time.getStartValue(), "yyyy-MM-dd"));
				newDateBase.setEndTimeString(DateWrapper.format(time.getEndValue(), "yyyy-MM-dd"));
				newDateBase.setAdUrl(url.getValue());
				newDateBase.setUsedType(usedType.getValue());
				newDateBase.setSource(source.getValue());
				newDateBase.setDistribuRatio(Double.parseDouble((distribuRatio.getValue().equals("") ? "0"
						: distribuRatio.getValue() + "")));
				newDateBase.setName(name.getValue());
				String id = "";
				id = id + DateWrapper.format(time.getStartValue(), "yyyyMMdd");
				id = id + DateWrapper.format(time.getEndValue(), "yyyyMMdd");
				id = id + posMap.get(newDateBase.getPosId()).toString();
				// newDateBase.setAdName(posMap.get(newDateBase.getPosId()).toString());
				logger.info("看看posMap：" + posMap);
				logger.info("看看 ID：" + id);
				newDateBase.setId(id);
				newDateBase.setAdId(posMap.get(newDateBase.getPosId()).toString());
				presenter.checkSheduleId(id, new AsyncCallbackEx<Boolean>() {

					@Override
					public void onSuccess(final Boolean result) {
						if (result) {
							presenter.addShedule(newDateBase, new AsyncCallbackEx<Integer>() {

								@Override
								public void onSuccess(final Integer result) {
									if (result == 1) {
										view.getGrid().load();
										hide();
									} else {
										MessageBox.alert("添加广告排期失败");
									}
								}
							});
						} else {
							MessageBox.alert("排期码是由[开始时间+结束时间+广告ID]组成并且不能重复，此编码已经存在");
						}
					}
				});
			}
		}
		if (button == getButtonByItemId(CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
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
