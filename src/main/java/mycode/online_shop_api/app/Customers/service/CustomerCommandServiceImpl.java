package mycode.online_shop_api.app.Customers.service;

import mycode.online_shop_api.app.Customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.Customers.dtos.CreateCustomerResponse;
import mycode.online_shop_api.app.Customers.exceptions.EmailAlreadyExists;
import mycode.online_shop_api.app.Customers.exceptions.NoCustomerFound;
import mycode.online_shop_api.app.Customers.model.Customer;
import mycode.online_shop_api.app.Customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerCommandServiceImpl implements CustomerCommandService{


    private CustomerRepository customerRepository;

    public CustomerCommandServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void deleteCustomer(int customerId){

        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent()){
            customerRepository.delete(customer.get());
        }else{
            throw new NoCustomerFound("");
        }
    }


    public CreateCustomerResponse addCustomer(CreateCustomerRequest customer){


        List<Customer> list = customerRepository.findAll();

        for (Customer customer1 : list) {
            if(customer1.getEmail().equals(customer.email())){
                throw new EmailAlreadyExists("");
            }
        }

        Customer customerNew = Customer.builder().fullName(customer.fullName()).billingAddress(customer.billingAddress()).country(customer.country()).email(customer.email()).password(customer.password()).phone(customer.phone()).shippingAddress(customer.shippingAddress()).build();
        customerRepository.saveAndFlush(customerNew);
        return new CreateCustomerResponse(customerNew.getId(),customerNew.getFullName(),customerNew.getEmail(),customerNew.getPassword(),customerNew.getBillingAddress(),customerNew.getShippingAddress(),customerNew.getPhone(),customerNew.getCountry());

    }


}
