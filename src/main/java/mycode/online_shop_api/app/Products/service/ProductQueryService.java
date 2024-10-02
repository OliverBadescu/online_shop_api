package mycode.online_shop_api.app.products.service;

import mycode.online_shop_api.app.products.dto.ProductResponse;
import mycode.online_shop_api.app.products.dto.ProductResponseList;

public interface ProductQueryService {

    void showProducts();

    void showProductsSortedASC();

    void showProductsSortedDESC();

    ProductResponse findById(int id);

    ProductResponse mostExpensive();

    ProductResponseList getByCategory(String category);

    ProductResponse findByName(String productName);
}
