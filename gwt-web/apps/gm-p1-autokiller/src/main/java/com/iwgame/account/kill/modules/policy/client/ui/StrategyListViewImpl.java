/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： StrategyListViewImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.iwgame.account.kill.modules.policy.client.presenter.StrategyListPresenter;
import com.iwgame.account.kill.modules.policy.client.ui.dialogs.StrategyDialog;
import com.iwgame.account.kill.modules.policy.client.ui.dialogs.HoldTipInfo;
import com.iwgame.account.kill.modules.policy.shared.model.KillPolicy;
import com.iwgame.account.kill.modules.policy.shared.model.Policy;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.MessageBoxEvent;
import com.iwgame.ui.panel.client.box.MessageBoxHandler;
import com.iwgame.ui.panel.client.box.XButton;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述：自动封停策略视图实现类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-3 上午10:08:02
 */
public class StrategyListViewImpl extends SchemaGridView implements StrategyListView {

	private StrategyListPresenter presenter;

	private ButtonToolItem addbtn;
	
	private XFormPanel formPanel;
	private FormLayout layout;
	private SimpleSelector statustype;

	public StrategyListViewImpl() {
		super(15);
		
		formPanel = new XFormPanel("自动封杀策略");
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);
		
		statustype = new SimpleSelector("封杀策略状态") {

			@Override
			public void initItems() {
				addItem("所有", "-1");
				addItem("运行", "1");
				addItem("休眠", "0");
			}
		};
		
		statustype.getWidget().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
				loadConfig.set("statustype", Integer.parseInt(statustype.getValue()));
				getGrid().load();
			}
		});
		
		layout.add(statustype);
		addbtn = new ButtonToolItem("添加自动封杀策略");
		addbtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				new StrategyDialog(true, getPanel().getGrid(), presenter, null).center();
			}
		});
		
		getPanel().getToolbar().addItem(formPanel);
		getPanel().getToolbar().addItem(addbtn);

		// 渲染
		getPanel().setColumnRenderer("statusShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				if (null != object.get("status")) {
					Double d = object.get("status");
					int sd = d.intValue();
					if (sd == 0) {
						sb.appendHtmlConstant("<font color = 'gray'>休眠</b>");
					} else if (sd == 1) {
						sb.appendHtmlConstant("<font color = 'green'>运行</font>");
					} else {
						sb.appendHtmlConstant("未知状态：" + object.get("status"));
					}
				}
			}
		});

		// 渲染执行时间
		getPanel().setColumnRenderer("startDateShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				if (null != object.get("startDate") && null != object.get("status")) {
					if (((Double) object.get("status")).intValue() == 1) {
						String str = object.get("startDate").toString();
						sb.appendHtmlConstant(str.substring(0,str.length()-3));
					} else {
						sb.appendHtmlConstant("&#8212;");
					}
				}
			}
		});
		
		
		
		// 渲染结束时间
		getPanel().setColumnRenderer("endDateShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				if (null != object.get("startDate") && null != object.get("status")) {
					if (((Double) object.get("status")).intValue() == 1) {
						String s = object.get("startDate").toString();;
						DateTimeFormat formatter=DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
							DateWrapper dw=new DateWrapper(formatter.parse(s)).addDays(7);
							sb.appendHtmlConstant(DateWrapper.format(dw.asDate(),"yyyy-MM-dd HH:mm"));

					} else {
						sb.appendHtmlConstant("&#8212;");
					}
				}
			}
		});

		// 渲染等级过滤
		getPanel().setColumnRenderer("levelShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				/*
				 * "level":0,"levelOpt":"gt"
				 */
				if (null != object.get("policy")) {
					if (null != object.get("policy.levelOpt")) {
						String show = "";
						String levelOpt = object.get("policy.levelOpt").toString();
						if (levelOpt.equals("-1"))
							show = "&#8212;";
						else if (levelOpt.equals("gt"))
							show = "&ge;";
						else if (levelOpt.equals("lt"))
							show = "&#8804;";
						if (!levelOpt.equals("-1")) {
							Double d = (Double) object.get("policy.level");
							show += " " + d.intValue();
						}
						sb.appendHtmlConstant(show);
					}
				}
			}
		});
		// 渲染充值过滤
		getPanel().setColumnRenderer("paidShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				/* "paid":-1 */
				if (null != object.get("policy")) {
					if (null != object.get("policy.paid")) {
						Double pd = object.get("policy.paid");
						if (pd.intValue() == -1) {
							sb.appendHtmlConstant("无限制");
						} else if (pd.intValue() == 0) {
							sb.appendHtmlConstant("无");
						} else {
							sb.appendHtmlConstant(pd.intValue() + "");
						}
					}
				}
			}
		});
		// 启用& 停用 按钮
		getGrid().setColumnRenderer("stop", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						Double ds = object.get("status");
						final Double id = object.get("id");
						final String name = object.get("title");
						final String mac = object.get("object");
						final String username = ApplicationContext.getCurrrentUser().getUsername();
						if (ds.intValue() == 1) { // return "停用";
							MessageBox.confirm("您确认停用" + name+"吗？", new MessageBoxHandler() {
								@Override
								public void onClose(final MessageBoxEvent event) {
									XButton button = event.getButton();
									if (button.getItemId().equals(XDialogBox.YES)) {
										presenter.stop(id.intValue(), username, mac,
												new AsyncCallbackEx<Integer>() {

													@Override
													public void onSuccess(Integer result) {
														// if (result == 1) {
														new HoldTipInfo("停用",getGrid()).center();
														// } else {
														// MessageBox.alert("停用失败");
														// }
													}
												});
									}
								}
							});
						} else if (ds.intValue() == 0) { // return "启用";
							presenter.checkIsEnabled(mac, new AsyncCallbackEx<Boolean>() {

								@Override
								public void onSuccess(Boolean result) {
									if (result) {
										MessageBox.confirm("您确认启用" + name+"吗？", new MessageBoxHandler() {
											@Override
											public void onClose(final MessageBoxEvent event) {
												XButton button = event.getButton();
												if (button.getItemId().equals(XDialogBox.YES)) {
													presenter.enabled(id.intValue(), username, mac,
															new AsyncCallbackEx<Integer>() {

																@Override
																public void onSuccess(Integer result) {
																	// if
																	// (result
																	// == 1) {
																	new HoldTipInfo("启用",getGrid()).center();
																	// } else {
																	// MessageBox.alert("启用失败");
																	// }
																}
															});
												}
											}
										});
									} else {
										MessageBox.alert("启用数已经达到最大或者已有相同的MAC启动了");
									}
								}
							});
						}
					}
				};
			}

			// 渲染 按钮
			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				Double d = rowData.get("status");
				if (d.intValue() == 1) {
					return "停用";
				} else if (d.intValue() == 0) {
					return "启用";
				}
				return null;
			}
		});
		// 修改 按钮
		getGrid().setColumnRenderer("update", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						Policy p = new Policy();

						p.setAccounts(((Double) object.get("policy.accounts")).intValue());
						p.setDays(((Double) object.get("policy.days")).intValue());
						p.setDelay(((Double) object.get("policy.delay")).intValue());
						p.setLevel(((Double) object.get("policy.level")).intValue());
						p.setLevelOpt(object.get("policy.levelOpt").toString());
						p.setPaid(((Double) object.get("policy.paid")).intValue());
						p.setReason(((Double) object.get("policy.reason")).intValue());
						KillPolicy kp = new KillPolicy();
						kp.setPolicy(p);
						kp.setObject(object.get("object").toString());
						kp.setObjectId(object.get("objectId").toString());
						kp.setTitle(object.get("title").toString());
						kp.setId(((Double) object.get("id")).intValue());
						kp.setStatus(((Double) object.get("status")).intValue());
						new StrategyDialog(false, getPanel().getGrid(), presenter, kp).center();
					}

				};
			}

			// 渲染 按钮
			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "修改";
			}
		});

	}

	@Override
	public void setPresenter(StrategyListPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	@Override
	public void reusltAddKillPolicy(Integer result) {

	}

}
