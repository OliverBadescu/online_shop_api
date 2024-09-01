package mycode.online_shop_api.app.repository;

import mycode.online_shop_api.app.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
