package mycode.online_shop_api;

import jakarta.validation.Validator;
import mycode.online_shop_api.app.Customers.model.Customer;
import mycode.online_shop_api.app.Customers.repository.CustomerRepository;
import mycode.online_shop_api.app.Customers.service.CustomerQueryService;
import mycode.online_shop_api.app.OrderDetails.model.OrderDetails;
import mycode.online_shop_api.app.OrderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.OrderDetails.service.OrderDetailsCommandService;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Orders.repository.OrderRepository;
import mycode.online_shop_api.app.Orders.service.OrderCommandService;
import mycode.online_shop_api.app.Products.model.Product;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import mycode.online_shop_api.app.Products.service.ProductQueryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class OnlineShopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository, OrderCommandService orderCommandService, CustomerQueryService customerQueryService, ProductQueryService productQueryService, OrderDetailsCommandService orderDetailsCommandService, ProductRepository productRepository, Validator validator, CustomerRepository customerRepository, OrderDetailsRepository orderDetailsRepository){

        return args -> {


        };
    }

}
