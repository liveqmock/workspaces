
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
 *         &lt;element name="GetMailReportDetailResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}MailReportDetailReturnDTO"/>
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
    "getMailReportDetailResult"
})
@XmlRootElement(name = "GetMailReportDetailResponse")
public class GetMailReportDetailResponse {

    @XmlElement(name = "GetMailReportDetailResult", required = true)
    protected MailReportDetailReturnDTO getMailReportDetailResult;

    /**
     * Gets the value of the getMailReportDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link MailReportDetailReturnDTO }
     *     
     */
    public MailReportDetailReturnDTO getGetMailReportDetailResult() {
        return getMailReportDetailResult;
    }

    /**
     * Sets the value of the getMailReportDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link MailReportDetailReturnDTO }
     *     
     */
    public void setGetMailReportDetailResult(MailReportDetailReturnDTO value) {
        this.getMailReportDetailResult = value;
    }

}
