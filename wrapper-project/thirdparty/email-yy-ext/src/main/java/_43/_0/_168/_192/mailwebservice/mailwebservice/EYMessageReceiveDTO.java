
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EY_MessageReceiveDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EY_MessageReceiveDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EY_FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_LastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_MobilePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_HomePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_FullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_Designation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_NickName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_Website" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_WorkPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_WorkFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_WorkAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_WorkCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_WorkState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_WorkZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_WorkCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_HomeFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_Pager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_HomeAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_HomeCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_HomeState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_HomeZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_HomeCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_Notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_Birthday" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EY_To" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_Reserve1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_Reserve2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_Reserve3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EY_MessageReceiveDTO", propOrder = {
    "eyFirstName",
    "eyLastName",
    "eyMobilePhone",
    "eyHomePhone",
    "eyMiddleName",
    "eyFullName",
    "eyDesignation",
    "eyNickName",
    "eyCompany",
    "eyWebsite",
    "eyWorkPhone",
    "eyWorkFax",
    "eyWorkAddress",
    "eyWorkCity",
    "eyWorkState",
    "eyWorkZip",
    "eyWorkCountry",
    "eyHomeFax",
    "eyPager",
    "eyHomeAddress",
    "eyHomeCity",
    "eyHomeState",
    "eyHomeZip",
    "eyHomeCountry",
    "eyNotes",
    "eyBirthday",
    "eyTo",
    "eyReserve1",
    "eyReserve2",
    "eyReserve3"
})
public class EYMessageReceiveDTO {

    @XmlElement(name = "EY_FirstName")
    protected String eyFirstName;
    @XmlElement(name = "EY_LastName")
    protected String eyLastName;
    @XmlElement(name = "EY_MobilePhone")
    protected String eyMobilePhone;
    @XmlElement(name = "EY_HomePhone")
    protected String eyHomePhone;
    @XmlElement(name = "EY_MiddleName")
    protected String eyMiddleName;
    @XmlElement(name = "EY_FullName")
    protected String eyFullName;
    @XmlElement(name = "EY_Designation")
    protected String eyDesignation;
    @XmlElement(name = "EY_NickName")
    protected String eyNickName;
    @XmlElement(name = "EY_Company")
    protected String eyCompany;
    @XmlElement(name = "EY_Website")
    protected String eyWebsite;
    @XmlElement(name = "EY_WorkPhone")
    protected String eyWorkPhone;
    @XmlElement(name = "EY_WorkFax")
    protected String eyWorkFax;
    @XmlElement(name = "EY_WorkAddress")
    protected String eyWorkAddress;
    @XmlElement(name = "EY_WorkCity")
    protected String eyWorkCity;
    @XmlElement(name = "EY_WorkState")
    protected String eyWorkState;
    @XmlElement(name = "EY_WorkZip")
    protected String eyWorkZip;
    @XmlElement(name = "EY_WorkCountry")
    protected String eyWorkCountry;
    @XmlElement(name = "EY_HomeFax")
    protected String eyHomeFax;
    @XmlElement(name = "EY_Pager")
    protected String eyPager;
    @XmlElement(name = "EY_HomeAddress")
    protected String eyHomeAddress;
    @XmlElement(name = "EY_HomeCity")
    protected String eyHomeCity;
    @XmlElement(name = "EY_HomeState")
    protected String eyHomeState;
    @XmlElement(name = "EY_HomeZip")
    protected String eyHomeZip;
    @XmlElement(name = "EY_HomeCountry")
    protected String eyHomeCountry;
    @XmlElement(name = "EY_Notes")
    protected String eyNotes;
    @XmlElement(name = "EY_Birthday", required = true, nillable = true)
    protected XMLGregorianCalendar eyBirthday;
    @XmlElement(name = "EY_To")
    protected String eyTo;
    @XmlElement(name = "EY_Reserve1")
    protected String eyReserve1;
    @XmlElement(name = "EY_Reserve2")
    protected String eyReserve2;
    @XmlElement(name = "EY_Reserve3")
    protected String eyReserve3;

    /**
     * Gets the value of the eyFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYFirstName() {
        return eyFirstName;
    }

    /**
     * Sets the value of the eyFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYFirstName(String value) {
        this.eyFirstName = value;
    }

    /**
     * Gets the value of the eyLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYLastName() {
        return eyLastName;
    }

    /**
     * Sets the value of the eyLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYLastName(String value) {
        this.eyLastName = value;
    }

    /**
     * Gets the value of the eyMobilePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYMobilePhone() {
        return eyMobilePhone;
    }

    /**
     * Sets the value of the eyMobilePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYMobilePhone(String value) {
        this.eyMobilePhone = value;
    }

    /**
     * Gets the value of the eyHomePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYHomePhone() {
        return eyHomePhone;
    }

    /**
     * Sets the value of the eyHomePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYHomePhone(String value) {
        this.eyHomePhone = value;
    }

    /**
     * Gets the value of the eyMiddleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYMiddleName() {
        return eyMiddleName;
    }

    /**
     * Sets the value of the eyMiddleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYMiddleName(String value) {
        this.eyMiddleName = value;
    }

    /**
     * Gets the value of the eyFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYFullName() {
        return eyFullName;
    }

    /**
     * Sets the value of the eyFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYFullName(String value) {
        this.eyFullName = value;
    }

    /**
     * Gets the value of the eyDesignation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYDesignation() {
        return eyDesignation;
    }

    /**
     * Sets the value of the eyDesignation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYDesignation(String value) {
        this.eyDesignation = value;
    }

    /**
     * Gets the value of the eyNickName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYNickName() {
        return eyNickName;
    }

    /**
     * Sets the value of the eyNickName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYNickName(String value) {
        this.eyNickName = value;
    }

    /**
     * Gets the value of the eyCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYCompany() {
        return eyCompany;
    }

    /**
     * Sets the value of the eyCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYCompany(String value) {
        this.eyCompany = value;
    }

    /**
     * Gets the value of the eyWebsite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYWebsite() {
        return eyWebsite;
    }

    /**
     * Sets the value of the eyWebsite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYWebsite(String value) {
        this.eyWebsite = value;
    }

    /**
     * Gets the value of the eyWorkPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYWorkPhone() {
        return eyWorkPhone;
    }

    /**
     * Sets the value of the eyWorkPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYWorkPhone(String value) {
        this.eyWorkPhone = value;
    }

    /**
     * Gets the value of the eyWorkFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYWorkFax() {
        return eyWorkFax;
    }

    /**
     * Sets the value of the eyWorkFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYWorkFax(String value) {
        this.eyWorkFax = value;
    }

    /**
     * Gets the value of the eyWorkAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYWorkAddress() {
        return eyWorkAddress;
    }

    /**
     * Sets the value of the eyWorkAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYWorkAddress(String value) {
        this.eyWorkAddress = value;
    }

    /**
     * Gets the value of the eyWorkCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYWorkCity() {
        return eyWorkCity;
    }

    /**
     * Sets the value of the eyWorkCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYWorkCity(String value) {
        this.eyWorkCity = value;
    }

    /**
     * Gets the value of the eyWorkState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYWorkState() {
        return eyWorkState;
    }

    /**
     * Sets the value of the eyWorkState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYWorkState(String value) {
        this.eyWorkState = value;
    }

    /**
     * Gets the value of the eyWorkZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYWorkZip() {
        return eyWorkZip;
    }

    /**
     * Sets the value of the eyWorkZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYWorkZip(String value) {
        this.eyWorkZip = value;
    }

    /**
     * Gets the value of the eyWorkCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYWorkCountry() {
        return eyWorkCountry;
    }

    /**
     * Sets the value of the eyWorkCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYWorkCountry(String value) {
        this.eyWorkCountry = value;
    }

    /**
     * Gets the value of the eyHomeFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYHomeFax() {
        return eyHomeFax;
    }

    /**
     * Sets the value of the eyHomeFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYHomeFax(String value) {
        this.eyHomeFax = value;
    }

    /**
     * Gets the value of the eyPager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYPager() {
        return eyPager;
    }

    /**
     * Sets the value of the eyPager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYPager(String value) {
        this.eyPager = value;
    }

    /**
     * Gets the value of the eyHomeAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYHomeAddress() {
        return eyHomeAddress;
    }

    /**
     * Sets the value of the eyHomeAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYHomeAddress(String value) {
        this.eyHomeAddress = value;
    }

    /**
     * Gets the value of the eyHomeCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYHomeCity() {
        return eyHomeCity;
    }

    /**
     * Sets the value of the eyHomeCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYHomeCity(String value) {
        this.eyHomeCity = value;
    }

    /**
     * Gets the value of the eyHomeState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYHomeState() {
        return eyHomeState;
    }

    /**
     * Sets the value of the eyHomeState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYHomeState(String value) {
        this.eyHomeState = value;
    }

    /**
     * Gets the value of the eyHomeZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYHomeZip() {
        return eyHomeZip;
    }

    /**
     * Sets the value of the eyHomeZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYHomeZip(String value) {
        this.eyHomeZip = value;
    }

    /**
     * Gets the value of the eyHomeCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYHomeCountry() {
        return eyHomeCountry;
    }

    /**
     * Sets the value of the eyHomeCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYHomeCountry(String value) {
        this.eyHomeCountry = value;
    }

    /**
     * Gets the value of the eyNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYNotes() {
        return eyNotes;
    }

    /**
     * Sets the value of the eyNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYNotes(String value) {
        this.eyNotes = value;
    }

    /**
     * Gets the value of the eyBirthday property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEYBirthday() {
        return eyBirthday;
    }

    /**
     * Sets the value of the eyBirthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEYBirthday(XMLGregorianCalendar value) {
        this.eyBirthday = value;
    }

    /**
     * Gets the value of the eyTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYTo() {
        return eyTo;
    }

    /**
     * Sets the value of the eyTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYTo(String value) {
        this.eyTo = value;
    }

    /**
     * Gets the value of the eyReserve1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYReserve1() {
        return eyReserve1;
    }

    /**
     * Sets the value of the eyReserve1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYReserve1(String value) {
        this.eyReserve1 = value;
    }

    /**
     * Gets the value of the eyReserve2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYReserve2() {
        return eyReserve2;
    }

    /**
     * Sets the value of the eyReserve2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYReserve2(String value) {
        this.eyReserve2 = value;
    }

    /**
     * Gets the value of the eyReserve3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYReserve3() {
        return eyReserve3;
    }

    /**
     * Sets the value of the eyReserve3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYReserve3(String value) {
        this.eyReserve3 = value;
    }

}
