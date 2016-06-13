package database;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;

import java.io.IOException;

/**
 * Created by Daniel on 6/9/2016.
 */
public class DatabaseManager {

    static public class Client{

        public static DatabaseClient getClient() throws IOException {
            DatabaseConfig.ConnectionProperties props = DatabaseConfig.loadProperties();

            if(props.database.equals("")){
                return DatabaseClientFactory.newClient(props.host, props.port, props.user, props.password, DatabaseClientFactory.Authentication.DIGEST);
            }else{
                return DatabaseClientFactory.newClient(props.host, props.port, props.database, props.user, props.password, DatabaseClientFactory.Authentication.DIGEST);
            }
        }
    }
}
