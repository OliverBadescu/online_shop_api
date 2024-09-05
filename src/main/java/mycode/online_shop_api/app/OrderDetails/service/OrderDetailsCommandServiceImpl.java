package mycode.online_shop_api.app.OrderDetails.service;

import mycode.online_shop_api.app.OrderDetails.dtos.CreateOrderDetailsRequest;
import mycode.online_shop_api.app.OrderDetails.model.OrderDetails;
import mycode.online_shop_api.app.OrderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderDetailsCommandServiceImpl implements OrderDetailsCommandService{

    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsCommandServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }


    public void addOrderDetails(CreateOrderDetailsRequest createOrderDetailsRequest){

        OrderDetails orderDetails = OrderDetails.builder().order(createOrderDetailsRequest.order()).price(createOrderDetailsRequest.price()).product(createOrderDetailsRequest.product()).quantity(createOrderDetailsRequest.quantity()).build();

        orderDetailsRepository.saveAndFlush(orderDetails);
    }


}
