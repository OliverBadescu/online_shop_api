package mycode.online_shop_api;

import jakarta.validation.Validator;
import mycode.online_shop_api.app.customers.repository.CustomerRepository;
import mycode.online_shop_api.app.customers.service.CustomerQueryService;
import mycode.online_shop_api.app.orderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.orderDetails.service.OrderDetailsCommandService;
import mycode.online_shop_api.app.orders.repository.OrderRepository;
import mycode.online_shop_api.app.orders.service.OrderCommandService;
import mycode.online_shop_api.app.products.repository.ProductRepository;
import mycode.online_shop_api.app.products.service.ProductQueryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
