import modelTester.test;
import services.databaseBank.databaseConnection;
import services.insertPkg.insert.insert;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        insert my = new insert();


        databaseConnection dc = new databaseConnection();
        dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite");

        /**
        test ts = new test();
        ts.setUsername("non");
        ts.setPassword("123d%*%sds6ss");
        my.insertRow(ts);
      */
        /**
        List<test> myls = new ArrayList<>();
        test ts = new test();
        ts.setUsername("list1");
        ts.setPassword("12s6ss");
        test ts1 = new test();
        ts1.setUsername("list2");
        ts1.setPassword("12s6s2s");
        test ts2 = new test();
        ts2.setUsername("list3");
        ts2.setPassword("12s6s3s");
        myls.add(ts);
        myls.add(ts1);
        myls.add(ts2);
        my.insertMultiRow(myls);
        */
    }

}



