
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
 *         &lt;element name="easeyeUserAccountDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeUserAccountDTO"/>
 *         &lt;element name="attachmentContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attachmentName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uploadMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "easeyeUserAccountDTO",
    "attachmentContent",
    "attachmentName",
    "uploadMode"
})
@XmlRootElement(name = "UploadAttachment")
public class UploadAttachment {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    protected String attachmentContent;
    protected String attachmentName;
    protected String uploadMode;

    /**
     * Gets the value of the easeyeUserAccountDTO property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeUserAccountDTO }
     *     
     */
    public EaseyeUserAccountDTO getEaseyeUserAccountDTO() {
        return easeyeUserAccountDTO;
    }

    /**
     * Sets the value of the easeyeUserAccountDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeUserAccountDTO }
     *     
     */
    public void setEaseyeUserAccountDTO(EaseyeUserAccountDTO value) {
        this.easeyeUserAccountDTO = value;
    }

    /**
     * Gets the value of the attachmentContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachmentContent() {
        return attachmentContent;
    }

    /**
     * Sets the value of the attachmentContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachmentContent(String value) {
        this.attachmentContent = value;
    }

    /**
     * Gets the value of the attachmentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * Sets the value of the attachmentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachmentName(String value) {
        this.attachmentName = value;
    }

    /**
     * Gets the value of the uploadMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadMode() {
        return uploadMode;
    }

    /**
     * Sets the value of the uploadMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadMode(String value) {
        this.uploadMode = value;
    }

}
