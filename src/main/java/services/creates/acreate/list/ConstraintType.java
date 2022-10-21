package services.creates.acreate.list;




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

