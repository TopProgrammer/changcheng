package annotation.selfannotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Notnull {
  public boolean NotNull() default true;
}
