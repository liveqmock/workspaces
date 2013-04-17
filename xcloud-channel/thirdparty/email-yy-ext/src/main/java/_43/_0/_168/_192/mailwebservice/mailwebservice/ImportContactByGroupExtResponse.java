
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
 *         &lt;element name="ImportContactByGroupExtResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeDetailedResultReturnDTO"/>
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
    "importContactByGroupExtResult"
})
@XmlRootElement(name = "ImportContactByGroupExtResponse")
public class ImportContactByGroupExtResponse {

    @XmlElement(name = "ImportContactByGroupExtResult", required = true)
    protected EaseyeDetailedResultReturnDTO importContactByGroupExtResult;

    /**
     * Gets the value of the importContactByGroupExtResult property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeDetailedResultReturnDTO }
     *     
     */
    public EaseyeDetailedResultReturnDTO getImportContactByGroupExtResult() {
        return importContactByGroupExtResult;
    }

    /**
     * Sets the value of the importContactByGroupExtResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeDetailedResultReturnDTO }
     *     
     */
    public void setImportContactByGroupExtResult(EaseyeDetailedResultReturnDTO value) {
        this.importContactByGroupExtResult = value;
    }

}
