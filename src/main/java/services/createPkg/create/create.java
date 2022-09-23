package services.createPkg.create;

import databaseBank.makeEstablishe.sqliteConnect;
import services.createPkg.createAnnotations.sqliteColumn;
import services.createPkg.interFace.interFace;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class create<T> implements interFace<T> {


    /**
     * PRIMARY KEY("id" AUTOINCREMENT)
     **/
    @Override
    public int newTable(T _object) throws NullPointerException {

        String sqlA = "PRAGMA table_info (" + _object.getClass().getSimpleName() + ")";
        //String sqlA = "SELECT sql FROM sqlite_master WHERE tbl_name = '"+_object.getClass().getSimpleName()+"'";

        /**
         * SELECT EXISTS (SELECT * FROM sqlite_master WHERE tbl_name = 'test');
         * SELECT * FROM sqlite_master WHERE tbl_name = 'test'
         * */
        String sql = "CREATE TABLE IF NOT EXISTS " + _object.getClass().getSimpleName() + " (";
        StringBuilder dataMember = new StringBuilder();

        int i = 0;

        StringBuilder filedType = new StringBuilder();
        StringBuilder constraint = new StringBuilder();

        Field[] objectAttributes = _object.getClass().getDeclaredFields();
        for (Field _objectAttributes : objectAttributes) {


            switch (_objectAttributes.getType().toString()) {
                case "class java.lang.String":
                    if (_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                        throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                _objectAttributes.getName());
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


                    filedType.append("text").append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint1().displayName())
                            .append(' ').append(_objectAttributes.getAnnotation(sqliteColumn.class).constraint2().displayName()).append(' ').append(constraint);

                    break;
                case "int":

                    if (_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                        throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                _objectAttributes.getName());
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

                    if (_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                        throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                _objectAttributes.getName());
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
                    if (_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                        throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                _objectAttributes.getName());

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
                    if (_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                        throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                _objectAttributes.getName());

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

                    if (_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                        throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                _objectAttributes.getName());
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

                    if (_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                        throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                _objectAttributes.getName());
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

                    if (_objectAttributes.getAnnotation(sqliteColumn.class) == null) {
                        throw new NullPointerException("you must add sqliteColumn annotation to filed name -> " +
                                _objectAttributes.getName());
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
                if (i++ == objectAttributes.length - 1) {

                    dataMember.append(_objectAttributes.getName()).append(" ").append(filedType).append(')');


                } else {
                    /*Collect members for database sql command*/
                    dataMember.append(_objectAttributes.getName()).append(" ").append(filedType).append(',').append('\n');
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String lastSql = sql + dataMember;
        List<Map<String, String>> infoInDataBase_ = new ArrayList<>();
        List<String> infoInCode_ = new ArrayList<>();

        try (Connection conn = sqliteConnect.getConnect();
             Statement stmt = conn.createStatement()) {
            int sqlChecker = stmt.executeUpdate(lastSql);
            if (sqlChecker == 0) {

                try (ResultSet rs = stmt.executeQuery(sqlA)) {

                    String[] codeClassInfo = dataMember.toString().replace("\n", "").split(",");
                    infoInCode_.addAll(Arrays.asList(codeClassInfo));

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

                    conn.setAutoCommit(false);


                    for (int counter = 0; counter < infoInCode_.size(); counter++) {
                        StringBuilder updateCreateSql = new StringBuilder("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n");
                        try {
                            if (infoInCode_.get(counter).toUpperCase().contains(infoInDataBase_.get(counter).get("name").toUpperCase())) {
                                /* all Ok Same Name */
                                if (infoInCode_.get(counter).toUpperCase().contains(infoInDataBase_.get(counter).get("typeCol").toUpperCase())) {
                                    /* all Ok Same data type */
                                    if (infoInCode_.get(counter).toUpperCase().contains("PRIMARY KEY")) {
                                        if (infoInDataBase_.get(counter).get("isPK").equals("0")) {

                                            /*
                                             * refactor column constraint with PRIMARY KEY
                                             * drop and recreate */
                                            updateCreateSql.setLength(0);
                                            updateCreateSql.append("DROP TABLE ").append(_object.getClass().getSimpleName());
                                            System.out.println(updateCreateSql);


                                                 stmt.executeUpdate(updateCreateSql.toString());

                                                    System.out.println(lastSql);
                                                    stmt.executeUpdate(lastSql);
                                                    conn.commit();
                                        }
                                    }
                                    if (infoInCode_.get(counter).toUpperCase().contains("NOT NULL")) {
                                        if (infoInDataBase_.get(counter).get("isnull").equals("0")) {
                                            /*
                                             * refactor column constraint with not null */

                                            updateCreateSql.append("DROP  COLUMN ").append(infoInDataBase_.get(counter).get("name"));
                                            System.out.println("nono" +updateCreateSql);

                                           stmt.executeUpdate(updateCreateSql.toString());


                                                updateCreateSql.setLength(0);
                                            updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append(" ")
                                                    .append("ADD  ").append(infoInDataBase_.get(counter).get("name"))
                                                            .append(" ").append(infoInDataBase_.get(counter).get("typeCol"))
                                                            .append(" ").append("NOT NULL  ").append("DEFAULT ").append("0");

                                                    stmt.executeUpdate(updateCreateSql.toString());
                                                    conn.commit();
                                                System.out.println("kk" + updateCreateSql);

                                        }

                                    }
                                } else {

                                    /*
                                     * refactor column data type */
                                    updateCreateSql.append(" DROP COLUMN ").append(infoInDataBase_.get(counter).get("name"));
                                    System.out.println(updateCreateSql);

                                    stmt.executeUpdate(updateCreateSql.toString());
                                                 updateCreateSql.setLength(0);
                                                 updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n")
                                                         .append("ADD ").append(infoInCode_.get(counter).replace(")", ""));
                                                 System.out.println(updateCreateSql);

                                                 stmt.executeUpdate(updateCreateSql.toString());
                                                 conn.commit();




                                }
                            } else {
                                /*
                                 * refactor column name */

                                String[] nameColumn = infoInCode_.get(counter).split(" ");
                                updateCreateSql.append("RENAME ").append(infoInDataBase_.get(counter).get("name"))
                                        .append(" ").append("to ").append(nameColumn[0]);

                                System.out.println(updateCreateSql);
                                try {
                                    stmt.executeUpdate(updateCreateSql.toString());
                                    conn.commit();
                                } catch (SQLException x) {
                                    conn.rollback();
                                    System.out.println("there exist");
                                }

                            }


                        } catch (java.lang.IndexOutOfBoundsException ex) {

                            ex.printStackTrace();
                            /*
                             * refactor add new column  */

                            if (infoInCode_.get(counter).toUpperCase().contains("PRIMARY KEY")) {
                                updateCreateSql.setLength(0);
                                updateCreateSql.append("DROP TABLE  ").append(_object.getClass().getSimpleName());
                                   stmt.executeUpdate(updateCreateSql.toString());
                                    System.out.println(lastSql);
                                    stmt.executeUpdate(lastSql);
                                    conn.commit();




                            } else {
                                if (infoInCode_.get(counter).toUpperCase().contains("NOT NULL")) {
                                    if (!infoInCode_.get(counter).toUpperCase().contains("DEFAULT")) {
                                        updateCreateSql.append("ADD ").append(infoInCode_.get(counter))
                                                .append(" ").append("DEFAULT ").append("0");
                                        System.out.println(updateCreateSql);

                                        stmt.executeUpdate(updateCreateSql.toString());
                                        conn.commit();

                                    } else {
                                        updateCreateSql.append("ADD ").append(infoInCode_.get(counter).replace(")", ""));
                                        System.out.println("else " + updateCreateSql );
                                        stmt.executeUpdate(updateCreateSql.toString());
                                        conn.commit();
                                    }

                                } else {
                                    updateCreateSql.append("ADD ").append(infoInCode_.get(counter).replace(")", ""));
                                    System.out.println(updateCreateSql);

                                       stmt.executeUpdate(updateCreateSql.toString());
                                       conn.commit();



                                }
                            }


                        }


                    }


                } catch (SQLException e1){
                    e1.printStackTrace();
                    conn.rollback();
                }

            }


            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}
