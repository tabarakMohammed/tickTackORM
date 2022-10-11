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
        /* insert sql command query*/
        String sqlInsertStatement = "INSERT INTO " + object.getClass().getSimpleName() + "";
        Field[] objectAttributes = object.getClass().getDeclaredFields();
        /* make sql command query for generic object*/
        sqlInsertStatement = sqlInsertStatement + preparedData.sqliteInsertQuery(objectAttributes);

        try {
            Connection _connection = SqliteConnect.getConnect();
            /*create new PreparedStatement object and inject it into the preparedData class
             for ensure there is one object and single, to make our operations on it*/
            PreparedStatement _preparedStatement = _connection.prepareStatement(sqlInsertStatement);
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

