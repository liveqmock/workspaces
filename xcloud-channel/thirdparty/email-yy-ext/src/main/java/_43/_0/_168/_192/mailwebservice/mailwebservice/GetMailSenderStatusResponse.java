
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
 *         &lt;element name="GetMailSenderStatusResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeMailSenderStatusReturnDTO"/>
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
    "getMailSenderStatusResult"
})
@XmlRootElement(name = "GetMailSenderStatusResponse")
public class GetMailSenderStatusResponse {

    @XmlElement(name = "GetMailSenderStatusResult", required = true)
    protected EaseyeMailSenderStatusReturnDTO getMailSenderStatusResult;

    /**
     * Gets the value of the getMailSenderStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeMailSenderStatusReturnDTO }
     *     
     */
    public EaseyeMailSenderStatusReturnDTO getGetMailSenderStatusResult() {
        return getMailSenderStatusResult;
    }

    /**
     * Sets the value of the getMailSenderStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeMailSenderStatusReturnDTO }
     *     
     */
    public void setGetMailSenderStatusResult(EaseyeMailSenderStatusReturnDTO value) {
        this.getMailSenderStatusResult = value;
    }

}
