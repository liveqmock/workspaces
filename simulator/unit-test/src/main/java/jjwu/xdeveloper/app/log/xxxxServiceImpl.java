/****************************************************************
 * 文件名 : AppTest.java
 * 日期 : 2013-5-15
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app.log;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import webservice.AppTest;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-5-15 下午4:28:22
 * @版本: v1.0.0
 */
public class xxxxServiceImpl {

	private  final org.apache.log4j.Logger LOG4J = org.apache.log4j.Logger.getLogger(AppTest.class);

	private  final Logger XPORTAL_LOGGER = Logger.getLogger("菜单名称(页面)",LogApp.SECURITY.getAppName());

	public List<Model> getModelList(String args) {

		final List<Object> list = new ArrayList<Object>();

		try {
			//....
			//业务处理
			//....


			//成功
			LOG4J.info("xxxxxx");
			XPORTAL_LOGGER.writeSuccessfullyLog(LogAction.QUERY.getName(), "成功返回数据:" + list.size());

		} catch (final Exception e) {
			//失败
			LOG4J.error("xxxxxx",e);
			XPORTAL_LOGGER.writeFailedLog(LogAction.QUERY.getName(), "查询异常:" + e.getMessage());
		}
		return null;
	}

}
