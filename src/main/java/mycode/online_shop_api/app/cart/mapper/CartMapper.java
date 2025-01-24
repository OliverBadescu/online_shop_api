package mycode.online_shop_api.app.cart.mapper;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.cart.dtos.CartResponse;
import mycode.online_shop_api.app.cart.model.Cart;
import mycode.online_shop_api.app.products.dto.ProductResponse;
import mycode.online_shop_api.app.products.dto.ProductResponseList;
import mycode.online_shop_api.app.products.mapper.ProductMapper;
import mycode.online_shop_api.app.products.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;


public class CartMapper{


    public static CartResponse cartToResponseDto(Cart cart){

        List<ProductResponse> list = new ArrayList<>();

        cart.getCartProducts().forEach(product -> {
            list.add(ProductMapper.productToResponseDto(product.getProduct()));
        });

        return CartResponse.builder()
                .userId(cart.getUser().getId())
                .list(ProductResponseList.builder().list(list).build()).build();

    }
}
