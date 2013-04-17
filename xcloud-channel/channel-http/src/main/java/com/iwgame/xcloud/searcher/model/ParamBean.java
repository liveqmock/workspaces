/**      
* QueryParamBean.java Create on 2012-3-21     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.xcloud.searcher.model;


import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * @类名:   ParamBean 
 * @描述:  	公共参数,javaBean
 * 
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24上午11:00:04
 * @版本:   1.0
 */
public class ParamBean implements IwAnnotation {

	private static final long serialVersionUID = -6477982292865008041L;
	
	@NotEmpty
	private Long ts;          
	@NotEmpty
	private String sign;	  
	@NotEmpty
	private String appname;   
	
	/**
	 * 
	 * @说明: 时间戳  , 必填 , 不能为空
	 * @return: Long
	 */
	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}
	
	/**
	 * 
	 * @说明: 加密签名 , 必填 , 不能为空
	 * @return: String
	 */
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @说明: 应用名称 , 必填 , 不能为空
	 * @return: String
	 */
	public String getAppname() {
		return appname;
	}

	/**
	 * @param appname the appname to set
	 */
	public void setAppname(String appname) {
		this.appname = appname;
	}
}
