package dbconnection;

/**
 * set up the package name of data Object(Models) in your application
 * to start auto tables creating
 * */
public interface IConfigurationSchema {
    void setupSchema( String PackageNameForModels);
}
