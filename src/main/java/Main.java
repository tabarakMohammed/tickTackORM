import DBConnection.DatabaseConnection;
import model.test;
import services.inserts.insert.InsertWorker;

public class Main {
    public static void main(String[] args) {

      //  InsertWorker my = new InsertWorker();


        DatabaseConnection dc = new DatabaseConnection();
        dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite", "model");




//        test ts = new test();
//        ts.setUsername("pop");
//        ts.setPassword("123d%*%sds6ss76");
//        ts.setBoby(1000);
//        ts.setCoco("coco");
//        ts.setGlory("glory");
//        ts.setNow("now");
//        ts.setPrice(50000);
//        my.insertRow(ts);

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



