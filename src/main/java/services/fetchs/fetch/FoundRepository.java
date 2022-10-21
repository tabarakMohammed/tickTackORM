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
    public List<T> found(long id) {
        String sql = "select * from " +_object.getClass().getSimpleName()+ " where id = "+id+"";
        return  start(_object,sql);
    }

    @Override
    public List<T> foundByQuery(String query) {
        return  start(_object,query);
    }
}
