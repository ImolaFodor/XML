
package xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TOvlasceno_lice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TOvlasceno_lice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Titula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Prezime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Ko_dodaje" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOvlasceno_lice", namespace = "aktovi", propOrder = {
    "titula",
    "ime",
    "prezime"
})
public class TOvlascenoLice {

    @XmlElement(name = "Titula", namespace = "aktovi", required = true)
    protected String titula;
    @XmlElement(name = "Ime", namespace = "aktovi", required = true)
    protected String ime;
    @XmlElement(name = "Prezime", namespace = "aktovi", required = true)
    protected String prezime;
    @XmlAttribute(name = "Ko_dodaje")
    protected String koDodaje;

    /**
     * Gets the value of the titula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitula() {
        return titula;
    }

    /**
     * Sets the value of the titula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitula(String value) {
        this.titula = value;
    }

    /**
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the prezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets the value of the prezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezime(String value) {
        this.prezime = value;
    }

    /**
     * Gets the value of the koDodaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKoDodaje() {
        return koDodaje;
    }

    /**
     * Sets the value of the koDodaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKoDodaje(String value) {
        this.koDodaje = value;
    }

}
