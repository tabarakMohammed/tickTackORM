package services.fetchs.fetch;

import services.fetchs.ifetch.IFetch;
import services.fetchs.ifetch.IFetchAll;

import java.util.List;

public class FetchAll<T> implements IFetchAll<T> {
    T _object;
    public FetchAll(T object){
        this._object = object;
    }
    @Override
    public List<T> foundAll() {
        List<T> getData;
        String sql = "Select * from "+_object.getClass().getSimpleName()+"";
        IFetch<T> _fetch = new Fetch<>();
        getData  = _fetch.start(_object,sql);
        return  getData;
    }
}
