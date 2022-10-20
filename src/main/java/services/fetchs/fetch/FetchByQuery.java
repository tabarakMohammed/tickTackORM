package services.fetchs.fetch;
import services.fetchs.ifetch.IFetchByQuery;
import java.util.List;


public class FetchByQuery<T> extends Fetch<T> implements IFetchByQuery<T> {
    T _object;
    public FetchByQuery(T object){
        this._object = object;
    }

    @Override
    public List<T> found(String query) {
        List<T> getData;
        getData = start(_object,query);
        return  getData;
    }
}
