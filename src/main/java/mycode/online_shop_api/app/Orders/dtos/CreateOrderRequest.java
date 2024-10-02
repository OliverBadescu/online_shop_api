package mycode.online_shop_api.app.orders.dtos;

import jakarta.validation.constraints.NotNull;
import mycode.online_shop_api.app.orders.validators.*;
import mycode.online_shop_api.app.products.dto.ProductDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record CreateOrderRequest(
        @OrderEmailConstraint(message = "Email must contain @, and cannot be empty")
        String orderEmail,
        @ShippingAddressConstraint(message = "Shipping address cannot be empty")
        String shippingAddress,
        @BillingAddressConstraint(message = "Billing address cannot be empty")
        String orderAddress,
        @OrderDateConstraint(message = "Date cannot be empty")
        LocalDate orderDate,
        @NotNull(message = "Customer cannot be empty")
        int customerId,
        @NotNull(message = "Cart cannot be empty")
        List<ProductDto> list) implements Serializable {
}
