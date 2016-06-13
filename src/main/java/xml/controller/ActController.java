package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.Constants;
import xml.model.PravniAkt;
import xml.repositories.IActDAO;

import javax.ws.rs.Path;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class ActController {

	@Autowired
	private IActDAO aktDao;

	@RequestMapping(value = "/akt", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravniAkt>> getAll() {
		try {
			List<PravniAkt> akati = aktDao.getAll();
			if (akati.size() == 0)
				return new ResponseEntity<List<PravniAkt>>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<List<PravniAkt>>(akati, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<PravniAkt>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/akt/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getById(@PathVariable("id") Long id) {
		try {
			PravniAkt akt = aktDao.get(id);
			if (akt == null)
				return new ResponseEntity<List<PravniAkt>>(HttpStatus.NO_CONTENT);

			return new ResponseEntity(akt, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/akt", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity post(@RequestBody PravniAkt object) {
		try {
			aktDao.create(object, Constants.Act + object.getId().toString(), Constants.ActCollection);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/akt/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity put(@RequestBody PravniAkt object, @PathVariable("id") Long id) {
		try {
			aktDao.update(object, id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/akt/brisi/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		System.out.print(id);
		try {
			aktDao.delete(id, Constants.Act);
			System.out.print("Successfully deleted from db");
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/akt/openXHTML/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getByXHTMLId(@PathVariable("id") Long id) {
		try {
			System.out.println(id);
			List<PravniAkt> akti = aktDao.getAll();
			PravniAkt akt = new PravniAkt();
			for(PravniAkt a: akti){
				if(a.getId() == id){
					akt = a;
				}
			}
			if (akt == null)
				return new ResponseEntity<List<PravniAkt>>(HttpStatus.NO_CONTENT);
			
			JAXBContext context = JAXBContext.newInstance(PravniAkt.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file = new File("xhtmlFiles/AktMarshalled.xml");
			marshaller.marshal(akt, file);
			System.out.println("Usao");

			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer(new StreamSource(new File("xhtmlFiles/akt.xslt")));
			StreamSource ss = new StreamSource(file);
			StreamResult sr = new StreamResult(new File("src/main/webapp/generatedHtmlFiles/akt.html"));

			tf.transform(ss, sr);

			

			return new ResponseEntity(akt, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
