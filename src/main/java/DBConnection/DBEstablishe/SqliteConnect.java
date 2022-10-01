package DBConnection.DBEstablishe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnect {
    private static Connection conn = null;
    static String  url;

   public static void setConnectionUrl(String _url){
        url=_url;
    }
    public SqliteConnect()   {


        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("one catch ... > " + e.getMessage());
            System.exit(1);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("one catch ... > " + e.getMessage());
            System.exit(1);
        }
    }

    public static Connection getConnect()  {

        if (conn == null) {

        new SqliteConnect();

        }
        return conn;
    }

    public static void closeConnect() throws SQLException {

        if (conn != null) {

            conn.close();
            System.out.println("connction clossed");

        }

    }


}
