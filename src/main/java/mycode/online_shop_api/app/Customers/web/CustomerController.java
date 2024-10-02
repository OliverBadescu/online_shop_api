package mycode.online_shop_api.app.customers.web;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


import mycode.online_shop_api.app.customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.customers.dtos.CreateCustomerUpdateRequest;
import mycode.online_shop_api.app.customers.dtos.CustomerResponse;
import mycode.online_shop_api.app.customers.service.CustomerCommandService;
import mycode.online_shop_api.app.customers.service.CustomerQueryService;
import mycode.online_shop_api.app.orders.service.OrderQueryService;
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
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable int customerId){

        return new ResponseEntity<>(customerQueryService.findById(customerId), HttpStatus.OK);

    }

    @GetMapping(path = "/orders/{customerId}")
    public ResponseEntity<List<mycode.online_shop_api.app.orders.dtos.OrderResponse>> getCustomerOrders(@PathVariable int customerId){
        return new  ResponseEntity<>(orderQueryService.customerOrders(customerId), HttpStatus.ACCEPTED);
    }


    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return new ResponseEntity<>(customerCommandService.addCustomer(createCustomerRequest), HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId){
        customerCommandService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable int customerId, @Valid @RequestBody CreateCustomerUpdateRequest createCustomerUpdateRequest){

        customerCommandService.updateCustomer(createCustomerUpdateRequest, customerId);
        CustomerResponse customerResponse = customerQueryService.findById(customerId);
        return new ResponseEntity<>(customerResponse, HttpStatus.ACCEPTED);

    }


}
