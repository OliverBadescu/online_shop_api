package mycode.online_shop_api;

import jakarta.validation.Validator;
import mycode.online_shop_api.app.Customers.service.CustomerQueryService;
import mycode.online_shop_api.app.OrderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.OrderDetails.service.OrderDetailsCommandService;
import mycode.online_shop_api.app.Orders.repository.OrderRepository;
import mycode.online_shop_api.app.Orders.service.OrderCommandService;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import mycode.online_shop_api.app.Products.service.ProductQueryService;
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
    CommandLineRunner commandLineRunner(OrderRepository orderRepository, OrderCommandService orderCommandService, CustomerQueryService customerQueryService, ProductQueryService productQueryService, OrderDetailsCommandService orderDetailsCommandService, OrderDetailsRepository orderDetailsRepository, ProductRepository productRepository, Validator validator){

        return args -> {



        };
    }

}
