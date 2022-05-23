package saffchen.checkvalidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SatelliteConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @
interface City {
    String message() default "Укажите название склада Москва или Санкт-Петербург";
    Class<?> [] groups() default {};
    Class <? extends Payload>[] payload() default {};
}