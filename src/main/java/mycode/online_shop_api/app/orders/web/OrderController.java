package mycode.online_shop_api.app.orders.web;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.orders.dtos.OrderResponse;
import mycode.online_shop_api.app.orders.dtos.CreateOrderUpdateRequest;
import mycode.online_shop_api.app.orders.repository.OrderRepository;
import mycode.online_shop_api.app.orders.service.OrderCommandService;
import mycode.online_shop_api.app.orders.service.OrderQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private OrderCommandService orderCommandService;
    private OrderQueryService orderQueryService;

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable int orderId){

        return new ResponseEntity<>(orderQueryService.findById(orderId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest){

        return new ResponseEntity<>(orderCommandService.addOrder(createOrderRequest), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{orderId}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable int orderId){

        return new ResponseEntity<>(orderCommandService.deleteOrder(orderId), HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable int orderId, @RequestBody CreateOrderUpdateRequest createOrderUpdateRequest){
        orderCommandService.updateOrder(orderId,createOrderUpdateRequest);

        return new ResponseEntity<>(orderQueryService.findById(orderId), HttpStatus.ACCEPTED);
    }

}
