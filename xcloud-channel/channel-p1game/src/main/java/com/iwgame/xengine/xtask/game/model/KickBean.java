package com.iwgame.xengine.xtask.game.model;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * 类说明
 * 
 * @简述： 踢人Bean
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-5-3 下午06:16:13
 */
public class KickBean implements IwAnnotation{
	
	
	private static final long serialVersionUID = -1743099891132048541L;
	
	private final Logger logger = Logger.getLogger(KickBean.class);
	
	@NotEmpty
	private String guid;	 	 	//大区ID,
	@NotEmpty
	private String username;	 	//角色名称
	
	private String appname;			//来源应用名称
	private String source;		 	//Json字符串
	
	public KickBean() {
		super();
	}
	
	public KickBean(String source) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(source);
			this.guid = jsonObject.getString("guid");
			this.username = jsonObject.getString("username");
			this.appname = String.valueOf(jsonObject.get("appname"));
			this.source = source;
		}catch(Exception e){
			logger.error("[忽略]踢人出错,参数异常!",e);
		}
		
	}
	/**
	 * 大区ID
	 * @return
	 */
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * 帐号名称
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 来源应用名称
	 * @return
	 */
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}

	/**
	 * 数据源
	 * @return
	 */
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
