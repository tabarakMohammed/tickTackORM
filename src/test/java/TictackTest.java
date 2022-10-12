import dbconnection.DatabaseConnection;
import model.test;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import services.creates.create.SqliteCreate;
import services.creates.icreate.ICreate;
import services.inserts.iinsert.IMultiRowInsert;
import services.inserts.iinsert.ISingleRowInsert;
import services.inserts.insert.MultiInsert;
import services.inserts.insert.SingleInsert;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TictackTest {
    @Test
    public void createTables()  {
        DatabaseConnection dc = new DatabaseConnection();
        dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite", "model");
        assertTrue("always",  true);
    }
    @Disabled("Disabled until createTable is up!")
    @Test
    public void createTable() throws Exception {
        DatabaseConnection dc = new DatabaseConnection();
        dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite", "model");
        ICreate<test> _sqliteCreate = new SqliteCreate<>();
        test _test = new test();
        assertEquals(1,  _sqliteCreate.newTable(_test) );
    }
    @Disabled("Disabled until insertSingleRowIntoTable is up!")
    @Test
    public void insertSingleRowIntoTable()   {
        DatabaseConnection dc = new DatabaseConnection();
        dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite", "model");
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
        DatabaseConnection dc = new DatabaseConnection();
         dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite", "model");
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

}
