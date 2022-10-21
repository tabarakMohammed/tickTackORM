package model;

import services.creates.acreate.*;
import services.creates.acreate.list.ConstraintType;
import services.creates.acreate.list.ConstraintTypePlus;

@MakeTable
public class test{


    @additiveColumnConstraint()
    @SqliteColumn(constraint = ConstraintType.PRIMARY_KEY)
    int id;

    @SqliteColumn(constraint = ConstraintType.NOTNULL)
    @additiveColumnConstraint(constraint = ConstraintTypePlus.UNIQUE)
    String username;

    @SqliteColumn(defaultConstraint = 1500)
    @additiveColumnConstraint(constraint = ConstraintTypePlus.NOTNULL)
    String password;



    @SqliteColumn(constraint = ConstraintType.NOTNULL)
    boolean now;



    @SqliteColumn(constraint = ConstraintType.NOTNULL,defaultConstraint = 50.000)
    String prices;

    public int getId() {
        return id;
    }

    public String getPrices() {return prices;}

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public boolean getNow() {return now;}

    public void setId(int id) {
        this.id = id;
    }

    public void setNow(boolean now) {this.now = now;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }
}