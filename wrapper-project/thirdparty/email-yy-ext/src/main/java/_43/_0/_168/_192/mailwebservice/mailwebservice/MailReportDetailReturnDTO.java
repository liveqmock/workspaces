
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MailReportDetailReturnDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MailReportDetailReturnDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ErrorInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MailReportDetailDTOList" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfReportDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MailReportDetailReturnDTO", propOrder = {
    "errorCode",
    "errorInfo",
    "mailReportDetailDTOList"
})
public class MailReportDetailReturnDTO {

    @XmlElement(name = "ErrorCode")
    protected int errorCode;
    @XmlElement(name = "ErrorInfo")
    protected String errorInfo;
    @XmlElement(name = "MailReportDetailDTOList")
    protected ArrayOfReportDetail mailReportDetailDTOList;

    /**
     * Gets the value of the errorCode property.
     * 
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     */
    public void setErrorCode(int value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * Sets the value of the errorInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorInfo(String value) {
        this.errorInfo = value;
    }

    /**
     * Gets the value of the mailReportDetailDTOList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfReportDetail }
     *     
     */
    public ArrayOfReportDetail getMailReportDetailDTOList() {
        return mailReportDetailDTOList;
    }

    /**
     * Sets the value of the mailReportDetailDTOList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfReportDetail }
     *     
     */
    public void setMailReportDetailDTOList(ArrayOfReportDetail value) {
        this.mailReportDetailDTOList = value;
    }

}
