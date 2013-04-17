/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： UsernameField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client.ui.widget;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.iwgame.gm.p1.security.common.shared.util.RegexGwtHelper;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FieldPlugin;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.xmvp.client.utils.AppUtils;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-23 下午02:25:46
 */
public class UsernameField extends RadioButtonGroup{
	private String vString;
	
	public class XPanel extends Composite implements HasRadioSelectHandlers{
		private TextBox box;
		private FileUpload upload;
		public XPanel(String boxName,String uploadName) {
			super();
			FlowPanel panel=new FlowPanel();
			panel.add(box=new TextBox());
			box.setName(boxName);
			panel.add(upload=new FileUpload());
			upload.setVisible(false);
			upload.setName(uploadName);
			initWidget(panel);
			
			addRadioSelectHandler(new RadioSelectHandler() {
				@Override
				public void onSelect(RadioSelectEvent event) {
					if("one".equals(event.getSelected())){
						box.setVisible(true);
						upload.setVisible(false);
					}else if("more".equals(event.getSelected())){
						box.setVisible(false);
						upload.setVisible(true);
					}
				}
			});
		}
		@Override
		public HandlerRegistration addRadioSelectHandler(
				RadioSelectHandler handler) {
			return AppUtils.EVENT_BUS.addHandler(RadioSelectEvent.TYPE, handler);
		}
		public TextBox getBox() {
			return box;
		}
		public FileUpload getUpload() {
			return upload;
		}
	}
	
	public class Plugin implements FieldPlugin<XPanel>{
		
		private XPanel panel;
		
		private String boxName,uploadName;
		
		public Plugin(String boxName, String uploadName) {
			super();
			this.boxName = boxName;
			this.uploadName = uploadName;
		}

		@Override
		public XPanel getWidget(Field<?, ?> field) {
			panel=new XPanel(boxName, uploadName);
			return panel;
		}

		public XPanel getPanel() {
			return panel;
		}
	}

	public UsernameField(String label, String groupName,String boxName,String uploadName) {
		super(label, groupName,Direction.Horizontal);		
		initPanel();
		setEnablePlugin(true);
		setPlugin(new Plugin(boxName,uploadName));
		
		addValidator(new Validator() {
			
			@Override
			public String validate(Field<?, ?> field) {
				TextBox box=((Plugin)getPlugin()).getPanel().getBox();
				if(!box.isVisible()){
					return null;
				}else {
					vString=box.getValue();
					if("username".equals(box.getName())){
						if(!vString.matches(RegexGwtHelper.STR_ENG_NUM))return "只能输入数字,字母";
					}else if("dbid".equals(box.getName())){
						if(!vString.matches(RegexGwtHelper.STR_NUM_))return "只能输入数字,-";
					}
					if("".equals(vString))return "不能为空";else
					return null;
				}
			}
		});
		
		addValidator(new Validator() {
			
			@Override
			public String validate(Field<?, ?> field) {
				FileUpload box=((Plugin)getPlugin()).getPanel().getUpload();
				if(!box.isVisible()){
					return null;
				}else {
					String vString=box.getFilename();
					int i = vString.lastIndexOf(".");
					if(!"txt".equals(vString.substring(i+1)))return "限定文件格式txt";
					if("".equals(vString))return "不能为空";else
					return null;
				}
			}
		});
		
	}

	private void initPanel(){
		this.addRadioButton("单个", "one", true);
		this.addRadioButton("批量", "more", false);
		
		this.setRadioSelectedAction(new RadioSelectedAction() {
			@Override
			public void execute(RadioButton rb) {
				AppUtils.EVENT_BUS.fireEvent(new RadioSelectEvent(rb.getFormValue()));
			}
		});
	}

	public String getVString() {
		return vString;
	}
	
}
