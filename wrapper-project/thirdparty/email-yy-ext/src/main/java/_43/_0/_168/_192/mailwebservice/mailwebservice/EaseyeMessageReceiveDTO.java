
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EaseyeMessageReceiveDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EaseyeMessageReceiveDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Birthday" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="MobilePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="To" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Designation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NickName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Website" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomeFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomeAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomeCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomeState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomeZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomeCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserve10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EaseyeMessageReceiveDTO", propOrder = {
    "firstName",
    "lastName",
    "birthday",
    "mobilePhone",
    "homePhone",
    "to",
    "middleName",
    "fullName",
    "designation",
    "nickName",
    "company",
    "website",
    "workPhone",
    "workFax",
    "workAddress",
    "workCity",
    "workState",
    "workZip",
    "workCountry",
    "homeFax",
    "pager",
    "homeAddress",
    "homeCity",
    "homeState",
    "homeZip",
    "homeCountry",
    "notes",
    "reserve1",
    "reserve2",
    "reserve3",
    "reserve4",
    "reserve5",
    "reserve6",
    "reserve7",
    "reserve8",
    "reserve9",
    "reserve10"
})
public class EaseyeMessageReceiveDTO {

    @XmlElement(name = "FirstName")
    protected String firstName;
    @XmlElement(name = "LastName")
    protected String lastName;
    @XmlElement(name = "Birthday", required = true, nillable = true)
    protected XMLGregorianCalendar birthday;
    @XmlElement(name = "MobilePhone")
    protected String mobilePhone;
    @XmlElement(name = "HomePhone")
    protected String homePhone;
    @XmlElement(name = "To")
    protected String to;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "FullName")
    protected String fullName;
    @XmlElement(name = "Designation")
    protected String designation;
    @XmlElement(name = "NickName")
    protected String nickName;
    @XmlElement(name = "Company")
    protected String company;
    @XmlElement(name = "Website")
    protected String website;
    @XmlElement(name = "WorkPhone")
    protected String workPhone;
    @XmlElement(name = "WorkFax")
    protected String workFax;
    @XmlElement(name = "WorkAddress")
    protected String workAddress;
    @XmlElement(name = "WorkCity")
    protected String workCity;
    @XmlElement(name = "WorkState")
    protected String workState;
    @XmlElement(name = "WorkZip")
    protected String workZip;
    @XmlElement(name = "WorkCountry")
    protected String workCountry;
    @XmlElement(name = "HomeFax")
    protected String homeFax;
    @XmlElement(name = "Pager")
    protected String pager;
    @XmlElement(name = "HomeAddress")
    protected String homeAddress;
    @XmlElement(name = "HomeCity")
    protected String homeCity;
    @XmlElement(name = "HomeState")
    protected String homeState;
    @XmlElement(name = "HomeZip")
    protected String homeZip;
    @XmlElement(name = "HomeCountry")
    protected String homeCountry;
    @XmlElement(name = "Notes")
    protected String notes;
    @XmlElement(name = "Reserve1")
    protected String reserve1;
    @XmlElement(name = "Reserve2")
    protected String reserve2;
    @XmlElement(name = "Reserve3")
    protected String reserve3;
    @XmlElement(name = "Reserve4")
    protected String reserve4;
    @XmlElement(name = "Reserve5")
    protected String reserve5;
    @XmlElement(name = "Reserve6")
    protected String reserve6;
    @XmlElement(name = "Reserve7")
    protected String reserve7;
    @XmlElement(name = "Reserve8")
    protected String reserve8;
    @XmlElement(name = "Reserve9")
    protected String reserve9;
    @XmlElement(name = "Reserve10")
    protected String reserve10;

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the birthday property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthday() {
        return birthday;
    }

    /**
     * Sets the value of the birthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthday(XMLGregorianCalendar value) {
        this.birthday = value;
    }

    /**
     * Gets the value of the mobilePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Sets the value of the mobilePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilePhone(String value) {
        this.mobilePhone = value;
    }

    /**
     * Gets the value of the homePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * Sets the value of the homePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomePhone(String value) {
        this.homePhone = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the designation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the value of the designation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignation(String value) {
        this.designation = value;
    }

    /**
     * Gets the value of the nickName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the value of the nickName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickName(String value) {
        this.nickName = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the website property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsite(String value) {
        this.website = value;
    }

    /**
     * Gets the value of the workPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * Sets the value of the workPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkPhone(String value) {
        this.workPhone = value;
    }

    /**
     * Gets the value of the workFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkFax() {
        return workFax;
    }

    /**
     * Sets the value of the workFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkFax(String value) {
        this.workFax = value;
    }

    /**
     * Gets the value of the workAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkAddress() {
        return workAddress;
    }

    /**
     * Sets the value of the workAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkAddress(String value) {
        this.workAddress = value;
    }

    /**
     * Gets the value of the workCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkCity() {
        return workCity;
    }

    /**
     * Sets the value of the workCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkCity(String value) {
        this.workCity = value;
    }

    /**
     * Gets the value of the workState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkState() {
        return workState;
    }

    /**
     * Sets the value of the workState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkState(String value) {
        this.workState = value;
    }

    /**
     * Gets the value of the workZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkZip() {
        return workZip;
    }

    /**
     * Sets the value of the workZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkZip(String value) {
        this.workZip = value;
    }

    /**
     * Gets the value of the workCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkCountry() {
        return workCountry;
    }

    /**
     * Sets the value of the workCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkCountry(String value) {
        this.workCountry = value;
    }

    /**
     * Gets the value of the homeFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeFax() {
        return homeFax;
    }

    /**
     * Sets the value of the homeFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeFax(String value) {
        this.homeFax = value;
    }

    /**
     * Gets the value of the pager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPager() {
        return pager;
    }

    /**
     * Sets the value of the pager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPager(String value) {
        this.pager = value;
    }

    /**
     * Gets the value of the homeAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * Sets the value of the homeAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeAddress(String value) {
        this.homeAddress = value;
    }

    /**
     * Gets the value of the homeCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeCity() {
        return homeCity;
    }

    /**
     * Sets the value of the homeCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeCity(String value) {
        this.homeCity = value;
    }

    /**
     * Gets the value of the homeState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeState() {
        return homeState;
    }

    /**
     * Sets the value of the homeState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeState(String value) {
        this.homeState = value;
    }

    /**
     * Gets the value of the homeZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeZip() {
        return homeZip;
    }

    /**
     * Sets the value of the homeZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeZip(String value) {
        this.homeZip = value;
    }

    /**
     * Gets the value of the homeCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeCountry() {
        return homeCountry;
    }

    /**
     * Sets the value of the homeCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeCountry(String value) {
        this.homeCountry = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the reserve1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve1() {
        return reserve1;
    }

    /**
     * Sets the value of the reserve1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve1(String value) {
        this.reserve1 = value;
    }

    /**
     * Gets the value of the reserve2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve2() {
        return reserve2;
    }

    /**
     * Sets the value of the reserve2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve2(String value) {
        this.reserve2 = value;
    }

    /**
     * Gets the value of the reserve3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve3() {
        return reserve3;
    }

    /**
     * Sets the value of the reserve3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve3(String value) {
        this.reserve3 = value;
    }

    /**
     * Gets the value of the reserve4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve4() {
        return reserve4;
    }

    /**
     * Sets the value of the reserve4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve4(String value) {
        this.reserve4 = value;
    }

    /**
     * Gets the value of the reserve5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve5() {
        return reserve5;
    }

    /**
     * Sets the value of the reserve5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve5(String value) {
        this.reserve5 = value;
    }

    /**
     * Gets the value of the reserve6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve6() {
        return reserve6;
    }

    /**
     * Sets the value of the reserve6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve6(String value) {
        this.reserve6 = value;
    }

    /**
     * Gets the value of the reserve7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve7() {
        return reserve7;
    }

    /**
     * Sets the value of the reserve7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve7(String value) {
        this.reserve7 = value;
    }

    /**
     * Gets the value of the reserve8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve8() {
        return reserve8;
    }

    /**
     * Sets the value of the reserve8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve8(String value) {
        this.reserve8 = value;
    }

    /**
     * Gets the value of the reserve9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve9() {
        return reserve9;
    }

    /**
     * Sets the value of the reserve9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve9(String value) {
        this.reserve9 = value;
    }

    /**
     * Gets the value of the reserve10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve10() {
        return reserve10;
    }

    /**
     * Sets the value of the reserve10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve10(String value) {
        this.reserve10 = value;
    }

}
