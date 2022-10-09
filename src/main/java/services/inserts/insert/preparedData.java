package services.inserts.insert;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class preparedData {

    static PreparedStatement _preparedStatement;

    protected preparedData(PreparedStatement preparedStatement_) {
        this._preparedStatement = preparedStatement_;
    }

    protected PreparedStatement getData(Object object) throws SQLException {

        Field[] objectAttributes = object.getClass().getDeclaredFields();

        String _stringCheckType = "";

        int count = 0;
        Method getMethods;
        Object _objectExecutor = null;
        for (Field _objectAttributes : objectAttributes) {

            if (_objectAttributes.getName().equalsIgnoreCase("id")) {
                continue;
            } else

                count = count + 1;

            try {

                getMethods = object.getClass().getDeclaredMethod("get" + _objectAttributes.getName().substring(0, 1).toUpperCase() + _objectAttributes.getName().substring(1));
                _objectExecutor = getMethods.invoke(object);

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (_objectAttributes.getType().isInstance(_stringCheckType)) {
                _preparedStatement.setString(count, _objectExecutor.toString());
            } else if (_objectAttributes.getType().isPrimitive()) {
                /*switch use for single datatype*/
                switch (_objectAttributes.getType().toString()) {
                    case "int": {
                        _preparedStatement.setInt(count, Integer.parseInt(_objectExecutor.toString()));
                    }
                    break;
                    case "boolean": {
                        _preparedStatement.setBoolean(count, Boolean.parseBoolean(_objectExecutor.toString()));
                    }
                    break;
                }

            }

        }

        return _preparedStatement;

    }

    protected static String sqliteInsertQuery(Field[] objectAttributes) {

        String returnValue;
        StringBuilder dataMember = new StringBuilder();
        StringBuilder valuesForSql = new StringBuilder(" VALUES(");

        int i = 0;
        for (Field _objectAttributes : objectAttributes) {
            if (_objectAttributes.getName().equalsIgnoreCase("id")) {
                /*skip Id*/
            } else try {
                if (i++ == objectAttributes.length - 2) {
                    /*Last Iteration*/
                    dataMember.append(_objectAttributes.getName()).append(')');
                    valuesForSql.append('?').append(')');
                } else {
                    /*Collect members for database sql command*/
                    dataMember.append(_objectAttributes.getName()).append(',');
                    valuesForSql.append('?').append(',');
                }

            } catch (Exception e) {
                e.printStackTrace();
                returnValue = "0";
                return returnValue;

            }
        }
        returnValue = "(" + dataMember + valuesForSql;
        return returnValue;
    }


}
