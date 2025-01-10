package mycode.online_shop_api.app.orders.mappers;


import mycode.online_shop_api.app.orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.orders.dtos.OrderResponse;
import mycode.online_shop_api.app.orders.model.Order;
import mycode.online_shop_api.app.users.mapper.UserMapper;
import mycode.online_shop_api.app.users.model.User;

public class OrderMapper {

    public static OrderResponse orderToResponseDto(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderEmail(),
                order.getShippingAddress(),
                order.getOrderAddress(),
                order.getOrderDate(),
                order.getAmount(),
                order.getOrderStatus(),
                UserMapper.userToResponseDto(order.getUser())
        );
    }



}