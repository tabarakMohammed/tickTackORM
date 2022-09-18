package modelTester;

import services.createPkg.createAnnotations.columnConstraint;
import services.createPkg.createAnnotations.makeTable;
import services.createPkg.createAnnotations.size;

@makeTable
public class test{


    @columnConstraint(constraint = constraintType.PRIMARY_KEY)
    int id;
    @columnConstraint(constraint = constraintType.NOTNULL)
    @size(filedSize = 20)
    String username;

    @columnConstraint(constraint = constraintType.NOTNULL)
    @size(filedSize = 40)
    String password;


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