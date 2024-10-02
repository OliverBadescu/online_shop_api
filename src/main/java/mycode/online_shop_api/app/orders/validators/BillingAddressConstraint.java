package mycode.online_shop_api.app.orders.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BillingAddressValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface BillingAddressConstraint {

    String message() default "Invalid billing address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
