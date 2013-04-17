
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SingleRecipient complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SingleRecipient">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContactId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MobilePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Website" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomeAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "SingleRecipient", propOrder = {
    "contactId",
    "fullName",
    "email",
    "company",
    "mobilePhone",
    "lastName",
    "firstName",
    "website",
    "workPhone",
    "workFax",
    "workAddress",
    "homePhone",
    "homeAddress",
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
public class SingleRecipient {

    @XmlElement(name = "ContactId")
    protected int contactId;
    @XmlElement(name = "FullName")
    protected String fullName;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Company")
    protected String company;
    @XmlElement(name = "MobilePhone")
    protected String mobilePhone;
    @XmlElement(name = "LastName")
    protected String lastName;
    @XmlElement(name = "FirstName")
    protected String firstName;
    @XmlElement(name = "Website")
    protected String website;
    @XmlElement(name = "WorkPhone")
    protected String workPhone;
    @XmlElement(name = "WorkFax")
    protected String workFax;
    @XmlElement(name = "WorkAddress")
    protected String workAddress;
    @XmlElement(name = "HomePhone")
    protected String homePhone;
    @XmlElement(name = "HomeAddress")
    protected String homeAddress;
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
     * Gets the value of the contactId property.
     * 
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the value of the contactId property.
     * 
     */
    public void setContactId(int value) {
        this.contactId = value;
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
