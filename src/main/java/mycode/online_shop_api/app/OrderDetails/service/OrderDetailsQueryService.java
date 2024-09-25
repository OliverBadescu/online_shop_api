package mycode.online_shop_api.app.OrderDetails.service;

import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.model.Product;

public interface OrderDetailsQueryService {


    CreateProductResponse mostSoldProduct();

}
