package mycode.online_shop_api.app.Customers.dtos;

public record CreateCustomerRequest(String fullName, String email, String password, String billingAddress, String shippingAddress, String phone, String country) {



}
