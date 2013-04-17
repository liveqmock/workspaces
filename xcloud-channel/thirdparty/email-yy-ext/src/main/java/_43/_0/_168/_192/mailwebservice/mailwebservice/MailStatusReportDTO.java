
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MailStatusReportDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MailStatusReportDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AllCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SentPerCent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="UnSendCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Sent" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Bounce" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Delay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OptOut" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Open" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BounceFalse" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BouncePercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="DelayPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ErrorPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="OptOutPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="OpenPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="SuccessPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="BounceFalsePercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MailListName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SendTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FinishTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="SentMailListId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EmailErrorCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EmailSuccessCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MailClicked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NetLinkClicked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SumLinkClicked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Transmit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MailProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MailProjectName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MailStatusReportDTO", propOrder = {
    "allCount",
    "sentPerCent",
    "unSendCount",
    "sent",
    "bounce",
    "delay",
    "error",
    "optOut",
    "open",
    "success",
    "bounceFalse",
    "bouncePercent",
    "delayPercent",
    "errorPercent",
    "optOutPercent",
    "openPercent",
    "successPercent",
    "bounceFalsePercent",
    "userName",
    "companyName",
    "mailListName",
    "sendTime",
    "finishTime",
    "sentMailListId",
    "emailErrorCount",
    "emailSuccessCount",
    "mailClicked",
    "netLinkClicked",
    "sumLinkClicked",
    "transmit",
    "mailProjectId",
    "mailProjectName"
})
public class MailStatusReportDTO {

    @XmlElement(name = "AllCount")
    protected int allCount;
    @XmlElement(name = "SentPerCent")
    protected float sentPerCent;
    @XmlElement(name = "UnSendCount")
    protected int unSendCount;
    @XmlElement(name = "Sent")
    protected int sent;
    @XmlElement(name = "Bounce")
    protected int bounce;
    @XmlElement(name = "Delay")
    protected int delay;
    @XmlElement(name = "Error")
    protected int error;
    @XmlElement(name = "OptOut")
    protected int optOut;
    @XmlElement(name = "Open")
    protected int open;
    @XmlElement(name = "Success")
    protected int success;
    @XmlElement(name = "BounceFalse")
    protected int bounceFalse;
    @XmlElement(name = "BouncePercent")
    protected float bouncePercent;
    @XmlElement(name = "DelayPercent")
    protected float delayPercent;
    @XmlElement(name = "ErrorPercent")
    protected float errorPercent;
    @XmlElement(name = "OptOutPercent")
    protected float optOutPercent;
    @XmlElement(name = "OpenPercent")
    protected float openPercent;
    @XmlElement(name = "SuccessPercent")
    protected float successPercent;
    @XmlElement(name = "BounceFalsePercent")
    protected float bounceFalsePercent;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "CompanyName")
    protected String companyName;
    @XmlElement(name = "MailListName")
    protected String mailListName;
    @XmlElement(name = "SendTime", required = true)
    protected XMLGregorianCalendar sendTime;
    @XmlElement(name = "FinishTime", required = true)
    protected XMLGregorianCalendar finishTime;
    @XmlElement(name = "SentMailListId")
    protected int sentMailListId;
    @XmlElement(name = "EmailErrorCount")
    protected int emailErrorCount;
    @XmlElement(name = "EmailSuccessCount")
    protected int emailSuccessCount;
    @XmlElement(name = "MailClicked", required = true, type = Integer.class, nillable = true)
    protected Integer mailClicked;
    @XmlElement(name = "NetLinkClicked", required = true, type = Integer.class, nillable = true)
    protected Integer netLinkClicked;
    @XmlElement(name = "SumLinkClicked", required = true, type = Integer.class, nillable = true)
    protected Integer sumLinkClicked;
    @XmlElement(name = "Transmit", required = true, type = Integer.class, nillable = true)
    protected Integer transmit;
    @XmlElement(name = "MailProjectId", required = true, type = Integer.class, nillable = true)
    protected Integer mailProjectId;
    @XmlElement(name = "MailProjectName")
    protected String mailProjectName;

    /**
     * Gets the value of the allCount property.
     * 
     */
    public int getAllCount() {
        return allCount;
    }

    /**
     * Sets the value of the allCount property.
     * 
     */
    public void setAllCount(int value) {
        this.allCount = value;
    }

    /**
     * Gets the value of the sentPerCent property.
     * 
     */
    public float getSentPerCent() {
        return sentPerCent;
    }

    /**
     * Sets the value of the sentPerCent property.
     * 
     */
    public void setSentPerCent(float value) {
        this.sentPerCent = value;
    }

    /**
     * Gets the value of the unSendCount property.
     * 
     */
    public int getUnSendCount() {
        return unSendCount;
    }

    /**
     * Sets the value of the unSendCount property.
     * 
     */
    public void setUnSendCount(int value) {
        this.unSendCount = value;
    }

    /**
     * Gets the value of the sent property.
     * 
     */
    public int getSent() {
        return sent;
    }

    /**
     * Sets the value of the sent property.
     * 
     */
    public void setSent(int value) {
        this.sent = value;
    }

    /**
     * Gets the value of the bounce property.
     * 
     */
    public int getBounce() {
        return bounce;
    }

    /**
     * Sets the value of the bounce property.
     * 
     */
    public void setBounce(int value) {
        this.bounce = value;
    }

    /**
     * Gets the value of the delay property.
     * 
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Sets the value of the delay property.
     * 
     */
    public void setDelay(int value) {
        this.delay = value;
    }

    /**
     * Gets the value of the error property.
     * 
     */
    public int getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     */
    public void setError(int value) {
        this.error = value;
    }

    /**
     * Gets the value of the optOut property.
     * 
     */
    public int getOptOut() {
        return optOut;
    }

    /**
     * Sets the value of the optOut property.
     * 
     */
    public void setOptOut(int value) {
        this.optOut = value;
    }

    /**
     * Gets the value of the open property.
     * 
     */
    public int getOpen() {
        return open;
    }

    /**
     * Sets the value of the open property.
     * 
     */
    public void setOpen(int value) {
        this.open = value;
    }

    /**
     * Gets the value of the success property.
     * 
     */
    public int getSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(int value) {
        this.success = value;
    }

    /**
     * Gets the value of the bounceFalse property.
     * 
     */
    public int getBounceFalse() {
        return bounceFalse;
    }

    /**
     * Sets the value of the bounceFalse property.
     * 
     */
    public void setBounceFalse(int value) {
        this.bounceFalse = value;
    }

    /**
     * Gets the value of the bouncePercent property.
     * 
     */
    public float getBouncePercent() {
        return bouncePercent;
    }

    /**
     * Sets the value of the bouncePercent property.
     * 
     */
    public void setBouncePercent(float value) {
        this.bouncePercent = value;
    }

    /**
     * Gets the value of the delayPercent property.
     * 
     */
    public float getDelayPercent() {
        return delayPercent;
    }

    /**
     * Sets the value of the delayPercent property.
     * 
     */
    public void setDelayPercent(float value) {
        this.delayPercent = value;
    }

    /**
     * Gets the value of the errorPercent property.
     * 
     */
    public float getErrorPercent() {
        return errorPercent;
    }

    /**
     * Sets the value of the errorPercent property.
     * 
     */
    public void setErrorPercent(float value) {
        this.errorPercent = value;
    }

    /**
     * Gets the value of the optOutPercent property.
     * 
     */
    public float getOptOutPercent() {
        return optOutPercent;
    }

    /**
     * Sets the value of the optOutPercent property.
     * 
     */
    public void setOptOutPercent(float value) {
        this.optOutPercent = value;
    }

    /**
     * Gets the value of the openPercent property.
     * 
     */
    public float getOpenPercent() {
        return openPercent;
    }

    /**
     * Sets the value of the openPercent property.
     * 
     */
    public void setOpenPercent(float value) {
        this.openPercent = value;
    }

    /**
     * Gets the value of the successPercent property.
     * 
     */
    public float getSuccessPercent() {
        return successPercent;
    }

    /**
     * Sets the value of the successPercent property.
     * 
     */
    public void setSuccessPercent(float value) {
        this.successPercent = value;
    }

    /**
     * Gets the value of the bounceFalsePercent property.
     * 
     */
    public float getBounceFalsePercent() {
        return bounceFalsePercent;
    }

    /**
     * Sets the value of the bounceFalsePercent property.
     * 
     */
    public void setBounceFalsePercent(float value) {
        this.bounceFalsePercent = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the mailListName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailListName() {
        return mailListName;
    }

    /**
     * Sets the value of the mailListName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailListName(String value) {
        this.mailListName = value;
    }

    /**
     * Gets the value of the sendTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSendTime() {
        return sendTime;
    }

    /**
     * Sets the value of the sendTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSendTime(XMLGregorianCalendar value) {
        this.sendTime = value;
    }

    /**
     * Gets the value of the finishTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFinishTime() {
        return finishTime;
    }

    /**
     * Sets the value of the finishTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFinishTime(XMLGregorianCalendar value) {
        this.finishTime = value;
    }

    /**
     * Gets the value of the sentMailListId property.
     * 
     */
    public int getSentMailListId() {
        return sentMailListId;
    }

    /**
     * Sets the value of the sentMailListId property.
     * 
     */
    public void setSentMailListId(int value) {
        this.sentMailListId = value;
    }

    /**
     * Gets the value of the emailErrorCount property.
     * 
     */
    public int getEmailErrorCount() {
        return emailErrorCount;
    }

    /**
     * Sets the value of the emailErrorCount property.
     * 
     */
    public void setEmailErrorCount(int value) {
        this.emailErrorCount = value;
    }

    /**
     * Gets the value of the emailSuccessCount property.
     * 
     */
    public int getEmailSuccessCount() {
        return emailSuccessCount;
    }

    /**
     * Sets the value of the emailSuccessCount property.
     * 
     */
    public void setEmailSuccessCount(int value) {
        this.emailSuccessCount = value;
    }

    /**
     * Gets the value of the mailClicked property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMailClicked() {
        return mailClicked;
    }

    /**
     * Sets the value of the mailClicked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMailClicked(Integer value) {
        this.mailClicked = value;
    }

    /**
     * Gets the value of the netLinkClicked property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNetLinkClicked() {
        return netLinkClicked;
    }

    /**
     * Sets the value of the netLinkClicked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNetLinkClicked(Integer value) {
        this.netLinkClicked = value;
    }

    /**
     * Gets the value of the sumLinkClicked property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSumLinkClicked() {
        return sumLinkClicked;
    }

    /**
     * Sets the value of the sumLinkClicked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSumLinkClicked(Integer value) {
        this.sumLinkClicked = value;
    }

    /**
     * Gets the value of the transmit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTransmit() {
        return transmit;
    }

    /**
     * Sets the value of the transmit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTransmit(Integer value) {
        this.transmit = value;
    }

    /**
     * Gets the value of the mailProjectId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMailProjectId() {
        return mailProjectId;
    }

    /**
     * Sets the value of the mailProjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMailProjectId(Integer value) {
        this.mailProjectId = value;
    }

    /**
     * Gets the value of the mailProjectName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailProjectName() {
        return mailProjectName;
    }

    /**
     * Sets the value of the mailProjectName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailProjectName(String value) {
        this.mailProjectName = value;
    }

}
