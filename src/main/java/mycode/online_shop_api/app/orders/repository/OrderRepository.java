package mycode.online_shop_api.app.orders.repository;



import mycode.online_shop_api.app.orders.model.Order;
import mycode.online_shop_api.app.users.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @EntityGraph(attributePaths = {"orderDetails"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT co FROM Order co LEFT JOIN co.user c WHERE c.id = :userId")
    Optional<List<Order>> getAllUserOrders(long userId);


    Optional<List<Order>> findTop10ByOrderByOrderDateDesc();

    @Query("SELECT o.user FROM Order o GROUP BY o.user ORDER BY COUNT(o.id) DESC")
    Optional<List<User>> findMostActiveUsers();

}
