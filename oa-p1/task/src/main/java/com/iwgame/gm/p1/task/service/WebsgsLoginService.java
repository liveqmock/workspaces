/****************************************************************
 *  文件名     ： WebsgsLoginService.java
 *  日期         :  2012-11-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.service;

/** 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-19下午05:49:13
 * @版本:   v1.0 
 */
public interface WebsgsLoginService {
	
	public int disableLogin(String username,String pid);
	
	public int enableLogin(String username,String pid);

}
