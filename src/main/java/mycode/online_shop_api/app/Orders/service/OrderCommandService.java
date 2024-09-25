package mycode.online_shop_api.app.Orders.service;

import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderUpdateRequest;
import mycode.online_shop_api.app.Products.dto.ProductDto;

import java.util.ArrayList;

public interface OrderCommandService {

    CreateOrderResponse addOrder(CreateOrderRequest createOrderRequest);

    CreateOrderResponse deleteOrder(int id);

    void updateOrder(int id, CreateOrderUpdateRequest createOrderUpdateRequest);
}
