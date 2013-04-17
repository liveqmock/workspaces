
package _43._0._168._192.mailwebservice.mailwebservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMailSenderStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMailSenderStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MailSenderStatus" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}MailSenderStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMailSenderStatus", propOrder = {
    "mailSenderStatus"
})
public class ArrayOfMailSenderStatus {

    @XmlElement(name = "MailSenderStatus", required = true, nillable = true)
    protected List<MailSenderStatus> mailSenderStatus;

    /**
     * Gets the value of the mailSenderStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mailSenderStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMailSenderStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MailSenderStatus }
     * 
     * 
     */
    public List<MailSenderStatus> getMailSenderStatus() {
        if (mailSenderStatus == null) {
            mailSenderStatus = new ArrayList<MailSenderStatus>();
        }
        return this.mailSenderStatus;
    }

}
