package mycode.online_shop_api.app.customers.service;

import mycode.online_shop_api.app.customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.customers.dtos.CustomerResponse;
import mycode.online_shop_api.app.customers.dtos.CreateCustomerUpdateRequest;
import mycode.online_shop_api.app.customers.exceptions.EmailAlreadyExists;
import mycode.online_shop_api.app.customers.exceptions.NoCustomerFound;
import mycode.online_shop_api.app.customers.model.Customer;
import mycode.online_shop_api.app.customers.repository.CustomerRepository;
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


    public CustomerResponse addCustomer(CreateCustomerRequest customer){


        List<Customer> list = customerRepository.findAll();

        for (Customer customer1 : list) {
            if(customer1.getEmail().equals(customer.email())){
                throw new EmailAlreadyExists("");
            }
        }

        Customer customerNew = Customer.builder().fullName(customer.fullName()).billingAddress(customer.billingAddress()).country(customer.country()).email(customer.email()).password(customer.password()).phone(customer.phone()).shippingAddress(customer.shippingAddress()).build();
        customerRepository.saveAndFlush(customerNew);
        return new CustomerResponse(customerNew.getId(),customerNew.getFullName(),customerNew.getEmail(),customerNew.getPassword(),customerNew.getBillingAddress(),customerNew.getShippingAddress(),customerNew.getPhone(),customerNew.getCountry());

    }

    @Override
    public void updateCustomer(CreateCustomerUpdateRequest createCustomerUpdateRequest, int id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            Customer customer1 = customer.get();

            customer1.setBillingAddress(createCustomerUpdateRequest.billingAddress());
            customer1.setCountry(createCustomerUpdateRequest.country());
            customer1.setEmail(createCustomerUpdateRequest.email());
            customer1.setFullName(createCustomerUpdateRequest.fullName());
            customer1.setPassword(createCustomerUpdateRequest.password());
            customer1.setPhone(createCustomerUpdateRequest.phone());
            customer1.setShippingAddress(createCustomerUpdateRequest.shippingAddress());

             customerRepository.saveAndFlush(customer1);
        }else{
            throw new NoCustomerFound(" ");
        }
    }


}
