
package xml.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="Stanje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Kontekst" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{amandmani}Podamandman" maxOccurs="unbounded"/>
 *         &lt;element name="Obrazlozenje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Ovlasceno_lice" type="{aktovi}TOvlasceno_lice"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="IdAct" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="Ko_dodaje" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stanje",
    "kontekst",
    "podamandman",
    "obrazlozenje",
    "ovlascenoLice"
})
@XmlRootElement(name = "Amandman", namespace = "amandmani")
public class Amandman {

    @XmlElement(name = "Stanje", namespace = "amandmani", required = true)
    protected String stanje;
    @XmlElement(name = "Kontekst", namespace = "amandmani", required = true)
    protected String kontekst;
    @XmlElement(name = "Podamandman", namespace = "amandmani", required = true)
    protected List<Podamandman> podamandman;
    @XmlElement(name = "Obrazlozenje", namespace = "amandmani", required = true)
    protected String obrazlozenje;
    @XmlElement(name = "Ovlasceno_lice", namespace = "amandmani", required = true)
    protected TOvlascenoLice ovlascenoLice;
    @XmlAttribute(name = "Id")
    protected Long id;
    @XmlAttribute(name = "IdAct")
    protected Long idAct;
    @XmlAttribute(name = "Ko_dodaje")
    protected String koDodaje;

    /**
     * Gets the value of the stanje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStanje() {
        return stanje;
    }

    /**
     * Sets the value of the stanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStanje(String value) {
        this.stanje = value;
    }

    /**
     * Gets the value of the kontekst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKontekst() {
        return kontekst;
    }

    /**
     * Sets the value of the kontekst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKontekst(String value) {
        this.kontekst = value;
    }

    /**
     * Gets the value of the podamandman property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the podamandman property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPodamandman().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Podamandman }
     * 
     * 
     */
    public List<Podamandman> getPodamandman() {
        if (podamandman == null) {
            podamandman = new ArrayList<Podamandman>();
        }
        return this.podamandman;
    }

    /**
     * Gets the value of the obrazlozenje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObrazlozenje() {
        return obrazlozenje;
    }

    /**
     * Sets the value of the obrazlozenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObrazlozenje(String value) {
        this.obrazlozenje = value;
    }

    /**
     * Gets the value of the ovlascenoLice property.
     * 
     * @return
     *     possible object is
     *     {@link TOvlascenoLice }
     *     
     */
    public TOvlascenoLice getOvlascenoLice() {
        return ovlascenoLice;
    }

    /**
     * Sets the value of the ovlascenoLice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOvlascenoLice }
     *     
     */
    public void setOvlascenoLice(TOvlascenoLice value) {
        this.ovlascenoLice = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the idAct property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAct() {
        return idAct;
    }

    /**
     * Sets the value of the idAct property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAct(Long value) {
        this.idAct = value;
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