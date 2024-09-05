package mycode.online_shop_api.app.OrderDetails.dtos;

import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Products.model.Product;

public record CreateOrderDetailsRequest(double price, int quantity, Order order, Product product) {
}
