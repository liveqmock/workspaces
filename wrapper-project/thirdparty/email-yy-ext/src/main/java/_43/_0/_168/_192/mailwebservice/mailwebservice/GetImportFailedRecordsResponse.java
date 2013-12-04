
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
 *         &lt;element name="GetImportFailedRecordsResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeDetailedResultReturnDTO"/>
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
    "getImportFailedRecordsResult"
})
@XmlRootElement(name = "GetImportFailedRecordsResponse")
public class GetImportFailedRecordsResponse {

    @XmlElement(name = "GetImportFailedRecordsResult", required = true)
    protected EaseyeDetailedResultReturnDTO getImportFailedRecordsResult;

    /**
     * Gets the value of the getImportFailedRecordsResult property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeDetailedResultReturnDTO }
     *     
     */
    public EaseyeDetailedResultReturnDTO getGetImportFailedRecordsResult() {
        return getImportFailedRecordsResult;
    }

    /**
     * Sets the value of the getImportFailedRecordsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeDetailedResultReturnDTO }
     *     
     */
    public void setGetImportFailedRecordsResult(EaseyeDetailedResultReturnDTO value) {
        this.getImportFailedRecordsResult = value;
    }

}
