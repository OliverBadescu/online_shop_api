package mycode.online_shop_api.app.Customers.service;

import mycode.online_shop_api.app.Customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.Customers.model.Customer;
import org.springframework.stereotype.Service;


public interface CustomerCommandService {

    void editCustomer(CreateCustomerRequest customer);

    void deleteCustomer(CreateCustomerRequest customer);

    void addCustomer(CreateCustomerRequest customer);


}
