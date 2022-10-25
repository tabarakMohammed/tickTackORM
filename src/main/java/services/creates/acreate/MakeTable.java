package services.creates.acreate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * annotated for making tables*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MakeTable {
}
