package mycode.online_shop_api.app.Products.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.Customers.dtos.CreateCustomerResponse;
import mycode.online_shop_api.app.Products.dto.CreateProductRequest;
import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.dto.UpdateProductRequest;
import mycode.online_shop_api.app.Products.service.ProductCommandService;
import mycode.online_shop_api.app.Products.service.ProductQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductQueryService productQueryService;
    private ProductCommandService productCommandService;

    @GetMapping(path = "/{productId}")
    public ResponseEntity<CreateProductResponse> getProduct(@PathVariable int productId){

        return new ResponseEntity<>(productQueryService.findById(productId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> addProduct(@RequestBody CreateProductRequest createProductRequest){
        return new ResponseEntity<>(productCommandService.addProduct(createProductRequest), HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<CreateProductResponse> deleteProduct(@PathVariable int productId){

        productCommandService.deleteProduct(productId);
        return new ResponseEntity<>(productCommandService.deleteProduct(productId), HttpStatus.ACCEPTED);
    }

    @PutMapping(path ="/{productId}")
    public ResponseEntity<CreateProductResponse> updateProductPut(@PathVariable int productId, @Valid @RequestBody UpdateProductRequest updateProductRequest){

        productCommandService.updateProductPut(productId,updateProductRequest);

        CreateProductResponse createProductResponse = productQueryService.findById(productId);
        return new ResponseEntity<>(createProductResponse, HttpStatus.ACCEPTED);

    }


    @PatchMapping(path ="/{productId}")
    public ResponseEntity<CreateProductResponse> updateProductPatch(@PathVariable int productId, @Valid @RequestBody UpdateProductRequest updateProductRequest){


        CreateProductResponse createProductResponse = productQueryService.findById(productId);
        return new ResponseEntity<>(createProductResponse, HttpStatus.ACCEPTED);

    }
}
