/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SafeModeCauseComboBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client.ui.combobox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;
import com.iwgame.gm.p1.security.modules.operate.shared.rpc.SecuritySafeModeService;
import com.iwgame.ui.panel.client.form.field.ComboBoxField;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 下午02:24:38
 */
@SuppressWarnings("rawtypes")
public class SafeModeCauseComboBox extends ComboBoxField{
	public SafeModeCauseComboBox(String label, String fieldname) {
		super(label, fieldname);
		this.initBox();
	}

	public void initBox(){
		Map<String, Object> map = new HashMap<String,Object>();
		SecuritySafeModeService.Util.get().getSafeModeCauseConfigs(ApplicationContext.getCurrentProductId(),map, new AsyncCallback<List<SafeModeCauseConfig>>() {
			
			@Override
			public void onSuccess(List<SafeModeCauseConfig> result) {
				for(SafeModeCauseConfig sm:result){
					addItem(sm.getCauseNote(), sm.getCauseNote());
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}	
}
