package mycode.online_shop_api.app.customers.service;

import mycode.online_shop_api.app.customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.customers.dtos.CustomerResponse;
import mycode.online_shop_api.app.customers.dtos.CreateCustomerUpdateRequest;


public interface CustomerCommandService {

    void deleteCustomer(int customerId);

    CustomerResponse addCustomer(CreateCustomerRequest customer);

    void updateCustomer(CreateCustomerUpdateRequest createCustomerUpdateRequest, int id);


}
