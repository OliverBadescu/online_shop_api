package mycode.online_shop_api.app.orders.service;

import lombok.AllArgsConstructor;

import mycode.online_shop_api.app.customers.exceptions.NoCustomerFound;
import mycode.online_shop_api.app.customers.mapper.CustomerMapper;
import mycode.online_shop_api.app.customers.model.Customer;
import mycode.online_shop_api.app.customers.repository.CustomerRepository;
import mycode.online_shop_api.app.orderDetails.exceptions.NoOrderDetailsFound;
import mycode.online_shop_api.app.orderDetails.model.OrderDetails;
import mycode.online_shop_api.app.orderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.orders.dtos.CreateOrderUpdateRequest;
import mycode.online_shop_api.app.orders.dtos.EditOrderRequest;
import mycode.online_shop_api.app.orders.dtos.OrderResponse;
import mycode.online_shop_api.app.orders.exceptions.NoOrderFound;
import mycode.online_shop_api.app.orders.mappers.OrderMapper;
import mycode.online_shop_api.app.orders.model.Order;
import mycode.online_shop_api.app.orders.repository.OrderRepository;
import mycode.online_shop_api.app.products.dto.ProductDto;
import mycode.online_shop_api.app.products.exceptions.NoProductFound;
import mycode.online_shop_api.app.products.model.Product;
import mycode.online_shop_api.app.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private OrderRepository orderRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;




    @Override
    public OrderResponse addOrder(CreateOrderRequest createOrderRequest) {
        List<ProductDto> list = createOrderRequest.list();

        Order order = OrderMapper.requestDtoToOrder(createOrderRequest);
        Customer customer = customerRepository.findById(Integer.valueOf(createOrderRequest.customerId()))
                .orElseThrow(() -> new NoCustomerFound("Customer not found"));
        order.setCustomer(customer);
        order.setAmount(0);
        orderRepository.saveAndFlush(order);
        double sum = list.stream()
                .map(productDto -> {
                    Product product = productRepository.findByName(productDto.getName())
                            .orElseThrow(() -> new NoProductFound("Product not found: " + productDto.getName()));
                    OrderDetails orderDetails = OrderDetails.builder()
                            .order(order)
                            .price(productDto.getPrice())
                            .product(product)
                            .quantity(productDto.getCantitate())
                            .build();
                    orderDetailsRepository.saveAndFlush(orderDetails);
                    return (Double) (orderDetails.getPrice() * orderDetails.getQuantity());
                })
                .mapToDouble(Double::doubleValue)
                .sum();

        order.setAmount(sum);
        orderRepository.saveAndFlush(order);

        return OrderResponse.builder()
                .id(order.getId())
                .orderEmail(order.getOrderEmail())
                .shippingAddress(order.getShippingAddress())
                .orderAddress(order.getOrderAddress())
                .orderDate(order.getOrderDate())
                .amount(order.getAmount())
                .orderStatus(order.getOrderStatus())
                .customer(CustomerMapper.customerToDto(order.getCustomer()))
                .build();
    }

    @Override

    public OrderResponse deleteOrder(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            OrderResponse orderResponse = new OrderResponse(order.get().getId(), order.get().getOrderEmail(),order.get().getShippingAddress(),order.get().getOrderAddress(),order.get().getOrderDate(),order.get().getAmount(),order.get().getOrderStatus(), CustomerMapper.customerToDto(order.get().getCustomer()));
            orderRepository.delete(order.get());
            return orderResponse;
        }else{
            throw new NoOrderFound(" ");
        }
    }

    @Override
    public void updateOrder(int id, CreateOrderUpdateRequest createOrderUpdateRequest) {
        Optional<Order> order= orderRepository.findById(Integer.valueOf(id));

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

    @Override
    public OrderResponse deleteProductFromOrder(int orderId, EditOrderRequest editOrderRequest) {
        Product product = productRepository.findByName(editOrderRequest.productName())
                .orElseThrow(() -> new NoProductFound("No product with this name found"));


        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoOrderFound("No order with this ID found"));

        OrderDetails orderDetails = orderDetailsRepository
                .findByOrderIdAndProductId(order.getId(), product.getId())
                .orElseThrow(() -> new NoOrderDetailsFound("No order details for this order and product found"));

        double productTotalPrice = orderDetails.getQuantity() * orderDetails.getPrice();
        order.setAmount(order.getAmount() - productTotalPrice);

        order.removeOrderDetails(orderDetails);

        orderDetailsRepository.delete(orderDetails);

        orderRepository.save(order);
        return OrderMapper.orderToResponseDto(order);
    }

    @Override
    public OrderResponse updateProductQuantity(int orderId, EditOrderRequest editOrderRequest) {
        Product product = productRepository.findByName(editOrderRequest.productName())
                .orElseThrow(() -> new NoProductFound("No product with this name found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoOrderFound("No order with this id found"));


        OrderDetails orderDetails = orderDetailsRepository
                .findByOrderIdAndProductId(order.getId(), product.getId())
                .orElseThrow(() -> new NoOrderDetailsFound("No order details for this order and product found"));

        int oldQuantity = orderDetails.getQuantity();
        int newQuantity = editOrderRequest.quantity();

        orderDetails.setQuantity(newQuantity);

        int quantityDifference = newQuantity - oldQuantity;


        order.setAmount(order.getAmount() + (quantityDifference * orderDetails.getPrice()));


        orderRepository.save(order);
        orderDetailsRepository.save(orderDetails);


        return OrderMapper.orderToResponseDto(order);
    }


    @Override
    public OrderResponse cancelOrder(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoOrderFound("No order with this ID found"));

        Set<OrderDetails> orderDetailsSet = order.getOrderDetails();

        orderDetailsSet.forEach(orderDetails -> {
            order.removeOrderDetails(orderDetails);
            orderDetailsRepository.delete(orderDetails);
        });

        order.setOrderStatus("Cancelled");


        orderRepository.save(order);
        return OrderMapper.orderToResponseDto(order);
    }
}
