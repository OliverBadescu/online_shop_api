package mycode.online_shop_api.app.Products.service;

import mycode.online_shop_api.app.Products.dto.CreateProductRequest;
import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.dto.UpdateProductRequest;

public interface ProductCommandService {

    CreateProductResponse addProduct(CreateProductRequest createProductRequest);

    CreateProductResponse deleteProduct(int id);

    void updateProductPut(int id, UpdateProductRequest updateProductRequest);

    void updateProductPatch(int id, UpdateProductRequest updateProductRequest);
}
