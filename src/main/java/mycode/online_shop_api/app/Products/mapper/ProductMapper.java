package mycode.online_shop_api.app.Products.mapper;

import lombok.Builder;
import mycode.online_shop_api.app.Customers.mapper.CustomerMapper;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderResponse;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.model.Product;

@Builder
public class ProductMapper {


    public static CreateProductResponse productToResponseDto(Product product){
        return CreateProductResponse.builder()
                .category(product.getCategory())
                .createDate(product.getCreateDate())
                .id(product.getId())
                .description(product.getDescriptions())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .weight(product.getWeight())
                .build();
    }

    public static Product responseDtoToProduct(CreateProductResponse product){
        return Product.builder()
                .category(product.category())
                .createDate(product.createDate())
                .id(product.id())
                .descriptions(product.description())
                .name(product.name())
                .price(product.price())
                .stock(product.stock())
                .weight(product.weight())
                .build();
    }

}
