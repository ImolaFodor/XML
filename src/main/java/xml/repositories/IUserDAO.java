package xml.repositories;

import xml.model.Korisnik;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IUserDAO extends IGenericDAO<Korisnik,Long> {

    void create(Korisnik korisnik, String docId, String colId ) throws JAXBException, IOException;
    void update(Korisnik korisnik,Long id) throws JAXBException, IOException;
    void delete(Korisnik korisnik) throws JAXBException, IOException;
    List<Korisnik> getAll() throws JAXBException, IOException;

    public Korisnik getByLogin(String username,String password) throws IOException, JAXBException;
}
