package mycode.online_shop_api.app.Orders.service;

import mycode.online_shop_api.app.Customers.mapper.Mapper;
import mycode.online_shop_api.app.OrderDetails.model.OrderDetails;
import mycode.online_shop_api.app.OrderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderUpdateRequest;
import mycode.online_shop_api.app.Orders.exceptions.NoOrderFound;
import mycode.online_shop_api.app.Orders.mappers.OrderMapper;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Orders.repository.OrderRepository;
import mycode.online_shop_api.app.Products.dto.ProductDto;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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
    public CreateOrderResponse addOrder(CreateOrderRequest createOrderRequest, ArrayList<ProductDto> list) {
        //Order order = Order.builder().shippingAddress(createOrderRequest.shippingAddress()).orderAddress(createOrderRequest.orderAddress()).orderDate(createOrderRequest.orderDate()).orderEmail(createOrderRequest.orderEmail()).orderStatus(createOrderRequest.orderStatus()).amount(createOrderRequest.amount()).customer(createOrderRequest.customer()).build();
        Order order = createOrder(createOrderRequest);
        orderRepository.saveAndFlush(order);
        for (ProductDto productDto : list) {
            OrderDetails orderDetails = OrderDetails.builder().order(order).price(productDto.getPrice()).product(productRepository.findByName(productDto.getName()).get()).quantity(productDto.getCantitate()).build();
            orderDetailsRepository.saveAndFlush(orderDetails);
        }

        return new CreateOrderResponse(order.getId(), order.getOrderEmail(), order.getShippingAddress(),order.getOrderAddress(),order.getOrderDate(),order.getAmount(),order.getOrderStatus(), Mapper.customerToDto(order.getCustomer()));
    }

    @Override
    public CreateOrderResponse deleteOrder(int id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isPresent()){
            CreateOrderResponse createOrderResponse = new CreateOrderResponse(order.get().getId(), order.get().getOrderEmail(),order.get().getShippingAddress(),order.get().getOrderAddress(),order.get().getOrderDate(),order.get().getAmount(),order.get().getOrderStatus(),Mapper.customerToDto(order.get().getCustomer()));
            orderRepository.delete(order.get());
            return createOrderResponse;
        }else{
            throw new NoOrderFound(" ");
        }
    }

    @Override
    public void updateOrder(int id, CreateOrderUpdateRequest createOrderUpdateRequest) {
        Optional<Order> order= orderRepository.findById(id);

        if(order.isPresent()){
            Order order1 = order.get();

            order1.setAmount(createOrderUpdateRequest.amount());
            order1.setOrderAddress(createOrderUpdateRequest.orderAddress());
            order1.setOrderDate(createOrderUpdateRequest.orderDate());
            order1.setOrderEmail(createOrderUpdateRequest.orderEmail());
            order1.setOrderStatus(createOrderUpdateRequest.orderStatus());
            order1.setShippingAddress(createOrderUpdateRequest.shippingAddress());

            orderRepository.saveAndFlush(order1);
        }else{
            throw new NoOrderFound(" ");
        }

    }
}
