package mycode.online_shop_api.app.customers.service;

import mycode.online_shop_api.app.customers.dtos.CustomerResponse;
import mycode.online_shop_api.app.customers.exceptions.NoCustomerFound;
import mycode.online_shop_api.app.customers.model.Customer;
import mycode.online_shop_api.app.customers.repository.CustomerRepository;
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

    @Override
    public CustomerResponse findById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            return new CustomerResponse(customer.get().getId(), customer.get().getFullName(), customer.get().getEmail(), customer.get().getPassword(), customer.get().getBillingAddress(),customer.get().getShippingAddress(),customer.get().getPhone(),customer.get().getCountry());
        }else{
            throw new NoCustomerFound(" ");
        }
    }
}
