package services.createPkg.create;

import databaseBank.makeEstablishe.sqliteConnect;
import services.createPkg.createAnnotations.sqliteColumn;
import services.createPkg.interFace.interFace;

import java.lang.reflect.Field;
import java.sql.*;

public class create<T> implements interFace<T> {

    /**	PRIMARY KEY("id" AUTOINCREMENT)
     **/
    @Override
    public int newTable(T _object) throws NullPointerException {

      //  String sqlA = "PRAGMA table_info ("+ _object.getClass().getSimpleName() +")";
        String sqlA = "SELECT sql FROM sqlite_master WHERE tbl_name = '"+_object.getClass().getSimpleName()+"'";

        /**
         * SELECT EXISTS (SELECT * FROM sqlite_master WHERE tbl_name = 'test');
         * SELECT * FROM sqlite_master WHERE tbl_name = 'test'
         * */
        String sql = "CREATE TABLE IF NOT EXISTS "+ _object.getClass().getSimpleName() +" (";
        StringBuilder dataMember = new StringBuilder();

            int i = 0;

            StringBuilder filedType= new StringBuilder();
            StringBuilder constraint= new StringBuilder();

        Field[] objectAttributes = _object.getClass().getDeclaredFields();
                    for (Field _objectAttributes : objectAttributes) {


                        switch(_objectAttributes.getType().toString()) {
                            case "class java.lang.String":
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                            _objectAttributes.getName()  );
                                }
                                    filedType.setLength(0);
                                   constraint.setLength(0);
                                if( !_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                        .contentEquals("SQL Condition")){
                                    constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()).append(") ");
                                }
                                if( _objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000){
                                    constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT());
                                }


                                filedType.append("text").append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                        .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);

                                break;
                            case "int":

                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                            _objectAttributes.getName()  );
                                }
                                    filedType.setLength(0);
                                    constraint.setLength(0);
                                if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                             .contentEquals("SQL Condition")) {
                                         constraint.append("CHECK").append("(").append(
                                                 _objectAttributes.getAnnotation(sqliteColumn.class).CHECK()).append(") ");
                                     }
                                     if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                         constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT());
                                     }


                                     filedType.append("integer").append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                             .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);



                                break;
                            case "Long":

                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                            _objectAttributes.getName()  );
                                }
                                    filedType.setLength(0);
                                    constraint.setLength(0);
                                    if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()).append(") ");
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT());
                                    }

                                    filedType.append("BIGINT").append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                            .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);

                                break;
                            case "byte":
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                            _objectAttributes.getName()  );

                                }
                                    filedType.setLength(0);
                                    constraint.setLength(0);
                                    if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()).append(") ");
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT());
                                    }

                                    filedType.append("BIT" + ' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName()).append(' ')
                                            .append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);

                                break;
                            case "float":
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                            _objectAttributes.getName()  );

                                }
                                    filedType.setLength(0);
                                   constraint.setLength(0);
                                    if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()).append(") ");
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT());
                                    }
//

                                    filedType.append("FLOAT").append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                            .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);


                            /*
                               ("FLOAT(size, d)");
                             */
                                break;
                            case "double":

                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                            _objectAttributes.getName()  );
                                }
                                    filedType.setLength(0);
                                    constraint.setLength(0);
                                if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()).append(") ");
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT());
                                    }
//

                                    filedType.append("DOUBLE").append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                            .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);


                              /*
                               ("DOUBLE(size, d)");
                                */
                                break;
                            case "boolean":

                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                            _objectAttributes.getName()  );
                                }
                                    filedType.setLength(0);
                                    constraint.setLength(0);
                                if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint.append("CHECK" + "(").append(_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()).append(") ");
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint.append("DEFAULT ").append(_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT());
                                    }

                                    filedType.append("BOOLEAN" + ' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName()).append(' ')
                                            .append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);

                                break;
                            default:
                                 }
                    try {
                        if (i++ == objectAttributes.length -1) {

                            dataMember.append(_objectAttributes.getName()).append(" ").append(filedType).append(')');


                        } else {
                            /*Collect members for database sql command*/
                            dataMember.append(_objectAttributes.getName()).append(" ").append(filedType).append(',').append('\n');
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            }

                    System.out.println(sql+dataMember);
                    String lastSql = sql + dataMember;
        try (Connection conn = sqliteConnect.getConnect();
             Statement stmt = conn.createStatement()) {
            int stmtw = stmt.executeUpdate(lastSql);
            if(stmtw == 0){

                try (ResultSet rs = stmt.executeQuery( sqlA )) {
                    while (rs.next()) {
                        String dbTime = rs.getString("sql");


                        System.out.println(dbTime);
                    }
                }

            }
            System.out.print(stmtw);
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}
