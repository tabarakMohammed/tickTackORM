# tickTackORM
java Library for sqlite databsae (Object Relational Mapping) -- begin simple 

### How to use 

# using maven [Maven Central](https://central.sonatype.dev/artifact/io.github.tabarakmohammed/ticktackorm/0-1-1-beta/overview)

      ```
      <dependency>
          <groupId>io.github.tabarakmohammed</groupId>
          <artifactId>ticktackorm</artifactId>
          <version>0-1-1-beta</version>
      </dependency>
      ```

First make connection whith database sqlite file :
  ```
   IConnection dc = new DatabaseConnection();
   dc.sqliteConnect("jdbc:sqlite:Path To database File");
  ```  
for creating new tables and columns : 
```
   IConfigurationSchema configurationSchema = new ConfigurationSchema();
   configurationSchema.setupSchema("object model paackge name");
```
____________________
 First make Object class 
  
  use this @MakeTable annotation to create table 
  then in this class define variables 
  and use @SqliteColumn annotation for create columns
  
  @SqliteColumn has specifi parameters for define the attribute or Properties of columns in the table
 ````
 NOTNULL,UNIQUE,FOREIGN_KEY,PRIMARY_KEY,PRIMARY_KEY_AUTOINCREMENT.
 
 by example:
 @SqliteColumn(constraint = ConstraintType.NOTNULL)
 ````
 you can follow this link to see the example of object definintion
  [The Example](https://github.com/tabarakMohammed/tickTackORM/blob/main/src/test/java/model/test.java)
  
  ____________________________________________________________________
## general usage 

> create new crud and fetch countiner of specific  object :
  ```
  modelObject _modelObject = new modelObject();
  FUIDRepositoryInterface<test> _TestRepository_modelObject = new FUIDRepository<>(_modelObject);

  long id =1;
  String sqlCommand = "...";
  List<_modelObject> list_modelObject = new ArrayList<>();
  
    _TestRepository_modelObject.insertSingleRow(_modelObject)
    _TestRepository_modelObject.insertMultiRow(list_modelObject)
  
    list_modelObject = _TestRepository_modelObject.foundAll(id)
    list_modelObject = _TestRepository_modelObject.foundAllById(id)
    list_modelObject = _TestRepository_modelObject.foundAllBySqlCommandQuery(sqlCommand)
  
    _TestRepository_modelObject.deleteById(id)
    _TestRepository_modelObject.deleteBySqlCommand(sqlCommand)
    _TestRepository_modelObject.updateById(sqlCommand)
    _TestRepository_modelObject.updateBySqlCommand(sqlCommand)
 ``` 
 ____________________________________________________________________________
## specific usage
  
 **1. Inserting 
 
  - for one row :

                
          ISingleRowInsert<modelObject> _singleRowInsert = new SingleInsert<>();

                   modelObject _modelObject = new modelObject();

          _singleRowInsert.insertRow(_modelObject)
             
  - for more than one row :
              
              IMultiRowInsert<modelObject> _multiInsert = new MultiInsert<>();

                      modelObject _modelObject = new modelObject();
                      List<_modelObject> list_modelObject = new ArrayList<>();
                      list_modelObject.add(_modelObject)

              _multiInsert.insertMultiRow(list_modelObject)
              
  
 **2. Fetching 
             
   - get All data :
            
                      modelObject _modelObject = new modelObject();
             IFoundRepository<modelObject> _modelObject_FoundRepository = new FoundRepository<>(_modelObject);
            _modelObject_FoundRepository.foundAll()
            
   - get data by using id :
              
                        long id = 1
                        modelObject _modelObject = new modelObject();
             IFoundRepository<modelObject> _modelObject_FoundRepository = new FoundRepository<>(_modelObject);
             _modelObject_FoundRepository.foundById(id)
              
   - get data by using sqlite command :
                
                    String sqlCommand = "Select * from test";
                    modelObject _modelObject = new modelObject();
             IFoundRepository<modelObject> _modelObject_FoundRepository = new FoundRepository<>(_modelObject);
            _modelObject_FoundRepository.foundBySqlCommand(sqlCommand)
          
  
 **3. removing 
  
   - using ID :
          
                    long id = 1
                    modelObject _modelObject = new modelObject();
          IDeleteRepository<modelObject> _modelObject_DeleteRepository = new DeleteRepository<>(_modelObject);
          _modelObject_DeleteRepository.removeById(id)
          
   - using sqlite command : 
          
                    String sqlCommand = "DELETE FROM artists_backup WHERE artistid = 1;"
                    modelObject _modelObject = new modelObject();
          IDeleteRepository<modelObject> _modelObject_DeleteRepository = new DeleteRepository<>(_modelObject);
          _modelObject_DeleteRepository.removeBySqlCommand(sqlCommand)
          
 **4. updating 
  
   - using ID :
            
                      long id = 1
                      modelObject _modelObject = new modelObject();
              IUpdateRepository<modelObject> _modelObject_UpdateRepository = new UpdateRepository<>(_modelObject);
              _modelObject_DeleteRepository.updateById(id)
            ```
   - using sqlite command :
              

                      String sqlCommand = "update test set password = ? where id = 6"
                      modelObject _modelObject = new modelObject();
              IUpdateRepository<modelObject> _modelObject_UpdateRepository = new UpdateRepository<>(_modelObject);
              _modelObject_DeleteRepository.updateBySqlCommand(sqlCommand)
            
 ________________________________________ _________________________________
###  for creating specific table :
              
              modelObject _modelObject = new modelObject();
      ICreate<modelObject> _sqliteCreate = new SqliteCreate<>();
      _sqliteCreate.newTable(_modelObject)


  
