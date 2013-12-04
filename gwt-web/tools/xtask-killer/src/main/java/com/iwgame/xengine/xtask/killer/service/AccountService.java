/**      
 * AccountService.java Create on 2012-7-13     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.service;

/**
 * @ClassName: AccountService
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-13 上午11:52:22
 * @Version 1.0
 * 
 */
public interface AccountService {

	/**
	 * 查询帐号的累计充值金额
	 */
	public Integer getTotalPaid(String accountname);

	/**
	 * 查询帐号的最大等级
	 */
	public Integer getMaxLevel(String accountname);
}
