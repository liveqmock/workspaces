/****************************************************************
 * 文件名 ： XTaskMonitor.java
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

import com.iwgame.xengine.xtask.monitor.model.Mqtotal;
import com.iwgame.xengine.xtask.monitor.util.Constant;
import com.iwgame.xengine.xtask.monitor.util.LoadXml;
import com.iwgame.xtask.support.tools.XtaskTools;

/**
 * 
 * 类说明
 * @简述： 监听所有通道的总的条数大于阀值将通知
 * @作者： 吴君杰
 * @版本： 1.0.0
 * @时间： 2013-4-10 下午3:05:19
 * @邮箱： wujunjie@iwgame.com
 */
@Component
public class XTaskTotalMonitor {
   
   @Resource
   private XtaskTools xtaskTools;
   
   private final Logger monitorinfo = Logger.getLogger(XTaskTotalMonitor.class);
   
   public void work() {
      HttpEntity entity = null;
      
      List<Mqtotal> mqtotals = LoadXml.getMonitor().getMqtotals();
      
      for (Mqtotal mqtotal : mqtotals) {
         StringBuilder builder = new StringBuilder();
         builder.append(Constant.HTTP);
         builder.append(mqtotal.getHost());
         builder.append(Constant.COLON);
         builder.append(mqtotal.getPort());
         builder.append(Constant.TOTALPATH);
         int totalCount = 0;
         monitorinfo.info("开始执行检查通道:" + builder.toString());
         DefaultHttpClient httpclient = null;
         try {
            httpclient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(builder.toString());
            httpclient.getCredentialsProvider().setCredentials(new AuthScope(mqtotal.getHost(), 55672),
                  new UsernamePasswordCredentials(LoadXml.getMonitor().getUsername(), LoadXml.getMonitor().getPassword()));
            HttpResponse response = httpclient.execute(httpGet);
            entity = response.getEntity();
            if (entity != null) {
               InputStreamReader isr = new InputStreamReader(entity.getContent());
               BufferedReader reader = new BufferedReader(isr);
               String str = "";
               if (null != (str = reader.readLine())) {
                  JSONObject json = JSONObject.fromObject(str);
                  totalCount = JSONObject.fromObject(json.get("queue_totals")).getInt("messages");
                  monitorinfo.info("[" + builder.toString() + "]获取mq信息成功,当前mq消息总条数为:" + totalCount);
               } else {
                  monitorinfo.error(response.getStatusLine());
               }
            } else {
               monitorinfo.error(response.getStatusLine());
            }
         } catch (ClientProtocolException e) {
            monitorinfo.error("[Error](" + builder.toString() + "):Http方式获取mq信息出错!", e);
         } catch (IOException e) {
            monitorinfo.error("[Error](" + builder.toString() + "):Http方式获取mq信息出错!", e);
         } finally {
            if (null != entity) {
               try {
                  // 释放
                  EntityUtils.consume(entity);
               } catch (IOException e) {
                  
               }
            }
            if (null != httpclient) {
               httpclient.getConnectionManager().shutdown();
               httpclient = null;
            }
         }
         
         if (totalCount > mqtotal.getMaxwarn()) {
            String bizKey = LoadXml.createBizKey("MQ", mqtotal.getHost());
            xtaskTools.intervalNotice(bizKey, mqtotal.getPhones(), "MQ监控：IP(" + mqtotal.getHost() + "), 当前队列消息总数量(" + totalCount + "),大于设定的阀值(" + mqtotal.getMaxwarn()
                  + "),请检查队列情况...");
            monitorinfo.error("MQ监控：IP(" + mqtotal.getHost() + "), 当前队列消息总数量(" + totalCount + "),大于指定的阀值(" + mqtotal.getMaxwarn() + "),请检查队列情况...");
         } else {
            monitorinfo.info("MQ总监控：IP(" + mqtotal.getHost() + ")当前队列消息总数量:" + totalCount + "，指定最大值：" + mqtotal.getMaxwarn() + ",未超过最大值,队列正常...");
         }
         
      }
      
   }
}
