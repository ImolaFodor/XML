package xml.repositories;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xml.Constants;
import xml.model.Amandman;
import xml.model.Podamandman;

@Repository("amandmanDAO")
public class AmendmentDAO extends GenericDAO<Amandman,Long> implements IAmendmentDAO {

    @Autowired
    private IActDAO actDAO;
    private static final String AMENDMENT_SCHEMA_PATH = "./src/main/schema/amandman.xsd";

    public AmendmentDAO() throws IOException {
        super(AMENDMENT_SCHEMA_PATH,Constants.ProposedAmendmentCollection,Constants.Amendment,Constants.AmendmentNamespace);
    }


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
        .append("ns:Amandman[@IdAct = \"")
        .append(actId.toString())
        .append("\"]");

        ArrayList<Amandman> amendments = getByQuery(query.toString());

        return amendments;
    }

    /**
     * Metoda primenjuje amandman na akt
     * @param id Id amandmana
     */
    protected void applyAmendment(Long id) {

        try {
            Amandman amendment = get(id);
            for(Podamandman amendmentPart : amendment.getPodamandman()){
                applyOnAct(amendment.getIdAct(),amendment.getId(),amendmentPart.getId(),amendmentPart.getSadrzaj(),amendmentPart.getOperacija().toString());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void applyOnAct(Long actId, Long amemndmentId,Long amendmentPartId, Podamandman.Sadrzaj content, String operation) throws JAXBException, IOException {

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
                        .append("]\n");
            }

            if(content.getRStav() != null){
                xQuery
                        .append("/ns:Stav[@Id=")
                        .append(content.getRStav())
                        .append("]\n");
            }

            if(content.getRTacka() != null){
                xQuery
                        .append("/ns:Tacka[@broj=")
                        .append(content.getRTacka())
                        .append("]\n");
                if(content.getRPodtacka() != null){
                    xQuery
                            .append("/ns:Podtacka[@broj=")
                            .append(content.getRTacka())
                            .append("]\n");
                }
            }

            if(content.getRAlineja() != null){
                xQuery
                        .append("ns:Alineja[@Id=")
                        .append(content.getRAlineja())
                        .append("]\n");
            }
            xQuery.append(",");
            if(content.getGlavniDeo() != null)
                xQuery.append(getAmendmentContent(amemndmentId,amendmentPartId,true));
            else
                xQuery.append(getAmendmentContent(amemndmentId,amendmentPartId,false));

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
                        .append("]\n");
            }

            if(content.getRStav() != null){
                xQuery
                        .append("/ns:Stav[@Id=")
                        .append(content.getRStav())
                        .append("]\n");
            }

            if(content.getRTacka() != null){
                xQuery
                        .append("/ns:Tacka[@broj=")
                        .append(content.getRTacka())
                        .append("]\n");
                if(content.getRPodtacka() != null){
                    xQuery
                            .append("/ns:Podtacka[@broj=")
                            .append(content.getRTacka())
                            .append("]\n");
                }
            }

            if(content.getRAlineja() != null){
                xQuery
                        .append("/ns:Alineja[@Id=")
                        .append(content.getRAlineja())
                        .append("]\n");
            }

            xQuery.append(",");

            if(content.getGlavniDeo() != null)
                xQuery.append(getAmendmentContent(amemndmentId,amendmentPartId,true));
            else
                xQuery.append(getAmendmentContent(amemndmentId,amendmentPartId,false));

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


            if(content.getRStav() != null){
                xQuery
                        .append("/ns:Stav[@Id=")
                        .append(content.getRStav())
                        .append("]\n");
            }

            if(content.getRTacka() != null){
                xQuery
                        .append("/ns:Tacka[@broj=")
                        .append(content.getRTacka())
                        .append("]\n");
                if(content.getRPodtacka() != null){
                    xQuery
                            .append("/ns:Podtacka[@broj=")
                            .append(content.getRTacka())
                            .append("]\n");
                }
            }

            if(content.getRAlineja() != null){
                xQuery
                        .append("/ns:Alineja[@Id=")
                        .append(content.getRAlineja())
                        .append("]\n");
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
    protected String getAmendmentContent(Long id,Long amendmentPartId,boolean isGlavniDeo) {
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
                .append("[@Id = ");

        //String contetn = null;
        if(isGlavniDeo) {
            query.append(id.toString() + "]/ns:Podamandman[@Id = " + amendmentPartId + "]/ns:Sadrzaj/ns1:Glavni_deo/node()");
            //contetn = getStringByQuery(query.toString()).get(1);
        }else {
            query.append(id.toString() + "]/ns:Podamandman[@Id = " + amendmentPartId + "]/ns:Sadrzaj/node()");
            //contetn = getStringByQuery(query.toString()).get(0);
        }

        return getStringByQuery(query.toString());
    }
   


}
