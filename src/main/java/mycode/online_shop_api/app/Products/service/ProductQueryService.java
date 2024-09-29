package mycode.online_shop_api.app.Products.service;

import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.model.Product;

import java.util.List;

public interface ProductQueryService {

    void showProducts();

    void showProductsSortedASC();

    void showProductsSortedDESC();

    CreateProductResponse findById(int id);

    CreateProductResponse mostExpensive();

    List<CreateProductResponse> getByCategory(String category);

    CreateProductResponse findByName(String productName);
}
