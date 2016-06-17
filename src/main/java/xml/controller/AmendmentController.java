package xml.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import database.XMLConverter;
import xml.Constants;
import xml.model.Amandman;
import xml.model.PravniAkt;
import xml.repositories.IAmendmentDAO;


@RestController
public class AmendmentController{

    @Autowired
    private IAmendmentDAO amendmentDao;
    
    @Autowired
	private HttpServletRequest request;

    @RequestMapping(value = "/amandman/{aktId}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Amandman>> getAllAmendmentsFromAct(Long aktId) {
        try{
            List<Amandman> amendments = null;//amendmentDao.getAllFromAct(aktId);
            if(amendments.size() == 0)
                return new ResponseEntity(HttpStatus.NO_CONTENT);

            return new ResponseEntity<List<Amandman>>(amendments,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/amandman/{aktId}" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Amandman> post(@RequestBody Amandman amendment, @PathVariable("aktId") long aktId) {
    	System.out.println("DOdavanje amandmana");
        try{
        	Amandman najveci = amendmentDao.getEntityWithMaxId(Constants.ProposedAmendmentCollection, Constants.AmendmentNamespace, Constants.Amendment);
        	if(najveci == null){
        		amendment.setId((long)1);
        	}else{
        		amendment.setId(najveci.getId()+1);
        	}
        	
        	amendment.setIdAct(aktId);
            amendmentDao.create(amendment, Constants.Amendment+amendment.getId().toString(), Constants.ProposedAmendmentCollection);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
        	e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/amandman", method = RequestMethod.GET)
    public ResponseEntity getAll(){
    	List<Amandman> amandmani = new ArrayList<Amandman>();
		try {
			amandmani = amendmentDao.getAll();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ResponseEntity(amandmani, HttpStatus.OK);
    }
    @RequestMapping(value = "/amandman/getByAkt/{aktId}", method = RequestMethod.GET)
    public ResponseEntity getByAkt(@PathVariable("aktId") long aktId){
    	List<Amandman> amandmani = new ArrayList<Amandman>();
		try {
			amandmani = amendmentDao.getAmendmentsForAct(aktId);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ResponseEntity(amandmani, HttpStatus.OK);
    }
    @RequestMapping(value ="amandman/brisi/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAmandman(@PathVariable("id") Long id){
    	
    	try {
			amendmentDao.delete(id, Constants.Amendment);
			return new ResponseEntity(HttpStatus.OK);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return new ResponseEntity(HttpStatus.BAD_REQUEST);
    	
    }
    @RequestMapping(value = "/amandman/openXHTML/{id}", method = RequestMethod.GET, produces =MediaType.TEXT_HTML_VALUE)
	public ResponseEntity getByXHTMLId(@PathVariable("id") Long id) {
    	try{
    	// Odmah obrisati sadrzaj
    				Amandman amandman = null;

    				amandman = amendmentDao.get(id);
    				if (amandman == null) {
    					return new ResponseEntity<List<Amandman>>(HttpStatus.BAD_REQUEST);
    				}
    				JAXBContext context = JAXBContext.newInstance(Amandman.class);
    				Marshaller marshaller = context.createMarshaller();

    				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

    				File file = new File("xhtmlFiles/AmandmanMarshalled.xml");
    				marshaller.marshal(amandman, file);
    				System.out.println("Usao");

    				TransformerFactory tff = TransformerFactory.newInstance();
    				Transformer tf = tff.newTransformer(new StreamSource(new File("src/main/schema/amandman.xslt")));
    				String phyPath = this.request.getSession().getServletContext().getRealPath(File.pathSeparator);
    				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    				StreamSource ss = new StreamSource(file);
    				StreamResult sr = new StreamResult(outputStream);

    				tf.transform(ss, sr);

    				String html = new String(outputStream.toString(XMLConverter.UTF_8.name()));
    				outputStream.close();
    				return new ResponseEntity(html, HttpStatus.OK);
    			} catch (Exception e) {
    				System.out.println(e.toString());
    				e.printStackTrace();
    				return new ResponseEntity(HttpStatus.BAD_REQUEST);
    			}
    }

}
