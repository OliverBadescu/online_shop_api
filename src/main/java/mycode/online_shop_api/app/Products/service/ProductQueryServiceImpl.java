package mycode.online_shop_api.app.Products.service;

import mycode.online_shop_api.app.Products.dto.CreateProductResponse;
import mycode.online_shop_api.app.Products.exceptions.NoProductFound;
import mycode.online_shop_api.app.Products.mapper.ProductMapper;
import mycode.online_shop_api.app.Products.model.Product;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public CreateProductResponse mostExpensive() {
        Optional<List<Product>> list = productRepository.sortedDesc();
        return ProductMapper.productToResponseDto(list.get().get(0));

    }

    @Override
    public List<CreateProductResponse> getByCategory(String category) {
        List<CreateProductResponse> list = new ArrayList<>();

        List<Product> products = productRepository.findAll();

        products.forEach(product -> {
            if(product.getCategory().equals(category)){
                list.add(ProductMapper.productToResponseDto(product));
            }
        });

        if(list.isEmpty()){
            throw new NoProductFound("No products in this category found");
        }else{
            return list;
        }
    }
}
