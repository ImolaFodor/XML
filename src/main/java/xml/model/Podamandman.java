
package xml.model;

import java.math.BigInteger;
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
 *         &lt;element name="Operacija" type="{amandmani}TOperacija"/>
 *         &lt;element name="Sadrzaj">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element ref="{aktovi}Glavni_deo"/>
 *                   &lt;element ref="{aktovi}Stav"/>
 *                   &lt;element ref="{aktovi}Tacka"/>
 *                   &lt;element ref="{aktovi}Podtacka"/>
 *                   &lt;element ref="{aktovi}Alineja"/>
 *                 &lt;/choice>
 *                 &lt;attGroup ref="{amandmani}GSadrzaj"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "operacija",
    "sadrzaj"
})
@XmlRootElement(name = "Podamandman", namespace = "amandmani")
public class Podamandman {

    @XmlElement(name = "Operacija", namespace = "amandmani", required = true)
    @XmlSchemaType(name = "string")
    protected TOperacija operacija;
    @XmlElement(name = "Sadrzaj", namespace = "amandmani", required = true)
    protected Podamandman.Sadrzaj sadrzaj;
    @XmlAttribute(name = "Id")
    protected Long id;

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
     *     {@link Podamandman.Sadrzaj }
     *     
     */
    public Podamandman.Sadrzaj getSadrzaj() {
        return sadrzaj;
    }

    /**
     * Sets the value of the sadrzaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Podamandman.Sadrzaj }
     *     
     */
    public void setSadrzaj(Podamandman.Sadrzaj value) {
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
     *         &lt;element ref="{aktovi}Stav"/>
     *         &lt;element ref="{aktovi}Tacka"/>
     *         &lt;element ref="{aktovi}Podtacka"/>
     *         &lt;element ref="{aktovi}Alineja"/>
     *       &lt;/choice>
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
        "glavniDeo",
        "stav",
        "tacka",
        "podtacka",
        "alineja"
    })
    public static class Sadrzaj {

        @XmlElement(name = "Glavni_deo", namespace = "aktovi")
        protected GlavniDeo glavniDeo;
        @XmlElement(name = "Stav", namespace = "aktovi")
        protected Stav stav;
        @XmlElement(name = "Tacka", namespace = "aktovi")
        protected Tacka tacka;
        @XmlElement(name = "Podtacka", namespace = "aktovi")
        protected Podtacka podtacka;
        @XmlElement(name = "Alineja", namespace = "aktovi")
        protected Alineja alineja;
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
        @XmlAttribute(name = "r_podtacka")
        protected BigInteger rPodtacka;
        @XmlAttribute(name = "r_alineja")
        protected BigInteger rAlineja;

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
         * Gets the value of the stav property.
         * 
         * @return
         *     possible object is
         *     {@link Stav }
         *     
         */
        public Stav getStav() {
            return stav;
        }

        /**
         * Sets the value of the stav property.
         * 
         * @param value
         *     allowed object is
         *     {@link Stav }
         *     
         */
        public void setStav(Stav value) {
            this.stav = value;
        }

        /**
         * Gets the value of the tacka property.
         * 
         * @return
         *     possible object is
         *     {@link Tacka }
         *     
         */
        public Tacka getTacka() {
            return tacka;
        }

        /**
         * Sets the value of the tacka property.
         * 
         * @param value
         *     allowed object is
         *     {@link Tacka }
         *     
         */
        public void setTacka(Tacka value) {
            this.tacka = value;
        }

        /**
         * Gets the value of the podtacka property.
         * 
         * @return
         *     possible object is
         *     {@link Podtacka }
         *     
         */
        public Podtacka getPodtacka() {
            return podtacka;
        }

        /**
         * Sets the value of the podtacka property.
         * 
         * @param value
         *     allowed object is
         *     {@link Podtacka }
         *     
         */
        public void setPodtacka(Podtacka value) {
            this.podtacka = value;
        }

        /**
         * Gets the value of the alineja property.
         * 
         * @return
         *     possible object is
         *     {@link Alineja }
         *     
         */
        public Alineja getAlineja() {
            return alineja;
        }

        /**
         * Sets the value of the alineja property.
         * 
         * @param value
         *     allowed object is
         *     {@link Alineja }
         *     
         */
        public void setAlineja(Alineja value) {
            this.alineja = value;
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

        /**
         * Gets the value of the rPodtacka property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getRPodtacka() {
            return rPodtacka;
        }

        /**
         * Sets the value of the rPodtacka property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setRPodtacka(BigInteger value) {
            this.rPodtacka = value;
        }

        /**
         * Gets the value of the rAlineja property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getRAlineja() {
            return rAlineja;
        }

        /**
         * Sets the value of the rAlineja property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setRAlineja(BigInteger value) {
            this.rAlineja = value;
        }

    }

}