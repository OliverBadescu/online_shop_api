package mycode.online_shop_api.app.Orders.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.OrderDetails.validators.QuantityConstraint;
import mycode.online_shop_api.app.Orders.model.Order;

public class OrderEmailValidator implements ConstraintValidator<QuantityConstraint, String> {

    @Override
    public void initialize(QuantityConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return false;
        }
        return s.contains("@");
    }

}
