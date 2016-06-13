package xml.repositories;

import org.springframework.stereotype.Repository;
import xml.Constants;
import xml.model.PravniAkt;

import javax.xml.bind.JAXBException;
import java.io.IOException;


@Repository("pravniAktDAO")
public class ActDAO extends GenericDAO<PravniAkt,Long> implements IActDAO {

    private static final String USER_SCHEMA_PATH = "./src/main/schema/akt.xsd";

    public ActDAO() throws IOException {
        super(USER_SCHEMA_PATH, Constants.ActCollection , Constants.Act);
    }
}
