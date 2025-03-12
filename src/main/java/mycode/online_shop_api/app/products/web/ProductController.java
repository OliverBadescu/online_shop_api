package mycode.online_shop_api.app.products.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.orderDetails.service.OrderDetailsQueryService;
import mycode.online_shop_api.app.products.dto.CreateProductRequest;
import mycode.online_shop_api.app.products.dto.ProductResponse;
import mycode.online_shop_api.app.products.dto.ProductResponseList;
import mycode.online_shop_api.app.products.dto.UpdateProductRequest;
import mycode.online_shop_api.app.products.service.ProductCommandService;
import mycode.online_shop_api.app.products.service.ProductQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    private ProductQueryService productQueryService;
    private ProductCommandService productCommandService;
    private OrderDetailsQueryService orderDetailsQueryService;

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable int productId){

        return new ResponseEntity<>(productQueryService.findById(productId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody CreateProductRequest createProductRequest){
        return new ResponseEntity<>(productCommandService.addProduct(createProductRequest), HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable int productId){
        return new ResponseEntity<>(productCommandService.deleteProduct(productId), HttpStatus.ACCEPTED);
    }

    @PutMapping(path ="/{productId}")
    public ResponseEntity<ProductResponse> updateProductPut(@PathVariable int productId, @Valid @RequestBody UpdateProductRequest updateProductRequest){

        productCommandService.updateProductPut(productId,updateProductRequest);

        ProductResponse productResponse = productQueryService.findById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);

    }


    @PatchMapping(path ="/{productId}")
    public ResponseEntity<ProductResponse> updateProductPatch(@PathVariable int productId, @Valid @RequestBody UpdateProductRequest updateProductRequest){

        productCommandService.updateProductPatch(productId,updateProductRequest);
        ProductResponse productResponse = productQueryService.findById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);

    }


    @GetMapping(path = "/category/{categoryName}")
    public ResponseEntity<ProductResponseList> getByCategories(@PathVariable String categoryName){

        return new ResponseEntity<>(productQueryService.getByCategory(categoryName), HttpStatus.ACCEPTED);

    }

    @GetMapping(path = "/search/{productName}")
    public ResponseEntity<ProductResponse> findByName(@PathVariable String productName){
        return new ResponseEntity<>(productQueryService.findByName(productName), HttpStatus.ACCEPTED);

    }

    @GetMapping("/getAllProducts")
    public  ResponseEntity<ProductResponseList> getAllProducts(){
        return new ResponseEntity<>(productQueryService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(path = "/mostSold")
    public ResponseEntity<ProductResponseList> getMostSoldProduct(){
        return new ResponseEntity<>(productQueryService.getTopSellingProducts(), HttpStatus.OK);
    }

    @GetMapping("/totalProducts")
    public ResponseEntity<Integer> totalProducts(){
        return new ResponseEntity<>(productQueryService.totalProducts(), HttpStatus.OK);
    }
}
