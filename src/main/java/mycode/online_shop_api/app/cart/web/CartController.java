package mycode.online_shop_api.app.cart.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycode.online_shop_api.app.cart.dtos.AddProductToCartRequest;
import mycode.online_shop_api.app.cart.dtos.CartResponse;
import mycode.online_shop_api.app.cart.dtos.UpdateCartQuantityRequest;
import mycode.online_shop_api.app.cart.services.CartCommandService;
import mycode.online_shop_api.app.cart.services.CartQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for cart operations.
 * <p>
 * Security convention:
 *   - GrantedAuthority values: ROLE_ADMIN, ROLE_CLIENT
 *   - Therefore use hasAnyRole('ADMIN','CLIENT').
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
@CrossOrigin
@Slf4j
public class CartController {

    private CartCommandService cartCommandService;
    private CartQueryService cartQueryService;

    /* ------------------------------------------------------------------ */
    /* Queries                                                             */
    /* ------------------------------------------------------------------ */

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @GetMapping("/getCart")
    public ResponseEntity<CartResponse> getCart() {
        return new ResponseEntity<>(cartQueryService.getCart(), HttpStatus.OK);
    }

    /* ------------------------------------------------------------------ */
    /* Commands                                                            */
    /* ------------------------------------------------------------------ */

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @PostMapping("/addProductToCart")
    public ResponseEntity<CartResponse> addProductToCart(@RequestBody AddProductToCartRequest addProductToCartRequest) {
        return new ResponseEntity<>(cartCommandService.addProductToCart(addProductToCartRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    @DeleteMapping("/deleteProductFromCart/product/{productId}")
    public ResponseEntity<CartResponse> deleteProductFromCart( @PathVariable int productId) {
        return new ResponseEntity<>(cartCommandService.deleteProductFromCart(productId), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CLIENT')")
    @PutMapping("/updateProductQuantity/products/{productId}")
    public ResponseEntity<CartResponse> updateProductQuantity( @PathVariable int productId, @RequestBody UpdateCartQuantityRequest updateCartQuantityRequest){
        return new ResponseEntity<>(cartCommandService.updateCartQuantity(updateCartQuantityRequest,productId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CLIENT')")
    @DeleteMapping("/emptyUserCart")
    public ResponseEntity<String> emptyUserCart(){
        return new ResponseEntity<>(cartCommandService.emptyUserCart(), HttpStatus.ACCEPTED);
    }
}
