package mycode.online_shop_api.app.Orders.dtos;

public record EditOrderRequest(String action, String productName, Integer quantity) {
}
