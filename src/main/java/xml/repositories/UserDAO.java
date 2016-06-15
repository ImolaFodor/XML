package xml.repositories;

import org.springframework.stereotype.Repository;
import xml.Constants;
import xml.model.Korisnik;

import javax.xml.bind.JAXBException;
import java.io.IOException;


@Repository("korisnikDAO")
public class UserDAO extends GenericDAO<Korisnik,Long> implements IUserDAO {

	private static final String USER_SCHEMA_PATH = "./src/main/schema/korisnici.xsd";

    public UserDAO() throws IOException {
        super(USER_SCHEMA_PATH,Constants.UsersCollection,Constants.User,Constants.UserNamespace);
    }


    @Override
    public void delete(Korisnik korisnik) throws JAXBException, IOException {

    }

    @Override
    public Korisnik getByLogin(String username, String password) throws IOException, JAXBException {
        StringBuilder query = new StringBuilder();

        query
                .append("collection(\"")
                .append(Constants.UsersCollection)
                .append("\")")
                .append("/Korisnik[Username=\"")
                .append(username)
                .append("\" and Password=\"")
                .append(password)
                .append("\"]");

        Korisnik k = null;
        try{
        	k = getByQuery(query.toString()).get(0);
        }catch(Exception e){
        	
        }
        return k;
    }


}
