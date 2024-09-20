package mycode.online_shop_api.app.Orders.mappers;

import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toOrder(CreateOrderRequest dto);

    CreateOrderRequest toDto(Order order);

}
