package mycode.online_shop_api.app.Orders.web;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderUpdateRequest;
import mycode.online_shop_api.app.Orders.dtos.EditOrderRequest;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Orders.repository.OrderRepository;
import mycode.online_shop_api.app.Orders.service.OrderCommandService;
import mycode.online_shop_api.app.Orders.service.OrderQueryService;
import mycode.online_shop_api.app.Products.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private OrderCommandService orderCommandService;
    private OrderQueryService orderQueryService;

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<CreateOrderResponse> getOrder(@PathVariable int orderId){

        return new ResponseEntity<>(orderQueryService.findById(orderId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest){

        return new ResponseEntity<>(orderCommandService.addOrder(createOrderRequest), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{orderId}")
    public ResponseEntity<CreateOrderResponse> deleteOrder(@PathVariable int orderId){

        return new ResponseEntity<>(orderCommandService.deleteOrder(orderId), HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{orderId}")
    public ResponseEntity<CreateOrderResponse> updateOrder(@PathVariable int orderId, @RequestBody CreateOrderUpdateRequest createOrderUpdateRequest){
        orderCommandService.updateOrder(orderId,createOrderUpdateRequest);

        return new ResponseEntity<>(orderQueryService.findById(orderId), HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/editOrder/{orderId}")
    public ResponseEntity<CreateOrderResponse> editOrder(@PathVariable int orderId, @RequestBody EditOrderRequest editOrderRequest) {

        // todo: improve with enum
        String action = editOrderRequest.action();
        System.out.println(editOrderRequest.productName());

        switch (action) {
            case "delete":
                return new ResponseEntity<>(orderCommandService.deleteProductFromOrder(orderId, editOrderRequest), HttpStatus.ACCEPTED);
            case "edit":
                return new ResponseEntity<>(orderCommandService.updateProductQuantity(orderId, editOrderRequest), HttpStatus.ACCEPTED);
            case "cancel":
                return new ResponseEntity<>(orderCommandService.cancelOrder(orderId), HttpStatus.ACCEPTED);
            default:
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
