package services.deletes.idelete;

public interface IDeleteRepository<T> {

    int removeById(long id);
    int removeBySqlCommand(String sqlCommand);
}
