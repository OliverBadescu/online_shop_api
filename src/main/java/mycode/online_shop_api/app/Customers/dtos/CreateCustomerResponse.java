package mycode.online_shop_api.app.Customers.dtos;

import java.io.Serializable;

public record CreateCustomerResponse(
                                     int id,
                                     String fullName,
                                     String email,
                                     String password,
                                     String billingAddress,
                                     String shippingAddress,
                                     String phone,
                                     String country)implements Serializable {
}
