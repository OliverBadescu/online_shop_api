package mycode.online_shop_api.app.Customers.repository;

import mycode.online_shop_api.app.Customers.model.Customer;
import mycode.online_shop_api.app.Orders.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    @EntityGraph(attributePaths = {"orders"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Customer> findByEmailAndPassword(String email, String password);

    @EntityGraph(attributePaths = {"orders"}, type = EntityGraph.EntityGraphType.FETCH)
    boolean existsByEmail(String email);


}
