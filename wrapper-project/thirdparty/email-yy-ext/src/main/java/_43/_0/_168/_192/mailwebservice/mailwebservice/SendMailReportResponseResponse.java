
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
 *         &lt;element name="SendMailReportResponseResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}MailStatusReportDTO"/>
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
    "sendMailReportResponseResult"
})
@XmlRootElement(name = "SendMailReportResponseResponse")
public class SendMailReportResponseResponse {

    @XmlElement(name = "SendMailReportResponseResult", required = true)
    protected MailStatusReportDTO sendMailReportResponseResult;

    /**
     * Gets the value of the sendMailReportResponseResult property.
     * 
     * @return
     *     possible object is
     *     {@link MailStatusReportDTO }
     *     
     */
    public MailStatusReportDTO getSendMailReportResponseResult() {
        return sendMailReportResponseResult;
    }

    /**
     * Sets the value of the sendMailReportResponseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link MailStatusReportDTO }
     *     
     */
    public void setSendMailReportResponseResult(MailStatusReportDTO value) {
        this.sendMailReportResponseResult = value;
    }

}
