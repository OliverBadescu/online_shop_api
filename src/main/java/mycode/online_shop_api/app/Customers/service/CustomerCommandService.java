package mycode.online_shop_api.app.Customers.service;

import mycode.online_shop_api.app.Customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.Customers.dtos.CreateCustomerResponse;


public interface CustomerCommandService {

    void deleteCustomer(int customerId);

    CreateCustomerResponse addCustomer(CreateCustomerRequest customer);


}
