
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
 *         &lt;element name="easeyeGroupDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeGroupDTO"/>
 *         &lt;element name="easeyeMessageReceiveDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeMessageCustomReceiverDTO"/>
 *         &lt;element name="importName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "easeyeGroupDTO",
    "easeyeMessageReceiveDTO",
    "importName"
})
@XmlRootElement(name = "ImportContactByGroupExt")
public class ImportContactByGroupExt {

    @XmlElement(required = true)
    protected EaseyeUserAccountDTO easeyeUserAccountDTO;
    @XmlElement(required = true)
    protected EaseyeGroupDTO easeyeGroupDTO;
    @XmlElement(required = true)
    protected EaseyeMessageCustomReceiverDTO easeyeMessageReceiveDTO;
    protected String importName;

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
     * Gets the value of the easeyeGroupDTO property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeGroupDTO }
     *     
     */
    public EaseyeGroupDTO getEaseyeGroupDTO() {
        return easeyeGroupDTO;
    }

    /**
     * Sets the value of the easeyeGroupDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeGroupDTO }
     *     
     */
    public void setEaseyeGroupDTO(EaseyeGroupDTO value) {
        this.easeyeGroupDTO = value;
    }

    /**
     * Gets the value of the easeyeMessageReceiveDTO property.
     * 
     * @return
     *     possible object is
     *     {@link EaseyeMessageCustomReceiverDTO }
     *     
     */
    public EaseyeMessageCustomReceiverDTO getEaseyeMessageReceiveDTO() {
        return easeyeMessageReceiveDTO;
    }

    /**
     * Sets the value of the easeyeMessageReceiveDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EaseyeMessageCustomReceiverDTO }
     *     
     */
    public void setEaseyeMessageReceiveDTO(EaseyeMessageCustomReceiverDTO value) {
        this.easeyeMessageReceiveDTO = value;
    }

    /**
     * Gets the value of the importName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportName() {
        return importName;
    }

    /**
     * Sets the value of the importName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportName(String value) {
        this.importName = value;
    }

}
