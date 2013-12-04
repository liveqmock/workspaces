
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
 *         &lt;element name="GetReportMoreDetailResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfReportMoreDetail" minOccurs="0"/>
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
    "getReportMoreDetailResult"
})
@XmlRootElement(name = "GetReportMoreDetailResponse")
public class GetReportMoreDetailResponse {

    @XmlElement(name = "GetReportMoreDetailResult")
    protected ArrayOfReportMoreDetail getReportMoreDetailResult;

    /**
     * Gets the value of the getReportMoreDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfReportMoreDetail }
     *     
     */
    public ArrayOfReportMoreDetail getGetReportMoreDetailResult() {
        return getReportMoreDetailResult;
    }

    /**
     * Sets the value of the getReportMoreDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfReportMoreDetail }
     *     
     */
    public void setGetReportMoreDetailResult(ArrayOfReportMoreDetail value) {
        this.getReportMoreDetailResult = value;
    }

}
