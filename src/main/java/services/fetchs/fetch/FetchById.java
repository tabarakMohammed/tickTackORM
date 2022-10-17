package services.fetchs.fetch;

import services.fetchs.ifetch.IFetchById;

import java.util.List;

public class FetchById<T> extends Fetch<T> implements IFetchById<T>  {
    T _object;
    public FetchById(T object){
        this._object = object;
    }
    @Override
    public List<T> found(long id) {
        List<T> getData;
        String sql = "select * from " +_object.getClass().getSimpleName()+ " where id = "+id+"";
         getData = start(_object,sql);
        return  getData;
    }
}
