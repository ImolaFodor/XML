
package xml.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for TReferenca complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TReferenca">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attGroup ref="{aktovi}AReference"/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TReferenca", namespace = "aktovi", propOrder = {
    "value"
})
public class TReferenca {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "ref_akt")
    protected String refAkt;
    @XmlAttribute(name = "ref_clan")
    protected BigInteger refClan;
    @XmlAttribute(name = "ref_stav")
    protected BigInteger refStav;
    @XmlAttribute(name = "ref_tacka")
    protected BigInteger refTacka;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the refAkt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefAkt() {
        return refAkt;
    }

    /**
     * Sets the value of the refAkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefAkt(String value) {
        this.refAkt = value;
    }

    /**
     * Gets the value of the refClan property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRefClan() {
        return refClan;
    }

    /**
     * Sets the value of the refClan property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRefClan(BigInteger value) {
        this.refClan = value;
    }

    /**
     * Gets the value of the refStav property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRefStav() {
        return refStav;
    }

    /**
     * Sets the value of the refStav property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRefStav(BigInteger value) {
        this.refStav = value;
    }

    /**
     * Gets the value of the refTacka property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRefTacka() {
        return refTacka;
    }

    /**
     * Sets the value of the refTacka property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRefTacka(BigInteger value) {
        this.refTacka = value;
    }

}
