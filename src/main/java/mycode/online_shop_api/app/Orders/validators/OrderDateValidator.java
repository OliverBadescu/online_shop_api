package mycode.online_shop_api.app.Orders.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.Products.validator.NameConstraint;

import java.time.LocalDate;

public class OrderDateValidator implements ConstraintValidator<NameConstraint, LocalDate> {
    @Override
    public void initialize(NameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate!=null ;
    }
}
