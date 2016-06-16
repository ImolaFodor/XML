package queries;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.semantics.SPARQLMimeTypes;
import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;

import database.DatabaseConfig;
import database.DatabaseConfig.ConnectionProperties;


public class SparqlQueryExecutor {

	/**
	 * Reading file contents into a string.
	 */
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	/**
	 * Executes query
	 * @param props - connection properties
	 * @param metadataCollection - metadata collection
	 * @param useFilter - true if you want to filter your results
	 * @return
	 */
	public static String execute(ConnectionProperties props, String path) {
		String query = null;
		try {
			query = readFile(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println(query);
		
		DatabaseClient client = DatabaseClientFactory.newClient(props.host,
				props.port, props.database, props.user, props.password,
				DatabaseClientFactory.Authentication.DIGEST);
		
		// Create a SPARQL query manager to query RDF datasets
		SPARQLQueryManager sparqlQueryManager = client.newSPARQLQueryManager();

		JacksonHandle resultsHandle = new JacksonHandle();
		resultsHandle.setMimetype(SPARQLMimeTypes.SPARQL_JSON);
		
		SPARQLQueryDefinition queryDef = sparqlQueryManager.newQueryDefinition(query);
		
		System.out.println("executeSelect:");
		resultsHandle = sparqlQueryManager.executeSelect(queryDef, resultsHandle);

		client.release();		
		System.out.println("RESULT execute:");
		return resultsHandle.get().toString();
	}
	
	/**
	 * Executes query
	 * @param props - connection properties
	 * @param metadataCollection - metadata collection
	 * @param useFilter - true if you want to filter your results
	 * @return
	 */
	public static String executeFromString(ConnectionProperties props, String query) {
		
		DatabaseClient client = DatabaseClientFactory.newClient(props.host,
				props.port, props.database, props.user, props.password,
				props.authType);
		
		// Create a SPARQL query manager to query RDF datasets
		SPARQLQueryManager sparqlQueryManager = client.newSPARQLQueryManager();
		
		JacksonHandle resultsHandle = new JacksonHandle();
		resultsHandle.setMimetype(SPARQLMimeTypes.SPARQL_JSON);
		
		SPARQLQueryDefinition queryDef = sparqlQueryManager.newQueryDefinition(query);
		
		System.out.println("executeSelect:");
		resultsHandle = sparqlQueryManager.executeSelect(queryDef, resultsHandle);

		client.release();
		System.out.println("RESULT execute:");
		return resultsHandle.get().toString();
	}
/*	
	public static void main(String[] args) {
		String path =  "data/sparql/amendmentsForActs.rq";
		try {
			execute(DatabaseConfig.loadProperties(), path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
}
