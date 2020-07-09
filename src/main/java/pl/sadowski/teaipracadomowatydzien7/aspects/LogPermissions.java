package pl.sadowski.teaipracadomowatydzien7.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogPermissions {
    public boolean logExecutionTime() default false;
    public boolean logObjects() default false;
}
