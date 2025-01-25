package mycode.online_shop_api.app.cart.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycode.online_shop_api.app.cart.dtos.AddProductToCartRequest;
import mycode.online_shop_api.app.cart.dtos.CartResponse;
import mycode.online_shop_api.app.cart.services.CartCommandService;
import mycode.online_shop_api.app.cart.services.CartQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
@CrossOrigin
@Slf4j
public class CartController {

    private CartCommandService cartCommandService;
    private CartQueryService cartQueryService;

    @GetMapping("/getCartByUserId/{userId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable long userId) {
        return new ResponseEntity<>(cartQueryService.getCartByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/addProductToCart/{userId}")
    public ResponseEntity<CartResponse> addProductToCart(@RequestBody AddProductToCartRequest addProductToCartRequest, @PathVariable long userId) {
        return new ResponseEntity<>(cartCommandService.addProductToCart(addProductToCartRequest, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProductFromCart/{userId}/product/{productId}")
    public ResponseEntity<CartResponse> deleteProductFromCart(@PathVariable int userId, @PathVariable int productId) {
        return new ResponseEntity<>(cartCommandService.deleteProductFromCart(productId, userId), HttpStatus.ACCEPTED);
    }
}
