/****************************************************************
 * 文件名 : XportalAppName.java
 * 日期 : 2013-5-24
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app.log;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-5-24 上午11:43:32
 * @版本: v1.0.0
 */
public enum LogApp {

	ANALYSIS(0, "数据分析"),
	ACTIVITY(1,"活动系统"),
	FLUME(2, "数据采集"),
	SECURITY(3, "安全管理"),
	ADCOLLECT(4, "广告监控"),
	LOGMONITOR(5, "日志监控"),
	FINANCE(6, "财报系统"),
	GOALMANAGE(7,"全局管理"),
	REALMONITOR(8,"实时监控"),
	ITEMCARD(9,"道具卡管理");

	private int index;

	private String appName;

	/**
	 * @param index
	 * @param appName
	 */
	private LogApp(int index, String appName) {
		this.index = index;
		this.appName = appName;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}
}


