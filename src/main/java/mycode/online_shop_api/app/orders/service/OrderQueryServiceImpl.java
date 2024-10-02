package mycode.online_shop_api.app.orders.service;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.customers.mapper.CustomerMapper;
import mycode.online_shop_api.app.orders.dtos.OrderResponse;
import mycode.online_shop_api.app.orders.exceptions.NoOrderFound;
import mycode.online_shop_api.app.orders.model.Order;
import mycode.online_shop_api.app.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService{

    private OrderRepository orderRepository;


    @Override
    public OrderResponse findById(int id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isPresent()){
            return new OrderResponse(order.get().getId(),order.get().getOrderEmail(),order.get().getShippingAddress(),order.get().getOrderAddress(),order.get().getOrderDate(),order.get().getAmount(),order.get().getOrderStatus(), CustomerMapper.customerToDto(order.get().getCustomer()));

        }else{
            throw new NoOrderFound(" ");
        }
    }
}
