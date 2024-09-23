package mycode.online_shop_api.app.Orders.service;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.Customers.mapper.Mapper;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;
import mycode.online_shop_api.app.Orders.exceptions.NoOrderFound;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService{

    private OrderRepository orderRepository;


    @Override
    public CreateOrderResponse findById(int id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isPresent()){
            return new CreateOrderResponse(order.get().getId(),order.get().getOrderEmail(),order.get().getShippingAddress(),order.get().getOrderAddress(),order.get().getOrderDate(),order.get().getAmount(),order.get().getOrderStatus(), Mapper.customerToDto(order.get().getCustomer()));

        }else{
            throw new NoOrderFound(" ");
        }
    }
}
