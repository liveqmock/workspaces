
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
 *         &lt;element name="easeyeMessageReceiveDTOArray" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfEaseyeMessageReceiveDTO" minOccurs="0"/>
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
    "easeyeMessageReceiveDTOArray",
    "easeyeSendOptionDTO"
})
@XmlRootElement(name = "SendMailListRequest")
public class SendMailListRequest {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    @XmlElement(required = true)
    protected EaseyeMessageTemplateDTO easeyeMessageTemplateDTO;
    protected ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray;
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
     * Gets the value of the easeyeMessageReceiveDTOArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEaseyeMessageReceiveDTO }
     *     
     */
    public ArrayOfEaseyeMessageReceiveDTO getEaseyeMessageReceiveDTOArray() {
        return easeyeMessageReceiveDTOArray;
    }

    /**
     * Sets the value of the easeyeMessageReceiveDTOArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEaseyeMessageReceiveDTO }
     *     
     */
    public void setEaseyeMessageReceiveDTOArray(ArrayOfEaseyeMessageReceiveDTO value) {
        this.easeyeMessageReceiveDTOArray = value;
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
