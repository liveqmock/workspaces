
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
 *         &lt;element name="AddUserResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeReturnDTO"/>
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
    "addUserResult"
})
@XmlRootElement(name = "AddUserResponse")
public class AddUserResponse {

    @XmlElement(name = "AddUserResult", required = true)
    protected EaseyeReturnDTO addUserResult;

    /**
     * Gets the value of the addUserResult property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeReturnDTO }
     *     
     */
    public EaseyeReturnDTO getAddUserResult() {
        return addUserResult;
    }

    /**
     * Sets the value of the addUserResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeReturnDTO }
     *     
     */
    public void setAddUserResult(EaseyeReturnDTO value) {
        this.addUserResult = value;
    }

}
