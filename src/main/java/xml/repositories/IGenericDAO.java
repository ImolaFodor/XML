package xml.repositories;

import xml.Constants;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;


public interface IGenericDAO<E,K extends Serializable> {
	void create(E entity, String docId, String colId ) throws JAXBException, IOException;
    void update(E entity,Long id) throws JAXBException, IOException;
    void delete(Long id, String constant) throws JAXBException, IOException;
    List<E> getAll() throws JAXBException, IOException;
    E get(Long id) throws JAXBException, IOException;
	E getEntityWithMaxId(String colId, String ns, String entity) throws JAXBException, IOException;
}