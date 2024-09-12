package mycode.online_shop_api.app.OrderDetails.dtos;

import mycode.online_shop_api.app.OrderDetails.validators.OrderConstraint;
import mycode.online_shop_api.app.OrderDetails.validators.PriceConstraint;
import mycode.online_shop_api.app.OrderDetails.validators.ProductConstraint;
import mycode.online_shop_api.app.OrderDetails.validators.QuantityConstraint;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Products.model.Product;

public record CreateOrderDetailsRequest(
        @PriceConstraint(message = "Price cannot be empty")
        double price,
        @QuantityConstraint(message = "Quantity cannot be empty")
        int quantity,
        @OrderConstraint(message = "Order cannot be empty")
        Order order,
        @ProductConstraint(message = "Product cannot be empty")
        Product product
) {
}
