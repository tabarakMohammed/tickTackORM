package services.fetchs.ifetch;

import java.util.List;

public interface IFetchByQuery<T> {
    List<T> found(String query);

}
