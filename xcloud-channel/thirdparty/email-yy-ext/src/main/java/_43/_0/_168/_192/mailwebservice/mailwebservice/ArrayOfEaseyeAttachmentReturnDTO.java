
package _43._0._168._192.mailwebservice.mailwebservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfEaseyeAttachmentReturnDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEaseyeAttachmentReturnDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EaseyeAttachmentReturnDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EaseyeAttachmentReturnDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEaseyeAttachmentReturnDTO", propOrder = {
    "easeyeAttachmentReturnDTO"
})
public class ArrayOfEaseyeAttachmentReturnDTO {

    @XmlElement(name = "EaseyeAttachmentReturnDTO", required = true, nillable = true)
    protected List<EaseyeAttachmentReturnDTO> easeyeAttachmentReturnDTO;

    /**
     * Gets the value of the easeyeAttachmentReturnDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the easeyeAttachmentReturnDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEaseyeAttachmentReturnDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EaseyeAttachmentReturnDTO }
     * 
     * 
     */
    public List<EaseyeAttachmentReturnDTO> getEaseyeAttachmentReturnDTO() {
        if (easeyeAttachmentReturnDTO == null) {
            easeyeAttachmentReturnDTO = new ArrayList<EaseyeAttachmentReturnDTO>();
        }
        return this.easeyeAttachmentReturnDTO;
    }

}
