package mycode.online_shop_api.app.products.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddProductToCartRequest(
        @NotNull String productName,
        @NotNull int productId,
        @NotNull int quantity
) {
}
