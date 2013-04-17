package com.iwgame.xengine.xtask.game.model;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * @ClassName: SecurityCardBean
 * @Description: 密保卡实体类
 * @author: jjwu
 * @email: wujunjie@iwgame.com
 * @date: 2012-7-24 上午11:50:43
 */
public class SecurityCardBean implements IwAnnotation {
	
	/**
	 * @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -5536517596731953276L;

	private final Logger logger = Logger.getLogger(SecurityCardBean.class);
	
	@NotEmpty
	private String guid;
	@NotEmpty
	private int type;
	@NotEmpty
	private String name;
	@NotEmpty
	private String str3;
	@NotEmpty
	private String operate;
	
	private String source;
	
	private String appname;

	public SecurityCardBean(String source){
		try {
			JSONObject jsonObject = JSONObject.fromObject(source);
			this.guid = jsonObject.get("guid") == null ? "" : jsonObject.getString("guid");
			this.type = jsonObject.get("type") == null ? 6 : Integer.valueOf(jsonObject.get("type").toString());
			this.name = jsonObject.get("name") == null ? "" : jsonObject.getString("name");
			this.str3 = jsonObject.get("str3") == null ? "" : jsonObject.getString("str3");
			this.operate = jsonObject.get("operate") == null  ? "" : jsonObject.getString("operate");
			this.appname = String.valueOf(jsonObject.get("appname"));
			this.source = source;
		}catch(Exception e){
			logger.error("[忽略]道具卡绑定和解除出错,参数其他异常!",e);
		}
	}
	
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
}
