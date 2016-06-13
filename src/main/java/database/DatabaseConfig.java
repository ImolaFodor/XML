package database;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.marklogic.client.DatabaseClientFactory.Authentication;

public class DatabaseConfig {

    static public class ConnectionProperties {

        public String host;
        public int port = -1;
        public String user;
        public String password;
        public String database;
        public Authentication authType;

        public ConnectionProperties(Properties props) {
            host = props.getProperty("conn.host").trim();
            port = Integer.parseInt(props.getProperty("conn.port"));
            user = props.getProperty("conn.user").trim();
            password = props.getProperty("conn.pass").trim();
            database = props.getProperty("conn.database").trim();
            //authType = Authentication.valueOf(props.getProperty("conn.authentication_type").toUpperCase().trim());
        }

    }

    public static ConnectionProperties loadProperties() throws IOException {
        String propsName = "application.properties";

        InputStream propsStream = openStream(propsName);
        if (propsStream == null)
            throw new IOException("Could not read properties " + propsName);

        Properties props = new Properties();
        props.load(propsStream);

        return new ConnectionProperties(props);
    }

    public static InputStream openStream(String fileName) throws IOException {
        return DatabaseConfig.class.getClassLoader().getResourceAsStream(fileName);
    }

}
