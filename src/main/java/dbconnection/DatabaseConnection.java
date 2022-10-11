package dbconnection;


import dbconnection.establishe.SqliteConnect;
import org.reflections.Reflections;
import services.creates.create.SqliteCreate;
import services.creates.acreate.MakeTable;
import services.creates.icreate.ICreate;

import java.lang.reflect.Constructor;



public class DatabaseConnection implements IConnection {
    @Override
    public void sqliteConnect(String sqlUrl, String PackageNameForModels) {

        new  SqliteConnect(sqlUrl);

        Reflections _reflections = new Reflections(PackageNameForModels);
        for (Class<?> _annotatedClass : _reflections.getTypesAnnotatedWith(MakeTable.class)) {
            ICreate _sqliteCreate = new SqliteCreate();
            try {
                Constructor _constructor = _annotatedClass.getConstructor();
                _sqliteCreate.newTable(_constructor.newInstance());
            } catch (Exception e ){
                e.printStackTrace();
            }

        }


    }
}