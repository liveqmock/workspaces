/****************************************************************
 *  文件名     ： Version.java
 *  日期         :  2012-9-5
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.vsersion;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


/** 
 * @ClassName:    Version 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-9-5上午11:11:51
 * @Version:      1.0 
 */
@Component
public final class Version implements InitializingBean {
	
	private final Logger logger = Logger.getLogger(Version.class);
	
	private final String version = "1.0.0";
	
	private final String lastModifyTime = "2012.09.5 09:10";
	
	public final void print() {
		logger.info("===============================");
		logger.info("名称：水晶WebService");
		logger.info("版本号: " + version);
		logger.info("最后修改时间: " + lastModifyTime);
		logger.info("[xservice]-启动完成...");
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
