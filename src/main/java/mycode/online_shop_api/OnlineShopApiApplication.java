package mycode.online_shop_api;

import mycode.online_shop_api.app.Categories.repository.CategoryRepository;
import mycode.online_shop_api.app.Customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.Customers.model.Customer;
import mycode.online_shop_api.app.Customers.repository.CustomerRepository;
import mycode.online_shop_api.app.Categories.model.Category;
import mycode.online_shop_api.app.Customers.service.CustomerCommandService;
import mycode.online_shop_api.app.Customers.service.CustomerCommandServiceImpl;
import mycode.online_shop_api.app.Customers.service.CustomerQueryService;
import mycode.online_shop_api.app.OrderDetails.dtos.CreateOrderDetailsRequest;
import mycode.online_shop_api.app.OrderDetails.model.OrderDetails;
import mycode.online_shop_api.app.OrderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.OrderDetails.service.OrderDetailsCommandService;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Orders.repository.OrderRepository;
import mycode.online_shop_api.app.Orders.service.OrderCommandService;
import mycode.online_shop_api.app.ProductCategories.repository.ProductCategoriesRepository;
import mycode.online_shop_api.app.Products.model.Product;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import mycode.online_shop_api.app.Products.service.ProductQueryService;
import mycode.online_shop_api.app.utile.ProductDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class OnlineShopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository, OrderCommandService orderCommandService, CustomerQueryService customerQueryService, ProductQueryService productQueryService, OrderDetailsCommandService orderDetailsCommandService, OrderDetailsRepository orderDetailsRepository){

        return args -> {

            Customer customer = customerQueryService.findByEmailAndPassword("lala@email.com", "pw123");
            Product product = productQueryService.findByName("Iphone 15");

            Order order = Order.builder().customer(customer).amount(0).orderStatus(" ").orderEmail(customer.getEmail()).orderDate(LocalDate.now()).orderAddress(customer.getBillingAddress()).shippingAddress(customer.getShippingAddress()).build();

            orderRepository.saveAndFlush(order);

            OrderDetails orderDetails= OrderDetails.builder().quantity(0).product(product).price(product.getPrice()).order(order).build();

            orderDetailsRepository.saveAndFlush(orderDetails);







        };
    }

}
