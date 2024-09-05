package mycode.online_shop_api.app.Orders.repository;

import mycode.online_shop_api.app.Orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
