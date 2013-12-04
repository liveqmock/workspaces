
package _43._0._168._192.mailwebservice.mailwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="sentMailListId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sentFlag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="currentPageIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "sentMailListId",
    "sentFlag",
    "pageSize",
    "currentPageIndex"
})
@XmlRootElement(name = "GetMailReportDetail")
public class GetMailReportDetail {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    protected int sentMailListId;
    protected int sentFlag;
    protected int pageSize;
    protected int currentPageIndex;

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
     * Gets the value of the pageSize property.
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

    /**
     * Gets the value of the currentPageIndex property.
     * 
     */
    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    /**
     * Sets the value of the currentPageIndex property.
     * 
     */
    public void setCurrentPageIndex(int value) {
        this.currentPageIndex = value;
    }

}
