package mycode.online_shop_api.app.Customers.service;

import jakarta.persistence.OneToMany;
import mycode.online_shop_api.app.Customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.Customers.exceptions.EmailAlreadyExists;
import mycode.online_shop_api.app.Customers.model.Customer;
import mycode.online_shop_api.app.Customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCommandServiceImpl implements CustomerCommandService{


    private CustomerRepository customerRepository;

    public CustomerCommandServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void editCustomer(CreateCustomerRequest customer){


    }


    public void deleteCustomer(CreateCustomerRequest customer){


    }


    public void addCustomer(CreateCustomerRequest customer){


        List<Customer> list = customerRepository.findAll();

        for (Customer customer1 : list) {
            if(customer1.getEmail().equals(customer.email())){
                throw new EmailAlreadyExists("");
            }
        }

        Customer customerNew = Customer.builder().fullName(customer.fullName()).billingAddress(customer.billingAddress()).country(customer.country()).email(customer.email()).password(customer.password()).phone(customer.phone()).shippingAddress(customer.shippingAddress()).build();
        customerRepository.saveAndFlush(customerNew);


    }


}
