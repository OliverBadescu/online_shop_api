package mycode.online_shop_api.app.Orders.dtos;

import mycode.online_shop_api.app.Customers.dtos.CreateCustomerResponse;

import java.time.LocalDate;

public record CreateOrderUpdateRequest(String orderEmail,
                                       String shippingAddress,
                                       String orderAddress,
                                       LocalDate orderDate,
                                       double amount,
                                       String orderStatus) {
}
