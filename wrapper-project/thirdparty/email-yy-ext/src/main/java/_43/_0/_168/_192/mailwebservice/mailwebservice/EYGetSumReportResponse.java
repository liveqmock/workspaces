
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
 *         &lt;element name="EY_GetSumReportResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfEY_MailStatusReportDTO" minOccurs="0"/>
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
    "eyGetSumReportResult"
})
@XmlRootElement(name = "EY_GetSumReportResponse")
public class EYGetSumReportResponse {

    @XmlElement(name = "EY_GetSumReportResult")
    protected ArrayOfEYMailStatusReportDTO eyGetSumReportResult;

    /**
     * Gets the value of the eyGetSumReportResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEYMailStatusReportDTO }
     *     
     */
    public ArrayOfEYMailStatusReportDTO getEYGetSumReportResult() {
        return eyGetSumReportResult;
    }

    /**
     * Sets the value of the eyGetSumReportResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEYMailStatusReportDTO }
     *     
     */
    public void setEYGetSumReportResult(ArrayOfEYMailStatusReportDTO value) {
        this.eyGetSumReportResult = value;
    }

}
