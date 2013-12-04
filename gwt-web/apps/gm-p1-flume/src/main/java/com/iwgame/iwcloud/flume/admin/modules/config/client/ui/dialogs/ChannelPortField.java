/**      
 * ChannelPortField.java Create on 2012-6-2     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.config.client.ui.dialogs;

import com.iwgame.ui.panel.client.form.field.Field;

/**
 * @ClassName: ChannelPortField
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-2 下午12:37:45
 * @Version 1.0
 * 
 */
public class ChannelPortField extends Field<Integer, ChannelPortBox> {

	/**
	 * @param label
	 * @param widget
	 */
	public ChannelPortField(final String label) {
		super(label, new ChannelPortBox());
		getWidget().getPortBox().setValue(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.ui.panel.client.form.field.Field#getValue()
	 */
	@Override
	public Integer getValue() {
		return getWidget().getPortBox().getNumberValue().intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.panel.client.form.field.Field#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(final Integer value) {
		getWidget().getPortBox().setValue(value);
	}

}
