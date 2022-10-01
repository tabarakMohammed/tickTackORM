package services.inserts.iinsert;

import java.util.List;

public interface IInsert<T> {
     int insertRow(T object);
     int insertMultiRow(List<T> object);
}
