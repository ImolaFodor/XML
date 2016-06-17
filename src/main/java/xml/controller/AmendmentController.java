package xml.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import database.XMLConverter;
import xml.Constants;
import xml.model.Amandman;
import xml.model.PravniAkt;
import xml.repositories.IActDAO;
import xml.repositories.IAmendmentDAO;

@RestController
public class AmendmentController {

	@Autowired
	private IAmendmentDAO amendmentDao;
	@Autowired
	private IActDAO actDao;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/amandman/{aktId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Amandman>> getAllAmendmentsFromAct(Long aktId) {
		try {
			List<Amandman> amendments = null;// amendmentDao.getAllFromAct(aktId);
			if (amendments.size() == 0)
				return new ResponseEntity(HttpStatus.NO_CONTENT);

			return new ResponseEntity<List<Amandman>>(amendments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/amandman/{aktId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Amandman> post(@RequestBody Amandman amendment, @PathVariable("aktId") long aktId) {
		System.out.println("DOdavanje amandmana");
		try {
			Amandman najveci = amendmentDao.getEntityWithMaxId(Constants.ProposedAmendmentCollection,
					Constants.AmendmentNamespace, Constants.Amendment);
			if (najveci == null) {
				amendment.setId((long) 1);
			} else {
				amendment.setId(najveci.getId() + 1);
			}

			amendment.setIdAct(aktId);
			amendmentDao.create(amendment, Constants.Amendment + amendment.getId().toString(),
					Constants.ProposedAmendmentCollection);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/amandman", method = RequestMethod.GET)
	public ResponseEntity getAll() {
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
	public ResponseEntity getByAkt(@PathVariable("aktId") long aktId) {
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

	@RequestMapping(value = "amandman/brisi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteAmandman(@PathVariable("id") Long id) {

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

	@RequestMapping(value = "amandman/prihvati/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity prihvatiAmandman(@PathVariable("id") Long id) {

		try {
			Amandman am = amendmentDao.get(id);
			if (am == null) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			amendmentDao.applyAmendment(id);
			PravniAkt pa = actDao.get(am.getIdAct());
			amendmentDao.delete(id, Constants.Amendment);
			return new ResponseEntity(pa, HttpStatus.OK);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "amandman/odbij/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity odbijAmandman(@PathVariable("id") Long id) {

		try {
			Amandman am = amendmentDao.get(id);
			if (am == null) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
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

	@RequestMapping(value = "/amandman/openXHTML/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity getByXHTMLId(@PathVariable("id") Long id) {
		try {
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
	@RequestMapping(value = "/amandman/openPDF/{id}", method = RequestMethod.GET)
	public ResponseEntity getByXHTMLIdPdf(@PathVariable("id") Long id, HttpServletResponse response) {

		try {

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
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			StreamSource ss = new StreamSource(file);
			StreamResult sr = new StreamResult(outputStream);

			tf.transform(ss, sr);

			String html = new String(outputStream.toString(XMLConverter.UTF_8.name()));
			outputStream.close();

			OutputStream filepdf = new FileOutputStream(new File("src/main/webapp/generatedHtmlFiles/amandman.pdf"));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, filepdf);
			document.open();
			InputStream is = new ByteArrayInputStream(html.getBytes(XMLConverter.UTF_8.name()));

			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));
			document.close();
			filepdf.close();

			try {

				response.setContentType("application/pdf; charset=UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=amandman.pdf");
				File xls = new File("src/main/webapp/generatedHtmlFiles/amandman.pdf");
				FileInputStream in = new FileInputStream(xls);
				OutputStream out = response.getOutputStream();

				byte[] buffer = new byte[8192]; // use bigger if you want
				int length = 0;

				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
				in.close();
				out.close();
				response.flushBuffer();
				System.out.println("DOWNLOADING");
			} catch (IOException ex) {
				System.out.println("Error writing file to output stream. Filename was amandman.pdf");
				throw new RuntimeException("IOError writing file to output stream");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity(HttpStatus.OK);
	}

}
