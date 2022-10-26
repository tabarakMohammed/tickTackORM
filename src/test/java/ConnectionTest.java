import dbconnection.ConfigurationSchema;
import dbconnection.DatabaseConnection;
import dbconnection.IConfigurationSchema;
import dbconnection.IConnection;
/*import org.junit.jupiter.api.BeforeAll;*/
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

public class ConnectionTest {

    /*
    * @BeforeAll
    * public static void schemaBefore(){
    *   IConnection dc = new DatabaseConnection();
    *    dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite");
    *   IConfigurationSchema configurationSchema = new ConfigurationSchema();
    *   configurationSchema.setupSchema("model");
    * }
    */



      @BeforeAll
      public static void schemaBefore(){
        IConnection dc = new DatabaseConnection();
         dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite");
        IConfigurationSchema configurationSchema = new ConfigurationSchema();
        configurationSchema.setupSchema("model");
      }

    @Test
    public void connectionClass()  {
        IConnection dc = mock(DatabaseConnection.class);
        dc.sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite");
        verify(dc,times(1))
                .sqliteConnect("jdbc:sqlite:D:/backup/test.sqlite");
    }

    @Test
    public void createSchema(){
        IConfigurationSchema configurationSchema =  mock(ConfigurationSchema.class);
        configurationSchema.setupSchema("model");
        verify(configurationSchema,times(1))
                .setupSchema("model");
    }



}
