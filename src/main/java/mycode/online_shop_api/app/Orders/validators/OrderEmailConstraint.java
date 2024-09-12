package mycode.online_shop_api.app.Orders.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import mycode.online_shop_api.app.OrderDetails.validators.OrderValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OrderEmailValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderEmailConstraint {
    String message() default "Invalid input";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
