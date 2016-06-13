
package xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="Kontekst">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Naziv_pravnog_akta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Operacija" type="{amandmani}TOperacija"/>
 *         &lt;element name="Sadrzaj">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element ref="{aktovi}Glavni_deo"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}long" />
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
    "kontekst",
    "operacija",
    "sadrzaj"
})
@XmlRootElement(name = "Amandman", namespace = "amandmani")
public class Amandman {

    @XmlElement(name = "Kontekst", namespace = "amandmani", required = true)
    protected Amandman.Kontekst kontekst;
    @XmlElement(name = "Operacija", namespace = "amandmani", required = true)
    @XmlSchemaType(name = "string")
    protected TOperacija operacija;
    @XmlElement(name = "Sadrzaj", namespace = "amandmani", required = true)
    protected Amandman.Sadrzaj sadrzaj;
    @XmlAttribute(name = "Id")
    protected Long id;
    @XmlAttribute(name = "Ko_dodaje")
    protected String koDodaje;

    /**
     * Gets the value of the kontekst property.
     * 
     * @return
     *     possible object is
     *     {@link Amandman.Kontekst }
     *     
     */
    public Amandman.Kontekst getKontekst() {
        return kontekst;
    }

    /**
     * Sets the value of the kontekst property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amandman.Kontekst }
     *     
     */
    public void setKontekst(Amandman.Kontekst value) {
        this.kontekst = value;
    }

    /**
     * Gets the value of the operacija property.
     * 
     * @return
     *     possible object is
     *     {@link TOperacija }
     *     
     */
    public TOperacija getOperacija() {
        return operacija;
    }

    /**
     * Sets the value of the operacija property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOperacija }
     *     
     */
    public void setOperacija(TOperacija value) {
        this.operacija = value;
    }

    /**
     * Gets the value of the sadrzaj property.
     * 
     * @return
     *     possible object is
     *     {@link Amandman.Sadrzaj }
     *     
     */
    public Amandman.Sadrzaj getSadrzaj() {
        return sadrzaj;
    }

    /**
     * Sets the value of the sadrzaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amandman.Sadrzaj }
     *     
     */
    public void setSadrzaj(Amandman.Sadrzaj value) {
        this.sadrzaj = value;
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
     *         &lt;element name="Naziv_pravnog_akta" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "nazivPravnogAkta"
    })
    public static class Kontekst {

        @XmlElement(name = "Naziv_pravnog_akta", namespace = "amandmani", required = true)
        protected String nazivPravnogAkta;

        /**
         * Gets the value of the nazivPravnogAkta property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNazivPravnogAkta() {
            return nazivPravnogAkta;
        }

        /**
         * Sets the value of the nazivPravnogAkta property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNazivPravnogAkta(String value) {
            this.nazivPravnogAkta = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element ref="{aktovi}Glavni_deo"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "glavniDeo"
    })
    public static class Sadrzaj {

        @XmlElement(name = "Glavni_deo", namespace = "aktovi")
        protected GlavniDeo glavniDeo;

        /**
         * Gets the value of the glavniDeo property.
         * 
         * @return
         *     possible object is
         *     {@link GlavniDeo }
         *     
         */
        public GlavniDeo getGlavniDeo() {
            return glavniDeo;
        }

        /**
         * Sets the value of the glavniDeo property.
         * 
         * @param value
         *     allowed object is
         *     {@link GlavniDeo }
         *     
         */
        public void setGlavniDeo(GlavniDeo value) {
            this.glavniDeo = value;
        }

    }

}
