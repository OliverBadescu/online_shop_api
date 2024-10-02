package mycode.online_shop_api.app.products.exceptions;

import mycode.online_shop_api.app.utile.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoProductFound extends RuntimeException {
    public NoProductFound(String message) {
        super(message);
      System.out.println(Constants.NO_PRODUCT_FOUND);
    }
}
