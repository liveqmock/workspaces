
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EaseyeSendOptionDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EaseyeSendOptionDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MailProjectName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MailListName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SenderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SenderEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReplyTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsActive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ScheduleSendTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsSendOnce" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomDomain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrackOpen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EaseyeSendOptionDTO", propOrder = {
    "mailProjectName",
    "mailListName",
    "senderName",
    "senderEmail",
    "replyTo",
    "isActive",
    "scheduleSendTime",
    "isSendOnce",
    "customDomain",
    "trackOpen"
})
public class EaseyeSendOptionDTO {

    @XmlElement(name = "MailProjectName")
    protected String mailProjectName;
    @XmlElement(name = "MailListName")
    protected String mailListName;
    @XmlElement(name = "SenderName")
    protected String senderName;
    @XmlElement(name = "SenderEmail")
    protected String senderEmail;
    @XmlElement(name = "ReplyTo")
    protected String replyTo;
    @XmlElement(name = "IsActive")
    protected boolean isActive;
    @XmlElement(name = "ScheduleSendTime")
    protected String scheduleSendTime;
    @XmlElement(name = "IsSendOnce")
    protected String isSendOnce;
    @XmlElement(name = "CustomDomain")
    protected String customDomain;
    @XmlElement(name = "TrackOpen")
    protected String trackOpen;

    /**
     * Gets the value of the mailProjectName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailProjectName() {
        return mailProjectName;
    }

    /**
     * Sets the value of the mailProjectName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailProjectName(String value) {
        this.mailProjectName = value;
    }

    /**
     * Gets the value of the mailListName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailListName() {
        return mailListName;
    }

    /**
     * Sets the value of the mailListName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailListName(String value) {
        this.mailListName = value;
    }

    /**
     * Gets the value of the senderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Sets the value of the senderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderName(String value) {
        this.senderName = value;
    }

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
     * Gets the value of the replyTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplyTo() {
        return replyTo;
    }

    /**
     * Sets the value of the replyTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplyTo(String value) {
        this.replyTo = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     */
    public void setIsActive(boolean value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the scheduleSendTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduleSendTime() {
        return scheduleSendTime;
    }

    /**
     * Sets the value of the scheduleSendTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduleSendTime(String value) {
        this.scheduleSendTime = value;
    }

    /**
     * Gets the value of the isSendOnce property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSendOnce() {
        return isSendOnce;
    }

    /**
     * Sets the value of the isSendOnce property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSendOnce(String value) {
        this.isSendOnce = value;
    }

    /**
     * Gets the value of the customDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomDomain() {
        return customDomain;
    }

    /**
     * Sets the value of the customDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomDomain(String value) {
        this.customDomain = value;
    }

    /**
     * Gets the value of the trackOpen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackOpen() {
        return trackOpen;
    }

    /**
     * Sets the value of the trackOpen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackOpen(String value) {
        this.trackOpen = value;
    }

}
