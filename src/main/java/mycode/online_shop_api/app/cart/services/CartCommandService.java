package mycode.online_shop_api.app.cart.services;

import mycode.online_shop_api.app.cart.dtos.AddProductToCartRequest;
import mycode.online_shop_api.app.cart.dtos.CartResponse;
import mycode.online_shop_api.app.cart.dtos.UpdateCartQuantityRequest;

public interface CartCommandService {

    CartResponse addProductToCart(AddProductToCartRequest cartRequest, long userId);

    CartResponse deleteProductFromCart(int productId, long userId);

    CartResponse updateCartQuantity(UpdateCartQuantityRequest updateCartQuantityRequest, long userId, int productId);

    String emptyUserCart(long userId);

}
