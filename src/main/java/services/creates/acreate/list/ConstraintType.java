package services.creates.acreate.list;


/**
 * list of specific attributes for annotations of columns
 * default empty using for just annotated us this is a column to be created,
 * Not null that's making table not accepting null value,
 * UNIQUE for UNIQUE values to inserting in the table,
 * PRIMARY KEY that was ID in the table, we're taking as an ID of table */

public enum ConstraintType {
 empty(""),
 NOTNULL("NOT NULL"),
 UNIQUE("UNIQUE"),
 FOREIGN_KEY("FOREIGN KEY"),
 PRIMARY_KEY("PRIMARY KEY");


 private final String displayName ;

  ConstraintType(String displayName) {
  this.displayName = displayName;
 }

 public String displayName() { return displayName; }

 @Override public String toString() { return displayName; }
}

