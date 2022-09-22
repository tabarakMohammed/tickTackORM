package services.createPkg.create;

import databaseBank.makeEstablishe.sqliteConnect;
import services.createPkg.createAnnotations.sqliteColumn;
import services.createPkg.interFace.interFace;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class create<T> implements interFace<T> {


    /**	PRIMARY KEY("id" AUTOINCREMENT)
     **/
    @Override
    public int newTable(T _object) throws NullPointerException {

       String sqlA = "PRAGMA table_info ("+ _object.getClass().getSimpleName() +")";
        //String sqlA = "SELECT sql FROM sqlite_master WHERE tbl_name = '"+_object.getClass().getSimpleName()+"'";

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
                            case "class java.lang.Byte":
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


                                    filedType.append("FLOAT").append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                            .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);

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


                                    filedType.append("DOUBLE").append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                            .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);

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
                                case "class java.io.File":

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

                                    filedType.append("BLOB" + ' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName()).append(' ')
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

                 //   System.out.println(sql+dataMember);
        String lastSql = sql + dataMember;
        List<Map<String, String>> infoInDataBase_ = new ArrayList<>();
        List<String> infoInCode_ = new ArrayList<>();

        try (Connection conn = sqliteConnect.getConnect();
             Statement stmt = conn.createStatement()) {
            int sqlChecker = stmt.executeUpdate(lastSql);
            if (sqlChecker == 0) {

                try (ResultSet rs = stmt.executeQuery(sqlA)) {

                    String[] codeClassInfo = dataMember.toString().replace("\n", "").split(",");
                    for(int counter = 0; counter < codeClassInfo.length; counter ++) {
                        infoInCode_.add(codeClassInfo[counter]);
                    }

                    while (rs.next()) {
                        Map<String, String> tableMap = new HashMap<>();
                        String nameCol = rs.getString("name");
                        String typeCol = rs.getString("type");
                        String defaultValue = rs.getString("dflt_value");
                        int isnull = rs.getInt("notnull");
                        int isPK = rs.getInt("pk");
                        //  int ColumnCount = rs.getInt("cid");
                        tableMap.put("name", nameCol);
                        tableMap.put("typeCol", typeCol);
                        tableMap.put("defaultValue", defaultValue);
                        tableMap.put("isnull", String.valueOf(isnull));
                        tableMap.put("isPK", String.valueOf(isPK));
                        infoInDataBase_.add(tableMap);
                    }


                    for (int counter = 0; counter < infoInCode_.size(); counter++) {
                        try {
                            if (infoInCode_.get(counter).toUpperCase().contains(infoInDataBase_.get(counter).get("name").toUpperCase())) {
                               System.out.println("*");
                                System.out.printf(" same col %s :\t \n",infoInDataBase_.get(counter).get("name").toUpperCase());

                                if (infoInCode_.get(counter).toUpperCase().contains(infoInDataBase_.get(counter).get("typeCol").toUpperCase())) {
                                    System.out.printf(" same type %s :\t\n", infoInDataBase_.get(counter).get("typeCol"));
                                    if (infoInCode_.get(counter).toUpperCase().contains("PRIMARY KEY")) {
                                        if (infoInDataBase_.get(counter).get("isPK").equals("1")) {
                                            System.out.printf("PRIMARY KEY %s :\t\n", 1);
                                        } else {
                                            System.out.printf("Update PRIMARY KEY to %s :\t\n", 0);
                                        }
                                    }
                                    if (infoInCode_.get(counter).toUpperCase().contains("NOT NULL")) {
                                        if (infoInDataBase_.get(counter).get("isnull").equals("1")) {
                                            System.out.printf("NOT NULL %s :\t", 1);
                                        } else {
                                            System.out.printf("update NOT NULL to %s :\t\n", 0);
                                        }
                                    }
                                } else {
                                    System.out.println("different Column type \t\n" + infoInCode_.get(counter));
                                }
                            } else {
                                System.out.println("different Column name \t\n" + infoInCode_.get(counter));
                            }


                        } catch (Exception e) {
                            System.out.println("new column alter add \t"+infoInCode_.get(counter).toUpperCase());
                        }
                    }

                }

            }


            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}
