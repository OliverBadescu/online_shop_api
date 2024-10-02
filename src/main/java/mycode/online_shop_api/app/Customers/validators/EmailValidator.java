package mycode.online_shop_api.app.customers.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.products.validator.NameConstraint;

public class EmailValidator implements ConstraintValidator<NameConstraint,String> {
    @Override
    public void initialize(NameConstraint constraintAnnotation) {
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
