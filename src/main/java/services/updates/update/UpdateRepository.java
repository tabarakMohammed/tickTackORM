package services.updates.update;
import services.updates.iupdate.IUpdateRepository;

public class UpdateRepository<T> extends Update<T> implements IUpdateRepository<T> {

    T _object;

    public  UpdateRepository( T object){
        this._object = object;
    }
    @Override
    public int updateBySqlCommand(String sqlCommand) {
        return update(_object,sqlCommand);
    }

    @Override
    public int updateById(long id) {
        String sqlCommand = PreparedUpdateData.sqliteUpdateQuery(_object)+id;
        return update(_object,sqlCommand);
    }
}
