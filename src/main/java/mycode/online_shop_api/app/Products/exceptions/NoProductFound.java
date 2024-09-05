package mycode.online_shop_api.app.Products.exceptions;

import mycode.online_shop_api.app.utile.Constants;

public class NoProductFound extends RuntimeException {
    public NoProductFound(String message) {
        super(message);
      System.out.println(Constants.NO_PRODUCT_FOUND);
    }
}
