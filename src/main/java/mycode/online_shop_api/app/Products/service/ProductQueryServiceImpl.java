package mycode.online_shop_api.app.Products.service;

import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.exceptions.NoProductFound;
import mycode.online_shop_api.app.Products.model.Product;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService{

    private ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void showProducts() {
        productRepository.findAll().forEach(System.out::println);
    }

    @Override
    public void showProductsSortedASC() {
        productRepository.sortedAsc().get().forEach(System.out::println);
    }

    @Override
    public void showProductsSortedDESC() {
        productRepository.sortedDesc().get().forEach(System.out::println);
    }

    @Override
    public Product findByName(String name) {

        Optional<Product> product = productRepository.findByName(name);

        if(product.isPresent()){
            return product.get();
        }else{
            throw new NoProductFound("");
        }

    }

    @Override
    public CreateProductResponse findById(int id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return new CreateProductResponse(product.get().getId(),product.get().getCategory(),product.get().getCreateDate(),product.get().getDescriptions(),product.get().getName(),product.get().getPrice(),product.get().getStock(),product.get().getWeight());

        }else{
            throw new NoProductFound(" ");
        }
    }
}
