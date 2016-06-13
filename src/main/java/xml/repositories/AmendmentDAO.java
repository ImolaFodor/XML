package xml.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Repository;

import xml.model.Amandman;

@Repository("amandmanDAO")
public class AmendmentDAO implements IAmendmentDAO {

    public void create(Amandman amandman, String docId, String colId) throws FileNotFoundException, FileNotFoundException {

    }

    public void update(Amandman amandman, Long id) throws FileNotFoundException, FileNotFoundException {

    }

    public void delete(Long id, String constant) throws JAXBException, IOException {

    }

    public List<Amandman> getAll() throws FileNotFoundException, FileNotFoundException {
        return null;
    }

    public Amandman get(Long id) throws FileNotFoundException, FileNotFoundException {
        return null;
    }
}
