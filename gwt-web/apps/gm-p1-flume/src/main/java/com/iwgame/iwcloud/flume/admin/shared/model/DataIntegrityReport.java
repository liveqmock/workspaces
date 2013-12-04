/****************************************************************
 *  文件名     ： DataIntegrity.java
 *  日期         :  2012-8-24
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/** 
 * @ClassName:    DataIntegrity 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-8-24上午10:36:37
 * @Version:      1.0 
 */
public class DataIntegrityReport implements IsSerializable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3822503135181322940L;

	private Date date;
	
	private String zone;
	
	private int activity_paid;
	
	private int activity_consume;
	
	private int paid_count;
	
	private int bak_paid;
	
	private int consume_count;
	
	private int bak_consume;
	
	private int contrast_paid;
	
	private int contrast_consume;

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * @return the activity_paid
	 */
	public int getActivity_paid() {
		return activity_paid;
	}

	/**
	 * @param activity_paid the activity_paid to set
	 */
	public void setActivity_paid(int activity_paid) {
		this.activity_paid = activity_paid;
	}

	/**
	 * @return the activity_consume
	 */
	public int getActivity_consume() {
		return activity_consume;
	}

	/**
	 * @param activity_consume the activity_consume to set
	 */
	public void setActivity_consume(int activity_consume) {
		this.activity_consume = activity_consume;
	}

	/**
	 * @return the paid_count
	 */
	public int getPaid_count() {
		return paid_count;
	}

	/**
	 * @param paid_count the paid_count to set
	 */
	public void setPaid_count(int paid_count) {
		this.paid_count = paid_count;
	}

	/**
	 * @return the bak_paid
	 */
	public int getBak_paid() {
		return bak_paid;
	}

	/**
	 * @param bak_paid the bak_paid to set
	 */
	public void setBak_paid(int bak_paid) {
		this.bak_paid = bak_paid;
	}

	/**
	 * @return the consume_count
	 */
	public int getConsume_count() {
		return consume_count;
	}

	/**
	 * @param consume_count the consume_count to set
	 */
	public void setConsume_count(int consume_count) {
		this.consume_count = consume_count;
	}

	/**
	 * @return the bak_consume
	 */
	public int getBak_consume() {
		return bak_consume;
	}

	/**
	 * @param bak_consume the bak_consume to set
	 */
	public void setBak_consume(int bak_consume) {
		this.bak_consume = bak_consume;
	}

	/**
	 * @return the contrast_paid
	 */
	public int getContrast_paid() {
		return contrast_paid;
	}

	/**
	 * @param contrast_paid the contrast_paid to set
	 */
	public void setContrast_paid(int contrast_paid) {
		this.contrast_paid = contrast_paid;
	}

	/**
	 * @return the contrast_consume
	 */
	public int getContrast_consume() {
		return contrast_consume;
	}

	/**
	 * @param contrast_consume the contrast_consume to set
	 */
	public void setContrast_consume(int contrast_consume) {
		this.contrast_consume = contrast_consume;
	}
}
