
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EY_MailStatusReportDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EY_MailStatusReportDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EY_AllCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_SentPerCent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="EY_UnSendCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_Sent" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_Bounce" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_Delay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_Error" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_OptOut" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_Open" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_Success" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_BounceFalse" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_BouncePercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="EY_DelayPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="EY_ErrorPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="EY_OptOutPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="EY_OpenPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="EY_SuccessPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="EY_BounceFalsePercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="EY_UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_CompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_MailListName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EY_SendTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EY_FinishTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EY_SentMailListId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_EmailErrorCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_EmailSuccessCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_MailClicked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_NetLinkClicked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_SumLinkClicked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_Transmit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_MailProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EY_MailProjectName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EY_MailStatusReportDTO", propOrder = {
    "eyAllCount",
    "eySentPerCent",
    "eyUnSendCount",
    "eySent",
    "eyBounce",
    "eyDelay",
    "eyError",
    "eyOptOut",
    "eyOpen",
    "eySuccess",
    "eyBounceFalse",
    "eyBouncePercent",
    "eyDelayPercent",
    "eyErrorPercent",
    "eyOptOutPercent",
    "eyOpenPercent",
    "eySuccessPercent",
    "eyBounceFalsePercent",
    "eyUserName",
    "eyCompanyName",
    "eyMailListName",
    "eySendTime",
    "eyFinishTime",
    "eySentMailListId",
    "eyEmailErrorCount",
    "eyEmailSuccessCount",
    "eyMailClicked",
    "eyNetLinkClicked",
    "eySumLinkClicked",
    "eyTransmit",
    "eyMailProjectId",
    "eyMailProjectName"
})
public class EYMailStatusReportDTO {

    @XmlElement(name = "EY_AllCount")
    protected int eyAllCount;
    @XmlElement(name = "EY_SentPerCent")
    protected float eySentPerCent;
    @XmlElement(name = "EY_UnSendCount")
    protected int eyUnSendCount;
    @XmlElement(name = "EY_Sent")
    protected int eySent;
    @XmlElement(name = "EY_Bounce")
    protected int eyBounce;
    @XmlElement(name = "EY_Delay")
    protected int eyDelay;
    @XmlElement(name = "EY_Error")
    protected int eyError;
    @XmlElement(name = "EY_OptOut")
    protected int eyOptOut;
    @XmlElement(name = "EY_Open")
    protected int eyOpen;
    @XmlElement(name = "EY_Success")
    protected int eySuccess;
    @XmlElement(name = "EY_BounceFalse")
    protected int eyBounceFalse;
    @XmlElement(name = "EY_BouncePercent")
    protected float eyBouncePercent;
    @XmlElement(name = "EY_DelayPercent")
    protected float eyDelayPercent;
    @XmlElement(name = "EY_ErrorPercent")
    protected float eyErrorPercent;
    @XmlElement(name = "EY_OptOutPercent")
    protected float eyOptOutPercent;
    @XmlElement(name = "EY_OpenPercent")
    protected float eyOpenPercent;
    @XmlElement(name = "EY_SuccessPercent")
    protected float eySuccessPercent;
    @XmlElement(name = "EY_BounceFalsePercent")
    protected float eyBounceFalsePercent;
    @XmlElement(name = "EY_UserName")
    protected String eyUserName;
    @XmlElement(name = "EY_CompanyName")
    protected String eyCompanyName;
    @XmlElement(name = "EY_MailListName")
    protected String eyMailListName;
    @XmlElement(name = "EY_SendTime", required = true)
    protected XMLGregorianCalendar eySendTime;
    @XmlElement(name = "EY_FinishTime", required = true)
    protected XMLGregorianCalendar eyFinishTime;
    @XmlElement(name = "EY_SentMailListId")
    protected int eySentMailListId;
    @XmlElement(name = "EY_EmailErrorCount")
    protected int eyEmailErrorCount;
    @XmlElement(name = "EY_EmailSuccessCount")
    protected int eyEmailSuccessCount;
    @XmlElement(name = "EY_MailClicked", required = true, type = Integer.class, nillable = true)
    protected Integer eyMailClicked;
    @XmlElement(name = "EY_NetLinkClicked", required = true, type = Integer.class, nillable = true)
    protected Integer eyNetLinkClicked;
    @XmlElement(name = "EY_SumLinkClicked", required = true, type = Integer.class, nillable = true)
    protected Integer eySumLinkClicked;
    @XmlElement(name = "EY_Transmit", required = true, type = Integer.class, nillable = true)
    protected Integer eyTransmit;
    @XmlElement(name = "EY_MailProjectId", required = true, type = Integer.class, nillable = true)
    protected Integer eyMailProjectId;
    @XmlElement(name = "EY_MailProjectName")
    protected String eyMailProjectName;

    /**
     * Gets the value of the eyAllCount property.
     * 
     */
    public int getEYAllCount() {
        return eyAllCount;
    }

    /**
     * Sets the value of the eyAllCount property.
     * 
     */
    public void setEYAllCount(int value) {
        this.eyAllCount = value;
    }

    /**
     * Gets the value of the eySentPerCent property.
     * 
     */
    public float getEYSentPerCent() {
        return eySentPerCent;
    }

    /**
     * Sets the value of the eySentPerCent property.
     * 
     */
    public void setEYSentPerCent(float value) {
        this.eySentPerCent = value;
    }

    /**
     * Gets the value of the eyUnSendCount property.
     * 
     */
    public int getEYUnSendCount() {
        return eyUnSendCount;
    }

    /**
     * Sets the value of the eyUnSendCount property.
     * 
     */
    public void setEYUnSendCount(int value) {
        this.eyUnSendCount = value;
    }

    /**
     * Gets the value of the eySent property.
     * 
     */
    public int getEYSent() {
        return eySent;
    }

    /**
     * Sets the value of the eySent property.
     * 
     */
    public void setEYSent(int value) {
        this.eySent = value;
    }

    /**
     * Gets the value of the eyBounce property.
     * 
     */
    public int getEYBounce() {
        return eyBounce;
    }

    /**
     * Sets the value of the eyBounce property.
     * 
     */
    public void setEYBounce(int value) {
        this.eyBounce = value;
    }

    /**
     * Gets the value of the eyDelay property.
     * 
     */
    public int getEYDelay() {
        return eyDelay;
    }

    /**
     * Sets the value of the eyDelay property.
     * 
     */
    public void setEYDelay(int value) {
        this.eyDelay = value;
    }

    /**
     * Gets the value of the eyError property.
     * 
     */
    public int getEYError() {
        return eyError;
    }

    /**
     * Sets the value of the eyError property.
     * 
     */
    public void setEYError(int value) {
        this.eyError = value;
    }

    /**
     * Gets the value of the eyOptOut property.
     * 
     */
    public int getEYOptOut() {
        return eyOptOut;
    }

    /**
     * Sets the value of the eyOptOut property.
     * 
     */
    public void setEYOptOut(int value) {
        this.eyOptOut = value;
    }

    /**
     * Gets the value of the eyOpen property.
     * 
     */
    public int getEYOpen() {
        return eyOpen;
    }

    /**
     * Sets the value of the eyOpen property.
     * 
     */
    public void setEYOpen(int value) {
        this.eyOpen = value;
    }

    /**
     * Gets the value of the eySuccess property.
     * 
     */
    public int getEYSuccess() {
        return eySuccess;
    }

    /**
     * Sets the value of the eySuccess property.
     * 
     */
    public void setEYSuccess(int value) {
        this.eySuccess = value;
    }

    /**
     * Gets the value of the eyBounceFalse property.
     * 
     */
    public int getEYBounceFalse() {
        return eyBounceFalse;
    }

    /**
     * Sets the value of the eyBounceFalse property.
     * 
     */
    public void setEYBounceFalse(int value) {
        this.eyBounceFalse = value;
    }

    /**
     * Gets the value of the eyBouncePercent property.
     * 
     */
    public float getEYBouncePercent() {
        return eyBouncePercent;
    }

    /**
     * Sets the value of the eyBouncePercent property.
     * 
     */
    public void setEYBouncePercent(float value) {
        this.eyBouncePercent = value;
    }

    /**
     * Gets the value of the eyDelayPercent property.
     * 
     */
    public float getEYDelayPercent() {
        return eyDelayPercent;
    }

    /**
     * Sets the value of the eyDelayPercent property.
     * 
     */
    public void setEYDelayPercent(float value) {
        this.eyDelayPercent = value;
    }

    /**
     * Gets the value of the eyErrorPercent property.
     * 
     */
    public float getEYErrorPercent() {
        return eyErrorPercent;
    }

    /**
     * Sets the value of the eyErrorPercent property.
     * 
     */
    public void setEYErrorPercent(float value) {
        this.eyErrorPercent = value;
    }

    /**
     * Gets the value of the eyOptOutPercent property.
     * 
     */
    public float getEYOptOutPercent() {
        return eyOptOutPercent;
    }

    /**
     * Sets the value of the eyOptOutPercent property.
     * 
     */
    public void setEYOptOutPercent(float value) {
        this.eyOptOutPercent = value;
    }

    /**
     * Gets the value of the eyOpenPercent property.
     * 
     */
    public float getEYOpenPercent() {
        return eyOpenPercent;
    }

    /**
     * Sets the value of the eyOpenPercent property.
     * 
     */
    public void setEYOpenPercent(float value) {
        this.eyOpenPercent = value;
    }

    /**
     * Gets the value of the eySuccessPercent property.
     * 
     */
    public float getEYSuccessPercent() {
        return eySuccessPercent;
    }

    /**
     * Sets the value of the eySuccessPercent property.
     * 
     */
    public void setEYSuccessPercent(float value) {
        this.eySuccessPercent = value;
    }

    /**
     * Gets the value of the eyBounceFalsePercent property.
     * 
     */
    public float getEYBounceFalsePercent() {
        return eyBounceFalsePercent;
    }

    /**
     * Sets the value of the eyBounceFalsePercent property.
     * 
     */
    public void setEYBounceFalsePercent(float value) {
        this.eyBounceFalsePercent = value;
    }

    /**
     * Gets the value of the eyUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYUserName() {
        return eyUserName;
    }

    /**
     * Sets the value of the eyUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYUserName(String value) {
        this.eyUserName = value;
    }

    /**
     * Gets the value of the eyCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYCompanyName() {
        return eyCompanyName;
    }

    /**
     * Sets the value of the eyCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYCompanyName(String value) {
        this.eyCompanyName = value;
    }

    /**
     * Gets the value of the eyMailListName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYMailListName() {
        return eyMailListName;
    }

    /**
     * Sets the value of the eyMailListName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYMailListName(String value) {
        this.eyMailListName = value;
    }

    /**
     * Gets the value of the eySendTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEYSendTime() {
        return eySendTime;
    }

    /**
     * Sets the value of the eySendTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEYSendTime(XMLGregorianCalendar value) {
        this.eySendTime = value;
    }

    /**
     * Gets the value of the eyFinishTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEYFinishTime() {
        return eyFinishTime;
    }

    /**
     * Sets the value of the eyFinishTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEYFinishTime(XMLGregorianCalendar value) {
        this.eyFinishTime = value;
    }

    /**
     * Gets the value of the eySentMailListId property.
     * 
     */
    public int getEYSentMailListId() {
        return eySentMailListId;
    }

    /**
     * Sets the value of the eySentMailListId property.
     * 
     */
    public void setEYSentMailListId(int value) {
        this.eySentMailListId = value;
    }

    /**
     * Gets the value of the eyEmailErrorCount property.
     * 
     */
    public int getEYEmailErrorCount() {
        return eyEmailErrorCount;
    }

    /**
     * Sets the value of the eyEmailErrorCount property.
     * 
     */
    public void setEYEmailErrorCount(int value) {
        this.eyEmailErrorCount = value;
    }

    /**
     * Gets the value of the eyEmailSuccessCount property.
     * 
     */
    public int getEYEmailSuccessCount() {
        return eyEmailSuccessCount;
    }

    /**
     * Sets the value of the eyEmailSuccessCount property.
     * 
     */
    public void setEYEmailSuccessCount(int value) {
        this.eyEmailSuccessCount = value;
    }

    /**
     * Gets the value of the eyMailClicked property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEYMailClicked() {
        return eyMailClicked;
    }

    /**
     * Sets the value of the eyMailClicked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEYMailClicked(Integer value) {
        this.eyMailClicked = value;
    }

    /**
     * Gets the value of the eyNetLinkClicked property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEYNetLinkClicked() {
        return eyNetLinkClicked;
    }

    /**
     * Sets the value of the eyNetLinkClicked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEYNetLinkClicked(Integer value) {
        this.eyNetLinkClicked = value;
    }

    /**
     * Gets the value of the eySumLinkClicked property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEYSumLinkClicked() {
        return eySumLinkClicked;
    }

    /**
     * Sets the value of the eySumLinkClicked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEYSumLinkClicked(Integer value) {
        this.eySumLinkClicked = value;
    }

    /**
     * Gets the value of the eyTransmit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEYTransmit() {
        return eyTransmit;
    }

    /**
     * Sets the value of the eyTransmit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEYTransmit(Integer value) {
        this.eyTransmit = value;
    }

    /**
     * Gets the value of the eyMailProjectId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEYMailProjectId() {
        return eyMailProjectId;
    }

    /**
     * Sets the value of the eyMailProjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEYMailProjectId(Integer value) {
        this.eyMailProjectId = value;
    }

    /**
     * Gets the value of the eyMailProjectName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEYMailProjectName() {
        return eyMailProjectName;
    }

    /**
     * Sets the value of the eyMailProjectName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEYMailProjectName(String value) {
        this.eyMailProjectName = value;
    }

}
