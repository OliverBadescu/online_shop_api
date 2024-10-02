package mycode.online_shop_api.app.customers.repository;

import mycode.online_shop_api.app.customers.model.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    @EntityGraph(attributePaths = {"orders"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Customer> findByEmailAndPassword(String email, String password);

    @EntityGraph(attributePaths = {"orders"}, type = EntityGraph.EntityGraphType.FETCH)
    boolean existsByEmail(String email);
}
