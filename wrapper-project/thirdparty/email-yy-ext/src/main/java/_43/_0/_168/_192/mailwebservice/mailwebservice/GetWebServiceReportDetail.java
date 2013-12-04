
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="easeyeUserAccountDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeUserAccountDTO"/>
 *         &lt;element name="templateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sentDatetimeFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="sentDatetimeTo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="sentFlag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="currentPageIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "easeyeUserAccountDTO",
    "templateName",
    "sentDatetimeFrom",
    "sentDatetimeTo",
    "sentFlag",
    "pageSize",
    "currentPageIndex"
})
@XmlRootElement(name = "GetWebServiceReportDetail")
public class GetWebServiceReportDetail {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    protected String templateName;
    @XmlElement(required = true)
    protected XMLGregorianCalendar sentDatetimeFrom;
    @XmlElement(required = true)
    protected XMLGregorianCalendar sentDatetimeTo;
    protected int sentFlag;
    protected int pageSize;
    protected int currentPageIndex;

    /**
     * Gets the value of the easeyeUserAccountDTO property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeUserAccountDTO }
     *     
     */
    public EaseyeUserAccountDTO getEaseyeUserAccountDTO() {
        return easeyeUserAccountDTO;
    }

    /**
     * Sets the value of the easeyeUserAccountDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeUserAccountDTO }
     *     
     */
    public void setEaseyeUserAccountDTO(EaseyeUserAccountDTO value) {
        this.easeyeUserAccountDTO = value;
    }

    /**
     * Gets the value of the templateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * Sets the value of the templateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateName(String value) {
        this.templateName = value;
    }

    /**
     * Gets the value of the sentDatetimeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSentDatetimeFrom() {
        return sentDatetimeFrom;
    }

    /**
     * Sets the value of the sentDatetimeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSentDatetimeFrom(XMLGregorianCalendar value) {
        this.sentDatetimeFrom = value;
    }

    /**
     * Gets the value of the sentDatetimeTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSentDatetimeTo() {
        return sentDatetimeTo;
    }

    /**
     * Sets the value of the sentDatetimeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSentDatetimeTo(XMLGregorianCalendar value) {
        this.sentDatetimeTo = value;
    }

    /**
     * Gets the value of the sentFlag property.
     * 
     */
    public int getSentFlag() {
        return sentFlag;
    }

    /**
     * Sets the value of the sentFlag property.
     * 
     */
    public void setSentFlag(int value) {
        this.sentFlag = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

    /**
     * Gets the value of the currentPageIndex property.
     * 
     */
    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    /**
     * Sets the value of the currentPageIndex property.
     * 
     */
    public void setCurrentPageIndex(int value) {
        this.currentPageIndex = value;
    }

}
