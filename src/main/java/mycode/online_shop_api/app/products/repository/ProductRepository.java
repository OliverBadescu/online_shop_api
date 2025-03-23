package mycode.online_shop_api.app.products.repository;

import mycode.online_shop_api.app.products.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    @EntityGraph(attributePaths = {"productCategories", "orderDetails"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Product> findByName(String name);

}
