
package https.api_baidu_com.sem.sms.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="isGenerated" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "isGenerated"
})
@XmlRootElement(name = "getReportStateResponse")
public class GetReportStateResponse {

    protected int isGenerated;

    /**
     * Gets the value of the isGenerated property.
     * 
     */
    public int getIsGenerated() {
        return isGenerated;
    }

    /**
     * Sets the value of the isGenerated property.
     * 
     */
    public void setIsGenerated(int value) {
        this.isGenerated = value;
    }

}
