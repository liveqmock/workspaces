
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EaseyeMessageTemplateDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EaseyeMessageTemplateDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TemplateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Body" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HasAD" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="HasSysBody" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AttachmentUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrackLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsBodyHtml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AttachmentGUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EaseyeMessageTemplateDTO", propOrder = {
    "templateName",
    "subject",
    "body",
    "hasAD",
    "hasSysBody",
    "attachmentUrl",
    "trackLink",
    "isBodyHtml",
    "attachmentGUID"
})
public class EaseyeMessageTemplateDTO {

    @XmlElement(name = "TemplateName")
    protected String templateName;
    @XmlElement(name = "Subject")
    protected String subject;
    @XmlElement(name = "Body")
    protected String body;
    @XmlElement(name = "HasAD")
    protected boolean hasAD;
    @XmlElement(name = "HasSysBody")
    protected boolean hasSysBody;
    @XmlElement(name = "AttachmentUrl")
    protected String attachmentUrl;
    @XmlElement(name = "TrackLink")
    protected String trackLink;
    @XmlElement(name = "IsBodyHtml")
    protected String isBodyHtml;
    @XmlElement(name = "AttachmentGUID")
    protected String attachmentGUID;

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
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBody(String value) {
        this.body = value;
    }

    /**
     * Gets the value of the hasAD property.
     * 
     */
    public boolean isHasAD() {
        return hasAD;
    }

    /**
     * Sets the value of the hasAD property.
     * 
     */
    public void setHasAD(boolean value) {
        this.hasAD = value;
    }

    /**
     * Gets the value of the hasSysBody property.
     * 
     */
    public boolean isHasSysBody() {
        return hasSysBody;
    }

    /**
     * Sets the value of the hasSysBody property.
     * 
     */
    public void setHasSysBody(boolean value) {
        this.hasSysBody = value;
    }

    /**
     * Gets the value of the attachmentUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    /**
     * Sets the value of the attachmentUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachmentUrl(String value) {
        this.attachmentUrl = value;
    }

    /**
     * Gets the value of the trackLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackLink() {
        return trackLink;
    }

    /**
     * Sets the value of the trackLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackLink(String value) {
        this.trackLink = value;
    }

    /**
     * Gets the value of the isBodyHtml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsBodyHtml() {
        return isBodyHtml;
    }

    /**
     * Sets the value of the isBodyHtml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsBodyHtml(String value) {
        this.isBodyHtml = value;
    }

    /**
     * Gets the value of the attachmentGUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachmentGUID() {
        return attachmentGUID;
    }

    /**
     * Sets the value of the attachmentGUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachmentGUID(String value) {
        this.attachmentGUID = value;
    }

}
