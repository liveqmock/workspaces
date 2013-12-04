/**      
 * NetbarClientDataBase.java Create on 2012-11-8 下午2:53:39    
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: 网吧对应客户端
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-8 下午2:53:39
 * @Version 1.0
 * 
 */
public class NetbarClientDataBase implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3506565461369215279L;

    private int id;
    private String mediaId;
    private String version;
    private String code;
    private Date addTime;
    private Date lastUpdateTime;
    
    private String mediaName;

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

    public String getVersion() {
	return version;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
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
