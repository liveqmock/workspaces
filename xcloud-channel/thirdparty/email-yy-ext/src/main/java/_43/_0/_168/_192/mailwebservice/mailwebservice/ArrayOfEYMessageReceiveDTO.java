
package _43._0._168._192.mailwebservice.mailwebservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfEY_MessageReceiveDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEY_MessageReceiveDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EY_MessageReceiveDTO" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}EY_MessageReceiveDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEY_MessageReceiveDTO", propOrder = {
    "eyMessageReceiveDTO"
})
public class ArrayOfEYMessageReceiveDTO {

    @XmlElement(name = "EY_MessageReceiveDTO", required = true)
    protected List<EYMessageReceiveDTO> eyMessageReceiveDTO;

    /**
     * Gets the value of the eyMessageReceiveDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eyMessageReceiveDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEYMessageReceiveDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EYMessageReceiveDTO }
     * 
     * 
     */
    public List<EYMessageReceiveDTO> getEYMessageReceiveDTO() {
        if (eyMessageReceiveDTO == null) {
            eyMessageReceiveDTO = new ArrayList<EYMessageReceiveDTO>();
        }
        return this.eyMessageReceiveDTO;
    }

}
