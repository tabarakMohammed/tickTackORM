package dbconnection.establishe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnect {
    private static Connection connection = null;
    static String  url;

    public static void setConnectionUrl(String _url){
        url=_url;
    }
    public SqliteConnect()   {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            connection = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
           e.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection getConnect() {
        if(connection == null) {
            new SqliteConnect();
        }
        return connection;
    }

    public static void closeConnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }


}
