package xml.repositories;

import xml.model.Amandman;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IAmendmentDAO extends IGenericDAO<Amandman,Long> {

	void create(Amandman amandman, String docId, String colId) throws FileNotFoundException, FileNotFoundException;

	void update(Amandman amandman, Long id) throws FileNotFoundException, FileNotFoundException;

	void delete(Long id, String constant) throws JAXBException, IOException;

	List<Amandman> getAll() throws FileNotFoundException, FileNotFoundException;

}
