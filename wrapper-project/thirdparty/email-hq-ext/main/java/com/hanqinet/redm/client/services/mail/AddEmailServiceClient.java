
package com.hanqinet.redm.client.services.mail;

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
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class AddEmailServiceClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private final HashMap<QName,Endpoint> endpoints = new HashMap<QName,Endpoint>();
    private Service service0;

    public AddEmailServiceClient() {
        create0();
        Endpoint AddEmailServicePortTypeLocalEndpointEP = service0.addEndpoint(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServicePortTypeLocalEndpoint"), new QName("http://mail.services.redm.hanqinet.com", "AddEmailServicePortTypeLocalBinding"), "xfire.local://AddEmailService");
        endpoints.put(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServicePortTypeLocalEndpoint"), AddEmailServicePortTypeLocalEndpointEP);
        Endpoint AddEmailServiceHttpPortEP = service0 .addEndpoint(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServiceHttpPort"), new QName("http://mail.services.redm.hanqinet.com", "AddEmailServiceHttpBinding"), "http://redm1.zhujinnet.com:8080/services/AddEmailService");
        endpoints.put(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServiceHttpPort"), AddEmailServiceHttpPortEP);
    }

	public AddEmailServiceClient(String wsdlUrl) {
        create0();
        Endpoint AddEmailServicePortTypeLocalEndpointEP = service0 .addEndpoint(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServicePortTypeLocalEndpoint"), new QName("http://mail.services.redm.hanqinet.com", "AddEmailServicePortTypeLocalBinding"), "xfire.local://AddEmailService");
        endpoints.put(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServicePortTypeLocalEndpoint"), AddEmailServicePortTypeLocalEndpointEP);
        Endpoint AddEmailServiceHttpPortEP = service0 .addEndpoint(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServiceHttpPort"), new QName("http://mail.services.redm.hanqinet.com", "AddEmailServiceHttpBinding"), wsdlUrl);
        endpoints.put(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServiceHttpPort"), AddEmailServiceHttpPortEP);
	}
	
    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = (endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection<?> getEndpoints() {
        return endpoints.values();
    }

    @SuppressWarnings("unused")
    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap<String,Boolean> props = new HashMap<String,Boolean>();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((com.hanqinet.redm.client.services.mail.AddEmailServicePortType.class), props);
        {
			AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://mail.services.redm.hanqinet.com", "AddEmailServicePortTypeLocalBinding"), "urn:xfire:transport:local");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://mail.services.redm.hanqinet.com", "AddEmailServiceHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
    }

    public AddEmailServicePortType getAddEmailServicePortTypeLocalEndpoint() {
        return ((AddEmailServicePortType)getEndpoint(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServicePortTypeLocalEndpoint")));
    }

    public AddEmailServicePortType getAddEmailServicePortTypeLocalEndpoint(String url) {
        AddEmailServicePortType var = getAddEmailServicePortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public AddEmailServicePortType getAddEmailServiceHttpPort() {
        return (AddEmailServicePortType)getEndpoint(new QName("http://mail.services.redm.hanqinet.com", "AddEmailServiceHttpPort"));
    }

    public AddEmailServicePortType getAddEmailServiceHttpPort(String url) {
        AddEmailServicePortType var = getAddEmailServiceHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }
}
	
