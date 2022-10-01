package DBConnection;


import DBConnection.DBEstablishe.SqliteConnect;
import org.reflections.Reflections;
import services.creates.create.SqliteCreate;
import services.creates.acreate.MakeTable;
import services.creates.icreate.ICreate;

import java.lang.reflect.Constructor;



public class DatabaseConnection implements IConnection {
    @Override
    public void sqliteConnect(String sqlUrl, String PackageNameForModels) {
        SqliteConnect.setConnectionUrl(sqlUrl);

        Reflections ref = new Reflections(PackageNameForModels);
        for (Class<?> cl : ref.getTypesAnnotatedWith(MakeTable.class)) {
            ICreate nTable = new SqliteCreate();
            try {
                new SqliteConnect();
                Constructor con = cl.getConstructor();
                nTable.newTable(con.newInstance());
            } catch (Exception e ){
                e.printStackTrace();
            }

        }


    }
}