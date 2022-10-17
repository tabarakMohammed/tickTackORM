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
    public List<T> found(Object element) {
        String sql = "select * from " +_object.getClass().getSimpleName()+ " where id = "+element+"";
        IFetch<T> _TestIFetchAll = new Fetch(_object);
        _TestIFetchAll.found(sql);
        return  _TestIFetchAll.found(sql);
    }
}
