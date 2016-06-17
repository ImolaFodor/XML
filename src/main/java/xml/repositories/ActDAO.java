package xml.repositories;

import org.springframework.stereotype.Repository;
import xml.Constants;
import xml.model.PravniAkt;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;


@Repository("pravniAktDAO")
public class ActDAO extends GenericDAO<PravniAkt,Long> implements IActDAO {

	 private static final String USER_SCHEMA_PATH = "./src/main/schema/akt.xsd";
	    private static final String AKT_XSL_PATH = "./src/main/schema/akt.xsl";

	    public ActDAO() throws IOException {
	        super(USER_SCHEMA_PATH, Constants.ActCollection , Constants.Act ,Constants.ActNamespace);
	    }

	@Override
	public ArrayList<PravniAkt> getProposedActs() throws JAXBException, IOException {
		PravniAkt akt = new PravniAkt();
		
				
		return null;
	}

	@Override
	public ArrayList<PravniAkt> getAdoptedActs() throws JAXBException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public void updateActState(Long id,String state) throws IOException {
        StringBuilder query = new StringBuilder();
        query
                .append("declare namespace ns = ")
                .append("\"")
                .append(Constants.ActNamespace)
                .append("\";\n")
                .append("xdmp:node-replace(collection(\"")
                .append(Constants.ActCollection)
                .append("\")/ns:Pravni_akt[@Id = ")
                .append(id.toString())
                .append("]/ns:Stanje,")
                .append("<ns:Stanje>")
                .append(state)
                .append("</ns:Stanje>")
                .append(");");

        execQuery(query.toString());
    }

	@Override
	public ArrayList<PravniAkt> getProposedActsToChangeState() throws JAXBException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PravniAkt> searchByText(String criteria, String collection) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
