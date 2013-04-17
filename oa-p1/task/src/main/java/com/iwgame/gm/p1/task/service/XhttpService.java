/****************************************************************
 *  文件名     ： XhttpService.java
 *  日期         :  2012-11-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.service;

import com.iwgame.gm.p1.task.bean.SafeModelBean;
import com.iwgame.gm.p1.task.bean.SecurityAccount;
import com.iwgame.gm.p1.task.bean.SecurityMail;
import com.sun.jersey.api.client.UniformInterfaceException;

/** 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-19上午09:45:56
 * @版本:   v1.0 
 */
public interface XhttpService {
	
	public int sendMailByHttpService(SecurityMail param);
	
	public int sendLockAccountByHttpService(SecurityAccount param) throws UniformInterfaceException;
	
	public int sendUnLockAccountByHttpService(SecurityAccount param) throws UniformInterfaceException;
	
	public int sendUnSafeModelByHttpService(SafeModelBean param) throws UniformInterfaceException;
	
	public int sendAddSafeModelByHttpService(SafeModelBean param) throws UniformInterfaceException;
	
}
