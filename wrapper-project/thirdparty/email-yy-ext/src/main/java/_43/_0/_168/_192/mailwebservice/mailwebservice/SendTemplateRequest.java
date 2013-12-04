
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SendTemplateRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SendTemplateRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mailUser" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}MailUser"/>
 *         &lt;element name="FromEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TemplateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TemplateBody" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReplyTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsAddOptOutTag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsBodyHtml" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CustomDomain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrackOpen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="singleRecipient" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}SingleRecipient" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendTemplateRequest", propOrder = {
    "mailUser",
    "fromEmail",
    "fromName",
    "templateName",
    "subject",
    "templateBody",
    "replyTo",
    "isAddOptOutTag",
    "isBodyHtml",
    "customDomain",
    "trackOpen",
    "singleRecipient"
})
public class SendTemplateRequest {

    @XmlElement(required = true)
    protected MailUser mailUser;
    @XmlElement(name = "FromEmail")
    protected String fromEmail;
    @XmlElement(name = "FromName")
    protected String fromName;
    @XmlElement(name = "TemplateName")
    protected String templateName;
    @XmlElement(name = "Subject")
    protected String subject;
    @XmlElement(name = "TemplateBody")
    protected String templateBody;
    @XmlElement(name = "ReplyTo")
    protected String replyTo;
    @XmlElement(name = "IsAddOptOutTag")
    protected boolean isAddOptOutTag;
    @XmlElement(name = "IsBodyHtml")
    protected boolean isBodyHtml;
    @XmlElement(name = "CustomDomain")
    protected String customDomain;
    @XmlElement(name = "TrackOpen")
    protected String trackOpen;
    protected SingleRecipient singleRecipient;

    /**
     * Gets the value of the mailUser property.
     * 
     * @return
     *     possible object is
     *     {@link MailUser }
     *     
     */
    public MailUser getMailUser() {
        return mailUser;
    }

    /**
     * Sets the value of the mailUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link MailUser }
     *     
     */
    public void setMailUser(MailUser value) {
        this.mailUser = value;
    }

    /**
     * Gets the value of the fromEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromEmail() {
        return fromEmail;
    }

    /**
     * Sets the value of the fromEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromEmail(String value) {
        this.fromEmail = value;
    }

    /**
     * Gets the value of the fromName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * Sets the value of the fromName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromName(String value) {
        this.fromName = value;
    }

    /**
     * Gets the value of the templateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * Sets the value of the templateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateName(String value) {
        this.templateName = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the templateBody property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateBody() {
        return templateBody;
    }

    /**
     * Sets the value of the templateBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateBody(String value) {
        this.templateBody = value;
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
     * Gets the value of the isAddOptOutTag property.
     * 
     */
    public boolean isIsAddOptOutTag() {
        return isAddOptOutTag;
    }

    /**
     * Sets the value of the isAddOptOutTag property.
     * 
     */
    public void setIsAddOptOutTag(boolean value) {
        this.isAddOptOutTag = value;
    }

    /**
     * Gets the value of the isBodyHtml property.
     * 
     */
    public boolean isIsBodyHtml() {
        return isBodyHtml;
    }

    /**
     * Sets the value of the isBodyHtml property.
     * 
     */
    public void setIsBodyHtml(boolean value) {
        this.isBodyHtml = value;
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

    /**
     * Gets the value of the singleRecipient property.
     * 
     * @return
     *     possible object is
     *     {@link SingleRecipient }
     *     
     */
    public SingleRecipient getSingleRecipient() {
        return singleRecipient;
    }

    /**
     * Sets the value of the singleRecipient property.
     * 
     * @param value
     *     allowed object is
     *     {@link SingleRecipient }
     *     
     */
    public void setSingleRecipient(SingleRecipient value) {
        this.singleRecipient = value;
    }

}
