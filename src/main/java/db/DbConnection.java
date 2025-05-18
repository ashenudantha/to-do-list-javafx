package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;

    public DbConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toDoApp", "root", "1234");
    }
    public Connection getConnection(){
        return connection;
    }
    public static DbConnection getInstance() throws SQLException {
        return instance ==null?instance=new     DbConnection():instance;
    }
}
