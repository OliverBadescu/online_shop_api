package mycode.online_shop_api.app.products.service;

import mycode.online_shop_api.app.products.dto.ProductResponse;
import mycode.online_shop_api.app.products.dto.ProductResponseList;
import mycode.online_shop_api.app.products.exceptions.NoProductFound;
import mycode.online_shop_api.app.products.mapper.ProductMapper;
import mycode.online_shop_api.app.products.model.Product;
import mycode.online_shop_api.app.products.repository.ProductRepository;
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
    public ProductResponse findById(int id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return new ProductResponse(product.get().getId(),product.get().getCategory(),product.get().getCreateDate(),product.get().getDescriptions(),product.get().getName(),product.get().getPrice(),product.get().getStock(),product.get().getWeight());

        }else{
            throw new NoProductFound(" ");
        }
    }

    @Override
    public ProductResponse mostExpensive() {
        Optional<List<Product>> list = productRepository.sortedDesc();
        return ProductMapper.productToResponseDto(list.get().get(0));

    }

    @Override
    public ProductResponseList getByCategory(String category) {
        List<ProductResponse> list = new ArrayList<>();

        List<Product> products = productRepository.findAll();

        products.forEach(product -> {
            if(product.getCategory().equals(category)){
                list.add(ProductMapper.productToResponseDto(product));
            }
        });



        if(list.isEmpty()){
            throw new NoProductFound("No products in this category found");
        }else{
            return new ProductResponseList(list, list.size() + " products have been found in this category");
        }
    }

    @Override
    public ProductResponse findByName(String productName) {

        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new NoProductFound("No product with this name found"));


        return ProductMapper.productToResponseDto(product);
    }
}
