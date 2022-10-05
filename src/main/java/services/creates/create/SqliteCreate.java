package services.creates.create;

import DBConnection.DBEstablishe.SqliteConnect;
import org.sqlite.SQLiteException;
import services.creates.icreate.ICreate;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;

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
       // System.out.println(createQuery);

      /*___*/






        try (Connection conn = SqliteConnect.getConnect();
             Statement stmt = conn.createStatement()) {

            String existTable = "EXISTS (SELECT * FROM sqlite_master WHERE tbl_name = "+"'"+_object.getClass().getSimpleName() +"'"+")";
            ResultSet result =  stmt.executeQuery(" SELECT "+ existTable);
            String exist = result.getString(existTable);

                if(exist.equals("1")) {
                    return MemberAlter.alter(conn, stmt, sqlPragmaQuery, createQuery, dataMember, _object);
                } else {
                    try {
                        stmt.execute(createQuery);
                    }catch (SQLiteException e){
                        e.printStackTrace();

                        if(e.getMessage().startsWith("[SQLITE_ERROR] SQL error or missing database")  ||  e.getErrorCode() == 1) {
                            throw new SQLiteException("check the condition of Column Constraint ( @Check (condition!) ) in Class "
                                    +_object.getClass().getSimpleName()
                                    ,e.getResultCode());
                        }
                    }
                }


        } catch (SQLException e) {
            e.printStackTrace();

            return 0;

        }
        return 1;

    }

    }

