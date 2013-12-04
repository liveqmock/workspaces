package com.iwgame.xengine.xtask.monitor.model;

import java.io.Serializable;

/**
 * 类说明
 * 
 * @简述： XXX
 * @作者： 吴君杰
 * @版本： 1.0.0
 * @时间： 2013-4-9 下午 11:55:43
 * @邮箱： wujunjie@iwgame.com
 */
public class Mqtotal implements Serializable {
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 7778851996133736257L;
    
    private String host;
    
    private int maxwarn;
    
    private String phones;
    
    private int port = 55672;
    
    
    
    /**
     * 
     */
    public Mqtotal() {
        System.out.println("object Mqtotal init...");
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getPhones() {
        return phones;
    }
    
    public void setPhones(String phones) {
        this.phones = phones;
    }
    
    public int getMaxwarn() {
        return maxwarn;
    }
    
    public void setMaxwarn(int maxwarn) {
        this.maxwarn = maxwarn;
    }
    
    public int getPort() {
        return port;
    }
    
    public void setPort(int port) {
        if (port != 0) {
            this.port = port;
        }else{
            this.port = 55672;
        }
    }
}
