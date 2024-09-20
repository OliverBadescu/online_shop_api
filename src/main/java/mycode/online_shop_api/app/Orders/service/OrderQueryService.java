package mycode.online_shop_api.app.Orders.service;

import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;

public interface OrderQueryService {

    CreateOrderResponse findById(int id);

}
