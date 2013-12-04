/****************************************************************
 * 系统名称 ： '消息任务系统-公共服务-业务通道'
 * 文件名 ： Version.java
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * **************************************************************
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.monitor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

import com.iwgame.xengine.xtask.monitor.model.Monitor;
import com.iwgame.xengine.xtask.monitor.model.Mport;
import com.iwgame.xengine.xtask.monitor.model.Mqqueue;
import com.iwgame.xengine.xtask.monitor.model.Mqtotal;
import com.iwgame.xengine.xtask.monitor.model.Queue;
import com.thoughtworks.xstream.XStream;

/**
 * @简述： 加载自定义配置文件
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间： 2012-12-27 下午02:47:27
 */
public class LoadXml {
    
    private static Log logger = LogFactory.getLog(LoadXml.class);
    
    private Resource[] locations;
    
    private String fileEncoding = "UTF-8";
    
    private boolean ignoreResourceNotFound;
    
    private static Monitor monitor = null;
    
    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
    }
    
    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }
    
    public Resource[] getLocations() {
        return locations;
    }
    
    public void setLocations(Resource[] locations) {
        this.locations = locations;
    }
    
    protected void init() throws IOException {
        
        if (this.locations != null) {
            
            for (Resource location : this.locations) {
                
                InputStream is = null;
                
                try {
                    is = location.getInputStream();
                    
                    if (this.fileEncoding != null) {
                        StringBuilder xmlBuilder = new StringBuilder();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, this.fileEncoding));
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            xmlBuilder.append(line);
                        }
                        String xml = xmlBuilder.toString();
                        XStream xstream = new XStream();
                        xstream.alias("queue", Queue.class);
                        xstream.alias("mqtotal", Mqtotal.class);
                        xstream.alias("mqqueue", Mqqueue.class);
                        xstream.alias("mport", Mport.class);
                        monitor = (Monitor) xstream.fromXML(xml);
                        logger.info("Loading xml file from " + location);
                    }
                } catch (IOException ex) {
                    if (this.ignoreResourceNotFound) {
                        if (logger.isWarnEnabled()) {
                            logger.warn("Could not load properties from " + location + ": " + ex.getMessage());
                        }
                    } else {
                        throw ex;
                    }
                } finally {
                    if (is != null) {
                        is.close();
                    }
                    
                    if (monitor == null) {
                        throw new RuntimeException("读取xml配置文件失败...");
                    }
                }
            }
        }
    }
    
    
    public static Monitor getMonitor(){
        return monitor;
     }
    
    /**
     * @param string
     * @param host
     * @param virtualHost
     * @param name
     * @return
     */
    public static String createBizKey(String... params) {
        StringBuilder keys = new StringBuilder();
        for (String param : params) {
            keys.append(param);
        }
        return keys.toString();
    }
}
