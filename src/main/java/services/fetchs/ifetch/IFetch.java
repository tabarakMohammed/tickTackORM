package services.fetchs.ifetch;

import java.util.List;

public interface IFetch<T> {
    List<T> start(T object,String sqlSelect);
}
