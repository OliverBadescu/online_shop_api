package mycode.online_shop_api.app.Customers.web;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.Customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.Customers.dtos.CreateCustomerResponse;
import mycode.online_shop_api.app.Customers.dtos.CreateCustomerUpdateRequest;
import mycode.online_shop_api.app.Customers.model.Customer;
import mycode.online_shop_api.app.Customers.service.CustomerCommandService;
import mycode.online_shop_api.app.Customers.service.CustomerQueryService;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;
import mycode.online_shop_api.app.Orders.service.OrderQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private CustomerCommandService customerCommandService;
    private CustomerQueryService customerQueryService;
    private OrderQueryService orderQueryService;

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CreateCustomerResponse> getCustomer(@PathVariable int customerId){

        return new ResponseEntity<>(customerQueryService.findById(customerId), HttpStatus.OK);

    }

    @GetMapping(path = "/orders/{customerId}")
    public ResponseEntity<List<CreateOrderResponse>> getCustomerOrders(@PathVariable int customerId){
        return new  ResponseEntity<>(orderQueryService.customerOrders(customerId), HttpStatus.ACCEPTED);
    }


    @PostMapping
    public ResponseEntity<CreateCustomerResponse> addCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return new ResponseEntity<>(customerCommandService.addCustomer(createCustomerRequest), HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId){
        customerCommandService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<CreateCustomerResponse> updateCustomer(@PathVariable int customerId, @Valid @RequestBody CreateCustomerUpdateRequest createCustomerUpdateRequest){

        customerCommandService.updateCustomer(createCustomerUpdateRequest, customerId);
        CreateCustomerResponse createCustomerResponse = customerQueryService.findById(customerId);
        return new ResponseEntity<>(createCustomerResponse, HttpStatus.ACCEPTED);

    }


}
