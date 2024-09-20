package mycode.online_shop_api.app.Customers.service;

import mycode.online_shop_api.app.Customers.dtos.CreateCustomerResponse;
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

    @Override
    public CreateCustomerResponse findById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            return new CreateCustomerResponse(customer.get().getId(), customer.get().getFullName(), customer.get().getEmail(), customer.get().getPassword(), customer.get().getBillingAddress(),customer.get().getShippingAddress(),customer.get().getPhone(),customer.get().getCountry());
        }else{
            throw new NoCustomerFound(" ");
        }
    }
}
