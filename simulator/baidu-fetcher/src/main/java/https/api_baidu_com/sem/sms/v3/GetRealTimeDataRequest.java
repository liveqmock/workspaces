
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
 *         &lt;element name="realTimeRequestTypes" type="{https://api.baidu.com/sem/sms/v3}RealTimeRequestType"/>
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
    "realTimeRequestTypes"
})
@XmlRootElement(name = "getRealTimeDataRequest")
public class GetRealTimeDataRequest {

    @XmlElement(required = true)
    protected RealTimeRequestType realTimeRequestTypes;

    /**
     * Gets the value of the realTimeRequestTypes property.
     * 
     * @return
     *     possible object is
     *     {@link RealTimeRequestType }
     *     
     */
    public RealTimeRequestType getRealTimeRequestTypes() {
        return realTimeRequestTypes;
    }

    /**
     * Sets the value of the realTimeRequestTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RealTimeRequestType }
     *     
     */
    public void setRealTimeRequestTypes(RealTimeRequestType value) {
        this.realTimeRequestTypes = value;
    }

}
