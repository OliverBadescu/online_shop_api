package mycode.online_shop_api.app.customers.service;

import mycode.online_shop_api.app.customers.dtos.CustomerResponse;
import mycode.online_shop_api.app.customers.model.Customer;

public interface CustomerQueryService {

    Customer findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    CustomerResponse findById(int id);

}
