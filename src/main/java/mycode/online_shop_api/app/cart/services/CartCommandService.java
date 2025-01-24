package mycode.online_shop_api.app.cart.services;

import mycode.online_shop_api.app.cart.dtos.AddProductToCartRequest;
import mycode.online_shop_api.app.cart.dtos.CartResponse;

public interface CartCommandService {

    CartResponse addProductToCart(AddProductToCartRequest cartRequest);

    CartResponse deleteProductFromCart(int productId, int userId);

}
