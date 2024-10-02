package mycode.online_shop_api.app.products.dto;

import java.util.List;

public record ProductResponseList(List<ProductResponse> list, String message) {
}
