package mycode.online_shop_api.app.orders.repository;

import mycode.online_shop_api.app.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
