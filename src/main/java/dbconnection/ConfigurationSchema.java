package dbconnection;

import org.reflections.Reflections;
import services.creates.acreate.MakeTable;
import services.creates.create.SqliteCreate;
import services.creates.icreate.ICreate;

import java.lang.reflect.Constructor;

public class ConfigurationSchema implements  IConfigurationSchema {
    @Override
    public void setupSchema(String PackageNameForModels) {
        ICreate<Object> _sqliteCreate = new SqliteCreate<>();
        Reflections _reflections = new Reflections(PackageNameForModels);
        for (Class<?> _annotatedClass : _reflections.getTypesAnnotatedWith(MakeTable.class)) {
            try {
                Constructor<?> _constructor = _annotatedClass.getConstructor();
                _sqliteCreate.newTable(_constructor.newInstance());
            } catch (Exception e ){
                e.printStackTrace();
            }

        }
    }
}
