package services.updates.update;

import services.creates.acreate.SqliteColumn;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class PreparedUpdateData<T> {

    PreparedStatement _preparedStatement;

    protected PreparedUpdateData(PreparedStatement  preparedStatement_ ) {
        this._preparedStatement = preparedStatement_;
    }

    protected void setupStatement(T object,String UpdateFields) throws SQLException {
        Field[] objectAttributes = object.getClass().getDeclaredFields();
        String _stringCheckType = "";

        int setInsertCount = 0;
        Method getMethods;
        Object _objectExecutor = null;
        for (Field _objectAttributes : objectAttributes) {
            /*skip id for insertion because Auto Increment*/
            if (_objectAttributes.getAnnotation(SqliteColumn.class) != null &&
                    _objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName().equals("PRIMARY KEY")) {
                continue;
            } else

                if(!UpdateFields.contains(_objectAttributes.getName())) {
                    continue;
                }

                setInsertCount++;

            try {
                /*get "get" method from object for data usage*/
                getMethods = object.getClass().getDeclaredMethod("get" + _objectAttributes.getName().substring(0, 1).toUpperCase() + _objectAttributes.getName().substring(1));
                /*call get method and execute */
                _objectExecutor = getMethods.invoke(object);

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (_objectExecutor != null) {
                /* checking the type of  member variable in object, to set it in prepared statement for storing in database  */
                if (_objectAttributes.getType().isInstance(_stringCheckType)) {

                    _preparedStatement.setString(setInsertCount, _objectExecutor.toString());
                } else if (_objectAttributes.getType().isPrimitive()) {
                    /*switch use for single datatype, Primitive*/
                    switch (_objectAttributes.getType().toString()) {
                        case "int": {
                            _preparedStatement.setInt(setInsertCount, Integer.parseInt(_objectExecutor.toString()));
                        }
                        break;
                        case "long": {
                            _preparedStatement.setLong(setInsertCount, Long.parseLong(_objectExecutor.toString()));
                        }
                        break;
                        case "boolean": {
                            _preparedStatement.setBoolean(setInsertCount, Boolean.parseBoolean(_objectExecutor.toString()));
                        }
                        break;
                        case "byte": {
                            _preparedStatement.setByte(setInsertCount, Byte.parseByte(_objectExecutor.toString()));

                        }
                        break;
                        case "float": {
                            _preparedStatement.setFloat(setInsertCount, Float.parseFloat(_objectExecutor.toString()));
                        }
                        break;
                        case "double": {
                            _preparedStatement.setDouble(setInsertCount, Double.parseDouble(_objectExecutor.toString()));
                        }
                        break;
                        case "blob": {
                            _preparedStatement.setBlob(setInsertCount, (Blob) (_objectExecutor));
                        }
                        break;

                        default:
                            throw new NullPointerException("data type insert does not supported !! ");


                    }

                }

            }
        }

    }


    protected static String sqliteUpdateQuery(Object object)  {
        Field[] objectAttributes = object.getClass().getDeclaredFields();
        String returnValue;
        StringBuilder dataMember = new StringBuilder(" UPDATE " + object.getClass().getSimpleName()).append(" SET ");
        String idFiled = " ";

        Method getMethods;
        Object _objectExecutor;


        int i = 0;
        for (Field _objectAttributes : objectAttributes) {
            /*skip Id*/
            if (_objectAttributes.getAnnotation(SqliteColumn.class) != null &&
                 _objectAttributes.getAnnotation(SqliteColumn.class).constraint().displayName().equals("PRIMARY KEY")) {
                     idFiled = _objectAttributes.getName();
                    continue;
                 }

                try {

                    /*get "get" method from object for data usage*/
                    getMethods = object.getClass().getDeclaredMethod("get" + _objectAttributes.getName().substring(0, 1).toUpperCase() + _objectAttributes.getName().substring(1));
                    /*call get method and execute */
                    _objectExecutor = getMethods.invoke(object);
                    /*Default Values for * String object
                    * long, int, double, float*/
                    if(_objectExecutor == null || getMethods.invoke(object).equals(0L)
                       || getMethods.invoke(object).equals(0)
                       || getMethods.invoke(object).equals(0.0f)
                       || getMethods.invoke(object).equals(0.0d)
                       || getMethods.invoke(object).equals(0.0d)

                    ){
                        i++;
                        continue;
                    }

                    if (i++ == objectAttributes.length - 2) {
                        /*Last Iteration*/
                        dataMember.append(_objectAttributes.getName()).append(" = ? ");

                    } else {
                        /*Collect members for database sql command*/
                        dataMember.append(_objectAttributes.getName()).append(" = ? ").append(',');

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    returnValue = "0";
                    return returnValue;

                }

        }
        returnValue = " " +  dataMember.append(" where ").append(idFiled).append(" = ");
        return returnValue;
    }





}
