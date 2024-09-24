package mycode.online_shop_api.app.OrderDetails.service;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.OrderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.model.Product;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderDetailsQueryServiceImpl implements OrderDetailsQueryService {


    private OrderDetailsRepository orderDetailsRepository;
    private ProductRepository productRepository;

    @Override
    public CreateProductResponse mostSoldProduct() {
        List<Integer> list = orderDetailsRepository.mostSoldProduct();

        Optional<Product> product = productRepository.findById(list.get(0));

        return CreateProductResponse.builder()
                .category(product.get().getCategory())
                .createDate(product.get().getCreateDate())
                .id(product.get().getId())
                .description(product.get().getDescriptions())
                .name(product.get().getName())
                .price(product.get().getPrice())
                .stock(product.get().getStock())
                .weight(product.get().getWeight())
                .build();
    }
}
