package mycode.online_shop_api.app.orders.mappers;

import mycode.online_shop_api.app.customers.mapper.CustomerMapper;
import mycode.online_shop_api.app.orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.orders.dtos.OrderResponse;
import mycode.online_shop_api.app.orders.model.Order;

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
                CustomerMapper.customerToDto(order.getCustomer())
        );
    }

    public static Order responseDtoToOrder(OrderResponse dto) {
        return Order.builder()
                .amount(dto.amount())
                .customer(CustomerMapper.dtoToCustomer(dto.customer()))
                .orderAddress(dto.orderAddress())
                .orderDate(dto.orderDate())
                .orderEmail(dto.orderEmail())
                .orderStatus(dto.orderStatus())
                .shippingAddress(dto.shippingAddress())
                .build();
    }

    public static Order requestDtoToOrder(CreateOrderRequest dto){

        return Order.builder()
                .orderEmail(dto.orderEmail())
                .shippingAddress(dto.shippingAddress())
                .orderAddress(dto.orderAddress())
                .orderDate(dto.orderDate())
                .orderStatus("PENDING")
                .build();

    }


}