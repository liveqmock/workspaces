/**      
 * Styles.java Create on 2012-10-12     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.modules.baidu.account.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

/**
 * @ClassName: Styles
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-10-12 上午09:13:24
 * @Version 1.0
 * 
 */
public class Styles {
	public static interface Resources extends ClientBundle {

		@ImageOptions(flipRtl = true, repeatStyle = RepeatStyle.None)
		ImageResource tableEditEnabled();

		@ImageOptions(flipRtl = true, repeatStyle = RepeatStyle.None)
		ImageResource tableEditDisabled();

		@NotStrict
		@Source("actions.css")
		Style style();
	}

	public static interface Style extends CssResource {

		String edit();
	}

	private static Resources resources;

	static {
		resources = GWT.create(Resources.class);
		resources.style().ensureInjected();
	}

	public static Style style() {
		return resources.style();
	}

	public static Resources resources() {
		return resources;
	}
}
