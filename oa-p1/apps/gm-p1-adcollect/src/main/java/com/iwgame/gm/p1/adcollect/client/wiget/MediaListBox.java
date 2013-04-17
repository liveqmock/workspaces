/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ccc.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.client.wiget;

import java.util.List;

import com.google.gwt.user.client.ui.ListBox;
import com.iwgame.gm.p1.adcollect.modules.keys.shared.rpc.KeysService;
import com.iwgame.gm.p1.adcollect.shared.model.Media;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 媒体列表
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-2-8 下午05:49:03
 */
public class MediaListBox extends ListBox {

	private final String productId = ApplicationContext.getCurrentProductId();

	public MediaListBox() {
		initListBox();
	}

	/**
	 * 初始化ListBox
	 * 
	 * @param headCapation
	 */
	private void initListBox() {
		// 添加行头
		// super.addItem(headCapation, "");
		KeysService.Util.get().getMedia(productId, new AsyncCallbackEx<List<Media>>() {

			@Override
			public void onSuccess(final List<Media> result) {

				if ((result != null) && (result.size() > 0)) {
					for (int i = 0; i < result.size(); i++) {
						final Media media = result.get(i);
						MediaListBox.super.addItem(media.getMediaName(), media.getMediaId());
					}
				}

			}
		});

	}

}
