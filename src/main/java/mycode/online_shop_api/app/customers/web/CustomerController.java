package mycode.online_shop_api.app.customers.web;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.customers.dtos.CustomerResponse;
import mycode.online_shop_api.app.customers.dtos.CreateCustomerUpdateRequest;
import mycode.online_shop_api.app.customers.service.CustomerCommandService;
import mycode.online_shop_api.app.customers.service.CustomerQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private CustomerCommandService customerCommandService;
    private CustomerQueryService customerQueryService;

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable int customerId){

        return new ResponseEntity<>(customerQueryService.findById(customerId), HttpStatus.OK);

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
