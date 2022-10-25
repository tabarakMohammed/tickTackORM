package services.fetchs.ifetch;

import java.util.List;

public interface IFoundRepository<T>  {

    List<T> foundAll();
    List<T> foundById(long id);
    List<T> foundBySqlCommand(String sqlCommand);

}
