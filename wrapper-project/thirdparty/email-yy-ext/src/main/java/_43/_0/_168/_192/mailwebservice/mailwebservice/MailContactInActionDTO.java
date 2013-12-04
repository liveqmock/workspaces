
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MailContactInActionDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MailContactInActionDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SentFlag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="IP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SentMailListName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SentMailListDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MailContactInActionDTO", propOrder = {
    "sentFlag",
    "email",
    "actDateTime",
    "ip",
    "actDescription",
    "sentMailListName",
    "sentMailListDateTime"
})
public class MailContactInActionDTO {

    @XmlElement(name = "SentFlag")
    protected int sentFlag;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "ActDateTime", required = true)
    protected XMLGregorianCalendar actDateTime;
    @XmlElement(name = "IP")
    protected String ip;
    @XmlElement(name = "ActDescription")
    protected String actDescription;
    @XmlElement(name = "SentMailListName")
    protected String sentMailListName;
    @XmlElement(name = "SentMailListDateTime", required = true)
    protected XMLGregorianCalendar sentMailListDateTime;

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
     * Gets the value of the actDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActDateTime() {
        return actDateTime;
    }

    /**
     * Sets the value of the actDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActDateTime(XMLGregorianCalendar value) {
        this.actDateTime = value;
    }

    /**
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIP() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIP(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the actDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActDescription() {
        return actDescription;
    }

    /**
     * Sets the value of the actDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActDescription(String value) {
        this.actDescription = value;
    }

    /**
     * Gets the value of the sentMailListName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSentMailListName() {
        return sentMailListName;
    }

    /**
     * Sets the value of the sentMailListName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSentMailListName(String value) {
        this.sentMailListName = value;
    }

    /**
     * Gets the value of the sentMailListDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSentMailListDateTime() {
        return sentMailListDateTime;
    }

    /**
     * Sets the value of the sentMailListDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSentMailListDateTime(XMLGregorianCalendar value) {
        this.sentMailListDateTime = value;
    }

}
