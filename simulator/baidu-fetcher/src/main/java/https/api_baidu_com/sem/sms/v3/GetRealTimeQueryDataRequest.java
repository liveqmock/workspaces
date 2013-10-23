
package https.api_baidu_com.sem.sms.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="realTimeQueryRequestTypes" type="{https://api.baidu.com/sem/sms/v3}RealTimeQueryRequestType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "realTimeQueryRequestTypes"
})
@XmlRootElement(name = "getRealTimeQueryDataRequest")
public class GetRealTimeQueryDataRequest {

    @XmlElement(required = true)
    protected RealTimeQueryRequestType realTimeQueryRequestTypes;

    /**
     * Gets the value of the realTimeQueryRequestTypes property.
     * 
     * @return
     *     possible object is
     *     {@link RealTimeQueryRequestType }
     *     
     */
    public RealTimeQueryRequestType getRealTimeQueryRequestTypes() {
        return realTimeQueryRequestTypes;
    }

    /**
     * Sets the value of the realTimeQueryRequestTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RealTimeQueryRequestType }
     *     
     */
    public void setRealTimeQueryRequestTypes(RealTimeQueryRequestType value) {
        this.realTimeQueryRequestTypes = value;
    }

}
