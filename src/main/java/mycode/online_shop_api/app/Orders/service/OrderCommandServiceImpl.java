package mycode.online_shop_api.app.Orders.service;

import mycode.online_shop_api.app.OrderDetails.model.OrderDetails;
import mycode.online_shop_api.app.OrderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.mappers.OrderMapper;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Orders.repository.OrderRepository;
import mycode.online_shop_api.app.Products.dto.ProductDto;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderCommandServiceImpl implements OrderCommandService{

    private OrderRepository orderRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private ProductRepository productRepository;


    public OrderCommandServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(CreateOrderRequest dto) {
        Order order = OrderMapper.INSTANCE.toOrder(dto);
        return orderRepository.save(order);
    }


    @Override
    public void addOrder(CreateOrderRequest createOrderRequest,  ArrayList<ProductDto> list) {
        //Order order = Order.builder().shippingAddress(createOrderRequest.shippingAddress()).orderAddress(createOrderRequest.orderAddress()).orderDate(createOrderRequest.orderDate()).orderEmail(createOrderRequest.orderEmail()).orderStatus(createOrderRequest.orderStatus()).amount(createOrderRequest.amount()).customer(createOrderRequest.customer()).build();
        Order order = createOrder(createOrderRequest);
        orderRepository.saveAndFlush(order);
        for (ProductDto productDto : list) {
            OrderDetails orderDetails = OrderDetails.builder().order(order).price(productDto.getPrice()).product(productRepository.findByName(productDto.getName()).get()).quantity(productDto.getCantitate()).build();
            orderDetailsRepository.saveAndFlush(orderDetails);
        }


    }
}
