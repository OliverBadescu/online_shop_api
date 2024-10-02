package mycode.online_shop_api.app.orders.dtos;

import java.time.LocalDate;

public record CreateOrderUpdateRequest(String orderEmail,
                                       String shippingAddress,
                                       String orderAddress,
                                       LocalDate orderDate,
                                       double amount,
                                       String orderStatus) {
}
