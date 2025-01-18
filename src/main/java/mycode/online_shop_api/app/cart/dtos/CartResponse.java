package mycode.online_shop_api.app.cart.dtos;

import lombok.Builder;
import mycode.online_shop_api.app.products.dto.ProductResponseList;

@Builder
public record CartResponse(long id, long userId, ProductResponseList list) {
}
