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
            preparedData _preparedData = new preparedData(_preparedStatement);
            _preparedData.setupStatement(object);
            int processCheck = _preparedStatement.executeUpdate();
            if (processCheck == 1) {
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

