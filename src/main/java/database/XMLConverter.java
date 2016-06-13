package database;

import com.sun.imageio.plugins.common.InputStreamAdapter;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Dorian on 1.6.2016.
 */
public class XMLConverter<T> {

    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private JAXBContext jaxbContext;
    private Schema schema;

    public XMLConverter(String path){
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            schema = schemaFactory.newSchema(new File(path));
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    public String toXML(T entity) throws JAXBException, IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        jaxbContext = JAXBContext.newInstance("xml.model");
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING,UTF_8.name());
        marshaller.setSchema(schema);
        marshaller.marshal(entity,os);
        //close stream
        os.close();
        return os.toString(UTF_8.name());
    }

    public T toObject(String xml) throws JAXBException, IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes(UTF_8));
        jaxbContext = JAXBContext.newInstance("xml.model");
        unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);
        T object = (T) unmarshaller.unmarshal(is);
        is.close();
        return object;
    }

}
