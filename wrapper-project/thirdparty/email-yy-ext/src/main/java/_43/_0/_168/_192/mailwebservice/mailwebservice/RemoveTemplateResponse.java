
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
 *         &lt;element name="RemoveTemplateResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeReturnDTO"/>
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
    "removeTemplateResult"
})
@XmlRootElement(name = "RemoveTemplateResponse")
public class RemoveTemplateResponse {

    @XmlElement(name = "RemoveTemplateResult", required = true)
    protected EaseyeReturnDTO removeTemplateResult;

    /**
     * Gets the value of the removeTemplateResult property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeReturnDTO }
     *     
     */
    public EaseyeReturnDTO getRemoveTemplateResult() {
        return removeTemplateResult;
    }

    /**
     * Sets the value of the removeTemplateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeReturnDTO }
     *     
     */
    public void setRemoveTemplateResult(EaseyeReturnDTO value) {
        this.removeTemplateResult = value;
    }

}
