package mycode.online_shop_api.app.Customers.service;

import mycode.online_shop_api.app.Customers.exceptions.NoCustomerFound;
import mycode.online_shop_api.app.Customers.model.Customer;
import mycode.online_shop_api.app.Customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService{

    private CustomerRepository customerRepository;

    public CustomerQueryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByEmailAndPassword(String email, String password){

        Optional<Customer> customer = customerRepository.findByEmailAndPassword(email,password);
        if(customer.isPresent()){
            return customer.get();
        }else{
            throw new NoCustomerFound("");
        }
    }

    public boolean existsByEmail(String email){

        return customerRepository.existsByEmail(email);

    }
}
