package mycode.online_shop_api.app.Orders.dtos;

import mycode.online_shop_api.app.Customers.dtos.CreateCustomerResponse;
import mycode.online_shop_api.app.Customers.model.Customer;

import java.io.Serializable;
import java.time.LocalDate;

public record CreateOrderResponse (int id,
                                   String orderEmail,
                                   String shippingAddress,
                                   String orderAddress,
                                   LocalDate orderDate,
                                   double amount,
                                   String orderStatus,
                                   CreateCustomerResponse customer) implements Serializable {
}
