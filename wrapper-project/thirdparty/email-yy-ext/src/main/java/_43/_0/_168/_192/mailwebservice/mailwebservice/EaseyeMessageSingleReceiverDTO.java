
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EaseyeMessageSingleReceiverDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EaseyeMessageSingleReceiverDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReceiverEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FieldsValue" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EaseyeMessageSingleReceiverDTO", propOrder = {
    "receiverEmail",
    "fieldsValue"
})
public class EaseyeMessageSingleReceiverDTO {

    @XmlElement(name = "ReceiverEmail")
    protected String receiverEmail;
    @XmlElement(name = "FieldsValue")
    protected ArrayOfString fieldsValue;

    /**
     * Gets the value of the receiverEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverEmail() {
        return receiverEmail;
    }

    /**
     * Sets the value of the receiverEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverEmail(String value) {
        this.receiverEmail = value;
    }

    /**
     * Gets the value of the fieldsValue property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getFieldsValue() {
        return fieldsValue;
    }

    /**
     * Sets the value of the fieldsValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setFieldsValue(ArrayOfString value) {
        this.fieldsValue = value;
    }

}
