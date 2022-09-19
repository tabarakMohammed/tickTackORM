package services.createPkg.createAnnotations;


import modelTester.constraintType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface sqliteColumn {
   double DEFAULT() default (0.000);
   String CHECK() default ("SQL Condition");
   constraintType constraint1() default constraintType.empty;
   constraintType constraint2() default constraintType.empty;
}
