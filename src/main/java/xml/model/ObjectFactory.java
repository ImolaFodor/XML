
package xml.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Reference_QNAME = new QName("aktovi", "Reference");
    private final static QName _Alineja_QNAME = new QName("aktovi", "Alineja");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Amandman }
     * 
     */
    public Amandman createAmandman() {
        return new Amandman();
    }

    /**
     * Create an instance of {@link PravniAkt }
     * 
     */
    public PravniAkt createPravniAkt() {
        return new PravniAkt();
    }

    /**
     * Create an instance of {@link Amandman.Kontekst }
     * 
     */
    public Amandman.Kontekst createAmandmanKontekst() {
        return new Amandman.Kontekst();
    }

    /**
     * Create an instance of {@link Amandman.Sadrzaj }
     * 
     */
    public Amandman.Sadrzaj createAmandmanSadrzaj() {
        return new Amandman.Sadrzaj();
    }

    /**
     * Create an instance of {@link Stav }
     * 
     */
    public Stav createStav() {
        return new Stav();
    }

    /**
     * Create an instance of {@link Tacka }
     * 
     */
    public Tacka createTacka() {
        return new Tacka();
    }

    /**
     * Create an instance of {@link Podtacka }
     * 
     */
    public Podtacka createPodtacka() {
        return new Podtacka();
    }

    /**
     * Create an instance of {@link TReferenca }
     * 
     */
    public TReferenca createTReferenca() {
        return new TReferenca();
    }

    /**
     * Create an instance of {@link GlavniDeo }
     * 
     */
    public GlavniDeo createGlavniDeo() {
        return new GlavniDeo();
    }

    /**
     * Create an instance of {@link Deo }
     * 
     */
    public Deo createDeo() {
        return new Deo();
    }

    /**
     * Create an instance of {@link Glava }
     * 
     */
    public Glava createGlava() {
        return new Glava();
    }

    /**
     * Create an instance of {@link Odeljak }
     * 
     */
    public Odeljak createOdeljak() {
        return new Odeljak();
    }

    /**
     * Create an instance of {@link Clan }
     * 
     */
    public Clan createClan() {
        return new Clan();
    }

    /**
     * Create an instance of {@link PravniAkt.Preambula }
     * 
     */
    public PravniAkt.Preambula createPravniAktPreambula() {
        return new PravniAkt.Preambula();
    }

    /**
     * Create an instance of {@link TOvlascenoLice }
     * 
     */
    public TOvlascenoLice createTOvlascenoLice() {
        return new TOvlascenoLice();
    }

    /**
     * Create an instance of {@link Korisnik }
     * 
     */
    public Korisnik createKorisnik() {
        return new Korisnik();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TReferenca }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "aktovi", name = "Reference")
    public JAXBElement<TReferenca> createReference(TReferenca value) {
        return new JAXBElement<TReferenca>(_Reference_QNAME, TReferenca.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "aktovi", name = "Alineja")
    public JAXBElement<String> createAlineja(String value) {
        return new JAXBElement<String>(_Alineja_QNAME, String.class, null, value);
    }

}
