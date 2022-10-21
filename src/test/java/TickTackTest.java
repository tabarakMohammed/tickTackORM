
import dbconnection.DatabaseConnection;
import dbconnection.IConnection;
import model.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import services.creates.create.SqliteCreate;
import services.creates.icreate.ICreate;
import services.deletes.delete.DeleteRepository;
import services.deletes.idelete.IDeleteRepository;
import services.fetchs.fetch.FoundRepository;
import services.inserts.iinsert.IMultiRowInsert;
import services.inserts.iinsert.ISingleRowInsert;
import services.inserts.insert.MultiInsert;
import services.inserts.insert.SingleInsert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TickTackTest {

    IConnection databaseConnection;

    @BeforeEach
   public void setup(){
        IConnection dc = new DatabaseConnection();
         dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite");
         databaseConnection = dc;
    }
    @Disabled()
    @Test()
    public void createTable() throws Exception {
        ICreate<test> _sqliteCreate = new SqliteCreate<>();
        test _test = new test();
        assertEquals(1,  _sqliteCreate.newTable(_test) );
    }

    @Test
    public void insertSingleRowIntoTable()   {
        ISingleRowInsert<test> _singleRowInsert = new SingleInsert<>();

        test _test = new test();
        _test.setUsername("test");
        _test.setPassword("_test1223^");
        _test.setNow(true);
        _test.setPrices("50,000");

        assertEquals(1,  _singleRowInsert.insertRow(_test));
    }

    @Test
    public void insertMultiRowIntoTable()   {
         IMultiRowInsert<test> _multiInsert = new MultiInsert<>();

         List<test> objectList = new ArrayList<>();

                test _test = new test();
                _test.setUsername("multi03");
                _test.setPassword("12s6ss9u");
                _test.setPrices("1334");
                _test.setNow(true);

                test _test1 = new test();
                _test1.setUsername("multi04");
                _test1.setPassword("12s6s2s9789");
                _test1.setPrices("1334");
                _test1.setNow(false);

                objectList.add(_test);
                objectList.add(_test1);

        assertEquals(1,  _multiInsert.insertMultiRow(objectList));
    }

 @Test
    public void fetchAll()   {
     test _test = new test();
     FoundRepository<test> _testFoundRepository = new FoundRepository<>(_test);
     _testFoundRepository.foundAll().forEach(x-> System.out.println(
             x.getId()
                     +"-"+
             x.getUsername()
                     +"-"+
                     x.getPassword()
                     +"-"+
                     x.getPrices()
                     +"-"+
                     x.getNow()
     ));


    }
    @Test
    public void fetchById(){
        test _test = new test();
        FoundRepository<test> _testFoundRepository = new FoundRepository<>(_test);
        long id = 1;
        _testFoundRepository.found(id).forEach(x-> System.out.println(
                x.getId()
                        +"-"+
                x.getUsername()
                        +"-"+
                        x.getPassword()
                        +"-"+
                        x.getPrices()
                        +"-"+
                        x.getNow()
        ));
    }

    @Test
    public void fetchByQuery()  {
        test _test = new test();
        FoundRepository<test> _testFoundRepository = new FoundRepository<>(_test);
        String sql = "Select * from test";
        _testFoundRepository.foundByQuery(sql).forEach(x-> System.out.println(
                x.getUsername()
                        +"-"+
                        x.getPassword()
                        +"-"+
                        x.getPrices()
                        +"-"+
                        x.getNow()
        ));



    }

    @Test
    public void Delete(){
        test _test = new test();
        IDeleteRepository<test> _TestDeleteRepository = new DeleteRepository<>(_test);
        long id = 7;
        assertEquals(1,  _TestDeleteRepository.removeById(id));

    }

}
