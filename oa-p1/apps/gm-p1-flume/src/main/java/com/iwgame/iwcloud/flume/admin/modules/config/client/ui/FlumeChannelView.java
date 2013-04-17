/**      
 * FlumeChannelView.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.config.client.ui;

import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextArea;
import com.iwgame.iwcloud.flume.admin.client.dialogs.TimeDataDialog;
import com.iwgame.iwcloud.flume.admin.client.i18n.Messages;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.dialogs.ChannelDialog;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.guide.GuideDialog;
import com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService;
import com.iwgame.iwcloud.flume.admin.modules.monitor.shared.service.MonitorService;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeAnodeConfig;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeChannel;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeNode;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.GridColumnButton;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: FlumeChannelView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-30 下午05:59:54
 * @Version 1.0
 * 
 */
public class FlumeChannelView extends SchemaGridView {

	private TextField fldChannelKey;
	private SimpleSelector fldChannelNodeStatus;
	private SimpleSelector fldChannelStatus;
	private SimpleSelector fldChannelConfig;
	private PlainObjectSelector<FlumeNode> fldANodeRoom;

	DateTimeFormat dtFormat = DateTimeFormat.getFormat("yyyy年MM月dd日");
	Messages messages = GWT.create(Messages.class);

	/**
	 * 
	 */
	public FlumeChannelView() {
		super(15);
		getPanel().getGrid().setHasCheckBoxColumn(true);

		getPanel().getGrid().setColumnRenderer("channelName", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData rowData) {
						new TimeDataDialog() {

							@Override
							public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
								MonitorService.Util.get().getChannelTimeDatas(rowData.<String> get("channelId"),
										new Date(), callback);
							}

							@Override
							public String getDialogTitle() {
								return messages.channelTimeDialogTitle(rowData.<String> get("channelName"),
										dtFormat.format(new Date()));
							}
						}.center();
					}
				};
			}

		});

		getPanel().setColumnRenderer("status", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				int status = object.<Double> get("status").intValue();
				if (status == 0) {
					sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:green;'>运行中</span>"));
				} else {
					sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:red;'>已停用</span>"));
				}
			}
		});

		ColumnRenderer<BaseModelData> nodeStatusRenderer = new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				int status = object.<Double> get("status").intValue();
				if (status == 1) {
					sb.append(SafeHtmlUtils.fromSafeConstant("-"));
				} else {
					String v = object.get(config.getIndex());

					if ("ACTIVE".equals(v) || "IDLE".equals(v)) {
						sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:green;'>" + v + "</span>"));
					} else if ("EXCEPTION".equals(v) || "LOST".equals(v) || "ERROR".equals(v)) {
						sb.append(SafeHtmlUtils.fromSafeConstant("<span style='color:red;'>" + v + "</span>"));
					} else {
						sb.append(SafeHtmlUtils.fromSafeConstant("<span>" + v + "</span>"));
					}
				}
			}
		};
		getPanel().setColumnRenderer("aNodeStatus", nodeStatusRenderer);
		getPanel().setColumnRenderer("cNodeStatus", nodeStatusRenderer);

		getPanel().setColumnRenderer("aNodeActive", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				String text = "";
				if (status == 1) {
					text = "-";
				} else {
					text = "清除配置";
				}
				return text;
			}

			@Override
			public boolean isEnable(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				return status == 0;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						int status = object.<Double> get("status").intValue();
						if (status != 1) {
							final String config = object.get("aNodeConfig");

							MessageBox.confirm("您确定清除配置？", new Command() {

								@Override
								public void execute() {
									ConfigService.Util.get().unconfigAnode(config, new AsyncCallbackEx<String>() {

										@Override
										public void onSuccess(final String result) {
											if ("".equals(result)) {
												MessageBox.info("清除配置操作成功");
											} else {
												MessageBox.info(result);
											}
										}
									});
								}
							}, null);

						}

					}
				};
			}

			@Override
			public void onClick(final GridColumnButton<BaseModelData> btn) {

			}

		});

		getPanel().setColumnRenderer("aNodeDeactive", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				String text = "";
				if (status == 1) {
					text = "-";
				} else {
					text = "激活配置";
				}
				return text;
			}

			@Override
			public boolean isEnable(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				return status == 0;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						int status = object.<Double> get("status").intValue();
						if (status != 1) {
							final String config = object.get("aNodeConfig");

							MessageBox.confirm("您确定激活配置？", new Command() {

								@Override
								public void execute() {
									ConfigService.Util.get().configAnode(config, new AsyncCallbackEx<String>() {

										@Override
										public void onSuccess(final String result) {
											if ("".equals(result)) {
												MessageBox.info("激活配置操作成功");
											} else {
												MessageBox.info(result);
											}
										}
									});
								}
							}, null);

						}

					}
				};
			}

			@Override
			public void onClick(final GridColumnButton<BaseModelData> btn) {

			}

		});

		getPanel().setColumnRenderer("aNodeCommand", new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "获取命令";
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						FlumeAnodeConfig config = FlumeAnodeConfig.make(object.<String> get("aNodeConfig"));
						if (config == null) {
							MessageBox.alert("未配置！");
						} else {
							MessageBox.info(config.produceAnodeConfigCommand());
						}
					}
				};
			}

		});

		getPanel().setColumnRenderer("cNodeActive", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				String text = "";
				if (status == 1) {
					text = "-";
				} else {
					text = "清除配置";
				}
				return text;
			}

			@Override
			public boolean isEnable(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				return status == 0;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						int status = object.<Double> get("status").intValue();
						if (status != 1) {
							final String config = object.get("cNodeConfig");
							MessageBox.confirm("您确定清除配置？", new Command() {

								@Override
								public void execute() {
									ConfigService.Util.get().unconfigCnode(config, new AsyncCallbackEx<String>() {

										@Override
										public void onSuccess(final String result) {
											if ("".equals(result)) {
												MessageBox.info("清除配置操作成功");
											} else {
												MessageBox.info(result);
											}
										}
									});

								}

							}, null);

						}

					}
				};
			}

		});
		getPanel().setColumnRenderer("cNodeDeactive", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				String text = "";
				if (status == 1) {
					text = "-";
				} else {

					text = "激活配置";
				}
				return text;
			}

			@Override
			public boolean isEnable(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				return status == 0;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						int status = object.<Double> get("status").intValue();
						if (status != 1) {
							final String config = object.get("cNodeConfig");
							MessageBox.confirm("您确定激活配置？", new Command() {

								@Override
								public void execute() {
									ConfigService.Util.get().configCnode(config, new AsyncCallbackEx<String>() {

										@Override
										public void onSuccess(final String result) {
											if ("".equals(result)) {
												MessageBox.info("激活配置操作成功");
											} else {
												MessageBox.info(result);
											}
										}
									});

								}

							}, null);

						}

					}
				};
			}

		});

		getPanel().setColumnRenderer("admin", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				String text = "";
				if (status == 1) {
					text = "开启通道";
				} else {
					text = "关闭通道";
				}
				return text;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {

						int status = object.<Double> get("status").intValue();
						if (status == 1) {
							MessageBox.confirm("您确定开启通道？", new Command() {

								@Override
								public void execute() {
									ConfigService.Util.get().openChannel(object.<String> get("channelId"),
											object.<String> get("anodeId"), object.<String> get("cnodeId"),
											new AsyncCallbackEx<String>() {

												@Override
												public void onSuccess(final String result) {
													if ("".equals(result)) {
														MessageBox.info("开启通道操作成功");
													} else {
														MessageBox.info(result);
													}
													reload();
												}
											});

								}
							}, null);

						} else {
							MessageBox.confirm("您确定关闭通道？", new Command() {

								@Override
								public void execute() {

									ConfigService.Util.get().closeChannel(object.<String> get("channelId"),
											object.<String> get("anodeId"), object.<String> get("cnodeId"),
											new AsyncCallbackEx<String>() {

												@Override
												public void onSuccess(final String result) {
													if ("".equals(result)) {
														MessageBox.info("关闭通道操作成功");
													} else {
														MessageBox.info(result);
													}
													reload();
												}
											});
								}
							}, null);

						}
					}

				};
			}

		});
		
		getPanel().setColumnRenderer("action", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				int status = rowData.<Double> get("status").intValue();
				String text = "";
				if (status == 1) {
					text = "删除通道";
				} else {
					text = "--";
				}
				return text;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {

						int status = object.<Double> get("status").intValue();
						if (status == 1) {
							MessageBox.confirm("您确定删除通道？", new Command() {

								@Override
								public void execute() {
									ConfigService.Util.get().deleteChannel(ApplicationContext.getCurrentProductId(), object.<String> get("channelId"), new AsyncCallbackEx<Integer>(){

										@Override
										public void onSuccess(Integer result) {
											if(result>0){
												MessageBox.info("删除通道成功");
											}else{
												MessageBox.info("删除通道失败");
											}
											reload();
										}
									});
								}
							}, null);
						}
					}

				};
			}

		});
		
		

		ButtonToolItem btnRefresh = new ButtonToolItem("立即刷新");
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (!"".equals(fldChannelKey.getValue())) {
					getGrid().getLoadConfig().set("channelKey", fldChannelKey.getValue());
				} else {
					getGrid().getLoadConfig().remove("channelKey");
				}
				if (!"-1".equals(fldChannelNodeStatus.getValue())) {
					getGrid().getLoadConfig()
							.set("channelNodeStatus", Integer.valueOf(fldChannelNodeStatus.getValue()));
				} else {
					getGrid().getLoadConfig().remove("channelNodeStatus");
				}
				if (!"-1".equals(fldChannelStatus.getValue())) {
					getGrid().getLoadConfig().set("channelStatus", Integer.valueOf(fldChannelStatus.getValue()));
				} else {
					getGrid().getLoadConfig().remove("channelStatus");
				}
				if (!"-1".equals(fldChannelConfig.getValue())) {
					getGrid().getLoadConfig().set("channelConfig", fldChannelConfig.getValue());
				} else {
					getGrid().getLoadConfig().remove("channelConfig");
				}
				if (!"-1".equals(fldANodeRoom.getValue())) {
					getGrid().getLoadConfig().set("anoderoom", fldANodeRoom.getValue());
				} else {
					getGrid().getLoadConfig().remove("anoderoom");
				}
				getGrid().getLoadConfig().set("checkConfig", false);
				reload();
			}
		});

		ButtonToolItem btnAdd = new ButtonToolItem("新增通道");
		btnAdd.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				new ChannelDialog(FlumeChannelView.this, null).center();
			}
		});
		
		ButtonToolItem guideAdd = new ButtonToolItem("向导新增");
		guideAdd.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				new GuideDialog(FlumeChannelView.this, null).center();
			}
		});

		ButtonToolItem btnModify = new ButtonToolItem("修改通道");
		btnModify.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				List<BaseModelData> selected = getGrid().getSelected();
				if (selected.isEmpty()) {
					MessageBox.alert("请选择一个需要修改的通道！");
				} else if (selected.size() > 1) {
					MessageBox.info("只能选择一个需要修改的通道！");
				} else {
					BaseModelData data = selected.get(0);
					FlumeChannel channel = new FlumeChannel();
					channel.setChannelId(data.<String> get("channelId"));
					channel.setChannelName(data.<String> get("channelName"));
					channel.setPort(data.<Double> get("port").intValue());
					channel.setAnodeId(data.<String> get("anodeId"));
					channel.setaNodeConfig(data.<String> get("aNodeConfig"));
					channel.setCnodeId(data.<String> get("cnodeId"));
					channel.setcNodeConfig(data.<String> get("cNodeConfig"));
					new ChannelDialog(FlumeChannelView.this, channel).center();
				}

			}
		});

		ButtonToolItem btnCheckConfig = new ButtonToolItem("配置检查");
		btnCheckConfig.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				ConfigService.Util.get().retrieveAllFlumeChannels(ApplicationContext.getCurrentProductId(),
						new AsyncCallbackEx<String>() {

							@Override
							public void onSuccess(final String result) {
								MessageBox.info(result);
							}
						});
			}
		});

		ButtonToolItem btnPrintCommand = new ButtonToolItem("输出脚本命令");
		btnPrintCommand.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				ConfigService.Util.get().printScriptCommand(ApplicationContext.getCurrentProductId(),
						new AsyncCallbackEx<String>() {

							@Override
							public void onSuccess(final String result) {
								new CommandDialog(result).center();
							}
						});
			}
		});

		fldChannelKey = new TextField("通道关键字", "channelKey");
		fldChannelStatus = new SimpleSelector("通道状态", "channelStatus") {

			@Override
			public void initItems() {
				addItem("所有数据", "-1");
				addItem("运行中", "0");
				addItem("已停用", "1");
			}
		};
		fldChannelNodeStatus = new SimpleSelector("节点状态", "channelNodeStatus") {

			@Override
			public void initItems() {
				addItem("所有数据", "-1");
				addItem("正常", "0");
				addItem("异常", "1");
			}
		};
		fldChannelConfig = new SimpleSelector("配置状态", "channelConfig") {

			@Override
			public void initItems() {
				addItem("所有数据", "-1");
				addItem("已配置", "0");
				addItem("未配置", "1");
			}
		};
		fldANodeRoom = new PlainObjectSelector<FlumeNode>("采集节点机房", "") {

			@Override
			protected String getValue(final FlumeNode t) {
				return t.getNodeId();
			}

			@Override
			protected String getLabel(final FlumeNode t) {
				return t.getNodeName();
			}
		};
		fldANodeRoom.setDelegate(new PlainObjectSelector.Delegate<FlumeNode>() {

			@Override
			public void loadItem(final AsyncCallback<List<FlumeNode>> callback) {
				ConfigService.Util.get().retrieveFlumeNodes(ApplicationContext.getCurrentProduct().getName(), "fetch",
						callback);
			}
		});

		XFormPanel xFormPanel = new XFormPanel();
		FormLayout layout = new FormLayout();
		xFormPanel.setLayout(layout);
		layout.setColumn(3);
		fldANodeRoom.setColSpan(2);
		layout.add(1, fldChannelKey);
		layout.add(2, fldANodeRoom);
		layout.add(1, fldChannelStatus);
		layout.add(2, fldChannelConfig);
		layout.add(3, fldChannelNodeStatus);

		getPanel().getToolbar().addItem(xFormPanel);
		getPanel().getToolbar().addItem(btnRefresh);
		getPanel().getToolbar().addItem(guideAdd);
		getPanel().getToolbar().addItem(btnAdd);
		getPanel().getToolbar().addItem(btnModify);
		getPanel().getToolbar().addItem(btnCheckConfig);
		getPanel().getToolbar().addItem(btnPrintCommand);

	}

	public class CommandDialog extends XDialogBox {

		/**
		 * 
		 */
		public CommandDialog(final String text) {
			TextArea txt = new TextArea();
			txt.setValue(text);
			txt.setSize("650px", "350px");
			initBox("输出命令", txt);
			setHideOnButtonClick(true);
			setButtons(CLOSE);
		}
	}
}
