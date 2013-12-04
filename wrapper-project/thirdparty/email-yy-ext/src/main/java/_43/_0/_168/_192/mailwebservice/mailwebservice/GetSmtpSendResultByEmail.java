
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
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dtBeginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dtEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
    "email",
    "dtBeginTime",
    "dtEndTime"
})
@XmlRootElement(name = "GetSmtpSendResultByEmail")
public class GetSmtpSendResultByEmail {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    protected String email;
    @XmlElement(required = true)
    protected XMLGregorianCalendar dtBeginTime;
    @XmlElement(required = true)
    protected XMLGregorianCalendar dtEndTime;

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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the dtBeginTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDtBeginTime() {
        return dtBeginTime;
    }

    /**
     * Sets the value of the dtBeginTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDtBeginTime(XMLGregorianCalendar value) {
        this.dtBeginTime = value;
    }

    /**
     * Gets the value of the dtEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDtEndTime() {
        return dtEndTime;
    }

    /**
     * Sets the value of the dtEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDtEndTime(XMLGregorianCalendar value) {
        this.dtEndTime = value;
    }

}
