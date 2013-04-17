
package _43._0._168._192.mailwebservice.mailwebservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMailStatusReportDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMailStatusReportDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MailStatusReportDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}MailStatusReportDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMailStatusReportDTO", propOrder = {
    "mailStatusReportDTO"
})
public class ArrayOfMailStatusReportDTO {

    @XmlElement(name = "MailStatusReportDTO", required = true)
    protected List<MailStatusReportDTO> mailStatusReportDTO;

    /**
     * Gets the value of the mailStatusReportDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mailStatusReportDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMailStatusReportDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MailStatusReportDTO }
     * 
     * 
     */
    public List<MailStatusReportDTO> getMailStatusReportDTO() {
        if (mailStatusReportDTO == null) {
            mailStatusReportDTO = new ArrayList<MailStatusReportDTO>();
        }
        return this.mailStatusReportDTO;
    }

}
