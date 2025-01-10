package mycode.online_shop_api.app.orders.service;

import lombok.AllArgsConstructor;

import mycode.online_shop_api.app.orders.dtos.OrderResponse;
import mycode.online_shop_api.app.orders.exceptions.NoOrderFound;
import mycode.online_shop_api.app.orders.mappers.OrderMapper;
import mycode.online_shop_api.app.orders.model.Order;
import mycode.online_shop_api.app.orders.repository.OrderRepository;
import mycode.online_shop_api.app.users.exceptions.NoUserFound;
import mycode.online_shop_api.app.users.mapper.UserMapper;
import mycode.online_shop_api.app.users.model.User;
import mycode.online_shop_api.app.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService{

    private OrderRepository orderRepository;
    private UserRepository userRepository;


    @Override
    public OrderResponse findById(int id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isPresent()){
            return new OrderResponse(order.get().getId(),order.get().getOrderEmail(),order.get().getShippingAddress(),order.get().getOrderAddress(),order.get().getOrderDate(),order.get().getAmount(),order.get().getOrderStatus(), UserMapper.userToResponseDto(order.get().getUser()));

        }else{
            throw new NoOrderFound(" ");
        }
    }

    @Override
    public List<OrderResponse> customerOrders(int userId) {
        Optional<List<Order>> list = orderRepository.getAllUserOrders(userId);
        Optional<User> user = userRepository.findById(userId);
        List<OrderResponse> rez = new ArrayList<>();

        if(user.isPresent()){
            list.get().forEach(list1 -> {
                rez.add(OrderMapper.orderToResponseDto(list1));
            });

            return rez;
        }else{
            throw new NoUserFound("No customer with this id found");
        }

    }
}
