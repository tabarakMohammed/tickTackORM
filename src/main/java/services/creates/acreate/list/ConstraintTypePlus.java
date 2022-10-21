package services.creates.acreate.list;

public enum ConstraintTypePlus {
    empty(""),
    NOTNULL("NOT NULL"),
    UNIQUE("UNIQUE");


    private final String displayName ;

    ConstraintTypePlus(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }

    @Override public String toString() { return displayName; }
}
