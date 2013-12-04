/****************************************************************
 *  系统名称  ：  'TODO'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.monitor.model;

import java.io.Serializable;


/**
 * 类说明
 * @简述： XXX
 * @作者： 吴君杰
 * @版本： 1.0.0
 * @时间： 2013-4-10 下午3:09:06
 * @邮箱： wujunjie@iwgame.com
 */
public class Mport implements Serializable {
   
   /**
    * 
    */
   private static final long serialVersionUID = -7960097536304356934L;

   private String host;
   
   private int port;
   
   private String phones;

   
   public String getHost() {
      return host;
   }

   
   public void setHost(String host) {
      this.host = host;
   }

   
   public int getPort() {
      return port;
   }

   
   public void setPort(int port) {
      this.port = port;
   }

   
   public String getPhones() {
      return phones;
   }

   
   public void setPhones(String phones) {
      this.phones = phones;
   }
   
}
