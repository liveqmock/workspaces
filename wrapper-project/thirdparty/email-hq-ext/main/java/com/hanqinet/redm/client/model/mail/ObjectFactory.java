
package com.hanqinet.redm.client.model.mail;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hanqinet.redm.model.mail package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BasalMailDataData_QNAME = new QName("http://mail.model.redm.hanqinet.com", "data");
    private final static QName _BasalMailDataNickname_QNAME = new QName("http://mail.model.redm.hanqinet.com", "nickname");
    private final static QName _BasalMailDataEmail_QNAME = new QName("http://mail.model.redm.hanqinet.com", "email");
    private final static QName _BasalMailDataDataSplit_QNAME = new QName("http://mail.model.redm.hanqinet.com", "dataSplit");
    private final static QName _BasalMailDataTemplateId_QNAME = new QName("http://mail.model.redm.hanqinet.com", "templateId");
    private final static QName _BasalMailDataGroupId_QNAME = new QName("http://mail.model.redm.hanqinet.com", "groupId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hanqinet.redm.model.mail
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BasalMailData }
     * 
     */
    public BasalMailData createBasalMailData() {
        return new BasalMailData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.model.redm.hanqinet.com", name = "data", scope = BasalMailData.class)
    public JAXBElement<String> createBasalMailDataData(String value) {
        return new JAXBElement<String>(_BasalMailDataData_QNAME, String.class, BasalMailData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.model.redm.hanqinet.com", name = "nickname", scope = BasalMailData.class)
    public JAXBElement<String> createBasalMailDataNickname(String value) {
        return new JAXBElement<String>(_BasalMailDataNickname_QNAME, String.class, BasalMailData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.model.redm.hanqinet.com", name = "email", scope = BasalMailData.class)
    public JAXBElement<String> createBasalMailDataEmail(String value) {
        return new JAXBElement<String>(_BasalMailDataEmail_QNAME, String.class, BasalMailData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.model.redm.hanqinet.com", name = "dataSplit", scope = BasalMailData.class)
    public JAXBElement<String> createBasalMailDataDataSplit(String value) {
        return new JAXBElement<String>(_BasalMailDataDataSplit_QNAME, String.class, BasalMailData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.model.redm.hanqinet.com", name = "templateId", scope = BasalMailData.class)
    public JAXBElement<Integer> createBasalMailDataTemplateId(Integer value) {
        return new JAXBElement<Integer>(_BasalMailDataTemplateId_QNAME, Integer.class, BasalMailData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.model.redm.hanqinet.com", name = "groupId", scope = BasalMailData.class)
    public JAXBElement<Integer> createBasalMailDataGroupId(Integer value) {
        return new JAXBElement<Integer>(_BasalMailDataGroupId_QNAME, Integer.class, BasalMailData.class, value);
    }

}
