/****************************************************************
 *  文件名     ： AccountDataStoreDao.java
 *  日期         :  2012-11-20
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.security.dao.sm;

/** 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-20上午10:55:06
 * @版本:   v1.0 
 */
public interface SmAccountDataStoreDao {
	
	public int addAccountByUserNameToUnLook(String username);
	
	
	public int addAccountByUserNameToSafeModel(String username);

}
