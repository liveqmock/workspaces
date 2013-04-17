/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： RMBCSPropertiesNotFoundException.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.exception;
/**
 * 类说明
 * @简述： 人民币充值赠送策略未找到异常
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-10 下午11:19:26
 */
public class RMBCSPropertiesNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2111609934813652270L;

	/**
	 * 
	 */
	public RMBCSPropertiesNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public RMBCSPropertiesNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public RMBCSPropertiesNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public RMBCSPropertiesNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
