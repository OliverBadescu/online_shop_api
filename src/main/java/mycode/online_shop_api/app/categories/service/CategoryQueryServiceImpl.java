package mycode.online_shop_api.app.categories.service;


import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.categories.dtos.CategoryResponse;
import mycode.online_shop_api.app.categories.dtos.CategoryResponseList;
import mycode.online_shop_api.app.categories.exceptions.NoCategoryFound;
import mycode.online_shop_api.app.categories.model.Category;
import mycode.online_shop_api.app.categories.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService{

    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse getCategory(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isPresent()){
            return new CategoryResponse(category.get().getId(),category.get().getName());
        }else{
            throw new NoCategoryFound("No category with this id found");
        }

    }

    @Override
    public CategoryResponseList getAllCategories() {
        List<Category> list = categoryRepository.findAll();

        ArrayList<CategoryResponse> responses = new ArrayList<>();

        list.forEach(category -> {
            responses.add(CategoryResponse.builder()
                    .name(category.getName())
                    .id(category.getId()).build());
        });

        return new CategoryResponseList(responses);
    }

}
