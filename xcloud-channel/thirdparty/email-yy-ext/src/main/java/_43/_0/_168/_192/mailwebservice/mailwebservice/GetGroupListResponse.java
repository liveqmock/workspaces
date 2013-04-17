
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
 *         &lt;element name="GetGroupListResult" type="{http://192.168.0.43/MailWebService/MailWebService.asmx}ArrayOfEaseyeGroupDTO" minOccurs="0"/>
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
    "getGroupListResult"
})
@XmlRootElement(name = "GetGroupListResponse")
public class GetGroupListResponse {

    @XmlElement(name = "GetGroupListResult")
    protected ArrayOfEaseyeGroupDTO getGroupListResult;

    /**
     * Gets the value of the getGroupListResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEaseyeGroupDTO }
     *     
     */
    public ArrayOfEaseyeGroupDTO getGetGroupListResult() {
        return getGroupListResult;
    }

    /**
     * Sets the value of the getGroupListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEaseyeGroupDTO }
     *     
     */
    public void setGetGroupListResult(ArrayOfEaseyeGroupDTO value) {
        this.getGroupListResult = value;
    }

}
