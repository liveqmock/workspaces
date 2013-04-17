/****************************************************************
 * 文件名 ：XTaskQueuesMonitor.java
 * 日期 : 2012-9-17
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.monitor.worker.port.monitor;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.net.telnet.TelnetClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iwgame.xengine.xtask.monitor.model.Mport;
import com.iwgame.xengine.xtask.monitor.util.LoadXml;
import com.iwgame.xtask.support.tools.XtaskTools;

/**
 * 
 * 类说明  telnet方式监听端口
 * @简述： XXX
 * @作者： 吴君杰
 * @版本： 1.0.0
 * @时间： 2013-4-10 下午3:32:08
 * @邮箱： wujunjie@iwgame.com
 */
@Component
public class XTaskPortsMonitor {
   
   private final Logger logger = Logger.getLogger(XTaskPortsMonitor.class);
   
   @Resource
   private XtaskTools xtaskTools;
   
   /**
    * 检查端口
    * 
    * @param host
    * @param port
    * @return
    */
   
   public void work() {
      try {
         List<Mport> mports = LoadXml.getMonitor().getMports();
         for (Mport mport : mports) {
            String host = mport.getHost();
            int port = mport.getPort();
            if (checkPort(host, port)) {
               logger.info("端口监控：host(" + host + "), 端口(" + port + ") telnet 连接成功....");
            } else {
               String message = "端口监控：IP(" + host + "), 端口(" + port + ") telnet 连接失败,请检查....";
               logger.error(message);
               String bizKey = LoadXml.createBizKey("PORT", host, String.valueOf(port));
               xtaskTools.intervalNotice(bizKey, mport.getPhones(), message);// 发短信
            }
         }
      } catch (Exception e) {
         logger.error("端口监控任务异常:",e);
      }
   }
   
   
   private boolean checkPort(String host, int port) {
      boolean isAvailable = false;
      TelnetClient telnet = null;
      try {
         telnet = new TelnetClient();
         telnet.setConnectTimeout(10000);
         telnet.connect(host, port);
         isAvailable = telnet.isAvailable();
      } catch (Exception e) {
         isAvailable = false;
         logger.error("[连接失败]host:" + host + " , port:" + port, e);
      } finally {
         try {
            if (null != telnet) {
               telnet.disconnect();
            }
         } catch (IOException e) {
            
         }
         telnet = null;
      }
      return isAvailable;
   }
}
