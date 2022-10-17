package services.fetchs.fetch;


import services.fetchs.ifetch.IFetchAll;

import java.util.List;

public class FetchAll<T>  extends Fetch<T> implements IFetchAll<T> {
    T _object;
    public FetchAll(T object){
        this._object = object;
    }
    @Override
    public List<T> foundAll() {
        List<T> getData;
        String sql = "Select * from "+_object.getClass().getSimpleName()+"";
        getData  = start(_object,sql);
        return  getData;
    }
}
