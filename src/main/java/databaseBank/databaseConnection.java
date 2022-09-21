package databaseBank;


import databaseBank.makeEstablishe.sqliteConnect;
import org.reflections.Reflections;
import services.createPkg.create.create;
import services.createPkg.createAnnotations.makeTable;
import java.lang.reflect.Constructor;



public class databaseConnection implements  connectionInterface {
    @Override
    public void sqliteConnect(String url, String PackageNameForModels) {
        sqliteConnect.setConnectionUrl(url);

        Reflections ref = new Reflections(PackageNameForModels);
        for (Class<?> cl : ref.getTypesAnnotatedWith(makeTable.class)) {
            create nTable = new create();
            try {
                new sqliteConnect();
                Constructor con = cl.getConstructor();
                nTable.newTable(con.newInstance());
            } catch (Exception e ){
                e.printStackTrace();
            }

        }


    }
}