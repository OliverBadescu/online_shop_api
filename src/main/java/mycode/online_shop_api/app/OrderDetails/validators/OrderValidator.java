package mycode.online_shop_api.app.OrderDetails.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.Orders.model.Order;

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
