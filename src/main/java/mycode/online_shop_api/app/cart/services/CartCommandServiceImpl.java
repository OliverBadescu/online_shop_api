package mycode.online_shop_api.app.cart.services;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.cart.dtos.AddProductToCartRequest;
import mycode.online_shop_api.app.cart.dtos.CartResponse;
import mycode.online_shop_api.app.cart.exceptions.NoCartFound;
import mycode.online_shop_api.app.cart.mapper.CartMapper;
import mycode.online_shop_api.app.cart.model.Cart;
import mycode.online_shop_api.app.cart.repository.CartRepository;
import mycode.online_shop_api.app.products.exceptions.NoProductFound;
import mycode.online_shop_api.app.products.model.Product;
import mycode.online_shop_api.app.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartCommandServiceImpl implements CartCommandService{

    CartRepository cartRepository;
    ProductRepository productRepository;

    @Override
    public CartResponse addProductToCart(AddProductToCartRequest cartRequest) {
        Cart cart = cartRepository.findByUserId(cartRequest.userId())
                .orElseThrow(() -> new NoCartFound("No cart with this user id found"));


        Product product = productRepository.findById(cartRequest.productId())
                .orElseThrow(() -> new NoProductFound("No product with this id found"));

        cart.addProduct(product, cartRequest.quantity());

        return CartMapper.cartToResponseDto(cart);
    }

    @Override
    public CartResponse deleteProductFromCart(int productId, int userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NoCartFound("No cart with this user id found"));


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoProductFound("No product with this id found"));

        cart.removeProduct(product);

        return CartMapper.cartToResponseDto(cart);
    }
}
