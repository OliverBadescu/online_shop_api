package mycode.online_shop_api.app.OrderDetails.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.Products.validator.QuantityConstraint;

public class PriceValidator implements ConstraintValidator<mycode.online_shop_api.app.Products.validator.QuantityConstraint,Double> {
    @Override
    public void initialize(QuantityConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        return aDouble != null && aDouble >= 0;
    }
}
