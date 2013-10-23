
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
 *         &lt;element name="realTimePairRequestTypes" type="{https://api.baidu.com/sem/sms/v3}RealTimeRequestType"/>
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
    "realTimePairRequestTypes"
})
@XmlRootElement(name = "getRealTimePairDataRequest")
public class GetRealTimePairDataRequest {

    @XmlElement(required = true)
    protected RealTimeRequestType realTimePairRequestTypes;

    /**
     * Gets the value of the realTimePairRequestTypes property.
     * 
     * @return
     *     possible object is
     *     {@link RealTimeRequestType }
     *     
     */
    public RealTimeRequestType getRealTimePairRequestTypes() {
        return realTimePairRequestTypes;
    }

    /**
     * Sets the value of the realTimePairRequestTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RealTimeRequestType }
     *     
     */
    public void setRealTimePairRequestTypes(RealTimeRequestType value) {
        this.realTimePairRequestTypes = value;
    }

}
