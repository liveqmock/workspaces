/**      
 * RegistGuild.java Create on 2012-6-25     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.model;

import java.util.Date;

/**
 * @ClassName: RegistGuild
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-25 下午04:02:34
 * @Version 1.0
 * 
 */
public class RegistGuild {

	private int guildId;
	private String gameId;
	private String username;
	private String password;
	private String email;
	private String name;
	private String intro;
	private String ip;
	private long regTime;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(final String gameId) {
		this.gameId = gameId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(final String intro) {
		this.intro = intro;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public long getRegTime() {
		return regTime;
	}

	public void setRegTime(final long regTime) {
		this.regTime = regTime;
	}

	public Date getRegDateTime() {
		return new Date(regTime);
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(final int guildId) {
		this.guildId = guildId;
	}

}
