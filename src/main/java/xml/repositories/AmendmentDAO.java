package xml.repositories;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import database.XMLConverter;
import javassist.convert.Transformer;
import xml.Constants;
import xml.model.Amandman;
import xml.model.PravniAkt;

@Repository("amandmanDAO")
public class AmendmentDAO extends GenericDAO<Amandman,Long> implements IAmendmentDAO {

    @Autowired
    private IActDAO actDAO;
    private static final String AMENDMENT_SCHEMA_PATH = "./src/main/schema/amandman.xsd";
    private static final String AMENDMENT_XSL_PATH = "./src/main/schema/amandman.xsl";

    public AmendmentDAO() throws IOException {
        super(AMENDMENT_SCHEMA_PATH,Constants.ProposedAmendmentCollection,Constants.Amendment,Constants.AmendmentNamespace);
    }

    /**
     * Metoda reprezentuje glasanje na sednici
     * @param actsIds Id-jevi akata koji su izglasani za usvajanje
     * @param amendmentsIds Id-jevi amandmana koji su izglasani za usvajanje
     */
   /* @Override
    public void voting(ArrayList<Long> actsIds,ArrayList<Long> amendmentsIds) throws JAXBException, IOException {

        copyActToAdopted(actsIds);
        changeStateToNotAdopted();

        for(Long id : amendmentsIds){
            applyAmendment(id);
        }
    }*/

    @Override
    public ArrayList<Amandman> getAmendmentsForAct(Long actId) throws JAXBException, IOException {
        StringBuilder query = new StringBuilder();
        query
                .append("declare namespace ns = ")
                .append("\"")
                .append(Constants.AmendmentNamespace)
                .append("\";\n")
                .append("collection(\"")
                .append(Constants.ProposedAmendmentCollection)
                .append("\")/")
                .append("ns:Amandman/ns:Kontekst[@actId = \"")
                .append(actId.toString())
                .append("\"]/parent::ns:Amandman");

        ArrayList<Amandman> amendments = getByQuery(query.toString());

        return amendments;
    }

    

    /**
     * Metoda kopira sve izglasane predlozene aktove u usvojene aktove
     * from collection(/predlozeniAkati) to collection(/usvojeniAkati)
     * @param actsIds Id-jevi akata koji su izglasani za usvajanje
     */
   /* protected void copyActToAdopted(ArrayList<Long> actsIds){
        for(Long id : actsIds){
            try {
            	
                PravniAkt act = actDAO.get(id);
                act.setStanje(Constants.AdoptedState);
                actDAO.updateActState(id,Constants.AdoptedState);
                actDAO.create(act,Constants.AdoptedAct+act.getId().toString(),Constants.ActCollection);
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * Metoda primenjuje amandman na akt
     * @param id Id amandmana
     */
    protected void applyAmendment(Long id) {

        try {
            Amandman amendment = get(id);
            applyOnAct(amendment.getKontekst().getActId(),amendment.getId(), amendment.getSadrzaj(), amendment.getOperacija().toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * U zavisnosti od operacije, vrsi se dodavanje/brisanje izmena akta iz collection("usvojeniAkati")
     * Metoda generise xQuery
     * @param actId Id akta
     * @param amemndmentId Id amandmana
     * @param content Sadrzaj iz amandmana koji ce se primeniti na akt u zavisnosti od operacije
     * @param operation Operacija amandmana (dodavanje, brisanje, izmena)
     * @throws JAXBException
     * @throws IOException
     */
    protected void applyOnAct(Long actId,Long amemndmentId, Amandman.Sadrzaj content, String operation) throws JAXBException, IOException {

        StringBuilder xQuery = new StringBuilder();

        if(operation.equals(Constants.Add)){
            xQuery
                    .append("declare namespace ns = \"aktovi\";\n")
                    .append("xdmp:node-insert-after(collection(\"/usvojeniAkati\")/ns:")
                    .append(Constants.Act)
                    .append("[@Id = ")
                    .append(actId.toString())
                    .append("]/")
                    .append("ns:Glavni_deo/");

            if(content.getRDeo() != null){
                xQuery
                        .append("ns:Deo[@Id=")
                        .append(content.getRDeo())
                        .append("]/");
            }

            if(content.getRGlava() != null){
                xQuery
                        .append("ns:Glava[@Id=")
                        .append(content.getRGlava())
                        .append("]/");

            }

            if(content.getROdeljak() != null){
                xQuery
                        .append("ns:Odeljak[@Id=")
                        .append(content.getROdeljak())
                        .append("]/");
            }

            if(content.getRClan() != null){
                xQuery
                        .append("ns:Clan[@Broj_clana=")
                        .append(content.getRClan())
                        .append("],\n");
            }

            xQuery.append(getAmendmentContent(amemndmentId));
            xQuery.append(");");

            System.out.print(xQuery.toString());
            execQuery(xQuery.toString());


        }else if(operation.equals(Constants.Update)){
            xQuery
                    .append("declare namespace ns = \"aktovi\";\n")
                    .append("xdmp:node-replace(collection(\"/usvojeniAkati\")/ns:")
                    .append(Constants.Act)
                    .append("[@Id = ")
                    .append(actId.toString())
                    .append("]/")
                    .append("ns:Glavni_deo/");

            if(content.getRDeo() != null){
                xQuery
                        .append("ns:Deo[@Id=")
                        .append(content.getRDeo())
                        .append("]/");
            }

            if(content.getRGlava() != null){
                xQuery
                        .append("ns:Glava[@Id=")
                        .append(content.getRGlava())
                        .append("]/");

            }

            if(content.getROdeljak() != null){
                xQuery
                        .append("ns:Odeljak[@Id=")
                        .append(content.getROdeljak())
                        .append("]/");
            }

            if(content.getRClan() != null){
                xQuery
                        .append("ns:Clan[@Broj_clana=")
                        .append(content.getRClan())
                        .append("],\n");
            }

            xQuery.append(getAmendmentContent(amemndmentId));
            xQuery.append(");");

            System.out.print(xQuery.toString());
            execQuery(xQuery.toString());

        }else if (operation.equals(Constants.Delete)){
            xQuery
                    .append("declare namespace ns = \"aktovi\";\n")
                    .append("xdmp:node-delete(collection(\"/usvojeniAkati\")/ns:")
                    .append(Constants.Act)
                    .append("[@Id = ")
                    .append(actId.toString())
                    .append("]/")
                    .append("ns:Glavni_deo/");

            if(content.getRDeo() != null){
                xQuery
                        .append("ns:Deo[@Id=")
                        .append(content.getRDeo())
                        .append("]/");
            }

            if(content.getRGlava() != null){
                xQuery
                        .append("ns:Glava[@Id=")
                        .append(content.getRGlava())
                        .append("]/");

            }

            if(content.getROdeljak() != null){
                xQuery
                        .append("ns:Odeljak[@Id=")
                        .append(content.getROdeljak())
                        .append("]/");
            }

            if(content.getRClan() != null){
                xQuery
                        .append("ns:Clan[@Broj_clana=")
                        .append(content.getRClan())
                        .append("]");
            }

            xQuery.append(")");

            System.out.print(xQuery.toString());
            execQuery(xQuery.toString());
        }


    }

    /**
     * Metoda generise xQuery za dobijanje sadrzaja iz amandmana sa zadatim id-jem
     * @param id Id amandmana
     * @return String XML sadrzaj amandmana
     */
    protected String getAmendmentContent(Long id) {
        StringBuilder query = new StringBuilder();
        query
                .append("declare namespace ns = \"")
                .append("amandmani")
                .append("\";\n")
                .append("declare namespace ns1 = \"")
                .append("aktovi")
                .append("\";\n")
                .append("for $x in collection(\"")
                .append(Constants.ProposedAmendmentCollection)
                .append("\")\n")
                .append("return $x/ns:")
                .append("Amandman")
                .append("[@Id = ")
                .append(id.toString()+"]/ns:Sadrzaj/ns1:Glavni_deo/node()");
        String content = getStringByQuery(query.toString()).get(1);
        return content;
    }

	@Override
	public void voting(ArrayList<Long> actsIds, ArrayList<Long> amendmentsIds) throws JAXBException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getXsltDocument(Long id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Preuzima aktove koji nisu usvojeni i postavlja stanje na NotAdopted
     * @throws JAXBException
     * @throws IOException
     */
    /*protected void changeStateToNotAdopted() throws JAXBException, IOException {

        ArrayList<PravniAkt> acts = actDAO.getProposedActsToChangeState();
        for(PravniAkt act : acts){
            actDAO.updateActState(act.getId(),Constants.NotAdoptedState);
        }

    }*/

}
