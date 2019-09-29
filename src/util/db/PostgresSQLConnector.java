package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Babi
 */
public class PostgresSQLConnector implements ConnectionFactory {

    public final String databaseDriver = "org.postgresql.Driver";
    private final static String databaseURL = "jdbc:postgresql://host:5432/AddressApp";
    private final static String user = "postgres";
    private final static String password = "123456";

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(databaseDriver);
        return DriverManager.getConnection(databaseURL, user, password);
    }
    
}
