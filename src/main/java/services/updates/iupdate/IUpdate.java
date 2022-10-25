package services.updates.iupdate;

public interface IUpdate<T> {
    int update(T object,String SqlUpdate);
}
