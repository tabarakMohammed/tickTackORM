package services.deletes.delete;

import dbconnection.establishe.SqliteConnect;
import services.deletes.idelete.IDelete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete<T> implements IDelete<T> {
    @Override
    public int beginRemove(String sqlDelete) {
        try{

            Connection _connection = SqliteConnect.getConnect();
            PreparedStatement _preparedStatement = _connection.prepareStatement(sqlDelete);
           _preparedStatement.executeUpdate();

           return 1;
        }catch (SQLException _sqlException){

            _sqlException.printStackTrace();
            return 0;
        }


    }
}
