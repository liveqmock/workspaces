package com.iwgame.iwcloud.baidu.task.core;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;

import com.baidu.api.sem.common.v2.AuthHeader;
import com.baidu.api.sem.common.v2.ResHeader;
import com.iwgame.iwcloud.baidu.task.model.AdsConfigBean;
import com.iwgame.iwcloud.baidu.task.model.FetcherConfig;
import com.iwgame.iwcloud.baidu.task.version.V2;
import com.iwgame.iwcloud.baidu.task.version.Version;
import com.iwgame.security.key.MasterKeyUtil;

/**
 * 
 * 类说明
 * 
 * @简述： Webservice 服务类（修改）
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-6-27 下午06:32:12
 */
public abstract class VersionService {

	protected static final Logger logger = Logger.getLogger(VersionService.class);

	protected AdsConfigBean adsConfigBean;

	protected AuthHeader authHeader = null;

	private List<Header> headers = null;

	private Version currentVersion = V2.v;

	protected FetcherConfig fetcherConfig;

	public FetcherConfig getFetcherConfig() {
		return fetcherConfig;
	}

	public void setFetcherConfig(FetcherConfig fetcherConfig) {
		this.fetcherConfig = fetcherConfig;
	}

	public AdsConfigBean getAdsConfigBean() {
		return adsConfigBean;
	}

	public void setAdsConfigBean(AdsConfigBean adsConfigBean) {
		this.adsConfigBean = adsConfigBean;
	}

	/**
	 * 让客户端使用Json服务协议.
	 * 
	 * @param <T>
	 * @param cls
	 * @return The client side serivce stub.
	 */
	public final <T> T getJsonService(Class<T> cls) {
		return JsonProxy.createProxy(cls, this);
	}

	/**
	 * 
	 * @param <T>
	 * @param cls Calss 字节实例
	 * @param trycount 连接超时 尝试次数
	 * @return
	 * @throws ConnectException
	 */
	public final <T> T getService(Class<T> cls, int trycount) throws ConnectException {

		while (trycount-- > 0) {
			try {
				return getService(cls, currentVersion);
			} catch (Exception e) {
				logger.info("百度webservice接口连接超时,将重试连接，第" + trycount + "次...");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	    throw new ConnectException("连接百度webservice接口超时");
	}

	public final <T> T getService(Class<T> cls, Version v) throws ConnectException {
		T proxy = getInternalService(cls, v);
		Map<String, Object> reqCtxt = ((BindingProvider) proxy).getRequestContext();
		reqCtxt.put(Header.HEADER_LIST, headers);
		reqCtxt.put("com.sun.xml.internal.ws.connect.timeout", fetcherConfig.getConnectTimeoutMills());
		reqCtxt.put("com.sun.xml.internal.ws.request.timeout", fetcherConfig.getReadTimeoutMills());

		// 配置http管道动态
		Client client = ClientProxy.getClient(proxy);
		HTTPConduit http = (HTTPConduit) client.getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(fetcherConfig.getReadTimeoutMills());
		httpClientPolicy.setAllowChunking(true);
		httpClientPolicy.setReceiveTimeout(fetcherConfig.getReadTimeoutMills());
		http.setClient(httpClientPolicy);
		if (fetcherConfig.doDisableCNCheck()) {
			TLSClientParameters tlsClientParameters = new TLSClientParameters();
			tlsClientParameters.setDisableCNCheck(true);
			tlsClientParameters.setUseHttpsURLConnectionDefaultHostnameVerifier(false);
			http.setTlsClientParameters(tlsClientParameters);
		}
		return proxy;
	}

	/**
	 * 设置当前版本时必须使用调用 请不要直接调用这个方法,它将时自动从数据库读取
	 * 
	 * @param str
	 */
	public void setVersion(String str) {
		currentVersion = Version.getVersion(str);
		if (currentVersion == null) {
			logger.error("The Version you set [" + str + "] does not exist!");
			throw new ClientInternalException("The Version you set [" + str + "] does not exist!");
		}
	}

	// /////////////////////////////////////////////////////////////////////////////
	// Protected or private methods
	// /////////////////////////////////////////////////////////////////////////////

	protected abstract <T> T getInternalService(Class<T> cls, Version v);

	/**
	 * 生成头部信息 <soapenv:Envelope
	 * xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	 * xmlns:v2="http://api.baidu.com/sem/common/v2"
	 * xmlns:v21="https://api.baidu.com/sem/sms/v2"> <soapenv:Header>
	 * <v2:AuthHeader> <v2:username> your account</v2:username> <v2:password>
	 * your password</v2:password> <v2:token> your token</v2:token>
	 * </v2:AuthHeader> </soapenv:Header> <soapenv:Body>
	 * <v21:getCampaignIdRequest/> </soapenv:Body> </soapenv:Envelope>
	 */
	public void generateHeader() {
		try {
			authHeader = new AuthHeader();
			authHeader.setUsername(adsConfigBean.getUsername());
			authHeader.setPassword(MasterKeyUtil.decKey(adsConfigBean.getPassword()));
			authHeader.setToken(adsConfigBean.getToken());
			Header header = new Header(new QName(currentVersion.getHeaderNameSpace(), "AuthHeader"), authHeader, new JAXBDataBinding(ResHeader.class));
			headers = new ArrayList<Header>();
			headers.add(header);
			logger.info("-----------Bgein fetcher ! Current user: [" + adsConfigBean.getUsername() + " ]-----------");
		} catch (JAXBException e) {
			logger.error("Failed to genarate AuthHeader!", e);
			throw new ClientInternalException("Failed to genarate AuthHeader!");
		} catch (Exception e){
			logger.error("other exception:", e);
		}
	}

}
