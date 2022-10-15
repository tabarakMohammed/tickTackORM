package dbconnection;
import dbconnection.establishe.SqliteConnect;



public class DatabaseConnection implements IConnection  {
    @Override
    public void sqliteConnect(String sqlUrl) {
          SqliteConnect.setConnectionUrl(sqlUrl);
    }

}