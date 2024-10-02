package mycode.online_shop_api.app.products.service;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.categories.exceptions.NoCategoryFound;
import mycode.online_shop_api.app.categories.model.Category;
import mycode.online_shop_api.app.categories.repository.CategoryRepository;
import mycode.online_shop_api.app.productCategories.model.ProductCategories;
import mycode.online_shop_api.app.productCategories.repository.ProductCategoriesRepository;
import mycode.online_shop_api.app.products.dto.CreateProductRequest;
import mycode.online_shop_api.app.products.dto.ProductResponse;
import mycode.online_shop_api.app.products.dto.UpdateProductRequest;
import mycode.online_shop_api.app.products.exceptions.NoProductFound;
import mycode.online_shop_api.app.products.model.Product;
import mycode.online_shop_api.app.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AllArgsConstructor
@Service
public class ProductCommandServiceImpl implements ProductCommandService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductCategoriesRepository productCategoriesRepository;

    @Override
    public ProductResponse addProduct(CreateProductRequest createProductRequest) {
        Product product = Product.builder().name(createProductRequest.name()).category(createProductRequest.category()).createDate(createProductRequest.createDate()).descriptions(createProductRequest.description()).price(createProductRequest.price()).stock(createProductRequest.stock()).weight(createProductRequest.weight()).build();
        Category category = categoryRepository.findByName(product.getCategory())
                        .orElseThrow(() -> new NoCategoryFound("No category with this name found"));

        productRepository.saveAndFlush(product);
        ProductCategories productCategories = ProductCategories.builder().product(product).category(category).build();

        productCategoriesRepository.saveAndFlush(productCategories);



        return new ProductResponse(product.getId(),product.getCategory(), product.getCreateDate(),product.getDescriptions(), product.getName(), product.getPrice(),product.getStock(),product.getWeight());

    }

    @Override
    public ProductResponse deleteProduct(int id) {
        Optional<Product> product = productRepository.findById(id);


        if(product.isPresent()){
            ProductResponse productResponse = new ProductResponse(product.get().getId(),product.get().getCategory(),product.get().getCreateDate(),product.get().getDescriptions(), product.get().getName(),product.get().getPrice(),product.get().getStock(),product.get().getWeight());
            productRepository.delete(product.get());
            return productResponse;

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
