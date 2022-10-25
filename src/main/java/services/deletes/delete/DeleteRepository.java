package services.deletes.delete;
import services.deletes.idelete.IDeleteRepository;


public class DeleteRepository<T> extends Delete implements IDeleteRepository<T> {

    T _object;
   public DeleteRepository(T object)  {
        this._object = object;
   }
    @Override
    public int removeById(long id)  {
        String sqlDelete;
        int status = 0;
        DeleteById<T> _tDeleteById = new DeleteById<>(_object);
        sqlDelete =  _tDeleteById.sqlDeleteById(id);

        if(!sqlDelete.isEmpty()){
            status = beginRemove(sqlDelete);
        }
        return status;
    }


    @Override
    public int removeBySqlCommand(String sqlCommand) {
        return beginRemove(sqlCommand);
    }
}
