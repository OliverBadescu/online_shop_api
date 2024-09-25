package mycode.online_shop_api.app.Orders.mappers;

import mycode.online_shop_api.app.Customers.mapper.CustomerMapper;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;
import mycode.online_shop_api.app.Orders.model.Order;

public class OrderMapper {

    public static CreateOrderResponse orderToResponseDto(Order order) {
        return new CreateOrderResponse(
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

    public static Order responseDtoToOrder(CreateOrderResponse dto) {
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