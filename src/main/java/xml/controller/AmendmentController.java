package xml.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.Constants;
import xml.model.Amandman;
import xml.repositories.IAmendmentDAO;


@RestController
public class AmendmentController{

    @Autowired
    private IAmendmentDAO amendmentDao;

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
        	System.out.println("DOdavanje amandmana 2");
        	amendment.getKontekst().setActId(aktId);
            amendmentDao.create(amendment, Constants.Amendment+amendment.getId().toString(), Constants.ProposedAmendmentCollection);
            System.out.println("DOdavanje amandmana");
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
    @RequestMapping(value = "/amandman/openXHTML/{id}", method = RequestMethod.GET, produces =MediaType.TEXT_HTML_VALUE)
	public ResponseEntity getByXHTMLId(@PathVariable("id") Long id) {
    	
    	/*
    	 * GENERATE AMANDMAN HTML BY XSLT
    	 */
    	
    	String html = "<div> <h1>IMPLEMENTIRAJ ME :)</h1></div>";
    	return new ResponseEntity(html, HttpStatus.OK);
    }

}
