package services.inserts.iinsert;

import java.util.List;

/**
 * Inserting one List of object T in database */
public interface IMultiRowInsert<T> {
    int insertMultiRow(List<T> object);
}
