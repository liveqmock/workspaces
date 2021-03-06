
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
 *         &lt;element name="AttachmentQueryResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}AttachmentReturnDTO"/>
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
    "attachmentQueryResult"
})
@XmlRootElement(name = "AttachmentQueryResponse")
public class AttachmentQueryResponse {

    @XmlElement(name = "AttachmentQueryResult", required = true)
    protected AttachmentReturnDTO attachmentQueryResult;

    /**
     * Gets the value of the attachmentQueryResult property.
     * 
     * @return
     *     possible object is
     *     {@link AttachmentReturnDTO }
     *     
     */
    public AttachmentReturnDTO getAttachmentQueryResult() {
        return attachmentQueryResult;
    }

    /**
     * Sets the value of the attachmentQueryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttachmentReturnDTO }
     *     
     */
    public void setAttachmentQueryResult(AttachmentReturnDTO value) {
        this.attachmentQueryResult = value;
    }

}
