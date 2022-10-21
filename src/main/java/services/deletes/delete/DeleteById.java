package services.deletes.delete;

import services.creates.acreate.SqliteColumn;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteById<T> {

    T _object;

    DeleteById(T object){
        this._object = object;
    }

    String sqlDeleteById(long id){

        String sqlDelete;
        List<Field> objectField;
        objectField = Arrays.stream(_object.getClass().getDeclaredFields()).
                filter(field->field.getAnnotation(SqliteColumn.class) != null &&
                        field.getAnnotation(SqliteColumn.class).constraint().displayName().equals("PRIMARY KEY"))
                .collect(Collectors.toList());

        if( !objectField.get(0).getName().isEmpty()){

            sqlDelete="DELETE FROM " + _object.getClass().getSimpleName() + " WHERE " + objectField.get(0).getName() + " = " + id + "";
            System.out.println(sqlDelete);
        } else {
            sqlDelete = "";
        }
        return sqlDelete;
    }

}
