package mycode.online_shop_api.app.orderDetails.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductConstraint {
    String message() default "Invalid product";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
