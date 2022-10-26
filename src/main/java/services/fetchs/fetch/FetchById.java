package services.fetchs.fetch;

import services.creates.acreate.SqliteColumn;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FetchById<T> {

    T _object;

    FetchById(T object) {
        this._object = object;
    }

    String sqlSelect(long id) {

        List<Field> objectField;

        objectField = Arrays.stream(_object.getClass().getDeclaredFields()).
                filter(field -> field.getAnnotation(SqliteColumn.class) != null &&
                        field.getAnnotation(SqliteColumn.class).constraint().displayName().equals("PRIMARY KEY AUTOINCREMENT") ||
                        field.getAnnotation(SqliteColumn.class).constraint().displayName().equals("PRIMARY KEY"))
                .collect(Collectors.toList());


        if (!objectField.get(0).getName().isEmpty()) {
            return "select * from " + _object.getClass().getSimpleName() + " where " + objectField.get(0).getName() + " = " + id + "";
        } else return null;

    }
}
