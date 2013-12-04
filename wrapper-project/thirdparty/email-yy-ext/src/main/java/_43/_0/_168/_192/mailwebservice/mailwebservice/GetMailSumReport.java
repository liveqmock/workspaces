
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
 *         &lt;element name="projectName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maillistName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sentDatetimeFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="sentDatetimeTo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
    "projectName",
    "maillistName",
    "sentDatetimeFrom",
    "sentDatetimeTo"
})
@XmlRootElement(name = "GetMailSumReport")
public class GetMailSumReport {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    protected String projectName;
    protected String maillistName;
    @XmlElement(required = true)
    protected XMLGregorianCalendar sentDatetimeFrom;
    @XmlElement(required = true)
    protected XMLGregorianCalendar sentDatetimeTo;

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
     * Gets the value of the projectName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the value of the projectName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectName(String value) {
        this.projectName = value;
    }

    /**
     * Gets the value of the maillistName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaillistName() {
        return maillistName;
    }

    /**
     * Sets the value of the maillistName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaillistName(String value) {
        this.maillistName = value;
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

}
