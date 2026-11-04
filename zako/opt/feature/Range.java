package zako.opt.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Range {
  int min();
  
  int max();
  
  String unit() default "zakoopt.blocks";
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\Range.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */