package mycode.online_shop_api.app.Orders.service;

import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.utile.ProductDto;

import java.util.ArrayList;

public interface OrderCommandService {

    void addOrder(CreateOrderRequest createOrderRequest, ArrayList<ProductDto> list);
}
