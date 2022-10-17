package services.fetchs.fetch;

import dbconnection.establishe.SqliteConnect;
import services.fetchs.ifetch.IFetch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Fetch<T> implements IFetch<T> {

 T object;


 public Fetch(T bo)  {
  this.object = bo;
 };

 @Override
 public List<T> found(String sqlSelect) {

  try {
   Connection _connection = SqliteConnect.getConnect();
   Statement _statement = _connection.createStatement();
   ResultSet _resultSet = _statement.executeQuery(sqlSelect);
   PreData _PreData = new PreData(_resultSet);

   return _PreData.setupStatement(object);
    }
    catch (Exception e) {
      e.printStackTrace();
     return null;

    }


 }


}
