
package xml.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TOperacija.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TOperacija">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="dodavanje"/>
 *     &lt;enumeration value="izmena"/>
 *     &lt;enumeration value="brisanje"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TOperacija", namespace = "amandmani")
@XmlEnum
public enum TOperacija {

    @XmlEnumValue("dodavanje")
    DODAVANJE("dodavanje"),
    @XmlEnumValue("izmena")
    IZMENA("izmena"),
    @XmlEnumValue("brisanje")
    BRISANJE("brisanje");
    private final String value;

    TOperacija(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TOperacija fromValue(String v) {
        for (TOperacija c: TOperacija.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
