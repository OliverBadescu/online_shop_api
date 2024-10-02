package mycode.online_shop_api.app.orders.service;



import mycode.online_shop_api.app.orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.orders.dtos.CreateOrderUpdateRequest;
import mycode.online_shop_api.app.orders.dtos.EditOrderRequest;
import mycode.online_shop_api.app.orders.dtos.OrderResponse;

import java.util.ArrayList;

public interface OrderCommandService {

    OrderResponse addOrder(CreateOrderRequest createOrderRequest);

    OrderResponse deleteOrder(int id);

    void updateOrder(int id, CreateOrderUpdateRequest createOrderUpdateRequest);

    OrderResponse deleteProductFromOrder(int orderId, EditOrderRequest editOrderRequest);

    OrderResponse updateProductQuantity(int orderId, EditOrderRequest editOrderRequest);

    OrderResponse cancelOrder(int orderId);
}
