package mycode.online_shop_api.app.Customers.service;

import mycode.online_shop_api.app.Customers.dtos.CreateCustomerResponse;
import mycode.online_shop_api.app.Customers.model.Customer;

public interface CustomerQueryService {

    Customer findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    CreateCustomerResponse findById(int id);

}
