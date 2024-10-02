package mycode.online_shop_api.app.orders.exceptions;

import mycode.online_shop_api.app.utile.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoOrderFound extends RuntimeException {
    public NoOrderFound(String message) {
        super(message);
        System.out.println(Constants.NO_ORDER_FOUND);
    }
}
