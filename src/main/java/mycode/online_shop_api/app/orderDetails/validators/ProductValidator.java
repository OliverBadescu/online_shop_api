package mycode.online_shop_api.app.orderDetails.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.products.model.Product;

public class ProductValidator implements ConstraintValidator<QuantityConstraint, Product> {
    @Override
    public void initialize(QuantityConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        return product!= null;
    }
}
