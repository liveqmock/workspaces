
package https.api_baidu_com.sem.sms.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RealTimePairResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RealTimePairResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="keywordId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="creativeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="pairInfo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="KPIs" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RealTimePairResultType", propOrder = {
    "keywordId",
    "creativeId",
    "pairInfo",
    "date",
    "kpIs"
})
public class RealTimePairResultType {

    protected Long keywordId;
    protected Long creativeId;
    protected List<String> pairInfo;
    @XmlElement(required = true)
    protected String date;
    @XmlElement(name = "KPIs")
    protected List<String> kpIs;

    /**
     * Gets the value of the keywordId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getKeywordId() {
        return keywordId;
    }

    /**
     * Sets the value of the keywordId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setKeywordId(Long value) {
        this.keywordId = value;
    }

    /**
     * Gets the value of the creativeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCreativeId() {
        return creativeId;
    }

    /**
     * Sets the value of the creativeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCreativeId(Long value) {
        this.creativeId = value;
    }

    /**
     * Gets the value of the pairInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pairInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPairInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPairInfo() {
        if (pairInfo == null) {
            pairInfo = new ArrayList<String>();
        }
        return this.pairInfo;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the kpIs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kpIs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKPIs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getKPIs() {
        if (kpIs == null) {
            kpIs = new ArrayList<String>();
        }
        return this.kpIs;
    }

}
