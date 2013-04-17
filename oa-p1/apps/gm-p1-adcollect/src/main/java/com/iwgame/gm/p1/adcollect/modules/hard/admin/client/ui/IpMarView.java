/**      
 * IpMarView.java Create on 2012-11-8 下午12:05:03    
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.IpMarPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.ADDNetbarIpDialog;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.MessageBoxEvent;
import com.iwgame.ui.panel.client.box.MessageBoxHandler;
import com.iwgame.ui.panel.client.box.XButton;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FileUploadField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * @ClassName: 网吧对应IP列表视图
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-8 下午12:05:03
 * @Version 1.0
 * 
 */
public class IpMarView extends SchemaGridView {
    private IpMarPresenter presenter;

    public IpMarPresenter getPresenter() {
	return presenter;
    }

    public void setPresenter(IpMarPresenter presenter) {
	super.setPresenter(presenter);
	this.presenter = presenter;
    }

    private final XFormPanel formPanel;
    private final FormLayout layout;

    private PlainObjectSelector<DropDownListData> media;
    private TextField ip;
    private SimpleSelector ipArea;

    /**
     * @param mediaId  媒体ID
     */
    public IpMarView(String mediaId) {
	super(25);
	getPanel().getGrid().setHasCheckBoxColumn(true);

	formPanel = new XFormPanel("媒体对应ip表");
	formPanel.setWidth("100%");
	layout = new FormLayout();
	layout.setColumn(3);
	formPanel.setLayout(layout);

	media = new PlainObjectSelector<DropDownListData>("网吧媒体") {

	    @Override
	    protected String getValue(final DropDownListData t) {
		return t.getId();
	    }

	    @Override
	    protected String getLabel(final DropDownListData t) {
		return t.getName();
	    }
	};

	media.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

	    @Override
	    public void loadItem(
		    final AsyncCallback<List<DropDownListData>> callback) {
		presenter.getMedia(2, callback);
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

	layout.add(1, media);

	if(null!=mediaId){
		media.setValue(mediaId);
	}
	
	ip = new TextField("IP");
	layout.add(2, ip);

	ipArea = new SimpleSelector("对应IP区域") {

	    @Override
	    public void initItems() {
		
		addItem("---请选择---", "-1");
		String area = new String("北京-天津市-河北-山西省-内蒙古-辽宁-吉林省-黑龙江-上海-江苏-浙江"
			+ "-安徽-福建-江西-山东-河南-湖北-湖南-广东-广西-海南-重庆-四川-贵州-"
			+ "云南-西藏-陕西-甘肃-青海-宁夏回-新疆-香港-澳门-台湾-海外-未知地区");
		String[] aa = area.split("-");
		for (String areaItme : aa) {
		    addItem(areaItme, areaItme);
		}
	    }
	};
	
	layout.add(3, ipArea);
	final ButtonToolItem query = new ButtonToolItem("查询");
	query.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(final ClickEvent event) {
		BaseFilterPagingLoadConfig loadConfig = getGrid()
			.getLoadConfig();

		loadConfig.set("mediaId", media.getValue());

		loadConfig.set("ip", ip.getValue());

		loadConfig.set("ipArea", ipArea.getValue());

		getGrid().load();
	    }
	});

	final ButtonToolItem addBtn = new ButtonToolItem("录入");
	addBtn.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(final ClickEvent event) {
		// 调用添加的Dialog
		new ADDNetbarIpDialog(presenter, IpMarView.this).center();
	    }
	});
	final ButtonToolItem addsBtn = new ButtonToolItem("批量导入");
	addsBtn.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(final ClickEvent event) {
		presenter.chenkbatchAddNetbarIpAuthority(new AsyncCallbackEx<Void>() {
		    
		    @Override
		    public void onSuccess(Void result) {
			new batchAdDialog().center();
		    }
		});
	    }
	});
	final ButtonToolItem delBtn = new ButtonToolItem("删除");
	delBtn.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(final ClickEvent event) {
		if (!getGrid().getSelected().isEmpty()) {
		    MessageBox.confirm("您确定需要删除所选的用户吗？",
			    new MessageBoxHandler() {

				@Override
				public void onClose(MessageBoxEvent event) {
				    XButton btn = event.getButton();
				    if (btn.getItemId().equals(XDialogBox.YES)) {
					StringBuilder sb = new StringBuilder();
					for (BaseModelData data : getGrid()
						.getSelected()) {
					    Double id = data.get("id");
					    sb.append(id.intValue())
						    .append(",");
					}

					sb.deleteCharAt(sb.length() - 1);
					presenter.delNetbarIps(sb.toString(),
						new AsyncCallbackEx<Integer>() {

						    @Override
						    public void onSuccess(
							    Integer result) {
							if (result == getGrid()
								.getSelected()
								.size()) {
							    getGrid().load();
							} else {
							    MessageBox
								    .alert("删除出现错误");
							}
						    }
						});
				    }
				}
			    });
		} else {
		    MessageBox.alert("请选择需要删除的用户！");
		}
	    }
	});

	final Toolbar topToolbar = new Toolbar();
	topToolbar.addItem(formPanel);
	topToolbar.addItem(query);
	topToolbar.addItem(addBtn);
	topToolbar.addItem(addsBtn);
	topToolbar.addItem(delBtn);
	getPanel().setTopToolBar(topToolbar);
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

    

    // 添加弹出框的元素
    private XUploadFormPanel addPanel = null; // 玩家列表
    private FormLayout addlayout = null;
    private FileUploadField batchField = null;

    // 录入增加的弹出框
    // 内部类 弹出的对话框
    class batchAdDialog extends XDialogBox {

	public batchAdDialog() {

	    addPanel = new XUploadFormPanel("", "batchUploadNetbarIpProcessor"){
	    	@Override
	    	public void onUploadCompleted(String results) {
	    		MessageBox.alert(results);
	    		super.onUploadCompleted(results);
	    	}
	    };
	    addlayout = new FormLayout();
	    addPanel.setLayout(addlayout);

	    batchField = new FileUploadField("导入文本", "batch");
	    addlayout.add(batchField);

	    addPanel.setWidth("600px");
	    initBox("批量导入IP", addPanel);
	    initBox("批量导入IP", new HTML(getSampleCSVDesc()));
	    setButtons(XDialogBox.OKCANCEL);
	}

	/**
	 * 重写XDialog的 按钮方法
	 * 
	 * 
	 */
	@Override
	protected void onButtonPressed(final Button button) {
	    if (button == getButtonByItemId(OK)) {
		if (batchField.getValue().length() == 0) {
		    MessageBox.alert("请选择你要上传的CSV文件!");
		} else if (!batchField.getValue().endsWith(".csv")) {
		    MessageBox.alert("文件格式错误,请选CSV文件!");
		} else {
		    MessageBox.confirm("确认要导入吗?", new MessageBoxHandler() {
			@Override
			public void onClose(final MessageBoxEvent event) {
			    final XButton button = event.getButton();
			    if (button.getItemId().equals(XDialogBox.YES)) {
				addPanel.submit();
				addPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {

				    @Override
				    public void onSubmitComplete(
					    final SubmitCompleteEvent event) {
					getGrid().load();
					hide();
				    }
				});
			    }
			}
		    });
		}

		// hiddenField_productId.setValue(productId);
	    } else if (button == getButtonByItemId(CANCEL)) {
		hide();
	    }
	    super.onButtonPressed(button);
	}

    }

    /**
     * 取得CSV格式文件样例
     * 
     * @return
     */
    private String getSampleCSVDesc() {
	final StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("<hr/>");
	stringBuilder.append("<bgcolor=red>CSV约定的格式如下(仅限数据,不能带表头,表头仅供参考)：");
	stringBuilder.append("<table border=\"0\"><tbody>");
	stringBuilder.append("<tr>");
	stringBuilder.append("<th bgcolor=\"#CFCFCF\"> 网吧媒体名 </th>");
	stringBuilder.append("<th bgcolor=\"#CFCFCF\"> IP</th>");
	stringBuilder.append("</tr>");
	stringBuilder.append("<tr>");
	stringBuilder.append("<td> 网吧媒体 </td>");
	stringBuilder.append("<td> 192.10.0.1</td>");
	stringBuilder.append("</tr>");
	stringBuilder.append("</tbody></table>");
	stringBuilder.append("<a href = \"" + GWT.getHostPageBaseURL() + "template/adbatch-sample-netbar-ip.csv" + "\">模版下载</a>");

	return stringBuilder.toString();
    }
}
