package model;

import services.creates.acreate.*;
import services.creates.acreate.list.ConstraintType;

@MakeTable
public class test{


    @additiveColumnConstraint( constraint = ConstraintType.PRIMARY_KEY)
    @SqliteColumn
    int id;

    @SqliteColumn(constraint = ConstraintType.NOTNULL, defaultConstraint = 1000)
    @additiveColumnConstraint(constraint = ConstraintType.UNIQUE)
    String username;

    @SqliteColumn(defaultConstraint = 1500)
    @additiveColumnConstraint(constraint = ConstraintType.NOTNULL)
    String password;

    @additiveColumnConstraint
    @SqliteColumn
    String glory;

    @additiveColumnConstraint
    @SqliteColumn
    String coco;

 @additiveColumnConstraint
    @SqliteColumn
    String now;

   @additiveColumnConstraint
    @SqliteColumn(constraint = ConstraintType.NOTNULL)
    float boby;

   @additiveColumnConstraint
    @SqliteColumn(constraint = ConstraintType.NOTNULL,defaultConstraint = 50.000)
    int price;

    public String getGlory() {
        return glory;
    }

    public String getCoco() {
        return coco;
    }

    public String getNow() {
        return now;
    }

//    public int getBoby() {
//        return boby;
//    }

    public int getPrice() {
        return price;
    }

    public void setGlory(String glory) {
        this.glory = glory;
    }

    public void setCoco(String coco) {
        this.coco = coco;
    }

    public void setNow(String now) {
        this.now = now;
    }

//    public void setBoby(int boby) {
//        this.boby = boby;
//    }

    public void setPrice(int price) {
        this.price = price;
    }

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