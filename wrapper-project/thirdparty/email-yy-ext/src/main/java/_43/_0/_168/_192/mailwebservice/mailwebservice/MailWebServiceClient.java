package _43._0._168._192.mailwebservice.mailwebservice;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.namespace.QName;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.transport.TransportManager;

public class MailWebServiceClient {

	private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
	private HashMap<QName, Endpoint> endpoints = new HashMap<QName, Endpoint>();
	private Service service0;

	public MailWebServiceClient() {
		create0();
		Endpoint MailWebServiceSoapEP = service0.addEndpoint(new QName("http://192.168.0.43/MailWebService/MailWebService.asmx", "MailWebServiceSoap"), new QName(
			"http://192.168.0.43/MailWebService/MailWebService.asmx", "MailWebServiceSoap"), "http://app.easeye.com.cn/MailWebService/MailWebService.asmx");
		endpoints.put(new QName("http://192.168.0.43/MailWebService/MailWebService.asmx", "MailWebServiceSoap"), MailWebServiceSoapEP);
		Endpoint MailWebServiceSoapLocalEndpointEP = service0.addEndpoint(new QName("http://192.168.0.43/MailWebService/MailWebService.asmx", "MailWebServiceSoapLocalEndpoint"),
			new QName("http://192.168.0.43/MailWebService/MailWebService.asmx", "MailWebServiceSoapLocalBinding"), "xfire.local://MailWebService");
		endpoints.put(new QName("http://192.168.0.43/MailWebService/MailWebService.asmx", "MailWebServiceSoapLocalEndpoint"), MailWebServiceSoapLocalEndpointEP);
	}

	public Object getEndpoint(Endpoint endpoint) {
		try {
			return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
		} catch (MalformedURLException e) {
			throw new XFireRuntimeException("Invalid URL", e);
		}
	}

	public Object getEndpoint(QName name) {
		Endpoint endpoint = endpoints.get((name));
		if ((endpoint) == null) {
			throw new IllegalStateException("No such endpoint!");
		}
		return getEndpoint((endpoint));
	}

	public Collection<Endpoint> getEndpoints() {
		return endpoints.values();
	}

	private void create0() {
		TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
		HashMap<String, Boolean> props = new HashMap<String, Boolean>();
		props.put("annotations.allow.interface", true);
		AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
		asf.setBindingCreationEnabled(false);
		service0 = asf.create((_43._0._168._192.mailwebservice.mailwebservice.MailWebServiceSoap.class), props);
	}

	public MailWebServiceSoap getMailWebServiceSoap() {
		return ((MailWebServiceSoap) (this).getEndpoint(new QName("http://192.168.0.43/MailWebService/MailWebService.asmx", "MailWebServiceSoap")));
	}

	public MailWebServiceSoap getMailWebServiceSoap(String url) {
		MailWebServiceSoap var = getMailWebServiceSoap();
		org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
		return var;
	}

	public MailWebServiceSoap getMailWebServiceSoapLocalEndpoint() {
		return ((MailWebServiceSoap) (this).getEndpoint(new QName("http://192.168.0.43/MailWebService/MailWebService.asmx", "MailWebServiceSoapLocalEndpoint")));
	}

	public MailWebServiceSoap getMailWebServiceSoapLocalEndpoint(String url) {
		MailWebServiceSoap var = getMailWebServiceSoapLocalEndpoint();
		org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
		return var;
	}

}
