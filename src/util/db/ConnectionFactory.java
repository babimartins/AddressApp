package util.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Babi
 */
public interface ConnectionFactory {
    
    Connection getConnection() throws ClassNotFoundException, SQLException;

}
