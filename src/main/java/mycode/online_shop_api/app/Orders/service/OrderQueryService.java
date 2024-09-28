package mycode.online_shop_api.app.Orders.service;

import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;

import java.util.List;

public interface OrderQueryService {

    CreateOrderResponse findById(int id);

    List<CreateOrderResponse> customerOrders(int customerId);

}
