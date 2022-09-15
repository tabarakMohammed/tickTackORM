package databaseBank;


import databaseBank.makeEstablishe.sqliteConnect;


public class databaseConnection implements  connectionInterface{
    @Override
    public void sqliteConnect(String url) {sqliteConnect.setConnectionUrl(url);}

    @Override
    public void mySqlConnect(String url, String username, String password) {
    }
}
