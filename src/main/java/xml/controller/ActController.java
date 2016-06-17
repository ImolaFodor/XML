package xml.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

import org.apache.commons.httpclient.Header;
import org.apache.fop.apps.FopFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import database.DatabaseConfig;
import database.XMLConverter;
import util.RDFtoTriples;
import xml.Constants;
import xml.model.Korisnik;
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
				if (pa.getStanje().toString().equals(Constants.ProposedState.toString())) {
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
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/akt", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity post(@RequestBody PravniAkt object) {
		try {
			PravniAkt najveci = aktDao.getEntityWithMaxId(Constants.ActCollection, Constants.ActNamespace,
					Constants.Act);
			if (najveci == null) {
				object.setId((long) 1);
			} else {
				object.setId(najveci.getId() + 1);
			}
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Korisnik korisnik = (Korisnik) authentication.getPrincipal();
			object.getOvlascenoLice().setKoDodaje(korisnik.getUsername());
			object.setStanje(Constants.ProposedState);
			aktDao.create(object, Constants.Act + object.getId().toString(), Constants.ActCollection);
			
			// metadata - begin
			try{
				JAXBContext context = JAXBContext.newInstance(PravniAkt.class);
				Marshaller marshaller = context.createMarshaller();
	
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	
				String xmlPath = "gen/tempPostAkt.xml";
				File file = new File(xmlPath);
				marshaller.marshal(object, file);
	
				//create metadata
				String grddlPath =  "data/xsl/grddl.xsl";
				String sparqlNamedGraph = "/akti/metadata";
				String rdfFilePath = "gen/tempPostAkt.rdf";
				RDFtoTriples.convert(DatabaseConfig.loadProperties(), xmlPath, rdfFilePath, sparqlNamedGraph, grddlPath);
				}
			catch (Exception e)	{
				System.out.println("MetadataExtract:");
				e.printStackTrace();
			}
			// metadata - end
			
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
	
	@PreAuthorize("isAuthenticated()")
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
	
	@PreAuthorize("hasAuthority('Predsednik')")
	@RequestMapping(value = "/akt/prihvati/{id}", method = RequestMethod.PUT)
	public ResponseEntity prihvatiAkt(@PathVariable("id") Long id) {
		
		
		
	    
		try {
			PravniAkt akt = aktDao.get(id);
			if(akt == null){
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			
			aktDao.updateActState(id, Constants.AdoptedState);
			
			String url = "http://localhost:9090/addFile/";
			String html = "";
			try {
				 html = getAktHtml(akt);
			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String name = akt.getNaziv();
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair("html", html));
			urlParameters.add(new BasicNameValuePair("name", name));
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			
			try {
				Header head = new Header();
				post.addHeader("charset", "UTF-8");
				post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
				HttpResponse response = client.execute(post);
			} catch (ClientProtocolException e) {
				System.out.println("Nije usepelo povezivanje sa istorijskim arhivom");
			} catch (IOException e) {
				// TODO Auto-generated catch block
			} catch(Exception e){
				System.out.println("Nije usepelo povezivanje sa istorijskim arhivom");
			}
			return new ResponseEntity(HttpStatus.OK);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PreAuthorize("hasAuthority('Predsednik')")
	@RequestMapping(value = "/akt/odbij/{id}", method = RequestMethod.PUT)
	public ResponseEntity odbijAkt(@PathVariable("id") Long id) {
		try {
			PravniAkt akt = aktDao.get(id);
			if(akt == null){
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			
			aktDao.updateActState(id, Constants.NotAdoptedState);
			
			return new ResponseEntity(HttpStatus.OK);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(value = "/akt/openXHTML/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity getByXHTMLId(@PathVariable("id") Long id) {
		try {
			// Odmah obrisati sadrzaj
			PravniAkt akt = null;

			akt = aktDao.get(id);
			if (akt == null) {
				return new ResponseEntity<List<PravniAkt>>(HttpStatus.BAD_REQUEST);
			}
			
			String html = getAktHtml(akt);
			return new ResponseEntity(html, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/akt/openPDF/{id}", method = RequestMethod.GET)
	public ResponseEntity getByXHTMLIdPdf(@PathVariable("id") Long id, HttpServletResponse response) {

		// Initialize FOP factory object
		/*
		 * try { fopFactory = FopFactory.newInstance(new File("src/fop.xconf"));
		 * } catch (SAXException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 * 
		 * // Setup the XSLT transformer factory transformerFactory = new
		 * TransformerFactoryImpl();
		 * 
		 * // Point to the XSL-FO file File xsltFile = new
		 * File("data/xsl-fo/bookstore_fo.xsl");
		 * 
		 * // Create transformation source StreamSource transformSource = new
		 * StreamSource(xsltFile);
		 * 
		 * // Initialize the transformation subject StreamSource source = new
		 * StreamSource(new File("data/xsl-fo/bookstore.xml"));
		 * 
		 * // Initialize user agent needed for the transformation FOUserAgent
		 * userAgent = fopFactory.newFOUserAgent();
		 * 
		 * // Create the output stream to store the results
		 * ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		 * 
		 * // Initialize the XSL-FO transformer object Transformer
		 * xslFoTransformer = null; try { xslFoTransformer =
		 * transformerFactory.newTransformer(transformSource); } catch
		 * (TransformerConfigurationException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * // Construct FOP instance with desired output format Fop fop = null;
		 * try { fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent,
		 * outStream); } catch (FOPException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * // Resulting SAX events Result res = null; try { res = new
		 * SAXResult(fop.getDefaultHandler()); } catch (FOPException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * // Start XSLT transformation and FOP processing try {
		 * xslFoTransformer.transform(source, res); } catch
		 * (TransformerException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * // Generate PDF file File pdfFile = new
		 * File("gen/bookstore_result.pdf"); OutputStream out = null; try { out
		 * = new BufferedOutputStream(new FileOutputStream(pdfFile)); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } try { out.write(outStream.toByteArray()); }
		 * catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * try { System.out.println("[INFO] File \"" +
		 * pdfFile.getCanonicalPath() + "\" generated successfully."); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } try { out.close(); } catch (IOException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * System.out.println("[INFO] End.");
		 */

		try {
			/*
			 * String html = ""; BufferedReader in = new BufferedReader(new
			 * FileReader("/src/main/webapp/generatedHtmlFiles/aktt.html"));
			 * String str; while ((str = in.readLine()) != null) { html +=str; }
			 * in.close();
			 */

			PravniAkt akt = null;

			akt = aktDao.get(id);
			if (akt == null) {
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
			// StreamResult result = new StreamResult(outputStream);

			StreamSource ss = new StreamSource(file);
			StreamResult sr = new StreamResult(outputStream);

			tf.transform(ss, sr);

			String html = new String(outputStream.toString(XMLConverter.UTF_8.name()));
			outputStream.close();

			OutputStream filepdf = new FileOutputStream(new File("src/main/webapp/generatedHtmlFiles/akt.pdf"));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, filepdf);
			document.open();
			InputStream is = new ByteArrayInputStream(html.getBytes(XMLConverter.UTF_8.name()));

			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));
			document.close();
			filepdf.close();

			try {

				response.setContentType("application/pdf; charset=UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=akt.pdf");
				File xls = new File("src/main/webapp/generatedHtmlFiles/akt.pdf");
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
				System.out.println("Error writing file to output stream. Filename was akt.pdf");
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
	
	private String getAktHtml(PravniAkt akt) throws JAXBException, TransformerException, IOException{
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

		StreamSource ss = new StreamSource(file);
		StreamResult sr = new StreamResult(outputStream);

		tf.transform(ss, sr);

		String html = new String(outputStream.toString(XMLConverter.UTF_8.name()));
		outputStream.close();
		
		return html;
	}
}
