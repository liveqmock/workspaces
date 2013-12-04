/**      
 * CachedServer.java Create on 2012-7-2     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName: CachedServer
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-2 上午11:56:56
 * @Version 1.0
 * 
 */
public class CachedServer {

	private int raceId;
	private int serverId;
	private String zone;
	private Date openDate;
	private int status;

	public CachedServer() {
	}

	public CachedServer(final int raceId, final String zone, final Map<String, String> map) {
		this.raceId = raceId;
		this.zone = zone;
		if (map.containsKey("serverId")) {
			serverId = Integer.valueOf(map.get("serverId"));
		}
		if (map.containsKey("opendate")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				openDate = dateFormat.parse(map.get("opendate"));
			} catch (ParseException e) {
				openDate = null;
			}
		}
		if (map.containsKey("status")) {
			status = Integer.valueOf(map.get("status"));
		}
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(final int raceId) {
		this.raceId = raceId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(final int serverId) {
		this.serverId = serverId;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(final String zone) {
		this.zone = zone;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(final Date openDate) {
		this.openDate = openDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

}
