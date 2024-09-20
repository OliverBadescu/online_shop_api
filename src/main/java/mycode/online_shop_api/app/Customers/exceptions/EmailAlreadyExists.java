package mycode.online_shop_api.app.Customers.exceptions;

import mycode.online_shop_api.app.utile.Constants;


public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String message) {
        super(message);
        System.out.println(Constants.USER_ALREADY_EXIST_EXCEPTION);
    }
}
