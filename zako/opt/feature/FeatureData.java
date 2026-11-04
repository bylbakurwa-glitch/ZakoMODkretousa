package zako.opt.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import zako.opt.bench.BenchView;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FeatureData {
  String key();
  
  FeatureGroup group();
  
  boolean visual() default false;
  
  boolean enabled() default true;
  
  String config() default "";
  
  String[] mixins() default {};
  
  BenchView bench() default BenchView.NONE;
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\FeatureData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */