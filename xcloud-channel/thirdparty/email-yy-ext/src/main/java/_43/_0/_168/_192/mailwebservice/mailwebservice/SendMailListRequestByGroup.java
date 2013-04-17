
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
 *         &lt;element name="easeyeMessageTemplateDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeMessageTemplateDTO"/>
 *         &lt;element name="easeyeGroupDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeGroupDTO"/>
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
    "easeyeMessageTemplateDTO",
    "easeyeGroupDTO",
    "easeyeSendOptionDTO"
})
@XmlRootElement(name = "SendMailListRequestByGroup")
public class SendMailListRequestByGroup {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    @XmlElement(required = true)
    protected EaseyeMessageTemplateDTO easeyeMessageTemplateDTO;
    @XmlElement(required = true)
    protected EaseyeGroupDTO easeyeGroupDTO;
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
     * Gets the value of the easeyeMessageTemplateDTO property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeMessageTemplateDTO }
     *     
     */
    public EaseyeMessageTemplateDTO getEaseyeMessageTemplateDTO() {
        return easeyeMessageTemplateDTO;
    }

    /**
     * Sets the value of the easeyeMessageTemplateDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeMessageTemplateDTO }
     *     
     */
    public void setEaseyeMessageTemplateDTO(EaseyeMessageTemplateDTO value) {
        this.easeyeMessageTemplateDTO = value;
    }

    /**
     * Gets the value of the easeyeGroupDTO property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeGroupDTO }
     *     
     */
    public EaseyeGroupDTO getEaseyeGroupDTO() {
        return easeyeGroupDTO;
    }

    /**
     * Sets the value of the easeyeGroupDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeGroupDTO }
     *     
     */
    public void setEaseyeGroupDTO(EaseyeGroupDTO value) {
        this.easeyeGroupDTO = value;
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
