
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
 *         &lt;element name="UploadAttachmentResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}AttachmentReturnDTO"/>
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
    "uploadAttachmentResult"
})
@XmlRootElement(name = "UploadAttachmentResponse")
public class UploadAttachmentResponse {

    @XmlElement(name = "UploadAttachmentResult", required = true)
    protected AttachmentReturnDTO uploadAttachmentResult;

    /**
     * Gets the value of the uploadAttachmentResult property.
     * 
     * @return
     *     possible object is
     *     {@link AttachmentReturnDTO }
     *     
     */
    public AttachmentReturnDTO getUploadAttachmentResult() {
        return uploadAttachmentResult;
    }

    /**
     * Sets the value of the uploadAttachmentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttachmentReturnDTO }
     *     
     */
    public void setUploadAttachmentResult(AttachmentReturnDTO value) {
        this.uploadAttachmentResult = value;
    }

}
