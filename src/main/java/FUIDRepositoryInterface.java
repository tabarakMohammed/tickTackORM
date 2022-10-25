import java.util.List;

/**
 * used to usage all the DQL and DML sample services of the library
 * Fitch,Update,Insert,Delete
 * */
public interface FUIDRepositoryInterface<T> {

    int insertSingleRow(T object);
    int insertMultiRow(List<T> object);

    List<T> foundAll();
    List<T> foundAllById(long id);
    List<T> foundAllBySqlCommandQuery( String sqlCommand);


    int deleteById(long id);
    int deleteBySqlCommand(String sqlCommand);

    int updateById(long id);
    int updateBySqlCommand(String sqlCommand);


}
