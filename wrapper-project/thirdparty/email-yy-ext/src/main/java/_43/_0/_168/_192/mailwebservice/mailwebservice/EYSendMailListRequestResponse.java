
package _43._0._168._192.mailwebservice.mailwebservice;

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
 *         &lt;element name="EY_SendMailListRequestResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeReturnDTO"/>
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
    "eySendMailListRequestResult"
})
@XmlRootElement(name = "EY_SendMailListRequestResponse")
public class EYSendMailListRequestResponse {

    @XmlElement(name = "EY_SendMailListRequestResult", required = true)
    protected EaseyeReturnDTO eySendMailListRequestResult;

    /**
     * Gets the value of the eySendMailListRequestResult property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeReturnDTO }
     *     
     */
    public EaseyeReturnDTO getEYSendMailListRequestResult() {
        return eySendMailListRequestResult;
    }

    /**
     * Sets the value of the eySendMailListRequestResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeReturnDTO }
     *     
     */
    public void setEYSendMailListRequestResult(EaseyeReturnDTO value) {
        this.eySendMailListRequestResult = value;
    }

}
