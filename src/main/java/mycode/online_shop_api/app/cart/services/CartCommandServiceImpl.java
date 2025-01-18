package mycode.online_shop_api.app.cart.services;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.cart.dtos.AddProductToCartRequest;
import mycode.online_shop_api.app.cart.dtos.CartResponse;
import mycode.online_shop_api.app.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartCommandServiceImpl implements CartCommandService{

    CartRepository cartRepository;

    @Override
    public CartResponse addProductToCart(AddProductToCartRequest cartRequest) {
        return null;
    }

    @Override
    public CartResponse deleteProductFromCart(int productId) {
        return null;
    }
}
