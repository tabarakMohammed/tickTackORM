package services.createPkg.create;

import databaseBank.makeEstablishe.sqliteConnect;
import services.createPkg.interFace.interFace;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class create<T> implements interFace<T> {

    @Override
    public int newTable(T _object) {

//        {
//            String sqlInsertStatement = "CREATE TABLE [IF NOT EXISTS]" + object.getClass().getSimpleName() + " (";
//            String dataMember = "";
//            String valuesForSql = " VALUES(";
//            Field[] objectAttributes = object.getClass().getDeclaredFields();
//
//            String _stringCheckType = "";
//            int i = 0;
//            for (Field _objectAttributes : objectAttributes) {
//                if (_objectAttributes.getName().contentEquals("id")) {
//                    /**skip Id*/
//                    continue;
//                } else
//                    try {
//                        if (i++ == objectAttributes.length - 2) {
//                            /**Last Iteration*/
//
//                            dataMember = dataMember + _objectAttributes.getName() + ')';
//                            valuesForSql = valuesForSql + '?' + ')';
//
//                        } else {
//                            /**Collect members for database sql command*/
//                            dataMember = dataMember + _objectAttributes.getName() + ',';
//                            valuesForSql = valuesForSql + '?' + ',';
//                        }
//
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//            }
//            int checkStatus = 0;
//
//            sqlInsertStatement = sqlInsertStatement + dataMember + valuesForSql;
//            System.out.println(sqlInsertStatement);
//
//            try (Connection conn = sqliteConnect.getConnect();
//                 PreparedStatement _preparedStatement = conn.prepareStatement(sqlInsertStatement)) {
//
//                int count = 0;
//                Method getMethods = null;
//                Object _objectExecutor = null;
//
//                for (Field _objectAttributes : objectAttributes) {
//
//                    if (_objectAttributes.getName().contentEquals("id")) {
//                        continue;
//                    } else
//
//                        count = count + 1;
//
//                    try {
//
//                        getMethods = object.getClass().
//                                getDeclaredMethod("get" + _objectAttributes.getName().substring(0, 1).toUpperCase()
//                                        + _objectAttributes.getName().substring(1));
//                        _objectExecutor = getMethods.invoke(object);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    if (_objectAttributes.getType().isInstance(_stringCheckType)) {
//                        _objectAttributes.getName();
//                        //System.out.println("hey I am big, I am String Class");
//                        _preparedStatement.setString(count, _objectExecutor.toString());
//                    } else if (_objectAttributes.getType().isPrimitive()) {
//                        switch (_objectAttributes.getType().toString()) {
//                            case "int": {
//                                //System.out.println("That's right Iam little one of Integers");
//                                _preparedStatement.setInt(count, Integer.parseInt(_objectExecutor.toString()));
//                            }
//                            break;
//                        }
//
//                    }
//
//                }
//
//                _preparedStatement.executeUpdate();
//
//
//                if (checkStatus == 1) {
//                    sqliteConnect.closeConnect();
//
//                    return 1;
//                } else return 0;
//
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//                return 0;
//            }
//
//        }

        String sql = "CREATE TABLE IF NOT EXISTS "+ _object.getClass().getSimpleName() +" (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        String coco = "CREATE TABLE IF NOT EXISTS "+ _object.getClass().getSimpleName() +" (";
            String dataMember = "";
            String valuesForSql = " VALUES(";
            int i = 0;

            String filedType="";
           Field[] objectAttributes = _object.getClass().getDeclaredFields();
                    for (Field _objectAttributes : objectAttributes) {


                        switch(_objectAttributes.getType().toString()) {
                            case "class java.lang.String":
                                System.out.println("VARCHAR(size)");
                                filedType = "text";
                                break;
                            case "int":
                                // code block
                                System.out.println("INT(size)");
                                filedType = "integer";

                                break;
                            case "Long":
                                // code block
                                System.out.println("BIGINT(size)");
                                break;
                            case "byte":
                                // code block
                                System.out.println("BIT");
                                break;
                            case "float":
                                // code block
                                System.out.println("FLOAT(size, d)");
                                break;
                            case "double":
                                // code block
                                System.out.println("DOUBLE(size, d)");
                                break;
                            case "boolean":
                                // code block
                                System.out.println("BOOLEAN");
                                break;
                            default:
                                // code block
                                 }
                    try {
                        if (i++ == objectAttributes.length - 1) {
                            /**Last Iteration*/

                            dataMember = dataMember + _objectAttributes.getName() + " "
                                    + filedType + ')';
                            valuesForSql = valuesForSql + '?' + ')';

                        } else {
                            /**Collect members for database sql command*/
                            dataMember = dataMember + _objectAttributes.getName() + " "
                                    + filedType + ',' +'\n';
                            valuesForSql = valuesForSql + '?' + ',';
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            }

                    System.out.println(coco+dataMember);

//        try (Connection conn = sqliteConnect.getConnect();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            // create a new table
//            stmt.executeUpdate();
//            return 1;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return 0;
//        }

        return 0;
    }

}
