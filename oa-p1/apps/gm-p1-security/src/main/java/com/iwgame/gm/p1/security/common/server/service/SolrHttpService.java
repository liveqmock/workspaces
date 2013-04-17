/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SolrHttpService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.service;

import com.iwgame.gm.p1.security.common.shared.bean.AccountBean;
import com.iwgame.gm.p1.security.common.shared.bean.AccountParam;
import com.iwgame.gm.p1.security.common.shared.bean.RoleBean;
import com.iwgame.gm.p1.security.common.shared.bean.RoleDocs;
import com.iwgame.gm.p1.security.common.shared.bean.RoleParam;

/**
 * 类说明
 * @简述： HTTP调用接口
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 下午03:52:30
 */
public interface SolrHttpService {
	
	public RoleBean getRolesByParam(RoleParam role);
	
	public RoleBean getRolesByDbid(RoleParam role);
	
	public RoleDocs getRoleName(RoleParam role);
	
	public AccountBean getAccountListByUsername(AccountParam options);
	
	public AccountBean getAccountListByRegisIp(AccountParam options);
	
	public AccountBean getAccountListByRegisInfo(AccountParam options);
	
	public String getAccountNameByAccountId(String productId,String accountId);
}
