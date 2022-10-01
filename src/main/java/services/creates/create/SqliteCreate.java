package services.creates.create;

import DBConnection.DBEstablishe.SqliteConnect;
import services.creates.icreate.ICreate;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class SqliteCreate<T> implements ICreate<T> {



    @Override
    public int newTable(T _object) throws NullPointerException {
        String createQuery;
        String sqlPragmaQuery = "PRAGMA table_info (" + _object.getClass().getSimpleName() + ")";
        String prefixCreateQuery = "CREATE TABLE IF NOT EXISTS " + _object.getClass().getSimpleName() + " (";
        StringBuilder dataMember = new StringBuilder();

        int counter = 0;
        Field[] objectAttributes = _object.getClass().getDeclaredFields();

        for (Field _objectAttributes : objectAttributes) {
            try {
                if (counter++ == objectAttributes.length - 1) {
                    /*put last member to database sql command*/
                    dataMember.append(_objectAttributes.getName()).append(" ").append(
                            MemberOptions.getMemberString(_objectAttributes.getType().getSimpleName(), _objectAttributes)).append(')');
                } else {
                    /*Collect members for database sql command*/
                    dataMember.append(_objectAttributes.getName()).append(" ").append(
                            MemberOptions.getMemberString(_objectAttributes.getType().getSimpleName(), _objectAttributes)
                    ).append(',').append('\n');
                }
            } catch (NullPointerException e){
                throw new NullPointerException("you must add (@sqliteColumn) annotation to filed name -> " +
                        _objectAttributes.getName() + " for Class "  + _object.getClass().getSimpleName());
            }


        }


        createQuery = prefixCreateQuery + dataMember;
        System.out.println(createQuery);

      /*___*/

        List<Map<String, String>> infoInDataBase_ = new ArrayList<>();
        List<String> infoInCode_ = new ArrayList<>();

        try (Connection conn = SqliteConnect.getConnect();
             Statement stmt = conn.createStatement()) {
               stmt.executeUpdate(createQuery);

/*___*/

                try (ResultSet rs = stmt.executeQuery(sqlPragmaQuery)) {

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


                    for (int forCounter = 0; forCounter < infoInCode_.size(); forCounter++) {
                        StringBuilder updateCreateSql = new StringBuilder("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n");
                        try {
                            if (infoInCode_.get(forCounter).toUpperCase().contains(infoInDataBase_.get(forCounter).get("name").toUpperCase())) {
                                /* all Ok Same Name */
                                if (infoInCode_.get(forCounter).toUpperCase().contains(infoInDataBase_.get(forCounter).get("typeCol").toUpperCase())) {
                                    /* all Ok Same data type */
                                    if (infoInCode_.get(forCounter).toUpperCase().contains("PRIMARY KEY")) {
                                        if (infoInDataBase_.get(forCounter).get("isPK").equals("0")) {

                                            /*
                                             * refactor column constraint with PRIMARY KEY
                                             * drop and recreate */
                                            updateCreateSql.setLength(0);
                                            updateCreateSql.append("DROP TABLE ").append(_object.getClass().getSimpleName());
                                            System.out.println(updateCreateSql);


                                                 stmt.executeUpdate(updateCreateSql.toString());

                                                    System.out.println(createQuery);
                                                    stmt.executeUpdate(createQuery);
                                                    conn.commit();
                                        }
                                    }
                                    if (infoInCode_.get(forCounter).toUpperCase().contains("NOT NULL")) {
                                        if (infoInDataBase_.get(forCounter).get("isnull").equals("0")) {
                                            /*
                                             * refactor column constraint with not null */

                                            updateCreateSql.append("DROP  COLUMN ").append(infoInDataBase_.get(forCounter).get("name"));
                                            System.out.println("nono" +updateCreateSql);

                                           stmt.executeUpdate(updateCreateSql.toString());


                                                updateCreateSql.setLength(0);
                                            updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append(" ")
                                                    .append("ADD  ").append(infoInDataBase_.get(forCounter).get("name"))
                                                            .append(" ").append(infoInDataBase_.get(forCounter).get("typeCol"))
                                                            .append(" ").append("NOT NULL  ").append("DEFAULT ").append("0");

                                                    stmt.executeUpdate(updateCreateSql.toString());
                                                    conn.commit();
                                                System.out.println("kk" + updateCreateSql);

                                        }

                                    }
                                } else {

                                    /*
                                     * refactor column data type */
                                    updateCreateSql.append(" DROP COLUMN ").append(infoInDataBase_.get(forCounter).get("name"));
                                    System.out.println(updateCreateSql);

                                    stmt.executeUpdate(updateCreateSql.toString());
                                                 updateCreateSql.setLength(0);
                                                 updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n")
                                                         .append("ADD ").append(infoInCode_.get(forCounter).replace(")", ""));
                                                 System.out.println(updateCreateSql);

                                                 stmt.executeUpdate(updateCreateSql.toString());
                                                 conn.commit();




                                }
                            } else {
                                /*
                                 * refactor column name */

                                String[] nameColumn = infoInCode_.get(forCounter).split(" ");
                                updateCreateSql.append("RENAME ").append(infoInDataBase_.get(forCounter).get("name"))
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

                            if (infoInCode_.get(forCounter).toUpperCase().contains("PRIMARY KEY")) {
                                updateCreateSql.setLength(0);
                                updateCreateSql.append("DROP TABLE  ").append(_object.getClass().getSimpleName());
                                   stmt.executeUpdate(updateCreateSql.toString());
                                    System.out.println(createQuery);
                                    stmt.executeUpdate(createQuery);
                                    conn.commit();




                            } else {
                                if (infoInCode_.get(forCounter).toUpperCase().contains("NOT NULL")) {
                                    if (!infoInCode_.get(forCounter).toUpperCase().contains("DEFAULT")) {
                                        updateCreateSql.append("ADD ").append(infoInCode_.get(forCounter))
                                                .append(" ").append("DEFAULT ").append("0");
                                        System.out.println(updateCreateSql);

                                        stmt.executeUpdate(updateCreateSql.toString());
                                        conn.commit();

                                    } else {
                                        updateCreateSql.append("ADD ").append(infoInCode_.get(forCounter).replace(")", ""));
                                        System.out.println("else " + updateCreateSql );
                                        stmt.executeUpdate(updateCreateSql.toString());
                                        conn.commit();
                                    }

                                } else {
                                    updateCreateSql.append("ADD ").append(infoInCode_.get(forCounter).replace(")", ""));
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




            return 1;

        } catch (SQLException e) {

           e.printStackTrace();
           return 0;

        }

       // return 0;
    }
}
