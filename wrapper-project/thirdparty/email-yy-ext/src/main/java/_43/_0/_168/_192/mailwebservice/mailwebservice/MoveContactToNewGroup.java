
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
 *         &lt;element name="newGroupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="easeyeMessageReceiveDTOArray" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfEaseyeMessageReceiveDTO" minOccurs="0"/>
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
    "newGroupName",
    "easeyeMessageReceiveDTOArray"
})
@XmlRootElement(name = "MoveContactToNewGroup")
public class MoveContactToNewGroup {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    protected String newGroupName;
    protected ArrayOfEaseyeMessageReceiveDTO easeyeMessageReceiveDTOArray;

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
     * Gets the value of the newGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewGroupName() {
        return newGroupName;
    }

    /**
     * Sets the value of the newGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewGroupName(String value) {
        this.newGroupName = value;
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

}
