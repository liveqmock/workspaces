
package https.api_baidu_com.sem.sms.v3;

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
 *         &lt;element name="reportRequestType" type="{https://api.baidu.com/sem/sms/v3}ReportRequestType"/>
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
    "reportRequestType"
})
@XmlRootElement(name = "getProfessionalReportIdRequest")
public class GetProfessionalReportIdRequest {

    @XmlElement(required = true)
    protected ReportRequestType reportRequestType;

    /**
     * Gets the value of the reportRequestType property.
     * 
     * @return
     *     possible object is
     *     {@link ReportRequestType }
     *     
     */
    public ReportRequestType getReportRequestType() {
        return reportRequestType;
    }

    /**
     * Sets the value of the reportRequestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportRequestType }
     *     
     */
    public void setReportRequestType(ReportRequestType value) {
        this.reportRequestType = value;
    }

}
