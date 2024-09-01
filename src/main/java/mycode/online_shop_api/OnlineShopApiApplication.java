package mycode.online_shop_api;

import mycode.online_shop_api.app.model.Category;
import mycode.online_shop_api.app.model.Customer;
import mycode.online_shop_api.app.model.Product;
import mycode.online_shop_api.app.repository.*;
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
    CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, CustomerRepository customerRepository, OrderDetailsRepository orderDetailsRepository, OrderRepository orderRepository, ProductCategoriesRepository productCategoriesRepository, ProductRepository productRepository){


        return args -> {

            Category homeAppliancesCategory = Category.builder()
                    .name("Home Appliances")
                    .build();

            categoryRepository.saveAndFlush(homeAppliancesCategory);

            Category furnitureCategory = Category.builder()
                    .name("Furniture")
                    .build();

            categoryRepository.saveAndFlush(furnitureCategory);

            Category booksCategory = Category.builder()
                    .name("Books")
                    .build();

            categoryRepository.saveAndFlush(booksCategory);

            Category toysCategory = Category.builder()
                    .name("Toys")
                    .build();

            categoryRepository.saveAndFlush(toysCategory);

            Category sportingGoodsCategory = Category.builder()
                    .name("Sporting Goods")
                    .build();

            categoryRepository.saveAndFlush(sportingGoodsCategory);

        };
    }

}
