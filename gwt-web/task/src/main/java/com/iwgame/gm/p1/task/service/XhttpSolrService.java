/****************************************************************
 *  文件名     ： XhttpSolrService.java
 *  日期         :  2012-11-26
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.service;

import com.iwgame.gm.p1.task.bean.SecurityAccount;

/** 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-26下午04:59:00
 * @版本:   v1.0 
 */
public interface XhttpSolrService {
	
	public String getEmailAddressByAccountId(SecurityAccount securityAccount);
	
}
