import java.util.List;

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
