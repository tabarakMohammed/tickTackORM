import databaseBank.databaseConnection;
import modelTester.burger;
import modelTester.test;
import services.createPkg.create.create;
import services.insertPkg.insert.insert;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

      //  insert my = new insert();


        databaseConnection dc = new databaseConnection();
        dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite","modelTester");

//        create nTable = new create();
//        burger bg = new burger();
//
//        test ts = new test();
//
//
//try {
//            nTable.newTable(ts);
//        } catch (NullPointerException e){
//    e.printStackTrace();
//}


/**
        test ts = new test();
        ts.setUsername("non67");
        ts.setPassword("123d%*%sds6ss76");
        my.insertRow(ts);
*/
/**
        List<test> myls = new ArrayList<>();
        test ts = new test();
        ts.setUsername("list107");
        ts.setPassword("12s6ss9u");
        test ts1 = new test();
        ts1.setUsername("list289");
        ts1.setPassword("12s6s2s9789");
        test ts2 = new test();
        ts2.setUsername("list3778");
        ts2.setPassword("12s6s3suyyy");
        myls.add(ts);
        myls.add(ts1);
        myls.add(ts2);
        my.insertMultiRow(myls);
 */

    }


}



