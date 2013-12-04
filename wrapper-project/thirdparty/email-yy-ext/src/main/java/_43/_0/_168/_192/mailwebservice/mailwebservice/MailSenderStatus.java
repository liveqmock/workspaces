
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MailSenderStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MailSenderStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SenderEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IfOwnedByPlatform" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IfActive" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MailSenderStatus", propOrder = {
    "senderEmail",
    "ifOwnedByPlatform",
    "ifActive"
})
public class MailSenderStatus {

    @XmlElement(name = "SenderEmail")
    protected String senderEmail;
    @XmlElement(name = "IfOwnedByPlatform")
    protected int ifOwnedByPlatform;
    @XmlElement(name = "IfActive")
    protected int ifActive;

    /**
     * Gets the value of the senderEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderEmail() {
        return senderEmail;
    }

    /**
     * Sets the value of the senderEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderEmail(String value) {
        this.senderEmail = value;
    }

    /**
     * Gets the value of the ifOwnedByPlatform property.
     * 
     */
    public int getIfOwnedByPlatform() {
        return ifOwnedByPlatform;
    }

    /**
     * Sets the value of the ifOwnedByPlatform property.
     * 
     */
    public void setIfOwnedByPlatform(int value) {
        this.ifOwnedByPlatform = value;
    }

    /**
     * Gets the value of the ifActive property.
     * 
     */
    public int getIfActive() {
        return ifActive;
    }

    /**
     * Sets the value of the ifActive property.
     * 
     */
    public void setIfActive(int value) {
        this.ifActive = value;
    }

}
