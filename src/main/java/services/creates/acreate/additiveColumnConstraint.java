package services.creates.acreate;

import services.creates.acreate.list.ConstraintTypePlus;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface additiveColumnConstraint {
  ConstraintTypePlus constraint() default ConstraintTypePlus.empty;
}
