package mycode.online_shop_api.app.orderDetails.dtos;

import mycode.online_shop_api.app.orderDetails.validators.OrderConstraint;
import mycode.online_shop_api.app.orderDetails.validators.PriceConstraint;
import mycode.online_shop_api.app.orderDetails.validators.ProductConstraint;
import mycode.online_shop_api.app.orderDetails.validators.QuantityConstraint;
import mycode.online_shop_api.app.orders.model.Order;
import mycode.online_shop_api.app.products.model.Product;

import java.io.Serializable;

public record CreateOrderDetailsRequest(
        @PriceConstraint(message = "Price cannot be empty")
        double price,
        @QuantityConstraint(message = "Quantity cannot be empty")
        int quantity,
        @OrderConstraint(message = "Order cannot be empty")
        Order order,
        @ProductConstraint(message = "Product cannot be empty")
        Product product
) implements Serializable {
}
