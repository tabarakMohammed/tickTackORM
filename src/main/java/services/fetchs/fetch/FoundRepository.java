package services.fetchs.fetch;
import services.fetchs.ifetch.IFoundRepository;


import java.util.List;

public class FoundRepository<T> extends Fetch<T> implements IFoundRepository<T> {

  T _object;
    public FoundRepository(T object){
        this._object = object;
    }

    @Override
    public List<T> foundAll() {
        String sql = "Select * from "+_object.getClass().getSimpleName()+"";
        return start(_object,sql);
    }

    @Override
    public List<T> foundById(long id) {
        FetchById<T> _tFetchById = new FetchById<>(_object);
        return start(_object, _tFetchById.sqlSelect(id));
    }

    @Override
    public List<T> foundBySqlCommand(String sqlCommand) {
        return  start(_object,sqlCommand);
    }
}
