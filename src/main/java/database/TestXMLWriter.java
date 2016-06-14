package database;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.InputStreamHandle;
import database.DatabaseConfig.ConnectionProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



public class TestXMLWriter {

    private static DatabaseClient client;

    public static void run(ConnectionProperties props) throws FileNotFoundException{

        if(props.database.equals("")){
            client = DatabaseClientFactory.newClient(props.host, props.port, props.user, props.password, DatabaseClientFactory.Authentication.DIGEST);
        }else{
            client = DatabaseClientFactory.newClient(props.host, props.port, props.database, props.user, props.password, DatabaseClientFactory.Authentication.DIGEST);
        }

        XMLDocumentManager xmlManager = client.newXMLDocumentManager();

        String docId = "/example1/books.xml";
        /*
        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }*/

        System.out.print("pre pravljenja input streama \n");
        InputStreamHandle handle = new InputStreamHandle(new FileInputStream("./src/main/resources/books.xml"));
        System.out.print("Pre pisanja u bazu");
        // Write the document to the database
        xmlManager.write(docId, handle);


        System.out.print("Upisao uspesno u bazu");

    }

   /* public static void main(String[] args) throws IOException {
        run(DatabaseConfig.loadProperties());
    }*/

}
