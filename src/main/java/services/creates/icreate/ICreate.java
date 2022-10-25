package services.creates.icreate;
/**
 * creation table and apple changes on existing table
 * create new table in database take instance of object T generic with @MakeTable annotation
 * and there fields must have @SqliteColumn annotation*/
public interface ICreate<T> {

    int newTable(T object) throws Exception;

}
