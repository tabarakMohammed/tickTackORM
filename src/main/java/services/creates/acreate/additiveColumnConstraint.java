package services.creates.acreate;

import services.creates.acreate.list.ConstraintType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface additiveColumnConstraint {
  ConstraintType constraint() default ConstraintType.empty;
}
