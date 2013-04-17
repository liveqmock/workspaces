/****************************************************************
 *  系统名称  ： 'P1项目-[gm-p1-analysis]'
 *  文件名    ： TestDemoServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.analysis.modules.report.shared.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.analysis.shared.model.Zone;
import com.iwgame.xportal.common.shared.model.Product;

/**
 * 类说明
 * @简述： 报表服务异步类
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-3-6 上午09:58:09
 */
public interface ReportServiceAsync {
	public void getZone(Product product,AsyncCallback<List<Zone>> callback);
}
