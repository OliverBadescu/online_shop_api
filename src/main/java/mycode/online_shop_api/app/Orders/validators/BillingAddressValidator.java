package mycode.online_shop_api.app.orders.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.products.validator.NameConstraint;

public class BillingAddressValidator implements ConstraintValidator<NameConstraint,String> {
    @Override
    public void initialize(NameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s!= null;
    }
}
