package services.creates.acreate;
import services.creates.acreate.list.ConstraintType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
/**
 * annotated for making columns and there attributes*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SqliteColumn {
   double defaultConstraint() default (0.000);
   String check() default ("SQL_Condition");
   ConstraintType constraint() default ConstraintType.empty;
}


