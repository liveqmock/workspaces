/**      
 * ChannelDialog.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.iwcloud.flume.admin.modules.config.client.ui.guide;

import java.util.List;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TabPanel;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.events.ChannelSelectedEvent;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.events.ChannelSelectedHandler;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.events.HasChannelSelectedHandlers;
import com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeChannel;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeNode;
import com.iwgame.ui.client.jsoneditor.JSONEditorField;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector.Delegate;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.AppUtils;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 
 * @ClassName: ChannelDialog
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-2 上午09:00:12
 * @Version 1.0
 * 
 */
@SuppressWarnings("deprecation")
public class OldGuideDialog extends XDialogBox implements HasChannelSelectedHandlers {

	/**
	 * 
	 */
	private static final String ANODE_ID_FLOW_ID_SOURCE_SINK = "{\"anodeId\":\"\",\"flowId\":\"\",\"source\":\"\",\"sink\":\"\"}";
	private static final String CNODE_ID_FLOW_ID_SOURCE_SINK = "{\"cnodeId\":\"\",\"flowId\":\"\",\"source\":\"\",\"sink\":\"\"}";
	// private static final String SINK =
	// "{ monitorDecorator( \"fetch\" ) => < { lazyOpen => rpcSink( \"${nodeid}\", ${port} ) } ? { diskFailover => { insistentAppend => { stubbornAppend => { insistentOpen => { lazyOpen => rpcSink( \"${nodeid}\", ${port} ) } } } } } > }";

	private static final String ANODE_SINK = "{ monitorDecorator( \"fetch\" ) => < { lazyOpen => mq-sink(${mqsink}) } ? { diskFailover => { insistentAppend => { stubbornAppend => { insistentOpen => { lazyOpen => mq-sink(${mqsink}) } } } } } > }";
	// private static final String CNODE_SOURCE = "myCollectorSource(${port})";
	private static final String CNODE_SINK = "collector(15000)  {{removeDuplicateDecorator() => {monitorDecorator( \"sink\" ) =>mq-sink(${mqsink})}}}";
	private XFormPanel basicForm;

	private XFormPanel fetcherForm;

	private XFormPanel sinkForm;

	private JSONEditorField fldANodeConfig;

	private JSONEditorField fldCNodeConfig;

	private PlainObjectSelector<FlumeNode> fldCNodeRoom;

	private PlainObjectSelector<FlumeNode> fldANodeRoom;

	private GuidePortField fldChannelPort;

	private TextField fldChannelName;

	private TextField fldChannelId;

	private final SchemaGridView view;

	private boolean isNewRecord = true;

	private String mqSink;

	private void buildBasicForm() {
		basicForm = new XFormPanel();
		FormLayout layout = new FormLayout();
		layout.setWidth(600);
		layout.setFieldWidth(400);
		basicForm.setLayout(layout);

		fldChannelId = new TextField("通道编号");
		fldChannelId.setAllowBlank(false);
		fldChannelId.setValidateOnBlur(true);
		fldChannelId.setDescription("  (格式: flow-[xxxx].{guid}.{gameid}." + ApplicationContext.getCurrentProduct().getName().toLowerCase() + ")");
		fldChannelId.getWidget().addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(final BlurEvent event) {
				String channelId = fldChannelId.getValue();
				boolean ok = false;
				RegExp regExp1 = RegExp.compile("(flow-[^\\.]+)\\.(p-\\w*\\.?\\d)");
				RegExp regExp2 = RegExp.compile("(flow-[^\\.]+)\\.([^\\.]*)\\.(p-\\w*\\.?\\d)");
				RegExp regExp3 = RegExp.compile("(flow-[^\\.]+)\\.([^\\.]*)\\.([^\\.]*)\\.(p-\\w*\\.?\\d)");
				if (regExp1.test(channelId)) {
					mqSink = "";
					ok = true;
				} else if (regExp2.test(channelId)) {
					MatchResult result = regExp2.exec(channelId);
					mqSink = "\"zone:" + result.getGroup(2) + "\"";
					ok = true;
				} else if (regExp3.test(channelId)) {
					MatchResult result = regExp3.exec(channelId);
					mqSink = "\"zone:" + result.getGroup(3) + "\",\"gameid:" + result.getGroup(2) + "\"";
					ok = true;
				} else {
					MessageBox.error("通道编号格式错误！");
					return;
				}
				if (ok) {
					if ((fldChannelName.getValue() == null) || fldChannelName.getValue().isEmpty()) {
						fldChannelName.setValue(channelId);
					}
					String a = channelId.replace("flow", "anode");
					String c = channelId.replace("flow", "cnode");
					JSONValue aconfig = fldANodeConfig.getValue();
					aconfig.isObject().put("anodeId", new JSONString(a));
					aconfig.isObject().put("flowId", new JSONString(channelId));
					aconfig.isObject().put("sink", new JSONString(ANODE_SINK.replace("${mqsink}", mqSink)));
					fldANodeConfig.setValue(aconfig);
					JSONValue cconfig = fldCNodeConfig.getValue();
					cconfig.isObject().put("cnodeId", new JSONString(c));
					cconfig.isObject().put("flowId", new JSONString(channelId));
					cconfig.isObject().put("sink", new JSONString(CNODE_SINK.replace("${mqsink}", mqSink)));
					fldCNodeConfig.setValue(cconfig);
				}
			}
		});

		layout.add(fldChannelId);

		fldChannelName = new TextField("通道名称");
		fldChannelName.setAllowBlank(false);
		fldChannelName.setValidateOnBlur(true);
		layout.add(fldChannelName);

		fldChannelPort = new GuidePortField("分配端口");
		fldChannelPort.setAllowBlank(false);
		fldChannelPort.setValidateOnBlur(true);
		fldChannelPort.getWidget().getBtnRecommendPort().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				String productId = ApplicationContext.getCurrentProduct().getName();
				String channelId = fldChannelId.getValue();
				ConfigService.Util.get().getNextRecommendPort(productId, channelId, new AsyncCallbackEx<Integer>() {

					@Override
					public void onSuccess(final Integer result) {
						fldChannelPort.setValue(result);
					}
				});

			}
		});
		fldChannelPort.getWidget().getPortBox().addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(final BlurEvent event) {
				int port = fldChannelPort.getValue();
				JSONValue cconfig = fldCNodeConfig.getValue();
				cconfig.isObject().put("source", new JSONString("myCollectorSource(" + port + ")"));
				fldCNodeConfig.setValue(cconfig);
			}

		});

//		layout.add(fldChannelPort);

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
		fldANodeRoom.setDelegate(new Delegate<FlumeNode>() {
			@Override
			public void loadItem(final AsyncCallback<List<FlumeNode>> callback) {
				ConfigService.Util.get().retrieveFlumeNodes(ApplicationContext.getCurrentProduct().getName(), "fetch", callback);

			}
		});
		fldANodeRoom.addValidator(new Validator() {
			@Override
			public String validate(final Field<?, ?> field) {
				if ("-1".equals(fldANodeRoom.getValue())) {
					return "请选择采集节点机房";
				}
				return null;
			}
		});

		fldANodeRoom.setValidateOnBlur(true);
		layout.add(fldANodeRoom);

		fldCNodeRoom = new PlainObjectSelector<FlumeNode>("收集节点机房", "") {

			@Override
			protected String getValue(final FlumeNode t) {
				return t.getNodeId();
			}

			@Override
			protected String getLabel(final FlumeNode t) {
				return t.getNodeName();
			}
		};
		fldCNodeRoom.setDelegate(new Delegate<FlumeNode>() {

			@Override
			public void loadItem(final AsyncCallback<List<FlumeNode>> callback) {
				ConfigService.Util.get().retrieveFlumeNodes(ApplicationContext.getCurrentProduct().getName(), "sink", callback);

			}
		});
		fldCNodeRoom.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if ("-1".equals(fldCNodeRoom.getValue())) {
					return "请选择收集节点机房";
				}
				return null;
			}
		});
		fldCNodeRoom.setValidateOnBlur(true);
		fldCNodeRoom.getWidget().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				fldCNodeRoom.unmark();
			}
		});
		layout.add(fldCNodeRoom);
	}

	private void buildFetcherForm() {
		fetcherForm = new XFormPanel();
		FormLayout layout = new FormLayout();
		layout.setWidth(600);
		fetcherForm.setLayout(layout);

		fldANodeConfig = new JSONEditorField("采集策略配置", "");
		fldANodeConfig.setValue(ANODE_ID_FLOW_ID_SOURCE_SINK);
		fldANodeConfig.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				JSONValue json = fldANodeConfig.getValue();
				boolean hasEmptyNode = "".equals(json.isObject().get("anodeId").isString().stringValue()) || "".equals(json.isObject().get("flowId").isString().stringValue())
						|| "".equals(json.isObject().get("source").isString().stringValue()) || "".equals(json.isObject().get("sink").isString().stringValue());
				if (hasEmptyNode) {
					return "采集策略配置不正确，不能有空值！";
				}
				return null;
			}
		});
		layout.add(fldANodeConfig);
	}

	private void buildSinkForm() {
		sinkForm = new XFormPanel();
		FormLayout layout = new FormLayout();
		layout.setWidth(600);
		sinkForm.setLayout(layout);

		fldCNodeConfig = new JSONEditorField("收集策略配置", "");
		fldCNodeConfig.setValue(CNODE_ID_FLOW_ID_SOURCE_SINK);
		fldCNodeConfig.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				JSONValue json = fldCNodeConfig.getValue();
				boolean hasEmptyNode = "".equals(json.isObject().get("cnodeId").isString().stringValue()) || "".equals(json.isObject().get("flowId").isString().stringValue())
						|| "".equals(json.isObject().get("source").isString().stringValue()) || "".equals(json.isObject().get("sink").isString().stringValue());
				if (hasEmptyNode) {
					return "收集策略配置不正确，不能有空值！";
				}
				return null;
			}
		});
		layout.add(fldCNodeConfig);
	}

	public OldGuideDialog(final SchemaGridView view, final FlumeChannel channel) {

		this.view = view;

		buildBasicForm();
		buildFetcherForm();
		buildSinkForm();

		TabPanel tab = new TabPanel();
		tab.add(basicForm, "通道基本信息");
		tab.add(fetcherForm, "采集节点信息");
//		tab.add(sinkForm, "收集节点信息");

		if (channel != null) {
			initBox("修改通道", tab);
			fldChannelId.setValue(channel.getChannelId());
			fldChannelId.getWidget().setReadOnly(true);
			fldChannelName.setValue(channel.getChannelName());
			fldChannelPort.setValue(channel.getPort());
			fldANodeConfig.setValue("".equals(channel.getaNodeConfig()) ? ANODE_ID_FLOW_ID_SOURCE_SINK : channel.getaNodeConfig());
			fldCNodeConfig.setValue("".equals(channel.getcNodeConfig()) ? CNODE_ID_FLOW_ID_SOURCE_SINK : channel.getcNodeConfig());
			fldANodeRoom.setValue(channel.getAnodeId());
			fldCNodeRoom.setValue(channel.getCnodeId());
			isNewRecord = false;
		} else {
			initBox("", tab);
			isNewRecord = true;
		}
		if (!isNewRecord) {
			fldChannelPort.getWidget().modifyStatus();
		}
		tab.selectTab(0);

		addChannelSelectedHandler(new ChannelSelectedHandler() {

			@Override
			public void buildChannelInfo(ChannelSelectedEvent event) {
				fldANodeRoom.setValue(event.getSelectIndexOf());
				fldChannelId.setValue(event.getChannelName());
				String source = event.getExecStr();
				String channelId = fldChannelId.getValue();
				fldChannelName.setValue(channelId);
				if (!channelId.equals("")) {
					boolean ok = false;
					RegExp regExp1 = RegExp.compile("(flow-[^\\.]+)\\.(p-\\w*\\.?\\d)");
					RegExp regExp2 = RegExp.compile("(flow-[^\\.]+)\\.([^\\.]*)\\.(p-\\w*\\.?\\d)");
					RegExp regExp3 = RegExp.compile("(flow-[^\\.]+)\\.([^\\.]*)\\.([^\\.]*)\\.(p-\\w*\\.?\\d)");
					if (regExp1.test(channelId)) {
						mqSink = "";
						ok = true;
					} else if (regExp2.test(channelId)) {
						MatchResult result = regExp2.exec(channelId);
						mqSink = "\"zone:" + result.getGroup(2) + "\"";
						ok = true;
					} else if (regExp3.test(channelId)) {
						MatchResult result = regExp3.exec(channelId);
						mqSink = "\"zone:" + result.getGroup(3) + "\",\"gameid:" + result.getGroup(2) + "\"";
						ok = true;
					} else {
						MessageBox.error("通道编号格式错误！");
						return;
					}
					if (ok) {
						if ((fldChannelName.getValue() == null) || fldChannelName.getValue().isEmpty()) {
							fldChannelName.setValue(channelId);
						}
						String a = channelId.replace("flow", "anode");
						String c = channelId.replace("flow", "cnode");
						JSONValue aconfig = fldANodeConfig.getValue();
						aconfig.isObject().put("anodeId", new JSONString(a));
						aconfig.isObject().put("flowId", new JSONString(channelId));
						aconfig.isObject().put("source", new JSONString(source));
						aconfig.isObject().put("sink", new JSONString(ANODE_SINK.replace("${mqsink}", mqSink)));
						fldANodeConfig.setValue(aconfig);
						JSONValue cconfig = fldCNodeConfig.getValue();
						cconfig.isObject().put("cnodeId", new JSONString(c));
						cconfig.isObject().put("flowId", new JSONString(channelId));
						cconfig.isObject().put("sink", new JSONString(CNODE_SINK.replace("${mqsink}", mqSink)));
						fldCNodeConfig.setValue(cconfig);
					}
				}

			}
		});

		String productId = ApplicationContext.getCurrentProduct().getName();
		String channelId = fldChannelId.getValue();
		ConfigService.Util.get().getNextRecommendPort(productId, channelId, new AsyncCallbackEx<Integer>() {

			@Override
			public void onSuccess(final Integer result) {
				fldChannelPort.setValue(result);
				int port = fldChannelPort.getValue();
				JSONValue cconfig = fldCNodeConfig.getValue();
				cconfig.isObject().put("source", new JSONString("myCollectorSource(" + port + ")"));
				fldCNodeConfig.setValue(cconfig);
			}
		});

		setButtons("");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.panel.client.box.XDialogBox#onButtonPressed(com.google.
	 * gwt.user.client.ui.Button)
	 */
	@Override
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(XDialogBox.OK)) {
			if (basicForm.validate() && fetcherForm.validate() && sinkForm.validate()) {
				FlumeChannel channel = new FlumeChannel();
				channel.setChannelId(fldChannelId.getValue());
				channel.setChannelName(fldChannelName.getValue());
				channel.setPort(fldChannelPort.getValue().intValue());
				channel.setProductId(ApplicationContext.getCurrentProduct().getName());
				channel.setAnodeId(fldANodeRoom.getValue());
				channel.setaNodeConfig(fldANodeConfig.getValue().toString());
				channel.setCnodeId(fldCNodeRoom.getValue());
				channel.setcNodeConfig(fldCNodeConfig.getValue().toString());
				if (isNewRecord) {
					channel.setStatus(1);
					ConfigService.Util.get().createChannel(channel, new AsyncCallbackEx<String>() {

						@Override
						public void onSuccess(final String result) {
							view.reload();
						}
					});
				} else {
					ConfigService.Util.get().updateChannel(channel, new AsyncCallbackEx<Integer>() {
						@Override
						public void onSuccess(final Integer result) {
							view.reload();
						}
					});
				}
			}
		}
		super.onButtonPressed(button);
	}

	public boolean onFilsh() {
		if (basicForm.validate() && fetcherForm.validate() && sinkForm.validate()) {
			FlumeChannel channel = new FlumeChannel();
			channel.setChannelId(fldChannelId.getValue());
			channel.setChannelName(fldChannelName.getValue());
			channel.setPort(fldChannelPort.getValue().intValue());
			channel.setProductId(ApplicationContext.getCurrentProduct().getName());
			channel.setAnodeId(fldANodeRoom.getValue());
			channel.setaNodeConfig(fldANodeConfig.getValue().toString());
			channel.setCnodeId(fldCNodeRoom.getValue());
			channel.setcNodeConfig(fldCNodeConfig.getValue().toString());
			if (isNewRecord) {
				channel.setStatus(1);
				ConfigService.Util.get().createChannel(channel, new AsyncCallbackEx<String>() {

					@Override
					public void onSuccess(final String result) {
						view.reload();
					}
				});
			} else {
				ConfigService.Util.get().updateChannel(channel, new AsyncCallbackEx<Integer>() {
					@Override
					public void onSuccess(final Integer result) {
						view.reload();
					}
				});
			}
			return true;
		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.iwcloud.flume.admin.modules.config.client.ui.events.
	 * HasChannelSelectedHandlers
	 * #addChannelSelectedHandler(com.iwgame.iwcloud.flume
	 * .admin.modules.config.client.ui.events.ChannelSelectedHandler)
	 */
	@Override
	public HandlerRegistration addChannelSelectedHandler(ChannelSelectedHandler handler) {
		return AppUtils.EVENT_BUS.addHandler(ChannelSelectedEvent.TYPE, handler);
	}

}
