package mycode.online_shop_api.app.Customers.exceptions;

import mycode.online_shop_api.app.utile.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoCustomerFound extends RuntimeException {
    public NoCustomerFound(String message) {
        super(message);
        System.out.println(Constants.NO_USER_FOUND);
    }
}
