package services.insertPkg.insert;
import databaseBank.makeEstablishe.sqliteConnect;
import services.insertPkg.interFace.interFace;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class insert<T> implements interFace<T> {
    @Override
    public int insertRow(T object) {
        String sqlInsertStatement = "INSERT INTO "+object.getClass().getSimpleName()+" (";
        StringBuilder dataMember = new StringBuilder();
        StringBuilder valuesForSql = new StringBuilder(" VALUES(");
        Field[] objectAttributes = object.getClass().getDeclaredFields();

        String _stringCheckType = "";
        int i = 0;
        for (Field _objectAttributes : objectAttributes) {
            if (_objectAttributes.getName().contentEquals("id")) {
                /*skip Id*/
                continue;
            } else
                try {
                    if (i++ == objectAttributes.length - 2) {
                        /*Last Iteration*/

                        dataMember.append(dataMember).append(_objectAttributes.getName()).append(')');
                        valuesForSql.append('?').append(')');

                    } else {
                        /*Collect members for database sql command*/
                        dataMember.append(dataMember).append( _objectAttributes.getName()).append(',');
                        valuesForSql.append('?').append(',');
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
        int checkStatus = 0;

        sqlInsertStatement = sqlInsertStatement + dataMember + valuesForSql;
        System.out.println(sqlInsertStatement);

        try (Connection conn = sqliteConnect.getConnect();
             PreparedStatement _preparedStatement = conn.prepareStatement(sqlInsertStatement)) {

            int count = 0;
            Method getMethods = null;
            Object _objectExecutor = null;

            for (Field _objectAttributes : objectAttributes) {

                if (_objectAttributes.getName().contentEquals("id")) {
                    continue;
                } else

                    count = count + 1;

                try {

                    getMethods = object.getClass().
                            getDeclaredMethod("get" + _objectAttributes.getName().substring(0, 1).toUpperCase()
                                    + _objectAttributes.getName().substring(1));
                    _objectExecutor = getMethods.invoke(object);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (_objectAttributes.getType().isInstance(_stringCheckType)) {
                    _objectAttributes.getName();
                    //System.out.println("hey I am big, I am String Class");
                    _preparedStatement.setString(count, _objectExecutor.toString());
                } else if (_objectAttributes.getType().isPrimitive()) {
                    switch (_objectAttributes.getType().toString()) {
                        case "int": {
                            //System.out.println("That's right Iam little one of Integers");
                            _preparedStatement.setInt(count, Integer.parseInt(_objectExecutor.toString()));
                        }
                        break;
                    }

                }

            }

            _preparedStatement.executeUpdate();


            if (checkStatus == 1) {
                sqliteConnect.closeConnect();

                return 1;
            } else return 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int insertMultiRow(List<T> objects) {
        String sqlInsertStatement = "INSERT INTO "+objects.get(0).getClass().getSimpleName()+" (";
        StringBuilder dataMember = new StringBuilder();
        StringBuilder valuesForSql = new StringBuilder(" VALUES(");

        Field[] objectAttributes = objects.get(0).getClass().getDeclaredFields();

        String _stringCheckType = "";
        int i = 0;
        for (Field _objectAttributes : objectAttributes) {
            if (_objectAttributes.getName().contentEquals("id")) {
                /*skip Id*/
                continue;
            } else
                try {
                    if (i++ == objectAttributes.length - 2) {
                        /*Last Iteration*/

                        dataMember.append(dataMember).append( _objectAttributes.getName()).append(')');
                        valuesForSql.append('?').append(')');

                    } else {
                        /*Collect members for database sql command*/
                        dataMember.append(dataMember).append(_objectAttributes.getName()).append(',');
                        valuesForSql.append('?').append(',');
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
        int checkStatus = 0;

        sqlInsertStatement = sqlInsertStatement + dataMember + valuesForSql;
        System.out.println(sqlInsertStatement);

        try (Connection conn = sqliteConnect.getConnect();
             PreparedStatement _preparedStatement = conn.prepareStatement(sqlInsertStatement)) {


            int _i = 0;

            for (T it : objects) {
                int count = 0;
                Method getMethods = null;
                Object _objectExecutor = null;
                for (Field _objectAttributes : objectAttributes) {

                    if (_objectAttributes.getName().contentEquals("id")) {
                        continue;
                    } else

                        count = count + 1;

                    try {

                        getMethods = it.getClass().
                                getDeclaredMethod("get" + _objectAttributes.getName().substring(0, 1).toUpperCase()
                                        + _objectAttributes.getName().substring(1));
                        _objectExecutor = getMethods.invoke(it);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (_objectAttributes.getType().isInstance(_stringCheckType)) {
                        _objectAttributes.getName();
                        //System.out.println("hey I am big, I am String Class");
                        String content ="empty";
                        if (_objectExecutor != null) {
                            content = _objectExecutor.toString();
                        }
                        _preparedStatement.setString(count, content);
                    } else if (_objectAttributes.getType().isPrimitive()) {
                        switch (_objectAttributes.getType().toString()) {
                            case "int": {
                                //System.out.println("That's right Iam little one of Integers");
                                _preparedStatement.setInt(count, Integer.parseInt(_objectExecutor.toString()));
                            }
                            break;
                        }

                    }

                }
                _preparedStatement.addBatch();
                _i++;

                if (_i % 1000 == 0 || _i == objects.size()) {
                    _preparedStatement.executeBatch();
                }
            }

            //_preparedStatement.executeUpdate();
            if (checkStatus == 1) {
                sqliteConnect.closeConnect();
                return 1;
            } else return 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }


    }

