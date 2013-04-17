/**      
 * PolicyMeta.java Create on 2012-7-12     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.model;

/**
 * @ClassName: PolicyMeta
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-12 上午09:10:37
 * @Version 1.0
 * 
 */
public class PolicyMeta {

	private int id;
	private String modifier;
	private int type;
	private String mac;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(final String modifier) {
		this.modifier = modifier;
	}

	public int getType() {
		return type;
	}

	public void setType(final int type) {
		this.type = type;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(final String mac) {
		this.mac = mac;
	}

}
