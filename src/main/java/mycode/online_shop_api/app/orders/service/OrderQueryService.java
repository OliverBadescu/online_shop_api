package mycode.online_shop_api.app.orders.service;

import mycode.online_shop_api.app.orders.dtos.OrderResponse;

public interface OrderQueryService {

    OrderResponse findById(int id);

}
