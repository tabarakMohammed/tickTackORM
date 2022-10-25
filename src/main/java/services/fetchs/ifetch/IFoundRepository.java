package services.fetchs.ifetch;

import java.util.List;
/** multi method fetching data from database by using core fetch
 * foundAll return List of object T has all data rows from table
 * FoundById return List of object T has all data rows from table using long Id Primary key
 * foundBySqlCommand List of object T has all data rows from table executing String sqlite Command with values*/
public interface IFoundRepository<T>  {

    List<T> foundAll();
    List<T> foundById(long id);
    List<T> foundBySqlCommand(String sqlCommand);

}
