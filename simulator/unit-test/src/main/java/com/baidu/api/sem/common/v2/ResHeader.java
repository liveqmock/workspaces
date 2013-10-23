
package com.baidu.api.sem.common.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="failures" type="{http://api.baidu.com/sem/common/v2}Failure" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="oprs" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="oprtime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="quota" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rquota" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResHeader", propOrder = {
    "desc",
    "failures",
    "oprs",
    "oprtime",
    "quota",
    "rquota",
    "status"
})
public class ResHeader {

    @XmlElement(required = true)
    protected String desc;
    protected List<Failure> failures;
    protected Integer oprs;
    protected Integer oprtime;
    protected Integer quota;
    protected Integer rquota;
    protected int status;

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the failures property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the failures property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFailures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Failure }
     * 
     * 
     */
    public List<Failure> getFailures() {
        if (failures == null) {
            failures = new ArrayList<Failure>();
        }
        return this.failures;
    }

    /**
     * Gets the value of the oprs property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOprs() {
        return oprs;
    }

    /**
     * Sets the value of the oprs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOprs(Integer value) {
        this.oprs = value;
    }

    /**
     * Gets the value of the oprtime property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOprtime() {
        return oprtime;
    }

    /**
     * Sets the value of the oprtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOprtime(Integer value) {
        this.oprtime = value;
    }

    /**
     * Gets the value of the quota property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuota() {
        return quota;
    }

    /**
     * Sets the value of the quota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuota(Integer value) {
        this.quota = value;
    }

    /**
     * Gets the value of the rquota property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRquota() {
        return rquota;
    }

    /**
     * Sets the value of the rquota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRquota(Integer value) {
        this.rquota = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

}
