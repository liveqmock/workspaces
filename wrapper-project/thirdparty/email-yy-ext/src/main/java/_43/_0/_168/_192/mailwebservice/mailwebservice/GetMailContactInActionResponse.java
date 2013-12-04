
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
 *         &lt;element name="GetMailContactInActionResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}MailContactInActionReturnDTO"/>
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
    "getMailContactInActionResult"
})
@XmlRootElement(name = "GetMailContactInActionResponse")
public class GetMailContactInActionResponse {

    @XmlElement(name = "GetMailContactInActionResult", required = true)
    protected MailContactInActionReturnDTO getMailContactInActionResult;

    /**
     * Gets the value of the getMailContactInActionResult property.
     * 
     * @return
     *     possible object is
     *     {@link MailContactInActionReturnDTO }
     *     
     */
    public MailContactInActionReturnDTO getGetMailContactInActionResult() {
        return getMailContactInActionResult;
    }

    /**
     * Sets the value of the getMailContactInActionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link MailContactInActionReturnDTO }
     *     
     */
    public void setGetMailContactInActionResult(MailContactInActionReturnDTO value) {
        this.getMailContactInActionResult = value;
    }

}
