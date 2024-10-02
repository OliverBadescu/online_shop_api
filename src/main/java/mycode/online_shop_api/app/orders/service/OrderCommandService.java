package mycode.online_shop_api.app.orders.service;

import mycode.online_shop_api.app.orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.orders.dtos.OrderResponse;
import mycode.online_shop_api.app.orders.dtos.CreateOrderUpdateRequest;

public interface OrderCommandService {

    OrderResponse addOrder(CreateOrderRequest createOrderRequest);

    OrderResponse deleteOrder(int id);

    void updateOrder(int id, CreateOrderUpdateRequest createOrderUpdateRequest);
}
