
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
 *         &lt;element name="GetMailSumReportResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}MailSumReportReturnDTO"/>
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
    "getMailSumReportResult"
})
@XmlRootElement(name = "GetMailSumReportResponse")
public class GetMailSumReportResponse {

    @XmlElement(name = "GetMailSumReportResult", required = true)
    protected MailSumReportReturnDTO getMailSumReportResult;

    /**
     * Gets the value of the getMailSumReportResult property.
     * 
     * @return
     *     possible object is
     *     {@link MailSumReportReturnDTO }
     *     
     */
    public MailSumReportReturnDTO getGetMailSumReportResult() {
        return getMailSumReportResult;
    }

    /**
     * Sets the value of the getMailSumReportResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link MailSumReportReturnDTO }
     *     
     */
    public void setGetMailSumReportResult(MailSumReportReturnDTO value) {
        this.getMailSumReportResult = value;
    }

}
