package mycode.online_shop_api.app.Products.service;

import mycode.online_shop_api.app.Products.dto.CreateProductRequest;
import mycode.online_shop_api.app.Products.dto.CreateProductResponse;

public interface ProductCommandService {

    CreateProductResponse addProduct(CreateProductRequest createProductRequest);

    CreateProductResponse deleteProduct(int id);
}
