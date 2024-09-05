package mycode.online_shop_api.app.Customers.exceptions;

import mycode.online_shop_api.app.utile.Constants;

public class NoCustomerFound extends RuntimeException {
    public NoCustomerFound(String message) {
        super(message);
        System.out.println(Constants.NO_USER_FOUND);
    }
}
