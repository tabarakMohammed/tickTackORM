package services.insertPkg.interFace;

import java.util.List;

public interface interFace<T> {
     int insertRow(T object);
     int insertMultiRow(List<T> object);
}
