package com.iwgame.iwcloud.baidu.task.core;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;

import com.iwgame.iwcloud.baidu.task.model.AdsConfigBean;
import com.iwgame.iwcloud.baidu.task.model.FetcherConfig;
import com.iwgame.iwcloud.baidu.task.version.Version;

/**
 * 
 * 类说明
 * 
 * @简述： Webservice 工厂实例 根据业务进行了修改！
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-6-28 下午06:48:38
 */
public class ServiceFactory extends VersionService {

	protected static final Logger logger = Logger.getLogger(ServiceFactory.class);

	protected <T> T getInternalService(Class<T> cls, Version v) {
		if (cls == null || v == null) {
			logger.error("Null parameter is not allowed!");
			throw new IllegalArgumentException("Null parameter is not allowed!");
		}
		Service service;
		try {
			String addr = v.getAddr(cls);
			QName qName = v.getQName(cls);
			if (addr == null || qName == null) {
				logger.error("Service [" + cls.getSimpleName() + "] not supported by version - " + v.getVersion());
				throw new ClientInternalException("Service [" + cls.getSimpleName() + "] not supported by version - " + v.getVersion());
			}
			service = new CoreService(new URL(fetcherConfig.getServerUrl() + addr), qName);
			return service.getPort(cls);
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException:", e);
			throw new ClientInternalException(e);
		}
	}

	public ServiceFactory(AdsConfigBean adsConfigBean, FetcherConfig fetcherConfig) {
		super();
		super.setAdsConfigBean(adsConfigBean);
		super.setFetcherConfig(fetcherConfig);
		super.setVersion("V2");
		super.generateHeader();
	}

	public static ServiceFactory getInstance(AdsConfigBean adsConfigBean, FetcherConfig fetcherConfig) {
		return new ServiceFactory(adsConfigBean, fetcherConfig);
	}
}
