package util.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Babi
 */
public class ConnectionManager {
    
    private static ConnectionManager onlyInstance;
    private static ConnectionFactory connectionFactory;
    
    private ConnectionManager(){
        connectionFactory = new PostgresSQLConnector();
    }
    
    public static ConnectionManager getInstance(){
        if(onlyInstance == null){
            onlyInstance = new ConnectionManager();
        }
        return onlyInstance;
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return connectionFactory.getConnection();
    }
    
}
