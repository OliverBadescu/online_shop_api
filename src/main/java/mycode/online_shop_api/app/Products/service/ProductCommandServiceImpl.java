package mycode.online_shop_api.app.Products.service;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.Products.dto.CreateProductRequest;
import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.dto.UpdateProductRequest;
import mycode.online_shop_api.app.Products.exceptions.NoProductFound;
import mycode.online_shop_api.app.Products.model.Product;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AllArgsConstructor
@Service
public class ProductCommandServiceImpl implements ProductCommandService{

    private ProductRepository productRepository;

    @Override
    public CreateProductResponse addProduct(CreateProductRequest createProductRequest) {
        Product product = Product.builder().name(createProductRequest.name()).category(createProductRequest.category()).createDate(createProductRequest.createDate()).descriptions(createProductRequest.description()).price(createProductRequest.price()).stock(createProductRequest.stock()).weight(createProductRequest.weight()).build();

        productRepository.saveAndFlush(product);

        return new CreateProductResponse(product.getId(),product.getCategory(), product.getCreateDate(),product.getDescriptions(), product.getName(), product.getPrice(),product.getStock(),product.getWeight());

    }

    @Override
    public CreateProductResponse deleteProduct(int id) {
        Optional<Product> product = productRepository.findById(id);


        if(product.isPresent()){
            CreateProductResponse createProductResponse = new CreateProductResponse(product.get().getId(),product.get().getCategory(),product.get().getCreateDate(),product.get().getDescriptions(), product.get().getName(),product.get().getPrice(),product.get().getStock(),product.get().getWeight());
            productRepository.delete(product.get());
            return createProductResponse;

        }else{
            throw new NoProductFound(" ");
        }
    }

    @Override
    public void updateProductPut(int id, UpdateProductRequest updateProductRequest) {
        Optional<Product > product = productRepository.findById(id);

        if(product.isPresent()){

            product.get().setCategory(updateProductRequest.category());
            product.get().setDescriptions(updateProductRequest.description());
            product.get().setName(updateProductRequest.name());
            product.get().setPrice(updateProductRequest.price());
            product.get().setStock(updateProductRequest.stock());
            product.get().setWeight(updateProductRequest.weight());


            productRepository.saveAndFlush(product.get());
        }else{
            throw new NoProductFound(" ");
        }
    }

    @Override
    public void updateProductPatch(int id, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoProductFound("Product not found"));

        Optional.ofNullable(updateProductRequest.category())
                .ifPresent(product::setCategory);

        Optional.ofNullable(updateProductRequest.description())
                .ifPresent(product::setDescriptions);

        Optional.ofNullable(updateProductRequest.name())
                .ifPresent(product::setName);

        setIfValid(product::setPrice, updateProductRequest.price(), price -> price > 0);
        setIfValid(product::setStock, updateProductRequest.stock(), stock -> stock >= 0);
        setIfValid(product::setWeight, updateProductRequest.weight(), weight -> weight > 0);

        productRepository.saveAndFlush(product);
    }

    private <T> void setIfValid(Consumer<T> setter, T value, Predicate<T> validator) {
        if (validator.test(value)) {
            setter.accept(value);
        }
    }
}
