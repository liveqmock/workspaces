/**      
 * NetbarIpDataBase.java Create on 2012-11-8 下午2:35:26    
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: 网吧对应IP
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-8 下午2:35:26
 * @Version 1.0
 * 
 */
public class NetbarIpDataBase implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2819016480162030692L;
    
    private int id;
    private String mediaId;
    private String mediaName;
    private String ip;
    private String ipArea;
    private Date addTime;
    private Date lastUpdateTime;
    
    
    private boolean full = false; // 完整性标志
    
    
    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getMediaId() {
	return mediaId;
    }

    public void setMediaId(String mediaId) {
	this.mediaId = mediaId;
    }

    public String getIp() {
	return ip;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    public String getIpArea() {
	return ipArea;
    }

    public void setIpArea(String ipArea) {
	this.ipArea = ipArea;
    }

    public Date getAddTime() {
	return addTime;
    }

    public void setAddTime(Date addTime) {
	this.addTime = addTime;
    }

    public Date getLastUpdateTime() {
	return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
    }
}
