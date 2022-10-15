package services.inserts.iinsert;

import java.util.List;

public interface IMultiRowInsert<T> {
    int insertMultiRow(List<T> object);
}
