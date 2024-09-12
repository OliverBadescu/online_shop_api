package mycode.online_shop_api.app.OrderDetails.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Products.model.Product;

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
