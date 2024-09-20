package mycode.online_shop_api.app.Orders.web;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;
import mycode.online_shop_api.app.Orders.service.OrderCommandService;
import mycode.online_shop_api.app.Orders.service.OrderQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderCommandService orderCommandService;
    private OrderQueryService orderQueryService;

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<CreateOrderResponse> getOrder(@PathVariable int orderId){

        return new ResponseEntity<>(orderQueryService.findById(orderId), HttpStatus.OK);

    }

}
