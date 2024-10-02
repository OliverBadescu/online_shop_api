package mycode.online_shop_api.app.customers.mapper;

import mycode.online_shop_api.app.customers.dtos.CustomerResponse;
import mycode.online_shop_api.app.customers.model.Customer;

public class CustomerMapper {

    public static CustomerResponse customerToDto(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getBillingAddress(),
                customer.getShippingAddress(),
                customer.getPhone(),
                customer.getCountry()
        );
    }

    public static Customer dtoToCustomer(CustomerResponse dto) {
        return Customer.builder()
                .id(dto.id())
                .fullName(dto.fullName())
                .email(dto.email())
                .password(dto.password())
                .billingAddress(dto.billingAddress())
                .shippingAddress(dto.shippingAddress())
                .phone(dto.phone())
                .country(dto.country())
                .build();
    }


}
