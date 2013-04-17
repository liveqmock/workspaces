/****************************************************************
 *  文件名     ： GuideAddView.java
 *  日期         :  2012-8-21
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.modules.config.client.ui.guide;

import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeEtcConfig;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeNode;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector.Delegate;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: GuideAddView
 * @Description: TODO(...)
 * @author: 吴君杰
 * @email: wujunjie@iwgame.com
 * @date: 2012-8-21下午03:20:20
 * @Version: 1.0
 */
@SuppressWarnings("deprecation")
public class GuideAddView extends Composite {

	private final XFormPanel formpanel;

	/**
	 * @return the formpanel
	 */
	public XFormPanel getFormpanel() {
		return formpanel;
	}

	private PlainObjectSelector<FlumeEtcConfig> collectSelector;

	private PlainObjectSelector<FlumeNode> collectRoom;

	private FlumeEtcConfig flumeEtcConfig;

	private TextField zoneField;

	private TextField groupField;

	public GuideAddView() {

		formpanel = new XFormPanel();
		formpanel.setWidth("580px");
		formpanel.setHeight("320px");
		FormLayout layout = new FormLayout();

		formpanel.setLayout(layout);
		zoneField = new TextField("游戏大区");
		zoneField.getWidget().setEnabled(false);
		

		groupField = new TextField("游戏组");
		groupField.getWidget().setEnabled(false);

		zoneField.setWidth("200px");
		groupField.setWidth("200px");
		
		//
		if (ApplicationContext.getCurrentProductId().toLowerCase().equalsIgnoreCase("p-wg1")) {
			zoneField.setDescription("  例如:wt1-group01 wg1-group02...");	
			groupField.setDescription("  ");
		} else {
			zoneField.setDescription("  例如:dx1 dx3 wt5 wt8...");	
			groupField.setDescription("  例如:game1 game2 game3...");
		}

		// 采集选项
		collectSelector = new PlainObjectSelector<FlumeEtcConfig>("采集类型", "") {
			@Override
			protected String getValue(final FlumeEtcConfig t) {
				return t.getT_channel_id();
			}

			@Override
			protected String getLabel(final FlumeEtcConfig t) {
				return t.getT_channel_name();
			}
		};
		collectSelector.setDelegate(new Delegate<FlumeEtcConfig>() {

			@Override
			public void loadItem(final AsyncCallback<List<FlumeEtcConfig>> callback) {
				ConfigService.Util.get().retrieveEtcConfig(ApplicationContext.getCurrentProduct().getName(), callback);

			}
		});

		collectSelector.getWidget().addChangeHandler(new ChangeHandler() {

			
			@Override
			public void onChange(ChangeEvent event) {
				collectSelector.unmark();
				collectRoom.unmark();

				zoneField.unmark();
				zoneField.setValue("");
				zoneField.setAllowBlank(true);
				zoneField.getWidget().setEnabled(false);

				groupField.unmark();
				groupField.setValue("");
				groupField.setAllowBlank(true);
				groupField.getWidget().setEnabled(false);
				groupField.reset();

				flumeEtcConfig = collectSelector.getData();
				if ("0".equals(flumeEtcConfig.getT_zone())) {
					zoneField.setAllowBlank(false);
					zoneField.setValidateOnBlur(true);
					zoneField.getWidget().setEnabled(true);
					if ("0".equals(flumeEtcConfig.getT_group())) {
						groupField.setAllowBlank(false);
						groupField.setValidateOnBlur(true);
						groupField.getWidget().setEnabled(true);
					}
				}
			}
		});

		collectSelector.addValidator(new Validator() {

			@Override
			public String validate(Field<?, ?> field) {
				if ("-1".equals(collectSelector.getValue())) {
					return "请选择采集数据选项";
				}
				return null;
			}
		});

		// 采集机房
		collectRoom = new PlainObjectSelector<FlumeNode>("采集机房", "") {
			@Override
			protected String getValue(final FlumeNode t) {
				return t.getNodeId();
			}

			@Override
			protected String getLabel(final FlumeNode t) {
				return t.getNodeName();
			}
		};
		collectRoom.setDelegate(new Delegate<FlumeNode>() {
			@Override
			public void loadItem(final AsyncCallback<List<FlumeNode>> callback) {
				ConfigService.Util.get().retrieveFlumeNodes(ApplicationContext.getCurrentProduct().getName(), "fetch", callback);
			}
		});

		collectRoom.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				collectRoom.unmark();
			}
		});

		collectRoom.addValidator(new Validator() {

			@Override
			public String validate(Field<?, ?> field) {
				if ("-1".equals(collectRoom.getValue())) {
					return "请选择采集机房";
				}
				return null;
			}
		});
		collectSelector.setValidateOnBlur(true);
		collectRoom.setValidateOnBlur(true);

		collectSelector.setWidth("210px");
		collectRoom.setWidth("210px");
		layout.add(collectSelector);
		layout.add(zoneField);
		layout.add(groupField);
		layout.add(collectRoom);
		initWidget(formpanel);
	}

	// 生成通道名称
	public String getGenerateFlowName() {
		String game = "";
		String zone = "";
		if (!"".equals(groupField.getValue())) {
			game = "." + groupField.getValue();
		}
		if (!"".equals(zoneField.getValue())) {
			zone = "." + zoneField.getValue();
		}
		String flow = flumeEtcConfig.getT_channel_id() + game + zone + "." + ApplicationContext.getCurrentProductId().toLowerCase();
		return flow;
	}

	// 生成通道名称
	public String getGenerateFlowExecSource() {
		String exec = flumeEtcConfig.getT_exec();
		String game = groupField.getValue() == null ? "" : groupField.getValue();
		String zone = zoneField.getValue() == null ? "" : zoneField.getValue();
		String productid = ApplicationContext.getCurrentProductId().toLowerCase();

		if ("".equals(zone)) {
			exec = exec.replace("${zone} ", zone);
		} else {
			exec = exec.replace("${zone}", zone);
		}
		if ("".equals(game)) {
			exec = exec.replace("${group} ", game);
		} else {
			exec = exec.replace("${group}", game);
		}

		exec = exec.replace("${productid}", productid);
		return exec.trim();
	}

	public String getCollectRoomSelectIndexOf() {
		return collectRoom.getValue();
	}

	public boolean validateFieldValue() {
		String zone = zoneField.getValue();
		if(zoneField.getWidget().isEnabled()){
			if (zone.indexOf("dx") != -1 || zone.indexOf("wt") != -1 || zone.indexOf("wg1") != -1) {
				String group = groupField.getValue();
				if(groupField.getWidget().isEnabled()){
					if (group.indexOf("game") != -1) {
						return true;
					} else {
						groupField.mark("游戏组格式不对!");
						return false;
					}
				}
				return true;
			} else {
				zoneField.mark("大区格式不对!");
				return false;
			}
		}
		return true;
	}
}
