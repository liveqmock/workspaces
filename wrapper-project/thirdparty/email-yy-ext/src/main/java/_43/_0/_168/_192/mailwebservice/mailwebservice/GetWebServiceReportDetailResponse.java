
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
 *         &lt;element name="GetWebServiceReportDetailResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}WebServiceReportDetailReturnDTO" minOccurs="0"/>
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
    "getWebServiceReportDetailResult"
})
@XmlRootElement(name = "GetWebServiceReportDetailResponse")
public class GetWebServiceReportDetailResponse {

    @XmlElement(name = "GetWebServiceReportDetailResult")
    protected WebServiceReportDetailReturnDTO getWebServiceReportDetailResult;

    /**
     * Gets the value of the getWebServiceReportDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link WebServiceReportDetailReturnDTO }
     *     
     */
    public WebServiceReportDetailReturnDTO getGetWebServiceReportDetailResult() {
        return getWebServiceReportDetailResult;
    }

    /**
     * Sets the value of the getWebServiceReportDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebServiceReportDetailReturnDTO }
     *     
     */
    public void setGetWebServiceReportDetailResult(WebServiceReportDetailReturnDTO value) {
        this.getWebServiceReportDetailResult = value;
    }

}
