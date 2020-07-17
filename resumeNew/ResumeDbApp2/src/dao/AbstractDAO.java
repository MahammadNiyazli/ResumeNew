package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {
    public Connection connect() throws SQLException, ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/resume";
        String userName = "root";
        String password = "0503771480m";
        Connection c = DriverManager.getConnection(url, userName, password);
        return c;
    }
}
