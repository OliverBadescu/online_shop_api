package mycode.online_shop_api.app.cart.mock;

import mycode.online_shop_api.app.cart.model.Cart;
import mycode.online_shop_api.app.products.mocks.ProductMockData;
import mycode.online_shop_api.app.products.model.Product;
import mycode.online_shop_api.app.users.model.User;

import java.util.HashSet;

public class CartMockData {

    public static Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCartProducts(new HashSet<>());
        return cart;
    }

    public static Cart createCartWithSampleProducts(User user) {
        Cart cart = createCart(user);
        Product cheapPhone = ProductMockData.createProduct("Cheap Phone", 100, 10, 0.5, "Basic phone", "Phone", java.time.LocalDate.now());
        Product midPhone = ProductMockData.createProduct("Mid Phone", 500, 5, 0.8, "Mid-range phone", "Phone", java.time.LocalDate.now());

        cart.addProduct(cheapPhone, 2);
        cart.addProduct(midPhone, 1);

        return cart;
    }

}
