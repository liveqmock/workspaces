package com.iwgame.xcloud.searcher.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @类名:   SearchResult 
 * @描述:  	帐号查询服务返回值,javaBean
 * 
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24上午10:59:56
 * @版本:   1.0
 */
@XmlRootElement(name = "result")
public class SearchResult implements Serializable {

	private static final long serialVersionUID = 5428982577659073396L;

	private int rc;

	private Object result;

	/**
	 * 
	 * @说明: 状态码
	 * @return: int
	 */
	public int getRc() {
		return rc;
	}

	public void setRc(int rc) {
		this.rc = rc;
	}

	/**
	 * 
	 * @说明: 返回值
	 * @return: List<Map<String,Object>>
	 */
	public Object getResult() {
		return  result;
	}

	public void setResult(List<Map<String, Object>> result) {
		if(result == null){
			this.result = new ArrayList<Map<String, Object>>();
		}else{
			this.result = result;
		}
	}
	
	public void setResult(Map<String, Object> result) {
		if(result == null){
			this.result = new HashMap<String, Object>();
		}else{
			this.result = result;
		}
	}
}
