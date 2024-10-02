package mycode.online_shop_api.app.orders.dtos;

import lombok.Builder;
import mycode.online_shop_api.app.customers.dtos.CustomerResponse;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record OrderResponse(int id,
                            String orderEmail,
                            String shippingAddress,
                            String orderAddress,
                            LocalDate orderDate,
                            double amount,
                            String orderStatus,
                            CustomerResponse customer) implements Serializable {
}
