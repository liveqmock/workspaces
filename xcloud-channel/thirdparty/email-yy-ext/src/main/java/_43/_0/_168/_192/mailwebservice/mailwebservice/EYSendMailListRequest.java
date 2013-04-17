
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
 *         &lt;element name="ey_MessageReceiveDTOArray" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfEY_MessageReceiveDTO" minOccurs="0"/>
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
    "eyMessageReceiveDTOArray",
    "easeyeSendOptionDTO"
})
@XmlRootElement(name = "EY_SendMailListRequest")
public class EYSendMailListRequest {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    @XmlElement(required = true)
    protected EaseyeMessageTemplateDTO easeyeMessageTemplateDTO;
    @XmlElement(name = "ey_MessageReceiveDTOArray")
    protected ArrayOfEYMessageReceiveDTO eyMessageReceiveDTOArray;
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
     * Gets the value of the eyMessageReceiveDTOArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEYMessageReceiveDTO }
     *     
     */
    public ArrayOfEYMessageReceiveDTO getEyMessageReceiveDTOArray() {
        return eyMessageReceiveDTOArray;
    }

    /**
     * Sets the value of the eyMessageReceiveDTOArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEYMessageReceiveDTO }
     *     
     */
    public void setEyMessageReceiveDTOArray(ArrayOfEYMessageReceiveDTO value) {
        this.eyMessageReceiveDTOArray = value;
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
