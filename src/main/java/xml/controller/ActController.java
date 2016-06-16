package xml.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.xalan.processor.TransformerFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import database.XMLConverter;
import xml.Constants;
import xml.model.PravniAkt;
import xml.repositories.IActDAO;

@RestController
public class ActController {

	@Autowired
	private IActDAO aktDao;

	private FopFactory fopFactory;

	private TransformerFactory transformerFactory;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/akt", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravniAkt>> getAll() {
		try {
			List<PravniAkt> akati = aktDao.getAll();
			/*
			 * if (akati.size() == 0) return new
			 * ResponseEntity<List<PravniAkt>>(HttpStatus.NO_CONTENT);
			 */
			return new ResponseEntity<List<PravniAkt>>(akati, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PravniAkt>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/akt/getPredlozeni/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravniAkt>> getPredlozeni() {
		try {
			List<PravniAkt> akti = aktDao.getAll();
			ArrayList<PravniAkt> predlozeni = new ArrayList<PravniAkt>();
			for (PravniAkt pa : akti) {

				System.out.println(pa.getStanje());
				if (pa.getStanje().toString().equals(Constants.ProposedState.toString())) {
					System.out.println(pa.getNaziv());
					predlozeni.add(pa);
				}
			}
			return new ResponseEntity<List<PravniAkt>>(predlozeni, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
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

	@RequestMapping(value = "/akt/openXHTML/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity getByXHTMLId(@PathVariable("id") Long id) {
		try {
			//Odmah obrisati sadrzaj
			System.out.println(id);
			PravniAkt akt = null;
			
			akt = aktDao.get(id);
			if (akt == null){
				return new ResponseEntity<List<PravniAkt>>(HttpStatus.BAD_REQUEST);
			}
			JAXBContext context = JAXBContext.newInstance(PravniAkt.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file = new File("xhtmlFiles/AktMarshalled.xml");
			marshaller.marshal(akt, file);
			System.out.println("Usao");

			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer(new StreamSource(new File("xhtmlFiles/akt.xslt")));
			String phyPath = this.request.getSession().getServletContext().getRealPath(File.pathSeparator);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			//StreamResult result = new StreamResult(outputStream);
			
			StreamSource ss = new StreamSource(file);
			StreamResult sr = new StreamResult(outputStream);

			tf.transform(ss, sr);
			
			//FileInputStream inputStream = new FileInputStream(sr);
			String html = new String(outputStream.toString(XMLConverter.UTF_8.name()));
			outputStream.close();
			return new ResponseEntity(html, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/akt/openPDF/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getByXHTMLIdPdf(@PathVariable("id") Long id) {

		// Initialize FOP factory object
		try {
			fopFactory = FopFactory.newInstance(new File("src/fop.xconf"));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Setup the XSLT transformer factory
		transformerFactory = new TransformerFactoryImpl();

		// Point to the XSL-FO file
		File xsltFile = new File("data/xsl-fo/bookstore_fo.xsl");

		// Create transformation source
		StreamSource transformSource = new StreamSource(xsltFile);

		// Initialize the transformation subject
		StreamSource source = new StreamSource(new File("data/xsl-fo/bookstore.xml"));

		// Initialize user agent needed for the transformation
		FOUserAgent userAgent = fopFactory.newFOUserAgent();

		// Create the output stream to store the results
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		// Initialize the XSL-FO transformer object
		Transformer xslFoTransformer = null;
		try {
			xslFoTransformer = transformerFactory.newTransformer(transformSource);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Construct FOP instance with desired output format
		Fop fop = null;
		try {
			fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
		} catch (FOPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Resulting SAX events
		Result res = null;
		try {
			res = new SAXResult(fop.getDefaultHandler());
		} catch (FOPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Start XSLT transformation and FOP processing
		try {
			xslFoTransformer.transform(source, res);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Generate PDF file
		File pdfFile = new File("gen/bookstore_result.pdf");
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(pdfFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.write(outStream.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println("[INFO] File \"" + pdfFile.getCanonicalPath() + "\" generated successfully.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("[INFO] End.");

		return new ResponseEntity(HttpStatus.OK);
	}

}
