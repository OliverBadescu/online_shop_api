package mycode.online_shop_api.app.Orders.dtos;

import mycode.online_shop_api.app.Customers.model.Customer;

import java.time.LocalDate;

public record CreateOrderRequest(String orderEmail, String shippingAddress, String orderAddress, LocalDate orderDate, double amount, String orderStatus, Customer customer) {
}
