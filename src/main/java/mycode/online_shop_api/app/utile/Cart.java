package mycode.online_shop_api.app.utile;

import lombok.Getter;
import lombok.Setter;
import mycode.online_shop_api.app.products.dto.ProductDto;

import java.util.ArrayList;


@Getter
@Setter
public class Cart {


    private int customerId;
    private ArrayList<ProductDto> products;

    public Cart(int customerId, ArrayList<ProductDto> products) {

        this.customerId = customerId;
        this.products = products;
    }


}
