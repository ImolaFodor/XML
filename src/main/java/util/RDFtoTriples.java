package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.FileHandle;
import com.marklogic.client.semantics.GraphManager;
import com.marklogic.client.semantics.RDFMimeTypes;

import database.DatabaseConfig;
import database.DatabaseConfig.ConnectionProperties;
import grddl.MetadataExtractor;

/**
 * Inicijalizacija RDF store-a.
 * 
 * Primenom GRDDL transformacije vrši se ekstrakcija RDF tripleta iz XML 
 * dokumenta "data/xml/contacts.xml" i inicijalizacija imenovanog grafa
 * "example/sparql/metadata" ekstrahovanim tripletima.
 * 
 */
public class RDFtoTriples {

	private static DatabaseClient client;

	public static void convert(ConnectionProperties props, String xmlFilePath, String rdfFilePath, String sparqlNamedGraph, String grddlPath) throws IOException, SAXException, TransformerException {

		if (props.database.equals("")) {
			client = DatabaseClientFactory.newClient(props.host, props.port, props.user, props.password, DatabaseClientFactory.Authentication.DIGEST);
		} else {
			client = DatabaseClientFactory.newClient(props.host, props.port, props.database, props.user, props.password, DatabaseClientFactory.Authentication.DIGEST);
		}

		// Create a document manager to work with XML files.
		GraphManager graphManager = client.newGraphManager();

		// Set the default media type (RDF/XML)
		graphManager.setDefaultMimetype(RDFMimeTypes.RDFXML);

		// Automatic extraction of RDF triples from XML file
		MetadataExtractor metadataExtractor = new MetadataExtractor();

		metadataExtractor.extractMetadata(
				new FileInputStream(new File(xmlFilePath)), 
				new FileOutputStream(new File(rdfFilePath)),
				grddlPath);

		// A handle to hold the RDF content.
		FileHandle rdfFileHandle =
				new FileHandle(new File(rdfFilePath))
				.withMimetype(RDFMimeTypes.RDFXML);
		
		// Writing the named graph
		//graphManager.write(sparqlNamedGraph, rdfFileHandle);
		graphManager.merge(sparqlNamedGraph, rdfFileHandle);


		// Read the triples from the named graph
		System.out.println();
		System.out.println("[INFO] Retrieving triples from RDF store.");
		System.out.println("[INFO] Using \"" + sparqlNamedGraph + "\" named graph.");

		// Define a DOM handle instance to hold the results 
		DOMHandle domHandle = new DOMHandle();

		// Retrieve RDF triplets in format (RDF/XML) other than default
		graphManager.read(sparqlNamedGraph, domHandle).withMimetype(RDFMimeTypes.RDFXML);

		// Serialize document to the standard output stream
		System.out.println("[INFO] Rendering triples as \"application/rdf+xml\".");
		DOMUtil.transform(domHandle.get(), System.out);

		// Release the client
		client.release();

		System.out.println("[INFO] End.");
	}
/*
	public static void main(String[] args) throws Exception {
		// Referencing XML file with RDF data in attributes
		//String xmlFilePath = "data/xml/ref_test.xml";
		String xmlFilePath = "data/xml/PravniAktMeta.xml";

		//String rdfFilePath = "gen/ref_test_metadata.rdf";
		String rdfFilePath = "gen/PravniAktMeta.rdf";

		String sparqlNamedGraph = "/akti/predlozeni/metadata";
		
		String grddlPath = "data/xsl/grddl.xsl";

		convert(DatabaseConfig.loadProperties(), xmlFilePath, rdfFilePath, sparqlNamedGraph, grddlPath);
	}
*/
}
