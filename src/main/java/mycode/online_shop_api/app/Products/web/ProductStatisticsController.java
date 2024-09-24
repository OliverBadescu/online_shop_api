package mycode.online_shop_api.app.Products.web;


import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.OrderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.OrderDetails.service.OrderDetailsQueryService;
import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.service.ProductCommandService;
import mycode.online_shop_api.app.Products.service.ProductQueryService;
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
    public ResponseEntity<CreateProductResponse> getMostSoldProduct(){

        return new ResponseEntity<>(orderDetailsQueryService.mostSoldProduct(), HttpStatus.OK);

    }


}
