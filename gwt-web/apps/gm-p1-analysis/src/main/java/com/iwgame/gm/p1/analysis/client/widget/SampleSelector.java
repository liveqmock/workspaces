/**      
 * SampleSelector.java Create on 2012-3-27     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.analysis.client.widget;

import com.iwgame.ui.client.util.ReportSelector;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;

/**
 * @ClassName: SampleSelector
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-3-27 上午11:26:13
 * @Version 1.0
 * 
 */
@ReportSelector("sampleSelector")
public class SampleSelector extends SimpleSelector {

	/**
	 * @param labelName
	 */
	public SampleSelector(final String labelName) {
		super(labelName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.ui.panel.client.form.field.SimpleSelector#initItems()
	 */
	@Override
	public void initItems() {
		addItem("XXXX", "1");
		addItem("YYYY", "2");
	}

}
