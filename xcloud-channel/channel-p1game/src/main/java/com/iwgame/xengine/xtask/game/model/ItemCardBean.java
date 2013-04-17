package com.iwgame.xengine.xtask.game.model;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * 类说明
 * 
 * @简述： 道具卡Bean
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-5-3 下午06:16:54
 */
public class ItemCardBean implements IwAnnotation{
	
	private static final long serialVersionUID = 267016403524288647L;
	
	private final Logger logger = Logger.getLogger(ItemCardBean.class);
	
	private int type = 3;	  	//   命令类型	3=道具卡、6=密保卡	
	
	@NotEmpty
	private String guid;      	//   大区ID
	@NotEmpty
	private String username;  	//   账号名
	@NotEmpty
	private String cardnum;     //   卡号
	@NotEmpty
	private String cardpwd;     //   卡密码（MD5编码，全部小写）
	private String validtime;   //   道具卡有效期，空字符串表示永不过期  过期"str3":"expired=过期时间戳"
	private int itype = 0 ;          //   类型：0 普通卡号，1 运营商赠送卡号
	private final int i1 = 0;
	private final int i2 = 1;
	private final int i3 = 0;
	private String source;      //	  请求资源
	private String appname;		//	 请求来源应用名称
	
	public ItemCardBean(String source){
		try {
			JSONObject jsonObject = JSONObject.fromObject(source);
			this.guid = jsonObject.get("guid").toString();//有可能是Integer | String
			this.username = jsonObject.getString("username");
			this.cardnum = jsonObject.getString("cardnum");
			this.cardpwd = jsonObject.getString("cardpwd");
			this.validtime = jsonObject.getString("validtime");
			this.itype = jsonObject.getInt("itype");
			this.appname = String.valueOf(jsonObject.get("appname"));
			this.source = source;
		}catch(Exception e){
			logger.error("[忽略]道具卡激活出错,参数异常!",e);
		}
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getCardpwd() {
		return cardpwd;
	}

	public void setCardpwd(String cardpwd) {
		this.cardpwd = cardpwd;
	}

	public String getValidtime() {
		return validtime;
	}

	public void setValidtime(String validtime) {
		this.validtime = validtime;
	}

	public int getItype() {
		return itype;
	}

	public void setItype(int itype) {
		this.itype = itype;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public int getI1() {
		return i1;
	}

	public int getI2() {
		return i2;
	}

	public int getI3() {
		return i3;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}
}
