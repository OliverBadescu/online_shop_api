package mycode.online_shop_api.app.categories.service;

import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.categories.dtos.CategoryResponse;
import mycode.online_shop_api.app.categories.dtos.CreateCategoryRequest;
import mycode.online_shop_api.app.categories.dtos.UpdateCategoryRequest;
import mycode.online_shop_api.app.categories.exceptions.CategoryAlreadyExists;
import mycode.online_shop_api.app.categories.exceptions.NoCategoryFound;
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

    @Override
    public CategoryResponse deleteCategory(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoCategoryFound("No category with this id found"));

        CategoryResponse categoryResponse = CategoryResponse.builder().id(category.getId()).name(category.getName()).build();

        categoryRepository.delete(category);

        return categoryResponse;
    }

    @Override
    public CategoryResponse updateCategory(int id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoCategoryFound("No category with this id found"));

        category.setName(updateCategoryRequest.name());

        return CategoryResponse.builder().name(category.getName()).id(category.getId()).build();
    }
}
