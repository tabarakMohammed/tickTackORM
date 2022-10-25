package services.updates.iupdate;

public interface IUpdateRepository<T> {

    int updateById(long id);
    int updateBySqlCommand(String sqlCommand);
}
