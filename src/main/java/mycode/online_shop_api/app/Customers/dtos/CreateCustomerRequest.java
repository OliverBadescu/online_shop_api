package mycode.online_shop_api.app.Customers.dtos;

import mycode.online_shop_api.app.Customers.validators.*;

import java.io.Serializable;

public record CreateCustomerRequest(

        @FullNameConstraint(message = "Name cannot be empty")
        String fullName,
        @EmailConstraint(message = "Email must contain @, and cannot be empty")
        String email,
        @PasswordConstraint(message = "Password cannot be empty")
        String password,
        @BillingAddressConstraint(message = "Billing address cannot be empty")
        String billingAddress,
        @ShippingAddressConstraint(message = "Shipping address cannot be empty ")
        String shippingAddress,
        @PhoneConstraint(message = "Phone cannot be empty and cannot contain letters")
        String phone,
        @CountryConstraint(message = "Country cannot be empty")
        String country) implements Serializable {



}
