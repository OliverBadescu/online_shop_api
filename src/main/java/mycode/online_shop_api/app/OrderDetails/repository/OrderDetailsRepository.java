package mycode.online_shop_api.app.OrderDetails.repository;

import mycode.online_shop_api.app.OrderDetails.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
