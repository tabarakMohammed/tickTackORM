package services.fetchs.fetch;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * setup data binding for getting data from database*/
public class PreparedFetchingData<T> {
    ResultSet _resultSet;

    protected PreparedFetchingData(ResultSet resultSet_) {
        this._resultSet = resultSet_;
    }

    protected  List<T>  setupResult(T object) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
         List<T> allData = new ArrayList<>();
         Field[] objectAttributes = object.getClass().getDeclaredFields();
         String _stringCheckType = "";



        while (_resultSet.next()) {

            /*new Instance for object to store data in it and put them in List*/
         T _object = (T) object.getClass().newInstance();

         for (Field _objectAttributes : objectAttributes) {

             /* call setter methods from object to store data into it */
                for (Method method : object.getClass().getMethods()) {
                    if (method.getName().startsWith("set")) {

                        if (_objectAttributes.getType().isInstance(_stringCheckType) &&
                                method.getName().toUpperCase().contains(_objectAttributes.getName().toUpperCase()) ) {

                           method.invoke(_object, _resultSet.getString(_objectAttributes.getName()));

                        } else if (_objectAttributes.getType().isPrimitive() &&
                        method.getName().toUpperCase().contains(_objectAttributes.getName().toUpperCase()) ) {
                            /*switch use for single datatype, Primitive*/
                            switch (_objectAttributes.getType().toString()) {
                                case "int": {
                                   method.invoke(_object, _resultSet.getInt(_objectAttributes.getName()));

                                }
                                break;
                                case "long": {
                                   method.invoke(_object, _resultSet.getLong(_objectAttributes.getName()));

                                }
                                break;
                                case "boolean": {
                                    int k = _resultSet.getInt(_objectAttributes.getName());
                                     method.invoke(_object,k == 1 ? true : false );
                                }
                                break;
                                case "byte": {
                                   method.invoke(_object, _resultSet.getByte(_objectAttributes.getName()));

                                }
                                break;
                                case "float": {
                                   method.invoke(_object, _resultSet.getFloat(_objectAttributes.getName()));

                                }
                                break;
                                case "double": {
                                   method.invoke(_object, _resultSet.getDouble(_objectAttributes.getName()));

                                }
                                break;
                                case "blob": {
                                   method.invoke(_object, _resultSet.getBlob(_objectAttributes.getName()));

                                }
                                break;

                                default:
                                    throw new NullPointerException("data type insert does not supported !! ");


                            }

                        }

                    }

                }


            }
            allData.add(_object);
        }
        return allData;
    }


}
