package services.deletes.idelete;
/**
 *this container has method for starting remove data from tables
 *removeById remove using long ID primary key
 *removeBySqlCommand remove using string of sqlite command with values*
 * */
public interface IDeleteRepository<T> {

    int removeById(long id);
    int removeBySqlCommand(String sqlCommand);
}
