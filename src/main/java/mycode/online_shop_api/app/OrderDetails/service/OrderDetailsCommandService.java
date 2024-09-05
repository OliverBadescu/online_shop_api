package mycode.online_shop_api.app.OrderDetails.service;

import mycode.online_shop_api.app.OrderDetails.dtos.CreateOrderDetailsRequest;

public interface OrderDetailsCommandService {

    void addOrderDetails(CreateOrderDetailsRequest createOrderDetailsRequest);
}
