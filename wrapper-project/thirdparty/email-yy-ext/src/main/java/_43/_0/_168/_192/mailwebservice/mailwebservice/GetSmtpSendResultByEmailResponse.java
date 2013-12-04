
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
 *         &lt;element name="GetSmtpSendResultByEmailResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}SmtpSendResultReturnDTO"/>
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
    "getSmtpSendResultByEmailResult"
})
@XmlRootElement(name = "GetSmtpSendResultByEmailResponse")
public class GetSmtpSendResultByEmailResponse {

    @XmlElement(name = "GetSmtpSendResultByEmailResult", required = true)
    protected SmtpSendResultReturnDTO getSmtpSendResultByEmailResult;

    /**
     * Gets the value of the getSmtpSendResultByEmailResult property.
     * 
     * @return
     *     possible object is
     *     {@link SmtpSendResultReturnDTO }
     *     
     */
    public SmtpSendResultReturnDTO getGetSmtpSendResultByEmailResult() {
        return getSmtpSendResultByEmailResult;
    }

    /**
     * Sets the value of the getSmtpSendResultByEmailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmtpSendResultReturnDTO }
     *     
     */
    public void setGetSmtpSendResultByEmailResult(SmtpSendResultReturnDTO value) {
        this.getSmtpSendResultByEmailResult = value;
    }

}
