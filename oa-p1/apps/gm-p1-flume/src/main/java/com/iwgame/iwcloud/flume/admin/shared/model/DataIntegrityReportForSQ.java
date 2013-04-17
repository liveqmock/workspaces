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
public class DataIntegrityReportForSQ implements IsSerializable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8603758802993864607L;
	private Date date;
	private String zone;
	private int paid_min_value;
	private int paid_max_value;
	private int paid_count_value;
	private int paid_sum_value;
	private int paid_sum_free_value;
	private int paid_activity_value;
	private int paid_activity_free_value;
	private int consume_min_value;
	private int consume_max_value;
	private int consume_count_value;
	private int consume_sum_value;
	private int consume_sum_free_value;
	private int consume_activity_value;
	private int consume_activity_free_value;
	private int contrast_paid_count;
	private int contrast_paid_activity;
	private int contrast_paid_activity_free;
	private int contrast_consume_count;
	private int contrast_consume_activity;
	private int contrast_consume_activity_free;
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
	 * @return the paid_min_value
	 */
	public int getPaid_min_value() {
		return paid_min_value;
	}
	/**
	 * @param paid_min_value the paid_min_value to set
	 */
	public void setPaid_min_value(int paid_min_value) {
		this.paid_min_value = paid_min_value;
	}
	/**
	 * @return the paid_max_value
	 */
	public int getPaid_max_value() {
		return paid_max_value;
	}
	/**
	 * @param paid_max_value the paid_max_value to set
	 */
	public void setPaid_max_value(int paid_max_value) {
		this.paid_max_value = paid_max_value;
	}
	/**
	 * @return the paid_count_value
	 */
	public int getPaid_count_value() {
		return paid_count_value;
	}
	/**
	 * @param paid_count_value the paid_count_value to set
	 */
	public void setPaid_count_value(int paid_count_value) {
		this.paid_count_value = paid_count_value;
	}
	/**
	 * @return the paid_sum_value
	 */
	public int getPaid_sum_value() {
		return paid_sum_value;
	}
	/**
	 * @param paid_sum_value the paid_sum_value to set
	 */
	public void setPaid_sum_value(int paid_sum_value) {
		this.paid_sum_value = paid_sum_value;
	}
	/**
	 * @return the paid_sum_free_value
	 */
	public int getPaid_sum_free_value() {
		return paid_sum_free_value;
	}
	/**
	 * @param paid_sum_free_value the paid_sum_free_value to set
	 */
	public void setPaid_sum_free_value(int paid_sum_free_value) {
		this.paid_sum_free_value = paid_sum_free_value;
	}
	/**
	 * @return the paid_activity_value
	 */
	public int getPaid_activity_value() {
		return paid_activity_value;
	}
	/**
	 * @param paid_activity_value the paid_activity_value to set
	 */
	public void setPaid_activity_value(int paid_activity_value) {
		this.paid_activity_value = paid_activity_value;
	}
	/**
	 * @return the paid_activity_free_value
	 */
	public int getPaid_activity_free_value() {
		return paid_activity_free_value;
	}
	/**
	 * @param paid_activity_free_value the paid_activity_free_value to set
	 */
	public void setPaid_activity_free_value(int paid_activity_free_value) {
		this.paid_activity_free_value = paid_activity_free_value;
	}
	/**
	 * @return the consume_min_value
	 */
	public int getConsume_min_value() {
		return consume_min_value;
	}
	/**
	 * @param consume_min_value the consume_min_value to set
	 */
	public void setConsume_min_value(int consume_min_value) {
		this.consume_min_value = consume_min_value;
	}
	/**
	 * @return the consume_max_value
	 */
	public int getConsume_max_value() {
		return consume_max_value;
	}
	/**
	 * @param consume_max_value the consume_max_value to set
	 */
	public void setConsume_max_value(int consume_max_value) {
		this.consume_max_value = consume_max_value;
	}
	/**
	 * @return the consume_count_value
	 */
	public int getConsume_count_value() {
		return consume_count_value;
	}
	/**
	 * @param consume_count_value the consume_count_value to set
	 */
	public void setConsume_count_value(int consume_count_value) {
		this.consume_count_value = consume_count_value;
	}
	/**
	 * @return the consume_sum_value
	 */
	public int getConsume_sum_value() {
		return consume_sum_value;
	}
	/**
	 * @param consume_sum_value the consume_sum_value to set
	 */
	public void setConsume_sum_value(int consume_sum_value) {
		this.consume_sum_value = consume_sum_value;
	}
	/**
	 * @return the consume_sum_free_value
	 */
	public int getConsume_sum_free_value() {
		return consume_sum_free_value;
	}
	/**
	 * @param consume_sum_free_value the consume_sum_free_value to set
	 */
	public void setConsume_sum_free_value(int consume_sum_free_value) {
		this.consume_sum_free_value = consume_sum_free_value;
	}
	/**
	 * @return the consume_activity_value
	 */
	public int getConsume_activity_value() {
		return consume_activity_value;
	}
	/**
	 * @param consume_activity_value the consume_activity_value to set
	 */
	public void setConsume_activity_value(int consume_activity_value) {
		this.consume_activity_value = consume_activity_value;
	}
	/**
	 * @return the consume_activity_free_value
	 */
	public int getConsume_activity_free_value() {
		return consume_activity_free_value;
	}
	/**
	 * @param consume_activity_free_value the consume_activity_free_value to set
	 */
	public void setConsume_activity_free_value(int consume_activity_free_value) {
		this.consume_activity_free_value = consume_activity_free_value;
	}
	/**
	 * @return the contrast_paid_count
	 */
	public int getContrast_paid_count() {
		return contrast_paid_count;
	}
	/**
	 * @param contrast_paid_count the contrast_paid_count to set
	 */
	public void setContrast_paid_count(int contrast_paid_count) {
		this.contrast_paid_count = contrast_paid_count;
	}
	/**
	 * @return the contrast_paid_activity
	 */
	public int getContrast_paid_activity() {
		return contrast_paid_activity;
	}
	/**
	 * @param contrast_paid_activity the contrast_paid_activity to set
	 */
	public void setContrast_paid_activity(int contrast_paid_activity) {
		this.contrast_paid_activity = contrast_paid_activity;
	}
	/**
	 * @return the contrast_paid_activity_free
	 */
	public int getContrast_paid_activity_free() {
		return contrast_paid_activity_free;
	}
	/**
	 * @param contrast_paid_activity_free the contrast_paid_activity_free to set
	 */
	public void setContrast_paid_activity_free(int contrast_paid_activity_free) {
		this.contrast_paid_activity_free = contrast_paid_activity_free;
	}
	/**
	 * @return the contrast_consume_count
	 */
	public int getContrast_consume_count() {
		return contrast_consume_count;
	}
	/**
	 * @param contrast_consume_count the contrast_consume_count to set
	 */
	public void setContrast_consume_count(int contrast_consume_count) {
		this.contrast_consume_count = contrast_consume_count;
	}
	/**
	 * @return the contrast_consume_activity
	 */
	public int getContrast_consume_activity() {
		return contrast_consume_activity;
	}
	/**
	 * @param contrast_consume_activity the contrast_consume_activity to set
	 */
	public void setContrast_consume_activity(int contrast_consume_activity) {
		this.contrast_consume_activity = contrast_consume_activity;
	}
	/**
	 * @return the contrast_consume_activity_free
	 */
	public int getContrast_consume_activity_free() {
		return contrast_consume_activity_free;
	}
	/**
	 * @param contrast_consume_activity_free the contrast_consume_activity_free to set
	 */
	public void setContrast_consume_activity_free(int contrast_consume_activity_free) {
		this.contrast_consume_activity_free = contrast_consume_activity_free;
	}
}
