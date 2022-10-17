package services.fetchs.fetch;

import dbconnection.establishe.SqliteConnect;
import services.fetchs.ifetch.IFetch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Fetch<T> implements IFetch<T> {

 @Override
 public List<T> start(T object,String sqlSelect) {

  try {
   Connection _connection = SqliteConnect.getConnect();
   Statement _statement = _connection.createStatement();
   ResultSet _resultSet = _statement.executeQuery(sqlSelect);
   PreData<T> _PreData = new PreData<>(_resultSet);

   return _PreData.setupResult(object);
    }
    catch (Exception e) {
      e.printStackTrace();
     return null;

    }


 }


}
