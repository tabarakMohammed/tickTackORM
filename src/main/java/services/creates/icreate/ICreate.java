package services.creates.icreate;
/**create class for creation in database*/
public interface ICreate<T> {
   /** create new table in database take instance of object T generic with @MakeTable annotation
    * and there fields must have @SqliteColumn annotation*/
    int newTable(T object) throws Exception;

}
