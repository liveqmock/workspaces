/**      
 * Messages.java Create on 2012-4-19     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.client.i18n;

/**
 * @ClassName: Messages
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-4-19 下午2:56:15
 * @Version 1.0
 * 
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {

	String channelTitle(String channelName, String date, int num);

	String groupTitle(String date, int num);

	String today();

	String titleDateFormat();

	String channelTimeDialogTitle(String channelName, String date);

	String groupTimeDialogTitle(String groupName, String date);

	String timeDialogErrorField(String v);

}
