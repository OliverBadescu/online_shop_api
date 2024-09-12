package mycode.online_shop_api.app.OrderDetails.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OrderValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderConstraint {
    String message() default "Invalid order";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
