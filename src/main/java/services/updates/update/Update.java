package services.updates.update;

import dbconnection.establishe.SqliteConnect;
import services.updates.update.iupdate.IUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class Update<T> implements IUpdate<T> {



    @Override
    public int update(T object,String SqlUpdate) {

        try {
            Connection _connection = SqliteConnect.getConnect();
            PreparedStatement _preparedStatement = _connection.prepareStatement(SqlUpdate);

            PreparedUpdateData<T> _preparedUpdateData = new PreparedUpdateData<>(_preparedStatement);
            _preparedUpdateData.setupStatement(object,SqlUpdate);
            _preparedStatement.executeUpdate();

            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;

        }

    }
}
