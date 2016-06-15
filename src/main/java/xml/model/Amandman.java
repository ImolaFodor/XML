package xml.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


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
 *         &lt;element name="Kontekst">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="actId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Operacija" type="{amandmani}TOperacija"/>
 *         &lt;element name="Sadrzaj">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{aktovi}Glavni_deo"/>
 *                 &lt;/sequence>
 *                 &lt;attGroup ref="{amandmani}GSadrzaj"/>
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
    "stanje",
    "kontekst",
    "operacija",
    "sadrzaj"
})
@XmlRootElement(name = "Amandman", namespace = "amandmani")
public class Amandman {

    @XmlElement(name = "Stanje", namespace = "amandmani", required = true)
    protected String stanje;
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
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="actId" type="{http://www.w3.org/2001/XMLSchema}long" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Kontekst {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "actId")
        protected Long actId;

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
         * Gets the value of the actId property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getActId() {
            return actId;
        }

        /**
         * Sets the value of the actId property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setActId(Long value) {
            this.actId = value;
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
     *       &lt;sequence>
     *         &lt;element ref="{aktovi}Glavni_deo"/>
     *       &lt;/sequence>
     *       &lt;attGroup ref="{amandmani}GSadrzaj"/>
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

        @XmlElement(name = "Glavni_deo", namespace = "aktovi", required = true)
        protected GlavniDeo glavniDeo;
        @XmlAttribute(name = "r_deo")
        protected BigInteger rDeo;
        @XmlAttribute(name = "r_glava")
        protected BigInteger rGlava;
        @XmlAttribute(name = "r_odeljak")
        protected BigInteger rOdeljak;
        @XmlAttribute(name = "r_clan")
        protected BigInteger rClan;
        @XmlAttribute(name = "r_stav")
        protected BigInteger rStav;
        @XmlAttribute(name = "r_tacka")
        protected BigInteger rTacka;

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

        /**
         * Gets the value of the rDeo property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getRDeo() {
            return rDeo;
        }

        /**
         * Sets the value of the rDeo property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setRDeo(BigInteger value) {
            this.rDeo = value;
        }

        /**
         * Gets the value of the rGlava property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getRGlava() {
            return rGlava;
        }

        /**
         * Sets the value of the rGlava property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setRGlava(BigInteger value) {
            this.rGlava = value;
        }

        /**
         * Gets the value of the rOdeljak property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getROdeljak() {
            return rOdeljak;
        }

        /**
         * Sets the value of the rOdeljak property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setROdeljak(BigInteger value) {
            this.rOdeljak = value;
        }

        /**
         * Gets the value of the rClan property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getRClan() {
            return rClan;
        }

        /**
         * Sets the value of the rClan property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setRClan(BigInteger value) {
            this.rClan = value;
        }

        /**
         * Gets the value of the rStav property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getRStav() {
            return rStav;
        }

        /**
         * Sets the value of the rStav property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setRStav(BigInteger value) {
            this.rStav = value;
        }

        /**
         * Gets the value of the rTacka property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getRTacka() {
            return rTacka;
        }

        /**
         * Sets the value of the rTacka property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setRTacka(BigInteger value) {
            this.rTacka = value;
        }

    }

}