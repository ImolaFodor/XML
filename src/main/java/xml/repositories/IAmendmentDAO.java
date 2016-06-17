package xml.repositories;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import xml.model.Amandman;

public interface IAmendmentDAO extends IGenericDAO<Amandman,Long> {

    public ArrayList<Amandman> getAmendmentsForAct(Long actId) throws JAXBException, IOException;


}
