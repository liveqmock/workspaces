package com.iwgame.iwcloud.baidu.task.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.iwgame.iwcloud.baidu.task.util.DateTimeUtil;

/**
 * 类说明
 * 
 * @compend： fetche config bean
 * @author： jjwu
 * @version： 1.0
 * @email： wujunjie@iwgame.com
 * @time：2012-6-29 上午11:50:23
 */
public class FetcherConfig implements Serializable {

	private final Logger logger = Logger.getLogger(FetcherConfig.class);

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3662317107640124225L;

	private Date fetcherBeginTime;

	private Date fetcherEndTime;

	private String fetcherBegin;

	private String fetcherEnd;

	private String saveFilePath;

	private String version;

	private int connectTimeoutMills;

	private int readTimeoutMills;

	private int disableCNCheck;

	private String serverUrl;
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getConnectTimeoutMills() {
		return connectTimeoutMills;
	}

	public void setConnectTimeoutMills(int connectTimeoutMills) {
		this.connectTimeoutMills = connectTimeoutMills;
	}

	public int getReadTimeoutMills() {
		return readTimeoutMills;
	}

	public void setReadTimeoutMills(int readTimeoutMills) {
		this.readTimeoutMills = readTimeoutMills;
	}

	public int getDisableCNCheck() {
		return disableCNCheck;
	}
	
	public boolean doDisableCNCheck() {
		return disableCNCheck == 0;
	}

	public void setDisableCNCheck(int disableCNCheck) {
		this.disableCNCheck = disableCNCheck;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	

	/**
	 * constructor
	 */
	@SuppressWarnings("deprecation")
	public FetcherConfig() {
		super();
		// defeat settings
		// get yesterday 00:00:00
		Date yesterday = DateTimeUtil.getYesterday();
		// set yesterday 23:59:59
		Calendar calendar = Calendar.getInstance();
		calendar.set(1900 + yesterday.getYear(), yesterday.getMonth(), yesterday.getDate(), 23, 59, 59);
		this.fetcherBeginTime = yesterday;
		this.fetcherEndTime = calendar.getTime();
	}

	// ///////////////////////////////////////////////////////
	// ///////// seter or geter ///////////////
	// ///////////////////////////////////////////////////////

	public Date getFetcherBeginTime() {
		try {
			if (null != fetcherBegin && !"".equals(fetcherBegin) && !"null".equals(fetcherBegin)) {
				this.fetcherBeginTime = DateTimeUtil.parseDateFromDateTimeStr(fetcherBegin);
			}
		} catch (ParseException e) {
			logger.error("解析日期格式不正确！", e);
		}
		return fetcherBeginTime;
	}

	public Date getFetcherEndTime() {
		try {
			if (null != fetcherEnd && !"".equals(fetcherEnd) && !"null".equals(fetcherEnd)) {
				this.fetcherEndTime = DateTimeUtil.parseDateFromDateTimeStr(fetcherEnd);
			}
		} catch (ParseException e) {
			logger.error("解析日期格式不正确！", e);
		}
		return fetcherEndTime;
	}

	public void setFetcherBegin(String fetcherBegin) {
		this.fetcherBegin = fetcherBegin;
	}

	public void setFetcherEnd(String fetcherEnd) {
		this.fetcherEnd = fetcherEnd;
	}

	public String getSaveFilePath() {
		return saveFilePath;
	}

	public void setSaveFilePath(String saveFilePath) {
		this.saveFilePath = saveFilePath;
	}
	
	public String getFetcherBegin() {
		return fetcherBegin;
	}

	public String getFetcherEnd() {
		return fetcherEnd;
	}
	
	public void setFetcherBeginTime(Date fetcherBeginTime) {
		this.fetcherBeginTime = fetcherBeginTime;
	}

	public void setFetcherEndTime(Date fetcherEndTime) {
		this.fetcherEndTime = fetcherEndTime;
	}

}
