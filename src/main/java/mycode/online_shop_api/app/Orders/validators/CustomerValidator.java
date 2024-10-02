package mycode.online_shop_api.app.orders.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.customers.model.Customer;
import mycode.online_shop_api.app.products.validator.NameConstraint;

public class CustomerValidator implements ConstraintValidator<NameConstraint, Customer> {
    @Override
    public void initialize(NameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext constraintValidatorContext) {
        return customer!= null;
    }
}
