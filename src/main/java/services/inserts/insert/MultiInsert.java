package services.inserts.insert;

import dbconnection.establishe.SqliteConnect;
import services.inserts.iinsert.IMultiRowInsert;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class MultiInsert<T> implements IMultiRowInsert<T> {
    @Override
    public int insertMultiRow(List<T> objects) {
        String sqlInsertStatement = "INSERT INTO " + objects.get(0).getClass().getSimpleName() + "";
        Field[] objectAttributes = objects.get(0).getClass().getDeclaredFields();
        sqlInsertStatement = sqlInsertStatement + preparedData.sqliteInsertQuery(objectAttributes);

        int _i = 0;
        int _return;
        try {
            Connection conn = SqliteConnect.getConnect();
            PreparedStatement _preparedStatement = conn.prepareStatement(sqlInsertStatement);
            preparedData _preparedData = new preparedData(_preparedStatement);

            for (T it : objects) {
                _preparedData.setupStatement(it);
                _preparedStatement.addBatch();
                _i++;
            }
            if (_i % 1000 == 0 || _i == objects.size()) {
                _preparedStatement.executeBatch();

            }
            _return = 1;

        } catch (SQLException e) {
            e.printStackTrace();
            _return = 0;
        }
        return _return;
    }

}
