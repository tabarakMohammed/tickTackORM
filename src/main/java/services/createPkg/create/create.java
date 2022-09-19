package services.createPkg.create;

import databaseBank.makeEstablishe.sqliteConnect;
import services.createPkg.createAnnotations.sqliteColumn;
import services.createPkg.createAnnotations.size;
import services.createPkg.interFace.interFace;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class create<T> implements interFace<T> {

    @Override
    public int newTable(T _object) throws NullPointerException {

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
            String constraint="";
            String sizey ="";

        Field[] objectAttributes = _object.getClass().getDeclaredFields();
                    for (Field _objectAttributes : objectAttributes) {


                        switch(_objectAttributes.getType().toString()) {
                            case "class java.lang.String":
                               // System.out.println("VARCHAR(size)");
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name ->\t" +
                                            _objectAttributes.getName()  );
                                } else
                                if( !_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                        .contentEquals("SQL Condition")){
                                    constraint = "CHECK"+"("
                                            +_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()+")\t";
                                }
                                if( _objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000){
                                    constraint = constraint + "DEFAULT\t" +
                                            _objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT();
                                }
//
                                if (_objectAttributes.getAnnotation(size.class) != null) {
                                    sizey = "(" + String.valueOf(_objectAttributes.getAnnotation(size.class).filedSize()) + ")\t";
                                }

                                filedType = "text" +sizey
                                        + _objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName()+'\t'
                                        + _objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()+'\t'
                                       + constraint;
                                constraint ="";
                                sizey ="";
                                break;
                            case "int":
                                // code block
                             //   System.out.println("INT(size)");
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name ->\t" +
                                            _objectAttributes.getName()  );
                                } else
                                if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                             .contentEquals("SQL Condition")) {
                                         constraint = "CHECK" + "("
                                                 + _objectAttributes.getAnnotation(sqliteColumn.class).CHECK() + ")\t";
                                     }
                                     if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                         constraint = constraint + "DEFAULT\t" +
                                                 _objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT();
                                     }
//
                                     if(_objectAttributes.getAnnotation(size.class) != null){
                                       sizey = "(" + String.valueOf(_objectAttributes.getAnnotation(size.class).filedSize()) +")\t";
                                     }
                                     filedType = "integer" + sizey
                                             + _objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName() + '\t'
                                             + _objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName() + '\t'
                                             + constraint;
                                     constraint = "";
                                        sizey = "";

                                break;
                            case "Long":
                                // code block
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name ->\t" +
                                            _objectAttributes.getName()  );
                                } else
                                    if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint = "CHECK" + "("
                                                + _objectAttributes.getAnnotation(sqliteColumn.class).CHECK() + ")\t";
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint = constraint + "DEFAULT\t" +
                                                _objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT();
                                    }
//
                                    if (_objectAttributes.getAnnotation(size.class) != null) {
                                        sizey = "(" + String.valueOf(_objectAttributes.getAnnotation(size.class).filedSize()) + ")\t";
                                    }
                                    filedType = "BIGINT" + sizey
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName() + '\t'
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName() + '\t'
                                            + constraint;
                                    constraint = "";

                                //System.out.println("BIGINT(size)");
                                break;
                            case "byte":
                                // code block
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name ->\t" +
                                            _objectAttributes.getName()  );

                                } else
                                    if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint = "CHECK" + "("
                                                + _objectAttributes.getAnnotation(sqliteColumn.class).CHECK() + ")\t";
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint = constraint + "DEFAULT\t" +
                                                _objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT();
                                    }
//
                                    filedType = "BIT"
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName() + '\t'
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName() + '\t'
                                            + constraint;
                                    constraint = "";

                             //   System.out.println("BIT");
                                break;
                            case "float":
                                // code block
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name ->\t" +
                                            _objectAttributes.getName()  );

                                } else
                                    if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint = "CHECK" + "("
                                                + _objectAttributes.getAnnotation(sqliteColumn.class).CHECK() + ")\t";
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint = constraint + "DEFAULT\t" +
                                                _objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT();
                                    }
//
                                    if (_objectAttributes.getAnnotation(size.class) != null) {
                                        sizey = "(" + String.valueOf(_objectAttributes.getAnnotation(size.class).filedSize()) + ")\t";
                                    }
                                    filedType = "FLOAT" +sizey
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName() + '\t'
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName() + '\t'
                                            + constraint;
                                    constraint = "";

                            //    System.out.println("FLOAT(size, d)");
                                break;
                            case "double":
                                // code block
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name ->\t" +
                                            _objectAttributes.getName()  );
                                } else
                                if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint = "CHECK" + "("
                                                + _objectAttributes.getAnnotation(sqliteColumn.class).CHECK() + ")\t";
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint = constraint + "DEFAULT\t" +
                                                _objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT();
                                    }
//
                                    if (_objectAttributes.getAnnotation(size.class) != null) {
                                        sizey = "(" + String.valueOf(_objectAttributes.getAnnotation(size.class).filedSize()) + ")\t";
                                    }
                                    filedType = "DOUBLE" +sizey
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName() + '\t'
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName() + '\t'
                                            + constraint;
                                    constraint = "";

                                System.out.println("DOUBLE(size, d)");
                                break;
                            case "boolean":
                                // code block
                                if(_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                                    throw new NullPointerException("you must add sqliteColumn annotation to filed name ->\t" +
                                            _objectAttributes.getName()  );
                                } else
                                if (!_objectAttributes.getAnnotation(sqliteColumn.class).CHECK()
                                            .contentEquals("SQL Condition")) {
                                        constraint = "CHECK" + "("
                                                + _objectAttributes.getAnnotation(sqliteColumn.class).CHECK() + ")\t";
                                    }
                                    if (_objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT() != 0.000) {
                                        constraint = constraint + "DEFAULT\t" +
                                                _objectAttributes.getAnnotation(sqliteColumn.class).DEFAULT();
                                    }
//
                                    filedType = "BOOLEAN"
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName() + '\t'
                                            + _objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName() + '\t'
                                            + constraint;
                                    constraint = "";

                              //  System.out.println("BOOLEAN");
                                break;
                            default:
                                // code block
                                 }
                    try {
                        if (i++ == objectAttributes.length -1) {
                            /**Last Iteration*/

                            dataMember = dataMember + _objectAttributes.getName() + " "
                                    + filedType + ')';


                        } else {
                            /**Collect members for database sql command*/
                            dataMember = dataMember + _objectAttributes.getName() + " "
                                    + filedType + ',' +'\n';
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            }

                    System.out.println(coco+dataMember);

        try (Connection conn = sqliteConnect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}
