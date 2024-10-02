package mycode.online_shop_api.app.orderDetails.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.products.validator.QuantityConstraint;

public class PriceValidator implements ConstraintValidator<mycode.online_shop_api.app.products.validator.QuantityConstraint,Double> {
    @Override
    public void initialize(QuantityConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        return aDouble != null && aDouble >= 0;
    }
}
