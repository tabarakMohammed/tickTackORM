package services.fetchs.ifetch;

import java.util.List;
/**core of Fetching data from data base */
public interface IFetch<T> {
    List<T> start(T object,String sqlSelect);
}
