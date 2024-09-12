package mycode.online_shop_api.app.Customers.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CountryValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryConstraint {

    String message() default "Invalid billing address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
