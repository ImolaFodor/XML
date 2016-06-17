package xml.repositories;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import xml.model.PravniAkt;


public interface IActDAO extends IGenericDAO<PravniAkt,Long>{
	public ArrayList<PravniAkt> getProposedActs() throws JAXBException, IOException;
    public ArrayList<PravniAkt> getAdoptedActs() throws JAXBException, IOException;
    public void updateActState(Long id,String state) throws IOException;
    public ArrayList<PravniAkt> getProposedActsToChangeState() throws JAXBException, IOException;
    public ArrayList<PravniAkt> searchByText(String criteria,String collection) throws IOException;

}