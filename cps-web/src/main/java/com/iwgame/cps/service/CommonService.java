/****************************************************************
 *  文件名   	:	CommonService.java
 *  日期		:  	2013-6-29
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.iwgame.cps.security.User;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-6-29 下午3:56:19
 * @版本:   	v1.0.0
 */
@Service
public class CommonService {

	/**
	 * @param username
	 * @param passwd
	 * @param captcha
	 * @param endDate
	 * @param cn
	 * @return
	 */
	public User checkUser(String username, String passwd, String captcha, Date endDate) {
		return null;
	}

}
