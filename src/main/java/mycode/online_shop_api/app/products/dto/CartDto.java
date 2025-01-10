package mycode.online_shop_api.app.products.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CartDto(@NotNull List<AddProductToCartRequest> cart) {
}
