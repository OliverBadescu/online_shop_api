package mycode.online_shop_api.app.Customers.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CreateCustomerUpdateRequest(@NotNull String fullName,
                                          @NotNull
                                          String email,
                                          @NotNull
                                          String password,
                                          @NotNull
                                          String billingAddress,
                                          @NotNull
                                          String shippingAddress,
                                          @NotNull
                                          String phone,
                                          @NotNull
                                          String country) implements Serializable {
}
