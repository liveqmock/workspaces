package com.iwgame.cps.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class Utils implements InitializingBean{


	private static Logger logger = Logger.getLogger(Utils.class);

	private static Properties properties = new Properties();

	private Resource[] locations;

	private String fileEncoding = "UTF-8";

	private boolean ignoreResourceNotFound;


	/**
	 * @param locations the locations to set
	 */
	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}


	/**
	 * @param fileEncoding the fileEncoding to set
	 */
	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}


	/**
	 * @param ignoreResourceNotFound the ignoreResourceNotFound to set
	 */
	public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
		this.ignoreResourceNotFound = ignoreResourceNotFound;
	}


	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.locations != null) {

			for (Resource location : this.locations) {

				InputStream is = null;

				try {
					is = location.getInputStream();

					if (this.fileEncoding != null) {
						if (Utils.properties != null) {
							Reader reader = new InputStreamReader(is, this.fileEncoding);
							Utils.properties.load(reader);
							if (Utils.logger.isInfoEnabled()) {
								Utils.logger.info("Loading properties file from " + location);
							}
						}
					} else {
						Utils.properties.load(is);
					}

				} catch (IOException ex) {
					if (this.ignoreResourceNotFound) {
						Utils.logger.warn("Could not load properties from " + location + ": " + ex.getMessage());
					}
					else {
						throw ex;
					}
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		}
	}


	public static String getProperty(String key, String defaultValue)
	{
		if(Utils.properties.getProperty(key) == null){
			return defaultValue;
		}else{
			return Utils.properties.getProperty(key);
		}
	}

	public static String getProperty(String key) {
		return Utils.properties.getProperty(key);
	}


	public static class Constant{

		/**
		 * 分页常量设置
		 */
		public final static int DEFAULT_PAGE_SIZE = 50;

		public final static String PAGE_START = "limit.start";

		public final static String PAGE_SIZE = "limit.length";

		public final static String PAGE_COUNT = "limit.count";

		/**
		 * Host
		 **/
		public final static String hostprotocol = Utils.getProperty("Host.protocol", "http");

		public final static String hostip = Utils.getProperty("Host.ip", "localhost");

		public final static String hostport = Utils.getProperty("Host.port", "9080");


		/**
		 * 文件上传路径
		 **/
		public final static String uploadDir = Utils.getProperty("upload.dir", "");

		public final static String jcaptcha = Utils.getProperty("jcaptcha.enabled", "true");

		public final static String usersessionMax = Utils.getProperty("usersession.max", "0");

	}

}