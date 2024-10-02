package mycode.online_shop_api.app.products.exceptions;

import mycode.online_shop_api.app.utile.Constants;

public class NegativePrice extends RuntimeException {
    public NegativePrice(String message) {
        super(message);
        System.out.println(Constants.PRODUCT_PRICE_NEGATIVE);
    }
}
