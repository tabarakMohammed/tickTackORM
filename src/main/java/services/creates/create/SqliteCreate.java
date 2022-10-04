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
       // System.out.println(createQuery);

      /*___*/






        try (Connection conn = SqliteConnect.getConnect();
             Statement stmt = conn.createStatement()) {

            String existTable = "SELECT EXISTS (SELECT * FROM sqlite_master WHERE tbl_name = "+"'"+_object.getClass().getSimpleName() +"'"+")";
            ResultSet result =  stmt.executeQuery(existTable);
            result.getString("EXISTS (SELECT * FROM sqlite_master WHERE tbl_name = "+"'"+_object.getClass().getSimpleName() +"'"+")");

           return  MemberAlter.alter(conn,stmt,sqlPragmaQuery,createQuery,dataMember,_object);



        } catch (SQLException e) {
            if(e.getMessage().startsWith("no such column:") || e.getErrorCode() == 0) {
                try (Connection conn = SqliteConnect.getConnect();
                     Statement statement = conn.createStatement()) {
                     statement.executeUpdate(createQuery);
                } catch (SQLException r) {
                    r.printStackTrace();
                }
            }
           e.printStackTrace();
           return 0;
        }

    }
}
