
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
 *         &lt;element name="AccessCheckResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "accessCheckResult"
})
@XmlRootElement(name = "AccessCheckResponse")
public class AccessCheckResponse {

    @XmlElement(name = "AccessCheckResult")
    protected boolean accessCheckResult;

    /**
     * Gets the value of the accessCheckResult property.
     * 
     */
    public boolean isAccessCheckResult() {
        return accessCheckResult;
    }

    /**
     * Sets the value of the accessCheckResult property.
     * 
     */
    public void setAccessCheckResult(boolean value) {
        this.accessCheckResult = value;
    }

}
