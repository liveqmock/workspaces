package com.iwgame.security.indexer.bean;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;


public class Account {
	
	@Field
	private String s_account_id;
	@Field
	private String s_user_name;
	@Field
	private String s_real_name;
	@Field
	private String s_idcard;
	@Field
	private String s_idcard_status;
	@Field
	private String s_user_email;
	@Field
	private String s_user_email_new;
	@Field
	private String s_account_status;
	@Field
	private Date s_register_time;
	@Field
	private String s_register_ip;
	@Field
	private String s_register_sourcetype;
	@Field
	private String s_register_sourceid;
	@Field
	private String s_register_sourceurl;
	@Field
	private String s_password4;
	@Field
	private int s_max_level;
	@Field
	private int s_total_paid;

	/**
	 * @return the s_account_id
	 */
	public String getS_account_id() {
		return s_account_id;
	}

	/**
	 * @param s_account_id
	 *            the s_account_id to set
	 */
	public void setS_account_id(String s_account_id) {
		this.s_account_id = s_account_id;
	}

	/**
	 * @return the s_user_name
	 */
	public String getS_user_name() {
		return s_user_name;
	}

	/**
	 * @param s_user_name
	 *            the s_user_name to set
	 */
	public void setS_user_name(String s_user_name) {
		this.s_user_name = s_user_name;
	}

	/**
	 * @return the s_real_name
	 */
	public String getS_real_name() {
		return s_real_name;
	}

	/**
	 * @param s_real_name
	 *            the s_real_name to set
	 */
	public void setS_real_name(String s_real_name) {
		this.s_real_name = s_real_name;
	}

	/**
	 * @return the s_idcard
	 */
	public String getS_idcard() {
		return s_idcard;
	}

	/**
	 * @param s_idcard
	 *            the s_idcard to set
	 */
	public void setS_idcard(String s_idcard) {
		this.s_idcard = s_idcard;
	}

	/**
	 * @return the s_idcard_status
	 */
	public String getS_idcard_status() {
		return s_idcard_status;
	}

	/**
	 * @param s_idcard_status
	 *            the s_idcard_status to set
	 */
	public void setS_idcard_status(String s_idcard_status) {
		this.s_idcard_status = s_idcard_status;
	}

	/**
	 * @return the s_user_email
	 */
	public String getS_user_email() {
		return s_user_email;
	}

	/**
	 * @param s_user_email
	 *            the s_user_email to set
	 */
	public void setS_user_email(String s_user_email) {
		this.s_user_email = s_user_email;
	}

	/**
	 * @return the s_user_email_new
	 */
	public String getS_user_email_new() {
		return s_user_email_new;
	}

	/**
	 * @param s_user_email_new
	 *            the s_user_email_new to set
	 */
	public void setS_user_email_new(String s_user_email_new) {
		this.s_user_email_new = s_user_email_new;
	}

	/**
	 * @return the s_account_status
	 */
	public String getS_account_status() {
		return s_account_status;
	}

	/**
	 * @param s_account_status
	 *            the s_account_status to set
	 */
	public void setS_account_status(String s_account_status) {
		this.s_account_status = s_account_status;
	}

	/**
	 * @return the s_register_time
	 */
	public Date getS_register_time() {
		return s_register_time;
	}

	/**
	 * @param s_register_time
	 *            the s_register_time to set
	 */
	public void setS_register_time(Date s_register_time) {
		this.s_register_time = s_register_time;
	}

	/**
	 * @return the s_register_ip
	 */
	public String getS_register_ip() {
		return s_register_ip;
	}

	/**
	 * @param s_register_ip
	 *            the s_register_ip to set
	 */
	public void setS_register_ip(String s_register_ip) {
		this.s_register_ip = s_register_ip;
	}

	/**
	 * @return the s_register_sourcetype
	 */
	public String getS_register_sourcetype() {
		return s_register_sourcetype;
	}

	/**
	 * @param s_register_sourcetype
	 *            the s_register_sourcetype to set
	 */
	public void setS_register_sourcetype(String s_register_sourcetype) {
		this.s_register_sourcetype = s_register_sourcetype;
	}

	/**
	 * @return the s_register_sourceid
	 */
	public String getS_register_sourceid() {
		return s_register_sourceid;
	}

	/**
	 * @param s_register_sourceid
	 *            the s_register_sourceid to set
	 */
	public void setS_register_sourceid(String s_register_sourceid) {
		this.s_register_sourceid = s_register_sourceid;
	}

	/**
	 * @return the s_register_sourceurl
	 */
	public String getS_register_sourceurl() {
		return s_register_sourceurl;
	}

	/**
	 * @param s_register_sourceurl
	 *            the s_register_sourceurl to set
	 */
	public void setS_register_sourceurl(String s_register_sourceurl) {
		this.s_register_sourceurl = s_register_sourceurl;
	}

	/**
	 * @return the s_password4
	 */
	public String getS_password4() {
		return s_password4;
	}

	/**
	 * @param s_password4
	 *            the s_password4 to set
	 */
	public void setS_password4(String s_password4) {
		this.s_password4 = s_password4;
	}

	/**
	 * @return the s_max_level
	 */
	public int getS_max_level() {
		return s_max_level;
	}

	/**
	 * @param s_max_level
	 *            the s_max_level to set
	 */
	public void setS_max_level(int s_max_level) {
		this.s_max_level = s_max_level;
	}

	/**
	 * @return the s_total_paid
	 */
	public int getS_total_paid() {
		return s_total_paid;
	}

	/**
	 * @param s_total_paid
	 *            the s_total_paid to set
	 */
	public void setS_total_paid(int s_total_paid) {
		this.s_total_paid = s_total_paid;
	}
}
