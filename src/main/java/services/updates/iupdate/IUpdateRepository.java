package services.updates.iupdate;

/**
 * update data object T set into database
 * updateById update using long ID primary key
 * updateBySqlCommand update using string of sqlite command with values*/
public interface IUpdateRepository<T> {

    int updateById(long id);
    int updateBySqlCommand(String sqlCommand);
}
