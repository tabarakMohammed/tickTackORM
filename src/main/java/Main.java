import dbconnection.DatabaseConnection;
import model.test;
import services.inserts.iinsert.IMultiRowInsert;
import services.inserts.iinsert.ISingleRowInsert;
import services.inserts.insert.MultiInsert;
import services.inserts.insert.SingleInsert;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        IMultiRowInsert<test>_multiInsert = new MultiInsert();
//        ISingleRowInsert<test> _singleRowInsert = new SingleInsert();

        DatabaseConnection dc = new DatabaseConnection();
        dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite", "model");




//        test ts = new test();
//        ts.setUsername("radonitsch");
//        ts.setPassword("123dww%*%sds6ss76");
//        ts.setNow(true);
//        ts.setPrices("123dd");
//        _singleRowInsert.insertRow(ts);


//        List<test> myls = new ArrayList<>();
//        test ts = new test();
//        ts.setUsername("list801");
//        ts.setPassword("12s6ss9u");
//        ts.setPrices("1334");
//        ts.setNow(false);
//        test ts1 = new test();
//        ts1.setUsername("list802");
//        ts1.setPassword("12s6s2s9789");
//        ts1.setPrices("1334");
//        ts1.setNow(false);
////        test ts2 = new test();
////        ts2.setUsername("list709");
////        ts2.setPassword("12s6s3suyyy");
////        ts2.setPrices("1334");
////        ts2.setNow(true);
//        myls.add(ts);
//        myls.add(ts1);
////        myls.add(ts2);
//        _multiInsert.insertMultiRow(myls);


    }


}



