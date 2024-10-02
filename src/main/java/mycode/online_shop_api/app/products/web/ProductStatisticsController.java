package mycode.online_shop_api.app.products.web;


import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.orderDetails.service.OrderDetailsQueryService;
import mycode.online_shop_api.app.products.dto.ProductResponse;
import mycode.online_shop_api.app.products.service.ProductCommandService;
import mycode.online_shop_api.app.products.service.ProductQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/product/statistics")
public class ProductStatisticsController {

    private ProductQueryService productQueryService;
    private ProductCommandService productCommandService;
    private OrderDetailsQueryService orderDetailsQueryService;

    //most sold product
    @GetMapping(path = "/mostSold")
    public ResponseEntity<ProductResponse> getMostSoldProduct(){
        return new ResponseEntity<>(orderDetailsQueryService.mostSoldProduct(), HttpStatus.OK);
    }


    @GetMapping(path = "/mostExpensive")
    public ResponseEntity<ProductResponse> getMostExpensiveProduct(){
        return new ResponseEntity<>(productQueryService.mostExpensive(), HttpStatus.OK);
    }


}
