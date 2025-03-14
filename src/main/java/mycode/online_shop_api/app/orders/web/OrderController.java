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

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable int orderId){

        return new ResponseEntity<>(orderQueryService.findById(orderId), HttpStatus.OK);

    }

    @PostMapping("/{customerId}")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable int customerId,@RequestBody CreateOrderRequest createOrderRequest){

        return new ResponseEntity<>(orderCommandService.addOrder(customerId, createOrderRequest), HttpStatus.CREATED);

    }

    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable int orderId){
        return new ResponseEntity<>(orderCommandService.cancelOrder(orderId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/deleteOrder/{orderId}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable int orderId){

        return new ResponseEntity<>(orderCommandService.deleteOrder(orderId), HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/updateOrder/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable int orderId, @RequestBody CreateOrderUpdateRequest createOrderUpdateRequest){
        orderCommandService.updateOrder(orderId,createOrderUpdateRequest);

        return new ResponseEntity<>(orderQueryService.findById(orderId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getRecentOrders")
    public ResponseEntity<OrderResponseList> getRecentOrders(){
        return new ResponseEntity<>(orderQueryService.getRecentOrders(), HttpStatus.OK);
    }

    @GetMapping("getCustomerOrders/{userId}")
    public ResponseEntity<OrderResponseList> getCustomerOrders(@PathVariable long userId){
        return new ResponseEntity<>(orderQueryService.customerOrders(userId), HttpStatus.OK);
    }

    @GetMapping("/totalOrders")
    public ResponseEntity<Integer> totalOrders(){
        return new ResponseEntity<>(orderQueryService.totalOrders(), HttpStatus.OK);
    }

    @GetMapping("/totalRevenue")
    public ResponseEntity<Double> totalRevenue(){

        return new ResponseEntity<>(orderQueryService.totalRevenue(), HttpStatus.OK);
    }

    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Double>> getMonthlyRevenue() {
        return new ResponseEntity<>(orderQueryService.getMonthlyRevenue(), HttpStatus.OK);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<OrderResponseList> getAllOrders(){
        return new ResponseEntity<>(orderQueryService.getAllOrders(), HttpStatus.OK);
    }
}
