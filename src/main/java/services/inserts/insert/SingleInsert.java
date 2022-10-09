package services.inserts.insert;

import dbconnection.establishe.SqliteConnect;
import services.inserts.iinsert.ISingleRowInsert;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class SingleInsert<T> implements ISingleRowInsert<T> {
    @Override
    public int insertRow(T object) {
        String sqlInsertStatement = "INSERT INTO " + object.getClass().getSimpleName() + "";
        Field[] objectAttributes = object.getClass().getDeclaredFields();
        sqlInsertStatement = sqlInsertStatement + preparedData.sqliteInsertQuery(objectAttributes);

        try {

            new SqliteConnect();

            Connection conn = SqliteConnect.getConnect();
            PreparedStatement _preparedStatement = conn.prepareStatement(sqlInsertStatement);
            preparedData on = new preparedData(_preparedStatement);

            int ch = on.getData(object).executeUpdate();
            if (ch != 0) {
                SqliteConnect.closeConnect();
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


}

