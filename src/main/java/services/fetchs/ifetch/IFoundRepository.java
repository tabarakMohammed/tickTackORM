package services.fetchs.ifetch;

import java.util.List;

public interface IFoundRepository<T>  {

    List<T> foundAll();
    List<T> found(long id);
    List<T> foundByQuery(String query);

}
