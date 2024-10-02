package mycode.online_shop_api.app.customers.dtos;

import java.io.Serializable;

public record CustomerResponse(
                                     int id,
                                     String fullName,
                                     String email,
                                     String password,
                                     String billingAddress,
                                     String shippingAddress,
                                     String phone,
                                     String country)implements Serializable {
}
