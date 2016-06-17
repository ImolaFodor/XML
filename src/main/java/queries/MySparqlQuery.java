package queries;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.semantics.SPARQLMimeTypes;
import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;

import database.DatabaseConfig;
import database.DatabaseConfig.ConnectionProperties;

public class MySparqlQuery {
	public static final String AMANDMAN = "amandmani";
	public static final String AKTI = "akti";
	
	private static final String PROPERTY = "akti/predicate/";

	private String type;
	private String naziv;
	private String mesto;
	private String datumMin;
	private String datumMax;
	private String stanje;
	private String operator = " && ";

	/**
	 * Executes query
	 * 
	 * @param props
	 *            - connection properties
	 * @param metadataCollection
	 *            - metadata collection
	 * @param useFilter
	 *            - true if you want to filter your results
	 * @return
	 */
	public String execute(ConnectionProperties props, String metadataCollection, boolean useFilter) {
		String query = makeQuery(metadataCollection, useFilter);

		// System.out.println(query);

		DatabaseClient client = DatabaseClientFactory.newClient(props.host, props.port, props.database, props.user,
				props.password, DatabaseClientFactory.Authentication.DIGEST);

		// Create a SPARQL query manager to query RDF datasets
		SPARQLQueryManager sparqlQueryManager = client.newSPARQLQueryManager();

		JacksonHandle resultsHandle = new JacksonHandle();
		resultsHandle.setMimetype(SPARQLMimeTypes.SPARQL_JSON);

		SPARQLQueryDefinition queryDef = sparqlQueryManager.newQueryDefinition(query);

		resultsHandle = sparqlQueryManager.executeSelect(queryDef, resultsHandle);

		client.release();

		return resultsHandle.get().toString();
	}

	/**
	 * Creates query
	 * 
	 * @param metadataCollection
	 *            - metadata collection
	 * @param useFilter
	 *            - true if you want to filter your results
	 * @return query
	 */
	private String makeQuery(String metadataCollection, boolean useFilter) {
		StringBuilder query = new StringBuilder();

		query.append("PREFIX xs:     <http://www.w3.org/2001/XMLSchema#>\n");
		query.append("SELECT * FROM <" + metadataCollection + ">\n");
		query.append("WHERE{\n");
		query.append(selectTemplate("naziv"));
		query.append(selectTemplate("datum"));
		query.append(selectTemplate("stanje"));
		query.append(selectTemplate("mesto"));

		if (useFilter) {
			query.append("FILTER (" + regexTemplate("?akt", type)
					+ operator + regexTemplate("?naziv", naziv) + " " + operator + "(?datum >= \"" + datumMin
					+ "\"^^xs:date && ?datum <= \"" + datumMax + "\"^^xs:date)" + " " + operator
					+ regexTemplate("?stanje", stanje) + " " + operator + regexTemplate("?mesto", mesto) + "))\n}");
		} else
			query.append("\n}");
		System.out.println(query.toString());
		return query.toString();
	}

	/**
	 * 
	 * @param what
	 * @return
	 */
	private String selectTemplate(String what) {
		return "?akt <" + PROPERTY + what + ">?" + what + " .\n";
	}

	/**
	 * 
	 * @param onWhat
	 * @param value
	 * @return
	 */
	private String regexTemplate(String onWhat, String value) {
		return "regex(" + onWhat + ",\".*" + value + ".*\",\"i\")";
	}

	/**
	 * 
	 * @param type
	 */
	public MySparqlQuery(String type) {
		super();
		this.type = type;
		this.naziv = "";
		this.mesto = "";
		this.datumMin = "1970-01-01";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		this.datumMax = dateFormat.format(date);
		this.stanje = "";
	}

	/**
	 * 
	 * @param type
	 * @param oznaka
	 * @param naziv
	 * @param mesto
	 * @param datum
	 * @param vrsta
	 */
	public MySparqlQuery(String type, String naziv, String mesto, String datumMin, String datumMax,
			String stanje) {
		super();
		this.type = type;
		this.naziv = naziv;
		this.mesto = mesto;
		this.datumMin = datumMin.equals("") ? "1970-01-01" : datumMin;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		this.datumMax = datumMax.equals("") ? dateFormat.format(date) : datumMax;
		this.stanje = stanje;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getDatumMin() {
		return datumMin;
	}

	public void setDatumMin(String datumMin) {
		this.datumMin = datumMin.equals("") ? "1970-01-01" : datumMin;
	}

	public String getDatumMax() {
		return datumMax;
	}

	public void setDatumMax(String datumMax) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		this.datumMax = datumMax.equals("") ? dateFormat.format(date) : datumMax;
	}

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String vrsta) {
		this.stanje = stanje;
	}

	public String getType() {
		return type;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

/*
	public static void main(String[] args) {
		//String metadataCollection = "/propisi/akti/doneti/metadata";
		String metadataCollection = "/usvojeniAkati";
		//boolean useFilter = true;
		boolean useFilter = false;

		MySparqlQuery msq = new MySparqlQuery(SVE_AAAAA, "", "", "", "", "", "");
		try {
			System.out.println(msq.execute(DatabaseConfig.loadProperties(), metadataCollection, useFilter));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}
