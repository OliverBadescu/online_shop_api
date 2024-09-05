package mycode.online_shop_api.app.Customers.service;

import mycode.online_shop_api.app.Customers.model.Customer;
import org.springframework.stereotype.Service;

public interface CustomerQueryService {

    Customer findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

}
