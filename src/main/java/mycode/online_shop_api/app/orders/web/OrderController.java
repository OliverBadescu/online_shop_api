package mycode.online_shop_api.app.orders.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycode.online_shop_api.app.orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.orders.dtos.OrderResponse;
import mycode.online_shop_api.app.orders.dtos.CreateOrderUpdateRequest;
import mycode.online_shop_api.app.orders.dtos.OrderResponseList;
import mycode.online_shop_api.app.orders.repository.OrderRepository;
import mycode.online_shop_api.app.orders.service.OrderCommandService;
import mycode.online_shop_api.app.orders.service.OrderQueryService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
@CrossOrigin
@Slf4j
public class OrderController {

    private OrderCommandService orderCommandService;
    private OrderQueryService orderQueryService;



    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CLIENT')")
    @PostMapping("/sendOrder")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest){

        return new ResponseEntity<>(orderCommandService.addOrder( createOrderRequest), HttpStatus.CREATED);

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CLIENT')")
    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable int orderId){
        return new ResponseEntity<>(orderCommandService.cancelOrder(orderId), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(path = "/deleteOrder/{orderId}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable int orderId){

        return new ResponseEntity<>(orderCommandService.deleteOrder(orderId), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(path = "/updateOrder/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable int orderId, @RequestBody CreateOrderUpdateRequest createOrderUpdateRequest){
        return new ResponseEntity<>(orderCommandService.updateOrder(orderId, createOrderUpdateRequest), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @GetMapping("/getRecentOrders")
    public ResponseEntity<OrderResponseList> getRecentOrders(){
        return new ResponseEntity<>(orderQueryService.getRecentOrders(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CLIENT')")
    @GetMapping("getCustomerOrders")
    public ResponseEntity<OrderResponseList> getCustomerOrders(){
        return new ResponseEntity<>(orderQueryService.customerOrders(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/totalOrders")
    public ResponseEntity<Integer> totalOrders(){
        return new ResponseEntity<>(orderQueryService.totalOrders(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/totalRevenue")
    public ResponseEntity<Double> totalRevenue(){

        return new ResponseEntity<>(orderQueryService.totalRevenue(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Double>> getMonthlyRevenue() {
        return new ResponseEntity<>(orderQueryService.getMonthlyRevenue(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getAllOrders")
    public ResponseEntity<OrderResponseList> getAllOrders(){
        return new ResponseEntity<>(orderQueryService.getAllOrders(), HttpStatus.OK);
    }
}
