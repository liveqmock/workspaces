
package com.baidu.api.sem.common.v2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.baidu.api.sem.common.v2 package. 
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

    private final static QName _ResHeader_QNAME = new QName("http://api.baidu.com/sem/common/v2", "ResHeader");
    private final static QName _Opt_QNAME = new QName("http://api.baidu.com/sem/common/v2", "opt");
    private final static QName _AuthHeader_QNAME = new QName("http://api.baidu.com/sem/common/v2", "AuthHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.baidu.api.sem.common.v2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FloatMapItemType }
     * 
     */
    public FloatMapItemType createFloatMapItemType() {
        return new FloatMapItemType();
    }

    /**
     * Create an instance of {@link OptType }
     * 
     */
    public OptType createOptType() {
        return new OptType();
    }

    /**
     * Create an instance of {@link StringMapItemType }
     * 
     */
    public StringMapItemType createStringMapItemType() {
        return new StringMapItemType();
    }

    /**
     * Create an instance of {@link ResHeader }
     * 
     */
    public ResHeader createResHeader() {
        return new ResHeader();
    }

    /**
     * Create an instance of {@link AuthHeader }
     * 
     */
    public AuthHeader createAuthHeader() {
        return new AuthHeader();
    }

    /**
     * Create an instance of {@link Failure }
     * 
     */
    public Failure createFailure() {
        return new Failure();
    }

    /**
     * Create an instance of {@link IntMapItemType }
     * 
     */
    public IntMapItemType createIntMapItemType() {
        return new IntMapItemType();
    }

    /**
     * Create an instance of {@link LongMapItemType }
     * 
     */
    public LongMapItemType createLongMapItemType() {
        return new LongMapItemType();
    }

    /**
     * Create an instance of {@link DoubleMapItemType }
     * 
     */
    public DoubleMapItemType createDoubleMapItemType() {
        return new DoubleMapItemType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.baidu.com/sem/common/v2", name = "ResHeader")
    public JAXBElement<ResHeader> createResHeader(ResHeader value) {
        return new JAXBElement<ResHeader>(_ResHeader_QNAME, ResHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OptType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.baidu.com/sem/common/v2", name = "opt")
    public JAXBElement<OptType> createOpt(OptType value) {
        return new JAXBElement<OptType>(_Opt_QNAME, OptType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.baidu.com/sem/common/v2", name = "AuthHeader")
    public JAXBElement<AuthHeader> createAuthHeader(AuthHeader value) {
        return new JAXBElement<AuthHeader>(_AuthHeader_QNAME, AuthHeader.class, null, value);
    }

}
