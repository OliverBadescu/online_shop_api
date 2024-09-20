package mycode.online_shop_api.app.Orders.dtos;

import mycode.online_shop_api.app.Customers.model.Customer;
import mycode.online_shop_api.app.Orders.validators.*;

import java.io.Serializable;
import java.time.LocalDate;

public record CreateOrderRequest(
        @OrderEmailConstraint(message = "Email must contain @, and cannot be empty")
        String orderEmail,
        @ShippingAddressConstraint(message = "Shipping address cannot be empty")
        String shippingAddress,
        @BillingAddressConstraint(message = "Billing address cannot be empty")
        String orderAddress,
        @OrderDateConstraint(message = "Date cannot be empty")
        LocalDate orderDate,
        @AmountConstraint(message = "Amount cannot be empty")
        double amount,
        @StatusConstraint(message = "Order status cannot be empty")
        String orderStatus,
        @CustomerConstraint(message = "Customer cannot be empty")
        Customer customer) implements Serializable {
}
