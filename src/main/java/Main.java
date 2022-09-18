import databaseBank.databaseConnection;
import modelTester.test;
import services.createPkg.create.create;
import services.createPkg.createAnnotations.columnConstraint;
import services.createPkg.createAnnotations.size;
import services.insertPkg.insert.insert;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {

        insert my = new insert();


        databaseConnection dc = new databaseConnection();
        dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite");
    //    create nTable = new create();
        test ts = new test();

//        Field[] objectAttributes = ts.getClass().getDeclaredFields();
//        for (Field _objectAttributes : objectAttributes) {
//
//            if(
//               _objectAttributes.getAnnotation(columnConstraint.class) != null ) {
//                System.out.print(_objectAttributes.getName() + "-"
//
//                                + _objectAttributes.getAnnotation(columnConstraint.class).constraint()
//                                + "-"
//                                + _objectAttributes.getAnnotation(columnConstraint.class).DEFAULT()
//                                + "-"
//                                + _objectAttributes.getAnnotation(columnConstraint.class).CHECK()
//                                +
//                                _objectAttributes.getAnnotation(columnConstraint.class).constraint2() +
//
//                        "-"
//
//                );
//
//            } if (_objectAttributes.getAnnotation(size.class) != null) {
//                System.out.println(    _objectAttributes.getAnnotation(size.class).filedSize());
//            }
//        }

      //  nTable.newTable(ts);



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



