/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityLockAccountViewImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client.ui;

import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledCauseBox;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledTypeBox;
import com.iwgame.gm.p1.security.modules.operate.client.ui.widget.UsernameField;
import com.iwgame.gm.p1.security.modules.operate.client.ui.xdialog.SampleDialogBox;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.LockAccountBean;
import com.iwgame.gm.p1.security.modules.operate.shared.rpc.SecurityLockAccountService;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.CheckBoxField;
import com.iwgame.ui.panel.client.form.field.ComboBoxField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-15 下午03:47:21
 */
public class SecurityLockAccountViewImpl extends Composite implements SecurityLockAccountView {
	private final VerticalPanel vPanel = new VerticalPanel();
	private XUploadFormPanel formPanel;
	private FormLayout layout;
	private SecurityKilledCauseBox causetype;
	private SecurityKilledTypeBox optype;
	@SuppressWarnings("rawtypes")
	private ComboBoxField causenote;
	private NumberField sealtime;
	private CheckBoxField showtimeCheckBox;
	private HiddenField showtime;
	private UsernameField username;
	private CheckBoxField onlineCheckBox;
	private HiddenField online;
	private CheckBoxField mailnoticeCheckBox;
	private HiddenField mailnotice;
	private CheckBoxField sqloginCheckBox;
	private HiddenField sqlogin;
	private HiddenField pid;
	private HiddenField operator;
	private ButtonToolItem sublock;
	private LabelField label;

	private SecurityLockAccountPresenter presenter;
	
	public SecurityLockAccountViewImpl(SecurityLockAccountPresenter presenter){
		this.presenter = presenter;
		this.initSecurityLockAccountViewInfo();
		initWidget(vPanel);
	}
	
	@SuppressWarnings("rawtypes")
	public void initSecurityLockAccountViewInfo(){
		formPanel = new XUploadFormPanel("封杀账号","fileUploadForLockAccount"){
			@Override
			public void onUploadCompleted(String results) {
				MessageBox.info(results);
			}
		};
		layout = new FormLayout();
		sublock = new ButtonToolItem("提交封杀");
		layout.setColumn(2);
		causetype = new SecurityKilledCauseBox("原因分类","causetype");
		causetype.setValidateOnBlur(true);
		causetype.setAllowBlank(false);
		optype = new SecurityKilledTypeBox("封杀类型","optype");
		optype.setAllowBlank(false);
		causenote = new ComboBoxField("请选择封停原因", "causenote");
		causenote.setAllowBlank(false);
		causenote.addItem("--不限--", "");
		causenote.setColSpan(2);
		sealtime = new NumberField("封杀天数","sealtime");
		sealtime.setValidateOnBlur(true);
		sealtime.setAllowBlank(false);
		sealtime.setMax(5000);
		sealtime.setMin(1);
		sealtime.setAlignment(TextAlignment.LEFT);
		//sealtime.setRegx(RegexGwtHelper.SEALTIME_REGX, "封杀天数范围1~5000的正整数");
		showtimeCheckBox = new CheckBoxField("","游戏客户端对该用户显示封停天数","");
		showtimeCheckBox.setHiddenLabel(true);
		showtime = new HiddenField("showtime", "");
		username=new UsernameField("封杀账号", "test","username","usernames");
		username.setAllowBlank(false);
		onlineCheckBox = new CheckBoxField("","", "");
		onlineCheckBox.setHiddenLabel(true);
		onlineCheckBox.setColSpan(2);		
		online = new HiddenField("online", "");
		mailnoticeCheckBox = new CheckBoxField("","","");
		mailnoticeCheckBox.setHiddenLabel(true);
		mailnoticeCheckBox.setColSpan(2);
		mailnotice = new HiddenField("mailnotice", "");
		sqloginCheckBox = new CheckBoxField("","限制社区登入","");
		sqloginCheckBox.setHiddenLabel(true);
		sqloginCheckBox.setColSpan(2);
		sqlogin = new HiddenField("sqlogin", "");
		pid = new HiddenField("pid", ApplicationContext.getCurrentProductId());
		operator = new HiddenField("operator", ApplicationContext.getCurrrentUser().getUsername());
		
		label = new LabelField("查看样例");
		label.getWidget().setStyleName("cw_Label");
		label.getWidget().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SampleDialogBox box = new SampleDialogBox();
				box.setWidth(150);
				box.setHeight(120);
				box.setPopupPosition(event.getClientX(), event.getClientY());
				box.show();
			}
		});
		final ListBox causeNoteBox = (ListBox) causenote.getWidget();
		ListBox causeTypeBox = (ListBox)causetype.getWidget();
		causeTypeBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				String optypeval = optype.getValue();
				String causeType = causetype.getValue();
				causeNoteBox.clear();
				causenote.addItem("--不限--", "");
				if (!"".equals(causeType) && !"".equals(optypeval)) {
					SecurityLockAccountService.Util.get().getKilledCausesByCauseTypeAndKilledType(ApplicationContext.getCurrentProductId(),Integer.parseInt(causeType),Integer.parseInt(optypeval),new AsyncCallback<List<KilledCauseConfig>>() {
						@Override
						public void onSuccess(List<KilledCauseConfig> result) {
							for(KilledCauseConfig cause:result){
								if(cause.getId()!=null){
									causenote.addItem(cause.getCauseNumber()+"-"+cause.getCauseNote(),cause.getCauseNote());
								}
							}
						}
						@Override
						public void onFailure(Throwable caught) {}
					});
				}
			}
		});
		
		ListBox optypeListBox = (ListBox)optype.getWidget();
		optypeListBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				String optypeval = optype.getValue();
				String causeType = causetype.getValue();
				causeNoteBox.clear();
				causenote.addItem("--不限--", "");
				if (!"".equals(causeType) && !"".equals(optypeval)) {
					SecurityLockAccountService.Util.get().getKilledCausesByCauseTypeAndKilledType(ApplicationContext.getCurrentProductId(),Integer.parseInt(causeType),Integer.parseInt(optypeval),new AsyncCallback<List<KilledCauseConfig>>() {
						@Override
						public void onSuccess(List<KilledCauseConfig> result) {
							for(KilledCauseConfig cause:result){
								if(cause.getId()!=null){
									causenote.addItem(cause.getCauseNumber()+"-"+cause.getCauseNote(),cause.getCauseNote());
								}
							}
						}
						@Override
						public void onFailure(Throwable caught) {}
					});
				}
				if("2".equals(optype.getValue())){
					sealtime.getWidget().setReadOnly(true);
					sealtime.setValue(5000);
				}else{
					sealtime.setValue(1);
					sealtime.getWidget().setReadOnly(false);
				}
			}
		});
		
		
		sublock.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(showtimeCheckBox.getValue()){
					showtime.setValue("1");
				}else{
					showtime.setValue("0");
				}
				if(onlineCheckBox.getValue()){
					online.setValue("1");
				}else{
					online.setValue("0");
				}
				if(mailnoticeCheckBox.getValue()){
					mailnotice.setValue("1");
				}else{
					mailnotice.setValue("0");
				}
				if(sqloginCheckBox.getValue()){
					sqlogin.setValue("1");
				}else{
					sqlogin.setValue("0");
				}
				if(formPanel.validate()){
					if("more".equals(username.getValue())){
						formPanel.submit();
					}else{
						LockAccountBean bean = new LockAccountBean();
						bean.setPid(pid.getValue());
						bean.setGuid("all");
						bean.setSealtime(sealtime.getValue().intValue());
						bean.setUsername(username.getVString());
						bean.setShowtime(Integer.parseInt(showtime.getValue()));
						bean.setOnline(Integer.parseInt(online.getValue()));
						bean.setMailnotice(Integer.parseInt(mailnotice.getValue()));
						bean.setSqlogin(Integer.parseInt(sqlogin.getValue()));
						bean.setOptype(Integer.parseInt(optype.getValue()));
						bean.setCausetype(Integer.parseInt(causetype.getValue()));
						bean.setCausenote(causenote.getValue());
						
						presenter.onClickSubmit(bean, operator.getValue());
					}
				}
			}
		});
		
		layout.add(causetype);
		layout.add(2,optype);
		layout.add(causenote);
		layout.add(sealtime);
		layout.add(2,showtimeCheckBox);
		layout.add(username);
		layout.add(onlineCheckBox);
		layout.add(mailnoticeCheckBox);
		layout.add(sqloginCheckBox);
		layout.add(showtime);
		layout.add(online);
		layout.add(mailnotice);
		layout.add(sqlogin);
		layout.add(pid);
		layout.add(operator);
		layout.add(2,label);
		formPanel.setLayout(layout);
		formPanel.addButton(sublock);
		vPanel.add(formPanel);
	}

	@Override
	public void doClickSubmit(LockAccountBean bean,String operator) {
		SecurityLockAccountService.Util.get().insert(bean, operator, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				MessageBox.info(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {}
		});
	}
	
	@Override
	protected void onAttach() {
		super.onAttach();
		SecurityLockAccountService.Util.get().getGlobalResource(new AsyncCallback<Map<String, Object>>() {
			
			@Override
			public void onSuccess(Map<String, Object> result) {
				onlineCheckBox.getWidget().setText("立即强制下线(会影响账号注册充值,批量账号下线禁止超过"+result.get("onlineMaxNum")+"个账号)");
				mailnoticeCheckBox.getWidget().setText("封停通知(勾选后,发送通知邮件到被封账号注册邮箱,最多"+result.get("mailnoticeMaxNum")+"个账号批量发送)");
			}
			
			@Override
			public void onFailure(Throwable caught) {}
		});
	}
}
