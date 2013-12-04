/****************************************************************
 *  系统名称  ：  '消息任务系统-公共服务-业务通道'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xtask.support.version;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


/**
 * 类说明
 * @简述： 版本信息描述类 
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-4-8 下午04:47:06
 */
@Component
public class Version implements InitializingBean{
	
	private static Logger logger = Logger.getLogger(Version.class);
	
	public static void print() {
		logger.info(" jar:[xtask-support] initialization Loaded Success!...");
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		print();
	}
}
