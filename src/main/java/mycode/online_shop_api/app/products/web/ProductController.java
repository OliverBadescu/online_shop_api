package mycode.online_shop_api.app.products.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycode.online_shop_api.app.products.dto.*;
import mycode.online_shop_api.app.products.service.ProductCommandService;
import mycode.online_shop_api.app.products.service.ProductQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;

    /* ------------------------------------------------------------------ */
    /* Commands                                                            */
    /* ------------------------------------------------------------------ */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productCommandService.addProduct(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int productId,
                                                         @Valid @RequestBody UpdateProductRequest request) {
        productCommandService.updateProductPut(productId, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productQueryService.findById(productId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable int productId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productCommandService.deleteProduct(productId));
    }

    /* ------------------------------------------------------------------ */
    /* Queries                                                             */
    /* ------------------------------------------------------------------ */

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @GetMapping("/getAllProducts")
    public ResponseEntity<ProductResponseList> getAllProducts() {
        return ResponseEntity.ok(productQueryService.getAllProducts());
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @GetMapping("/mostSold")
    public ResponseEntity<ProductResponseList> getMostSoldProducts() {
        return ResponseEntity.ok(productQueryService.getTopSellingProducts());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/totalProducts")
    public ResponseEntity<Integer> totalProducts() {
        return ResponseEntity.ok(productQueryService.totalProducts());
    }
}