package mycode.online_shop_api.app.orders.service;

import lombok.AllArgsConstructor;

import mycode.online_shop_api.app.customers.exceptions.NoCustomerFound;
import mycode.online_shop_api.app.customers.mapper.CustomerMapper;
import mycode.online_shop_api.app.customers.model.Customer;
import mycode.online_shop_api.app.customers.repository.CustomerRepository;
import mycode.online_shop_api.app.orders.dtos.OrderResponse;
import mycode.online_shop_api.app.orders.exceptions.NoOrderFound;
import mycode.online_shop_api.app.orders.mappers.OrderMapper;
import mycode.online_shop_api.app.orders.model.Order;
import mycode.online_shop_api.app.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService{

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;


    @Override
    public OrderResponse findById(int id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isPresent()){
            return new OrderResponse(order.get().getId(),order.get().getOrderEmail(),order.get().getShippingAddress(),order.get().getOrderAddress(),order.get().getOrderDate(),order.get().getAmount(),order.get().getOrderStatus(), CustomerMapper.customerToDto(order.get().getCustomer()));

        }else{
            throw new NoOrderFound(" ");
        }
    }

    @Override
    public List<OrderResponse> customerOrders(int customerId) {
        Optional<List<Order>> list = orderRepository.getAllCustomerOrders(customerId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        List<OrderResponse> rez = new ArrayList<>();

        if(customer.isPresent()){
            list.get().forEach(list1 -> {
                rez.add(OrderMapper.orderToResponseDto(list1));
            });

            return rez;
        }else{
            throw new NoCustomerFound("No customer with this id found");
        }

    }
}
