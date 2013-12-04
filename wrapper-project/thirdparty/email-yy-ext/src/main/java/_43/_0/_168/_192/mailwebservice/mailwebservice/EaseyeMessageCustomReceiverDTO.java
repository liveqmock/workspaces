
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EaseyeMessageCustomReceiverDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EaseyeMessageCustomReceiverDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FieldsName" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="CustomReceivers" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfEaseyeMessageSingleReceiverDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EaseyeMessageCustomReceiverDTO", propOrder = {
    "fieldsName",
    "customReceivers"
})
public class EaseyeMessageCustomReceiverDTO {

    @XmlElement(name = "FieldsName")
    protected ArrayOfString fieldsName;
    @XmlElement(name = "CustomReceivers")
    protected ArrayOfEaseyeMessageSingleReceiverDTO customReceivers;

    /**
     * Gets the value of the fieldsName property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getFieldsName() {
        return fieldsName;
    }

    /**
     * Sets the value of the fieldsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setFieldsName(ArrayOfString value) {
        this.fieldsName = value;
    }

    /**
     * Gets the value of the customReceivers property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEaseyeMessageSingleReceiverDTO }
     *     
     */
    public ArrayOfEaseyeMessageSingleReceiverDTO getCustomReceivers() {
        return customReceivers;
    }

    /**
     * Sets the value of the customReceivers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEaseyeMessageSingleReceiverDTO }
     *     
     */
    public void setCustomReceivers(ArrayOfEaseyeMessageSingleReceiverDTO value) {
        this.customReceivers = value;
    }

}
