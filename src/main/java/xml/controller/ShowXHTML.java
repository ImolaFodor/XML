package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import xml.Constants;
import xml.model.PravniAkt;
import xml.repositories.IActDAO;

import javax.ws.rs.Path;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
public class ShowXHTML{

    @Autowired
    private IActDAO aktDao;

    @RequestMapping(value = "/akt/openXHTML/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try{
            PravniAkt akt = aktDao.get(id);
            System.out.println("nESTO");
            JAXBContext context = JAXBContext.newInstance("main.java.xml.model");
         // Marshaller je objekat zadužen za konverziju iz objektnog u XML model
         			Marshaller marshaller = context.createMarshaller();
         			
         			// Podešavanje marshaller-a
         			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
         			
         			// Umesto System.out-a, može se koristiti FileOutputStream
         			File file = new File( "src\\main\\resources\\marshalled.xml" );
         			marshaller.marshal(akt, file);
         			System.out.println("Usao");
           
            	TransformerFactory tff= TransformerFactory.newInstance();
            	Transformer tf= tff.newTransformer(new StreamSource(new File("src\\main\\schema\\akt.xslt")));
            	StreamSource ss= new StreamSource(new File("src\\main\\resources\\marshalled.xml"));
            	StreamResult sr= new StreamResult(new File("src\\webapp\\module\\akt\\akt.html"));
            	
            	tf.transform(ss,sr);
            
            
            if(akt == null)
                return new ResponseEntity<List<PravniAkt>>(HttpStatus.NO_CONTENT);

            return new ResponseEntity(akt,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
