package xml.repositories;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.eval.EvalResult;
import com.marklogic.client.eval.EvalResultIterator;
import com.marklogic.client.eval.ServerEvaluationCall;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;
import database.DatabaseManager;
import database.XMLConverter;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public abstract class GenericDAO<T,K extends Serializable> implements IGenericDAO<T,K> {

    //fields
    protected XMLConverter<T> xmlConverter;
    protected DatabaseClient client;
    protected String collection;
    protected String entityName;
    protected String namespace;
    //

    //constructor

    public GenericDAO(String path,String collection,String entityName,String namespace) throws IOException {
        this.xmlConverter = new XMLConverter<T>(path);
        this.client = DatabaseManager.Client.getClient();
        this.collection = collection;
        this.entityName = entityName;
        this.namespace = namespace;
    }

    @Override
    public void create(T entity, String docId, String colId) throws JAXBException, IOException {
        add(entity,docId,colId);
    }

    @Override
    public void update(T entity, Long id) throws JAXBException {

        //throw new NotImplementedException();
    }

    @Override
    public void delete(Long id, String constant) throws JAXBException  {
        //document-delete("PravniAkt10")
        StringBuilder query = new StringBuilder();

        query
                .append("xdmp:document-delete(\"")
                .append(constant)
                .append(id.toString())
                .append("\")");

        System.out.print(query.toString());

        try {
            execQuery(query.toString());
        } catch (IOException e) {
            System.out.print("Cannot delete entity");
        }

    }

    @Override
    public List<T> getAll() throws JAXBException, IOException {
        StringBuilder query = new StringBuilder();

        query
                .append("collection(\"")
                .append(collection)
                .append("\")");

        return getByQuery(query.toString());
    }

    @Override
    public T get(Long id) throws JAXBException, IOException {
        StringBuilder query = new StringBuilder();

        /*
        query
                .append("collection(\"")
                .append(collection)
                .append("\")/")
                .append(entityName)
                .append("[@Id = ")
                .append(id.toString()+"]");
        */
        query
                .append("declare namespace ns = \"")
                .append(namespace)
                .append("\";\n")
                .append("for $x in collection(\"")
                .append(collection)
                .append("\")\n")
                .append("return $x/ns:")
                .append(entityName)
                .append("[@Id = ")
                .append(id.toString()+"]");

        System.out.print(query.toString());

        ArrayList<T> entities = getByQuery(query.toString());
        if(entities == null) {
            return null;
        }

        return entities.get(0);
    }

    /*@Override
    public T getEntityWithMaxId(String colId, String ns, String entity) throws JAXBException, IOException {
        StringBuilder query = new StringBuilder();

        query
                .append("declare namespace ns = \"")
                .append(ns)
                .append("\";\n")
                .append("let $id := ")
                .append("max(collection(\"")
                .append(colId)
                .append("\")/ns:")
                .append(entity)
                .append("/@Id)\n")
                .append("return collection(\"")
                .append(colId)
                .append("\")/ns:")
                .append(entity)
                .append("[@Id = $id]");

        EvalResultIterator iterator = null;

        ServerEvaluationCall invoker = client.newServerEval();
        invoker.xquery(query.toString());

        iterator = invoker.eval();

        ArrayList<T> list = new ArrayList<>();

        if(iterator.hasNext()){
            for(EvalResult res : iterator){
                list.add((T) xmlConverter.toObject(res.getString()));
            }
        }else
            return null;

        return list.get(0);
    }*/

    //helper methods
    protected ArrayList<T> getByQuery(String query) throws JAXBException, IOException {

        EvalResultIterator iterator = null;

        ServerEvaluationCall invoker = client.newServerEval();
        invoker.xquery(query);

        iterator = invoker.eval();

        ArrayList<T> list = new ArrayList<>();

        if(iterator.hasNext()){
            for(EvalResult res : iterator){
                list.add((T)xmlConverter.toObject(res.getString()));
            }
        }

        return list;
    }

    protected ArrayList<String> getStringByQuery(String query){
        EvalResultIterator iterator = null;

        ServerEvaluationCall invoker = client.newServerEval();
        invoker.xquery(query);

        iterator = invoker.eval();

        ArrayList<String> list = new ArrayList<>();

        if(iterator.hasNext()){
            for(EvalResult res : iterator){
                list.add(res.getString());
            }
        }else {
            return null;
        }
        return list;
    }

    protected  void execQuery(String query) throws IOException{
        ServerEvaluationCall invoker = client.newServerEval();
        invoker.xquery(query);
        invoker.eval();
    }

    protected  void add(T obj,String docId,String colId) throws IOException, JAXBException {
        XMLDocumentManager xmlManager = client.newXMLDocumentManager();
        DocumentMetadataHandle metadataHandle = new DocumentMetadataHandle();
        metadataHandle.getCollections().add(colId);

        InputStreamHandle handle = new InputStreamHandle(new ByteArrayInputStream(xmlConverter.toXML(obj).getBytes(XMLConverter.UTF_8.name())));
        xmlManager.write(docId,metadataHandle,handle);
    }



}