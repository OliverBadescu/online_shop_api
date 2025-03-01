package mycode.online_shop_api.app.users.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(@NotNull String fullName,
                                @NotNull String email,
                                @NotNull String phone,
                                @NotNull String country,
                                @NotNull String billingAddress,
                                @NotNull String shippingAddress) {
}
