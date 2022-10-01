package services.creates.acreate;


import services.creates.acreate.list.ConstraintType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SqliteColumn {
   double defaultConstraint() default (0.000);
   String check() default ("SQL_Condition");
   ConstraintType constraint() default ConstraintType.empty;
}


