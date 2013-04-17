/****************************************************************
 *  文件名     ： Version.java
 *  日期         :  2012-9-5
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


/**
 * 
 * @类名:   AppVersion 
 * @描述:  	版本信息
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24下午03:11:54
 * @版本:   1.0
 */
@Component
public final class AppVersion implements InitializingBean {
	
	private final Logger logger = Logger.getLogger(AppVersion.class);
	
	private final String version = "1.0.0";
	
	private final String lastModifyTime = "2012.12.10 09:10";
	
	public final void print() {
		logger.info("===============================");
		logger.info("应用名称：xHttpService");
		logger.info("版本号: " + version);
		logger.info("功能描述: ");
		logger.info("\t1.邮件服务");
		logger.info("\t2.短信服务");
		logger.info("\t3.道具卡激活");
		logger.info("\t4.帐号封杀&冻结");
		logger.info("\t5.帐号解封&解冻");
		logger.info("\t6.踢人操作");
		logger.info("\t7.水晶发放");
		logger.info("\t8.玩家帐号信息&角色查询");
		logger.info("最后更新时间:"+lastModifyTime);
		logger.info("xHttpService服务 - 启动完成...");
		logger.info("===============================");
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		print();
	}
}
