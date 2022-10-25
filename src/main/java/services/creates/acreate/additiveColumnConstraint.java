package services.creates.acreate;
import services.creates.acreate.list.ConstraintTypePlus;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * annotated for add more columns attributes*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface additiveColumnConstraint {
  ConstraintTypePlus constraint() default ConstraintTypePlus.empty;
}
