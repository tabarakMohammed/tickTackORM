package services.creates.create;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MemberAlter {

    static int alter(Connection  conn, Statement stmt,String sqlPragmaQuery ,String createQuery, StringBuilder dataMember, Object _object) throws SQLException {
        List<Map<String, String>> _infoInDataBase = new ArrayList<>();
        List<Map<String, String>> _sortInfoInDataBase = new ArrayList<>();
        List<String> _infoInCode = new ArrayList<>();

       try (ResultSet rs = stmt.executeQuery(sqlPragmaQuery)){

            String[] codeClassInfo = dataMember.toString().replace("\n", "").split(",");
            _infoInCode.addAll(Arrays.asList(codeClassInfo));

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
                _infoInDataBase.add(tableMap);
            }

            conn.setAutoCommit(false);
               String[] codeColumnName;
            int count;
            for (int forCounter = 0; forCounter < _infoInCode.size(); forCounter++) {
                 /*get column name and check it to avoid contain sequential characters issue */
                codeColumnName = _infoInCode.get(forCounter).split(" ");
                    /* first loop for sorting data from database to check the all changes*/
                 count = 0;
                while (count < _infoInDataBase.size()) {
                    if (codeColumnName[0].equalsIgnoreCase(_infoInDataBase.get(count).get("name"))) {
                        _sortInfoInDataBase.add(_infoInDataBase.get(count));
                         count = _infoInDataBase.size();
                    }
                    count++;
                }

            //    System.out.println(_sortInfoInDataBase.get(forCounter).get("name") );


//                try {
//                    if (_infoInCode.get(forCounter).toUpperCase().contains(_sortInfoInDataBase.get(forCounter).get("name").toUpperCase())) {
//                        System.out.println(forCounter + "- good");
//                    }
//
//                } catch (IndexOutOfBoundsException e){
//
//                    if (forCounter   >= _sortInfoInDataBase.size()  ) {
//                        System.out.println(codeColumnName[0] + " add ");
//
//                    }
//                 else
//                     if (!_infoInCode.get(forCounter).toUpperCase().contains(_infoInDataBase.get(forCounter).get("name").toUpperCase())) {
//                         System.out.println(_infoInCode.get(forCounter) + "rename from " + _infoInDataBase.get(forCounter).get("name"));
//                         _sortInfoInDataBase.add(_infoInDataBase.get(forCounter));
//                     }
//
//
//                }

                /*sql command that's make changes on database*/
                StringBuilder updateCreateSql = new StringBuilder("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n");

                try {
                    if (_infoInCode.get(forCounter).contains(_sortInfoInDataBase.get(forCounter).get("name"))) {
                        /* all Ok Same Name */
                        if (_infoInCode.get(forCounter).toUpperCase().contains(_sortInfoInDataBase.get(forCounter).get("typeCol").toUpperCase())) {
                            /* all Ok Same data type */
                            if (_infoInCode.get(forCounter).toUpperCase().contains("PRIMARY KEY")) {
                                if (_sortInfoInDataBase.get(forCounter).get("isPK").equals("0")) {

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
                            if (_infoInCode.get(forCounter).toUpperCase().contains("NOT NULL")) {
                                if (_sortInfoInDataBase.get(forCounter).get("isnull").equals("0")
                                && _sortInfoInDataBase.get(forCounter).get("isPK").equals("0")) {
                                    /*
                                     * refactor column constraint with not null */

                                    updateCreateSql.append("DROP  COLUMN ").append(_sortInfoInDataBase.get(forCounter).get("name"));
                                    System.out.println("nono " +updateCreateSql);

                                    stmt.executeUpdate(updateCreateSql.toString());


                                    updateCreateSql.setLength(0);
                                    updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append(" ")
                                            .append("ADD  ").append(_sortInfoInDataBase.get(forCounter).get("name"))
                                            .append(" ").append(_sortInfoInDataBase.get(forCounter).get("typeCol"))
                                            .append(" ").append("NOT NULL  ").append("DEFAULT ").append("0");

                                    stmt.executeUpdate(updateCreateSql.toString());
                                    conn.commit();
                                    System.out.println("kk" + updateCreateSql);

                                }

                            }
                        } else {

                            /*
                             * refactor column data type */
                            updateCreateSql.append(" DROP COLUMN ").append(_sortInfoInDataBase.get(forCounter).get("name"));
                            System.out.println(updateCreateSql);

                            stmt.executeUpdate(updateCreateSql.toString());
                            updateCreateSql.setLength(0);
                            updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n")
                                    .append("ADD ").append(_infoInCode.get(forCounter).replace(")", ""));
                            System.out.println(updateCreateSql);

                            stmt.executeUpdate(updateCreateSql.toString());
                            conn.commit();




                        }
                    }





                } catch (java.lang.IndexOutOfBoundsException ex) {

                 if (forCounter >= _sortInfoInDataBase.size()) {
                    /*
                     * refactor add new column  */

                    if (_infoInCode.get(forCounter).toUpperCase().contains("PRIMARY KEY")) {
                        updateCreateSql.setLength(0);
                        updateCreateSql.append("DROP TABLE  ").append(_object.getClass().getSimpleName());
                        stmt.executeUpdate(updateCreateSql.toString());
                        System.out.println(createQuery);
                        stmt.executeUpdate(createQuery);
                      //  conn.commit();


                    } else {
                        if (_infoInCode.get(forCounter).toUpperCase().contains("NOT NULL")) {
                            /*we can not alter table to add new column with NOT NULL constraint with set null value  */
                            if (!_infoInCode.get(forCounter).toUpperCase().contains("DEFAULT")) {
                                updateCreateSql.append("ADD ").append(_infoInCode.get(forCounter).replace(")", ""))
                                        .append(" ").append("DEFAULT ").append("0");
                                System.out.println(updateCreateSql);

                                stmt.executeUpdate(updateCreateSql.toString());
                                conn.commit();

                            } else {
                                updateCreateSql.append("ADD ").append(_infoInCode.get(forCounter).replace(")", ""));
                                System.out.println("else " + updateCreateSql );
                                stmt.executeUpdate(updateCreateSql.toString());
                                conn.commit();
                            }

                        } else {
                            updateCreateSql.append("ADD ").append(_infoInCode.get(forCounter).replace(")", ""));
                            System.out.println(updateCreateSql);

                            stmt.executeUpdate(updateCreateSql.toString());
                            conn.commit();


                        }
                    }
                     } else
                if (!_infoInCode.get(forCounter).toUpperCase().contains(_infoInDataBase.get(forCounter).get("name").toUpperCase())) {
                       System.out.println(_infoInCode.get(forCounter) + "rename");
                /*     * refactor column name */

                        updateCreateSql.append("RENAME ").append(_infoInDataBase.get(forCounter).get("name"))
                                .append(" ").append("to ").append(codeColumnName[0]);
                               _sortInfoInDataBase.add(_infoInDataBase.get(forCounter));
                        System.out.println(updateCreateSql);
                        try {
                            stmt.executeUpdate(updateCreateSql.toString());
                           conn.commit();
                        } catch (SQLException x) {
                            conn.rollback();
                            x.printStackTrace();
                        }
                     }
                }


            }



        } catch (SQLException e1){
          //  e1.printStackTrace();
            conn.rollback();
            return 0;
        }


            return 1;
        }
    }

