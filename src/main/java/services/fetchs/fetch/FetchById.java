package services.fetchs.fetch;

import services.fetchs.ifetch.IFetch;
import services.fetchs.ifetch.IFetchById;

import java.util.List;

public class FetchById<T> implements IFetchById<T> {
    T _object;
    public FetchById(T object){
        this._object = object;
    }
    @Override
    public List<T> found(long id) {
        List<T> getData;
        String sql = "select * from " +_object.getClass().getSimpleName()+ " where id = "+id+"";
        IFetch<T> _fetch = new Fetch<>();
         getData = _fetch.start(_object,sql);
        return  getData;
    }
}
