package mycode.online_shop_api.app.Customers.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.Products.validator.NameConstraint;

public class FullNameValidator implements ConstraintValidator<NameConstraint,String> {
    @Override
    public void initialize(NameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null;
    }
}
