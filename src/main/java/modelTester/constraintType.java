package modelTester;

public enum constraintType {
 empty(""),
 NOTNULL("NOT NULL"),
 UNIQUE("UNIQUE"),

 FOREIGN_KEY("FOREIGN KEY"),
 PRIMARY_KEY("PRIMARY KEY");


 private String displayName;

 constraintType(String displayName) {
  this.displayName = displayName;
 }

 public String displayName() { return displayName; }

 // Optionally and/or additionally, toString.
 @Override public String toString() { return displayName; }
}

