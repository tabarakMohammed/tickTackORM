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


        String sql = "CREATE TABLE IF NOT EXISTS "+ _object.getClass().getSimpleName() +" (";
        StringBuilder dataMember = new StringBuilder();

            int i = 0;

            StringBuilder filedType= new StringBuilder();
            StringBuilder constraint= new StringBuilder();
            String sizey ="";

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

                                if (_objectAttributes.getAnnotation(size.class) != null) {
                                    sizey = "(" + (_objectAttributes.getAnnotation(size.class).filedSize()) + ") ";
                                }

                                filedType.append("text").append(sizey).append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                        .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);
                                sizey ="";
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

                                     if(_objectAttributes.getAnnotation(size.class) != null){
                                       sizey = "(" +(_objectAttributes.getAnnotation(size.class).filedSize()) +") ";
                                     }
                                     filedType.append("integer").append(sizey).append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                             .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);

                                sizey = "";

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

                                    if (_objectAttributes.getAnnotation(size.class) != null) {
                                        sizey = "(" +(_objectAttributes.getAnnotation(size.class).filedSize()) + ") ";
                                    }
                                    filedType.append("BIGINT").append(sizey).append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                            .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);
                                    sizey = "";
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
                                    if (_objectAttributes.getAnnotation(size.class) != null) {
                                        sizey = "(" +(_objectAttributes.getAnnotation(size.class).filedSize()) + ") ";
                                    }
                                    filedType.append("FLOAT").append(sizey).append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                            .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);
                                    sizey = "";

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
                                    if (_objectAttributes.getAnnotation(size.class) != null) {
                                        sizey = "(" +(_objectAttributes.getAnnotation(size.class).filedSize()) + ") ";
                                    }
                                    filedType.append("DOUBLE").append(sizey).append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                                            .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);
                                 sizey = "";

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
             PreparedStatement stmt = conn.prepareStatement(lastSql)) {
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}
