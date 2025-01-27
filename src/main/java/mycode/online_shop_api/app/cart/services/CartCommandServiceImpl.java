package mycode.online_shop_api.app.cart.services;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.cart.dtos.AddProductToCartRequest;
import mycode.online_shop_api.app.cart.dtos.CartProductResponse;
import mycode.online_shop_api.app.cart.dtos.CartResponse;
import mycode.online_shop_api.app.cart.exceptions.NoCartFound;
import mycode.online_shop_api.app.cart.mapper.CartMapper;
import mycode.online_shop_api.app.cart.model.Cart;
import mycode.online_shop_api.app.cart.model.CartProduct;
import mycode.online_shop_api.app.cart.repository.CartProductRepository;
import mycode.online_shop_api.app.cart.repository.CartRepository;
import mycode.online_shop_api.app.products.exceptions.NoProductFound;
import mycode.online_shop_api.app.products.model.Product;
import mycode.online_shop_api.app.products.repository.ProductRepository;
import mycode.online_shop_api.app.users.exceptions.NoUserFound;
import mycode.online_shop_api.app.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartCommandServiceImpl implements CartCommandService{

    CartRepository cartRepository;
    ProductRepository productRepository;
    CartProductRepository cartProductRepository;
    UserRepository userRepository;

    @Override
    public CartResponse addProductToCart(AddProductToCartRequest cartRequest, long userId) {
        Optional<Cart> cartTemp = cartRepository.findByUserId(userId);
        Cart cart;

        cart = cartTemp.orElseGet(() -> Cart.builder()
                .user(userRepository.findById(userId).orElseThrow(() -> new NoUserFound("No user with this id found")))
                .cartProducts(null).build());


        Product product = productRepository.findById(cartRequest.productId())
                .orElseThrow(() -> new NoProductFound("No product with this id found"));

        cart.addProduct(product, cartRequest.quantity());
        cartRepository.save(cart);

        List<CartProductResponse> productResponses = cart.getCartProducts().stream()
                .map(cartProduct -> new CartProductResponse(
                        cartProduct.getProduct().getId(),
                        cartProduct.getProduct().getName(),
                        cartProduct.getProduct().getCategory(),
                        cartProduct.getProduct().getPrice(),
                        cartProduct.getQuantity()
                ))
                .toList();

        return CartResponse.builder().userId(userId).id(cart.getId()).list(productResponses).build();
    }

    @Override
    public CartResponse deleteProductFromCart(int productId, long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NoCartFound("No cart with this user id found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoProductFound("No product with this id found"));

        List<CartProduct> list = cartProductRepository.getAllByCart(cart)
                .orElseThrow(() -> new NoCartFound("No products found in this cart"));

        boolean productFound = false;


        System.out.println(list.size());
        for (CartProduct cartProduct : list) {
            System.out.println(cartProduct);
            if (product.getId() == cartProduct.getProduct().getId()) {
                cart.removeProduct(product);
                cartRepository.save(cart);
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            throw new NoProductFound("No product with this id in the cart");
        }

        return CartMapper.cartToResponseDto(cart);
    }

}
