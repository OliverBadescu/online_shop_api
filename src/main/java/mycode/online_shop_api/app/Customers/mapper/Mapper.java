package mycode.online_shop_api.app.Customers.mapper;

import mycode.online_shop_api.app.Customers.dtos.CreateCustomerResponse;
import mycode.online_shop_api.app.Customers.model.Customer;

public class Mapper {

    public static CreateCustomerResponse customerToDto(Customer customer) {
        return new CreateCustomerResponse(
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

    public static Customer dtoToCustomer(CreateCustomerResponse dto) {
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
