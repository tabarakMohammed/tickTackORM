package dbconnection.establishe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnect {
    private static Connection connection = null;
    static String  url;


    public SqliteConnect(String _url)   {
        try {
            url=_url;
            Class.forName("org.sqlite.JDBC").newInstance();
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
           e.printStackTrace();
            System.exit(1);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection getConnect() {
        if (connection != null) {
            new SqliteConnect(url);
       }
        return connection;
    }

    public static void closeConnect() throws SQLException {

        if (connection != null) {
            connection.close();
        }

    }


}
