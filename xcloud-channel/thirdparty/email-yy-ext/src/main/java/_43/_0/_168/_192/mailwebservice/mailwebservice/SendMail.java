
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
 *         &lt;element name="easeyeMessageReceiveDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeMessageReceiveDTO"/>
 *         &lt;element name="templateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="easeyeSendOptionDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeSendOptionDTO"/>
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
    "easeyeMessageReceiveDTO",
    "templateName",
    "easeyeSendOptionDTO"
})
@XmlRootElement(name = "SendMail")
public class SendMail {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    @XmlElement(required = true)
    protected EaseyeMessageReceiveDTO easeyeMessageReceiveDTO;
    protected String templateName;
    @XmlElement(required = true)
    protected EaseyeSendOptionDTO easeyeSendOptionDTO;

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
     * Gets the value of the easeyeMessageReceiveDTO property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeMessageReceiveDTO }
     *     
     */
    public EaseyeMessageReceiveDTO getEaseyeMessageReceiveDTO() {
        return easeyeMessageReceiveDTO;
    }

    /**
     * Sets the value of the easeyeMessageReceiveDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeMessageReceiveDTO }
     *     
     */
    public void setEaseyeMessageReceiveDTO(EaseyeMessageReceiveDTO value) {
        this.easeyeMessageReceiveDTO = value;
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
     * Gets the value of the easeyeSendOptionDTO property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeSendOptionDTO }
     *     
     */
    public EaseyeSendOptionDTO getEaseyeSendOptionDTO() {
        return easeyeSendOptionDTO;
    }

    /**
     * Sets the value of the easeyeSendOptionDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeSendOptionDTO }
     *     
     */
    public void setEaseyeSendOptionDTO(EaseyeSendOptionDTO value) {
        this.easeyeSendOptionDTO = value;
    }

}
