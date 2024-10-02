package mycode.online_shop_api.app.orderDetails.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.orders.model.Order;

public class OrderValidator implements ConstraintValidator<QuantityConstraint, Order> {
    @Override
    public void initialize(QuantityConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Order order, ConstraintValidatorContext constraintValidatorContext) {
        return order != null;
    }
}
