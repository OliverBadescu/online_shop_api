package mycode.online_shop_api.app.cart.repository;

import mycode.online_shop_api.app.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.awt.font.OpenType;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUserId(long userId);
}
