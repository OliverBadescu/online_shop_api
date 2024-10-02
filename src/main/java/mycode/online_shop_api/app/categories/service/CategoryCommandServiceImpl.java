package mycode.online_shop_api.app.categories.service;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.categories.dtos.CategoryResponse;
import mycode.online_shop_api.app.categories.dtos.CreateCategoryRequest;
import mycode.online_shop_api.app.categories.exceptions.CategoryAlreadyExists;
import mycode.online_shop_api.app.categories.model.Category;
import mycode.online_shop_api.app.categories.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService{

    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse addCategory(CreateCategoryRequest createCategoryRequest) {
        List<Category> list = categoryRepository.findAll();

        list.forEach(category -> {
            if(category.getName().equals(createCategoryRequest.name())){
                throw new CategoryAlreadyExists("Category with this name already exists");
            }
        });

        Category category = Category.builder()
                .name(createCategoryRequest.name()).build();

        categoryRepository.saveAndFlush(category);

        return new CategoryResponse(category.getId(), category.getName());
    }
}
