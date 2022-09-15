package databaseBank;

import java.sql.Connection;

public interface connectionInterface {
     void sqliteConnect( String url );
     void mySqlConnect(String url,String username, String password);
}
