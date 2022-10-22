package services.updates.update.iupdate;

public interface IUpdate<T> {
    int update(T object,String SqlUpdate);
}
