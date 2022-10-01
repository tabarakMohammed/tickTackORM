package services.creates.icreate;

public interface ICreate<T> {

    int newTable(T object) throws Exception;

}
