
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
 *         &lt;element name="MoveContactToNewGroupResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeReturnDTO"/>
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
    "moveContactToNewGroupResult"
})
@XmlRootElement(name = "MoveContactToNewGroupResponse")
public class MoveContactToNewGroupResponse {

    @XmlElement(name = "MoveContactToNewGroupResult", required = true)
    protected EaseyeReturnDTO moveContactToNewGroupResult;

    /**
     * Gets the value of the moveContactToNewGroupResult property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeReturnDTO }
     *     
     */
    public EaseyeReturnDTO getMoveContactToNewGroupResult() {
        return moveContactToNewGroupResult;
    }

    /**
     * Sets the value of the moveContactToNewGroupResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeReturnDTO }
     *     
     */
    public void setMoveContactToNewGroupResult(EaseyeReturnDTO value) {
        this.moveContactToNewGroupResult = value;
    }

}
