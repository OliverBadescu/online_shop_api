package mycode.online_shop_api.app.cart.mapper;

import mycode.online_shop_api.app.cart.dtos.CartResponse;
import mycode.online_shop_api.app.cart.model.Cart;
import mycode.online_shop_api.app.products.dto.ProductResponse;
import mycode.online_shop_api.app.products.dto.ProductResponseList;
import mycode.online_shop_api.app.products.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;

public class CartMapper{

    public static CartResponse cartToResponseDto(Cart cart){

        List<ProductResponse> list = new ArrayList<>();

        cart.getProducts().forEach(product -> {
            list.add(ProductMapper.productToResponseDto(product));
        });

        return CartResponse.builder()
                .userId(cart.getUser().getId())
                .list(ProductResponseList.builder().list(list).build()).build();

    }
}
