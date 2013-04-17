/****************************************************************
 * 文件名 ：XTaskQueuesMonitor.java
 * 日期 : 2012-9-17
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.monitor.worker.mq.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iwgame.xengine.xtask.monitor.model.Mqqueue;
import com.iwgame.xengine.xtask.monitor.model.Queue;
import com.iwgame.xengine.xtask.monitor.util.Constant;
import com.iwgame.xengine.xtask.monitor.util.LoadXml;
import com.iwgame.xtask.support.tools.XtaskTools;

/**
 * 类说明
 * 
 * @简述： 监听蜀门醉逍遥backup通道的错误数据
 * @作者： 吴君杰
 * @版本： 1.0.0
 * @时间： 2013-4-10 上午12:36:48
 * @邮箱： wujunjie@iwgame.com
 */
@Component
public class XTaskQueuesMonitor {
   
   @Resource
   private XtaskTools xtaskTools;
   
   private final Logger monitorinfo = Logger.getLogger(XTaskQueuesMonitor.class);
   
   public void work() {
      
      String username = LoadXml.getMonitor().getUsername();
      String password = LoadXml.getMonitor().getPassword();
      
      List<Mqqueue> mqqueues = LoadXml.getMonitor().getMqqueues();
      HttpEntity entity = null;
      for (Mqqueue mqqueue : mqqueues) {
         String virtualHost = mqqueue.getVirtualhost();
         String host = mqqueue.getHost();
         String phones = mqqueue.getPhones();
         List<Queue> queues = mqqueue.getQueues();
         for (Queue queue : queues) {
            StringBuilder builder = new StringBuilder();
            builder.append(Constant.HTTP);
            builder.append(host);
            builder.append(Constant.COLON);
            builder.append(Constant.PORT);
            builder.append(Constant.QUEUEPATH);
            builder.append(Constant.OBLIQUE);
            builder.append(virtualHost);
            builder.append(Constant.OBLIQUE);
            builder.append(queue.getName());
            monitorinfo.info("开始执行检查QUQUE:" + builder.toString());
            DefaultHttpClient httpclient = null;
            try {
               httpclient = new DefaultHttpClient();
               httpclient.getCredentialsProvider().setCredentials(new AuthScope(host, 55672), new UsernamePasswordCredentials(username, password));
               HttpGet httpGet = new HttpGet(builder.toString());
               HttpResponse response = httpclient.execute(httpGet);
               entity = response.getEntity();
               if (entity != null) {
                  InputStreamReader isr = new InputStreamReader(entity.getContent());
                  BufferedReader reader = new BufferedReader(isr);
                  String str = "";
                  if (null != (str = reader.readLine())) {
                     JSONObject object = JSONObject.fromObject(str);
                     int messages = object.getInt("messages");
                     if (messages > queue.getMaxwarn()) {
                        String message = "Queue监控：IP(" + host + "), VirtualHost(" + virtualHost + "), Queue(" + queue.getName() + "), 当前队列消息数量[" + messages + "],大于指定的阀值["
                              + queue.getMaxwarn() + "],请检查队列情况...";
                        monitorinfo.error(message);
                        String bizKey = LoadXml.createBizKey("QUEUE", host, virtualHost, queue.getName());
                        xtaskTools.intervalNotice(bizKey, phones, message);
                        return;
                     } else {
                        monitorinfo.info("Queue监控：IP(" + host + "), VirtualHost(" + virtualHost + "), Queue(" + queue.getName() + "), 当前队列消息数量[" + messages + "],未超过警戒值["
                              + queue.getMaxwarn() + "]，队列数据正常。");
                     }
                  } else {
                     monitorinfo.error(response.getStatusLine());
                     monitorinfo.error("用户名:" + username + " 密码:" + password);
                     monitorinfo.error("host:" + builder.toString());
                  }
               } else {
                  monitorinfo.error(response.getStatusLine());
               }
            } catch (ClientProtocolException e) {
               monitorinfo.error("IP:[" + host + "] ,VirtualHost: [" + virtualHost + "] ,QUEUE: [" + queue.getName() + "] :Http方式获取mq信息出错!", e);
            } catch (IOException e) {
               monitorinfo.error("IP:[" + host + "] ,VirtualHost: [" + virtualHost + "] ,QUEUE: [" + queue.getName() + "] :Http方式获取mq信息出错!", e);
            } catch (Exception e) {
               monitorinfo.error("IP:[" + host + "] ,VirtualHost: [" + virtualHost + "] ,QUEUE: [" + queue.getName() + "] :其他异常", e);
            } finally {
               if (null != entity) {
                  try {
                     EntityUtils.consume(entity);// 释放
                  } catch (IOException e) {
                     
                  }
               }
               if (null != httpclient) {
                  httpclient.getConnectionManager().shutdown();
                  httpclient = null;
               }
            }
         }
      }
      
   }
}
