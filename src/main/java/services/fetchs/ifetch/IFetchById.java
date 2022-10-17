package services.fetchs.ifetch;

import java.util.List;

public interface IFetchById<T> {
    List<T> found(long id);
}
