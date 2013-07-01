/****************************************************************
 *  文件名   	:	s.java
 *  日期		:  	2013-5-15
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app.log;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-5-15 下午4:15:26
 * @版本:   	v1.0.0
 */
public class Logger
{
	private String appUnitName;
	private String appName;

	/**
	 * 
	 * @param appUnitName  AU下的单元名称
	 * @param appName 应用名称
	 * @return
	 */
	public static Logger getLogger(final String appUnitName, final String appName)
	{
		final Logger logger = new Logger();
		logger.appUnitName = appUnitName;
		logger.appName = appName;
		return logger;
	}

	public void writeSuccessfullyLog(final String action, final String description)
	{
		LogWriter.writeSuccessfullyLog(action, this.appUnitName, this.appName, description);
	}

	public void writeFailedLog(final String action, final String description)
	{
		LogWriter.writeFailedLog(action, this.appUnitName, this.appName, description);
	}

	public void writeFailedLog(final String action, final Throwable throwable)
	{
		LogWriter.writeFailedLog(action, this.appUnitName, this.appName, throwable);
	}
}