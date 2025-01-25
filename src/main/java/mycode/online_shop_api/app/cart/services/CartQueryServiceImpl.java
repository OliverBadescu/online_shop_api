package mycode.online_shop_api.app.cart.services;


import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.cart.dtos.CartResponse;
import mycode.online_shop_api.app.cart.exceptions.NoCartFound;
import mycode.online_shop_api.app.cart.mapper.CartMapper;
import mycode.online_shop_api.app.cart.model.Cart;
import mycode.online_shop_api.app.cart.repository.CartRepository;
import mycode.online_shop_api.app.users.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartQueryServiceImpl implements CartQueryService{

    CartRepository cartRepository;

    @Override
    public CartResponse getCartByUserId(long id) {
        Cart cart = cartRepository.findByUserId(id)
                .orElseThrow(() -> new NoCartFound("No cart with this user id found"));

        return CartMapper.cartToResponseDto(cart);
    }
}


