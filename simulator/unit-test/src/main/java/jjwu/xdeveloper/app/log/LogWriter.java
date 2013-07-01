/****************************************************************
 * 文件名 : LogWriter.java
 * 日期 : 2013-5-15
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app.log;

import javax.annotation.PostConstruct;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-5-15 下午4:15:56
 * @版本: v1.0.0
 */
class LogWriter {
	private static LogWriter logWriter;

	@PostConstruct
	public void init() {
		logWriter = this;
	}

	public static void writeSuccessfullyLog(final String action, final String appUnitName, final String appName,
			final String description) {
		final Log log = buildLog(action, appUnitName, appName, description);
		log.setOpStatus("success");
		write(log);
	}

	public static void writeFailedLog(final String action, final String appUnitName, final String appName,
			final String description) {
		final Log log = buildLog(action, appUnitName, appName, description);
		log.setOpStatus("failed");
		write(log);
	}

	public static void writeFailedLog(final String action, final String appUnitName, final String appName,
			final Throwable throwable) {
		final Log log = buildLog(action, appUnitName, appName, throwable.getMessage());
		log.setOpStatus("failed");
		write(log);
	}

	private static Log buildLog(final String action, final String appUnitName, final String appName,
			final String description) {
		final Log log = new Log();
		log.setUsername("jjwu");
		log.setOpIp("127.0.0.1");
		log.setOption(action);
		log.setAppName(appName);
		log.setAuName(appUnitName);
		log.setDescription(description);
		return log;
	}

	private static void write(final Log log) {
		// 写日志
		System.out.println("存储日志:" + log.toString());
	}
}
