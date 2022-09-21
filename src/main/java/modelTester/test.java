package modelTester;

import services.createPkg.createAnnotations.constraintList.constraintType;
import services.createPkg.createAnnotations.sqliteColumn;
import services.createPkg.createAnnotations.makeTable;
import services.createPkg.createAnnotations.size;

@makeTable
public class test{


    @sqliteColumn(constraint1 = constraintType.PRIMARY_KEY)
    int id;
    @sqliteColumn(constraint1 = constraintType.NOTNULL, CHECK = ("a>b"), DEFAULT = 1000,constraint2 = constraintType.UNIQUE)
    @size(filedSize = 20)
    String username;

    @sqliteColumn(constraint1 = constraintType.NOTNULL,DEFAULT = 1500)
    @size(filedSize = 40)
    String password;

    @sqliteColumn
  String glory;




    public int getId() {return id;}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {this.id = id;}
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}