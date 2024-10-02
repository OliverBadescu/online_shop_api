package mycode.online_shop_api.app.products.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = QuantityValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantityConstraint {

    String message() default "Invalid quantity";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
