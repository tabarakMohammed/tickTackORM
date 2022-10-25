package services.updates.iupdate;
/**
 * core update data to set it into database*/
public interface IUpdate<T> {
    int update(T object,String SqlUpdate);
}
