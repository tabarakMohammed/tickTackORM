package dbconnection;

/**
 * make connection with database by
 * set the path of database sqlite file */
public interface IConnection {
     void sqliteConnect( String sqlUrl );
}
