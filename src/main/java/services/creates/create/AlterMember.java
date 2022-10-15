package services.creates.create;


import org.sqlite.SQLiteErrorCode;
import org.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

 class AlterMember {

     protected static int alter(Connection  _connection, Statement _statement,String sqlPragmaQuery ,String createQuery, StringBuilder dataMember, Object _object) throws SQLException {
        List<Map<String, String>> _infoInDataBase = new ArrayList<>();
        List<Map<String, String>> _sortInfoInDataBase = new ArrayList<>();
        List<String> _infoInCode = new ArrayList<>();

       try (ResultSet rs = _statement.executeQuery(sqlPragmaQuery)){

          /* remove last char of string to execute correctly*/
           String removeLastBract = dataMember.substring(0,dataMember.length()-1);
           /* split the string and put in array to used as we need*/
           String[] codeClassInfo = removeLastBract.replace("\n", "").split(",");
               _infoInCode.addAll(Arrays.asList(codeClassInfo));

              /* get schema information from database*/
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

            _connection.setAutoCommit(false);

            int count;
            int setNotExist;
            for (int forCounter = 0; forCounter < _infoInCode.size(); forCounter++) {
                 /*get column name and check it to avoid contain sequential characters issue */
                String[]  codeColumnName = _infoInCode.get(forCounter).split(" ");
                /*sql command that's make changes on database*/
                StringBuilder updateCreateSql = new StringBuilder("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n");

                    /* first loop for sorting data from database to check the all changes*/
                 count = 0;
                setNotExist=0;
                while (count < _infoInDataBase.size()) {

                    if (codeColumnName[0].equalsIgnoreCase(_infoInDataBase.get(count).get("name"))) {
                        _sortInfoInDataBase.add(_infoInDataBase.get(count));
                         count = _infoInDataBase.size();
                    } else  {
                        setNotExist++;
                        if(setNotExist == _infoInDataBase.size()) {
                            /* refactor add new column  */
                            if (_infoInCode.size() > _infoInDataBase.size()) {
                               /* add to list for indexing to move*/
                                _sortInfoInDataBase.add(_infoInDataBase.get(count));

                                if (_infoInCode.get(forCounter).toUpperCase().contains("PRIMARY KEY")) {
                                    updateCreateSql.setLength(0);
                                    updateCreateSql.append("DROP TABLE  ").append(_object.getClass().getSimpleName());
                                    _statement.executeUpdate(updateCreateSql.toString());
                                    _statement.executeUpdate(createQuery);
                                    _connection.commit();
                                } else if (_infoInCode.get(forCounter).toUpperCase().contains("NOT NULL")
                                        && !_infoInCode.get(forCounter).toUpperCase().contains("DEFAULT")) {
                                    /*we can not alter table to add new column with NOT NULL constraint with set null value  */
                                    updateCreateSql.append("ADD ").append(_infoInCode.get(forCounter)).append(" ")
                                            .append("DEFAULT ").append("0");
                                    _statement.executeUpdate(updateCreateSql.toString());
                                    _connection.commit();
                                } else {
                                    updateCreateSql.append("ADD ").append(_infoInCode.get(forCounter));
                                    _statement.executeUpdate(updateCreateSql.toString());
                                    _connection.commit();
                                }
                            }
                            else
                                /*refactor rename column*/
                                if(_infoInCode.size() == _infoInDataBase.size()){
                                updateCreateSql.append("RENAME ").append(_infoInDataBase.get(forCounter).get("name"))
                                        .append(" ").append("to ").append(codeColumnName[0]);
                                try {
                                    _statement.executeUpdate(updateCreateSql.toString());
                                    _connection.commit();
                                    /* add new column name to sort list for make changes */
                                    _infoInDataBase.get(forCounter).replace("name",codeColumnName[0]);
                                    _sortInfoInDataBase.add(_infoInDataBase.get(forCounter));

                                } catch (SQLException x) {
                                    _connection.rollback();
                                    x.printStackTrace();
                                }
                            }
                        }
                    }

                    count++;
                }





                try {
                    if (_infoInCode.get(forCounter).contains(_sortInfoInDataBase.get(forCounter).get("name"))) {
                        /* all Ok, Same Name enter,  to next data type*/
                        if (_infoInCode.get(forCounter).toUpperCase().contains(_sortInfoInDataBase.get(forCounter).get("typeCol").toUpperCase())) {
                            /* all Ok Same data type */
                            if (_infoInCode.get(forCounter).toUpperCase().contains("PRIMARY KEY")) {
                                if (_sortInfoInDataBase.get(forCounter).get("isPK").equals("0")) {
                                    /*
                                     * refactor column constraint with PRIMARY KEY
                                     * drop and recreate */
                                    updateCreateSql.setLength(0);
                                    updateCreateSql.append("DROP TABLE ").append(_object.getClass().getSimpleName());
                                    _statement.executeUpdate(updateCreateSql.toString());
                                    _statement.executeUpdate(createQuery);
                                    _connection.commit();
                                }
                            }
                            if (_infoInCode.get(forCounter).toUpperCase().contains("NOT NULL")) {

                                if (_sortInfoInDataBase.get(forCounter).get("isnull").equals("0")
                                        && _sortInfoInDataBase.get(forCounter).get("isPK").equals("0")) {
                                    /*
                                     * refactor column constraint with not null */
                                    updateCreateSql.setLength(0);
                                    updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append(" ")
                                    .append("DROP  COLUMN ").append(_sortInfoInDataBase.get(forCounter).get("name"));
                                    _statement.executeUpdate(updateCreateSql.toString());


                                    updateCreateSql.setLength(0);
                                    updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append(" ")
                                            .append("ADD  ").append(_sortInfoInDataBase.get(forCounter).get("name"))
                                            .append(" ").append(_sortInfoInDataBase.get(forCounter).get("typeCol"))
                                            .append(" ").append("NOT NULL  ").append("DEFAULT ").append("0");

                                    _statement.executeUpdate(updateCreateSql.toString());
                                    _connection.commit();

                                }

                            }
                        } else {

                            /*
                             * refactor column data type */
                            updateCreateSql.setLength(0);
                            updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n")
                                    .append(" DROP COLUMN ").append(_sortInfoInDataBase.get(forCounter).get("name"));
                            _statement.executeUpdate(updateCreateSql.toString());

                            if (_infoInCode.get(forCounter).toUpperCase().contains("NOT NULL")
                                    && !_infoInCode.get(forCounter).toUpperCase().contains("DEFAULT")) {
                                /*we can not alter table to add new column with NOT NULL constraint with set null value  */
                                updateCreateSql.setLength(0);
                                updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n")
                                        .append("ADD ").append(_infoInCode.get(forCounter))
                                        .append(" ").append("DEFAULT ").append("0");
                                _statement.executeUpdate(updateCreateSql.toString());
                                _connection.commit();

                            } else {
                                updateCreateSql.setLength(0);
                                updateCreateSql.append("ALTER TABLE  ").append(_object.getClass().getSimpleName()).append("\n")
                                        .append("ADD ").append(_infoInCode.get(forCounter));
                                _statement.executeUpdate(updateCreateSql.toString());
                                _connection.commit();
                            }


                        }
                    }


                } catch (IndexOutOfBoundsException ex) {
                          ex.printStackTrace();
                }



            }


            /* check the column is remove from class and exist in table, we drop it */
           StringBuilder updateCreateSql = new StringBuilder();
               _infoInDataBase.forEach((xCol)->{
                   AtomicInteger counterNotExits = new AtomicInteger(0);
                   AtomicInteger objectCounter = new AtomicInteger(0);

                           _infoInCode.forEach(cCol -> {
                               String[]  codeColumnName = _infoInCode.get(objectCounter.get()).split(" ");
                               if(!xCol.get("name").equalsIgnoreCase(codeColumnName[0])){
                                   counterNotExits.getAndIncrement();
                               }
                               if(_infoInCode.size() == counterNotExits.get() ){
                                       if(_infoInCode.size() < _infoInDataBase.size()) {
                                           updateCreateSql.setLength(0);
                                           updateCreateSql.append("ALTER TABLE ").append(_object.getClass().getSimpleName()).append(" ").append("DROP COLUMN '");
                                           updateCreateSql.append(xCol.get("name")).append("'");
                                       }
                                   try {
                                       _statement.executeUpdate(updateCreateSql.toString());
                                       _connection.commit();
                                   }catch (SQLException sqLiteException){sqLiteException.printStackTrace();}
                               }
                               objectCounter.getAndIncrement();
                           });
               });




        } catch (SQLException e){
            e.printStackTrace();
           if(e.getMessage().contains("(near \")\": syntax error)") || e.getMessage().contains("(error in table test after add column: no such column: ") ) {
               throw new SQLiteException("check the condition of Column Constraint ( @Check (condition!) ) in Class "
                       +_object.getClass().getSimpleName(), SQLiteErrorCode.getErrorCode(1));
            }
            _connection.rollback();
            return 0;
        }



            return 1;
        }


}

