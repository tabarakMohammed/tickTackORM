package modelTester;

import services.createPkg.createAnnotations.constraintList.constraintType;
import services.createPkg.createAnnotations.sqliteColumn;
import services.createPkg.createAnnotations.makeTable;

@makeTable
public class test{


    @sqliteColumn(constraint1 = constraintType.PRIMARY_KEY)
    int id;
    @sqliteColumn(constraint1 = constraintType.NOTNULL, CHECK = ("a>b"), DEFAULT = 1000,constraint2 = constraintType.UNIQUE)
    String username;

    @sqliteColumn(constraint1 = constraintType.NOTNULL,DEFAULT = 1500)
    String password;

    @sqliteColumn
    String glory;

    @sqliteColumn
    String coco;




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