
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for WebServiceReportDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebServiceReportDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TemplateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SentDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="SentCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OpenCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OptCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ClickTimes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="HardBounceCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SoftBounceCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SuccessCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebServiceReportDTO", propOrder = {
    "templateName",
    "sentDateTime",
    "sentCount",
    "openCount",
    "optCount",
    "clickTimes",
    "hardBounceCount",
    "softBounceCount",
    "successCount"
})
public class WebServiceReportDTO {

    @XmlElement(name = "TemplateName")
    protected String templateName;
    @XmlElement(name = "SentDateTime", required = true)
    protected XMLGregorianCalendar sentDateTime;
    @XmlElement(name = "SentCount")
    protected int sentCount;
    @XmlElement(name = "OpenCount")
    protected int openCount;
    @XmlElement(name = "OptCount")
    protected int optCount;
    @XmlElement(name = "ClickTimes")
    protected int clickTimes;
    @XmlElement(name = "HardBounceCount")
    protected int hardBounceCount;
    @XmlElement(name = "SoftBounceCount")
    protected int softBounceCount;
    @XmlElement(name = "SuccessCount")
    protected int successCount;

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
     * Gets the value of the sentDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSentDateTime() {
        return sentDateTime;
    }

    /**
     * Sets the value of the sentDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSentDateTime(XMLGregorianCalendar value) {
        this.sentDateTime = value;
    }

    /**
     * Gets the value of the sentCount property.
     * 
     */
    public int getSentCount() {
        return sentCount;
    }

    /**
     * Sets the value of the sentCount property.
     * 
     */
    public void setSentCount(int value) {
        this.sentCount = value;
    }

    /**
     * Gets the value of the openCount property.
     * 
     */
    public int getOpenCount() {
        return openCount;
    }

    /**
     * Sets the value of the openCount property.
     * 
     */
    public void setOpenCount(int value) {
        this.openCount = value;
    }

    /**
     * Gets the value of the optCount property.
     * 
     */
    public int getOptCount() {
        return optCount;
    }

    /**
     * Sets the value of the optCount property.
     * 
     */
    public void setOptCount(int value) {
        this.optCount = value;
    }

    /**
     * Gets the value of the clickTimes property.
     * 
     */
    public int getClickTimes() {
        return clickTimes;
    }

    /**
     * Sets the value of the clickTimes property.
     * 
     */
    public void setClickTimes(int value) {
        this.clickTimes = value;
    }

    /**
     * Gets the value of the hardBounceCount property.
     * 
     */
    public int getHardBounceCount() {
        return hardBounceCount;
    }

    /**
     * Sets the value of the hardBounceCount property.
     * 
     */
    public void setHardBounceCount(int value) {
        this.hardBounceCount = value;
    }

    /**
     * Gets the value of the softBounceCount property.
     * 
     */
    public int getSoftBounceCount() {
        return softBounceCount;
    }

    /**
     * Sets the value of the softBounceCount property.
     * 
     */
    public void setSoftBounceCount(int value) {
        this.softBounceCount = value;
    }

    /**
     * Gets the value of the successCount property.
     * 
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * Sets the value of the successCount property.
     * 
     */
    public void setSuccessCount(int value) {
        this.successCount = value;
    }

}
