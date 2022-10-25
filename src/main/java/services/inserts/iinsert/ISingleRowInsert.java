package services.inserts.iinsert;
/**
 * Inserting one object T in database */
public interface ISingleRowInsert<T> {
     int insertRow(T object);
}
