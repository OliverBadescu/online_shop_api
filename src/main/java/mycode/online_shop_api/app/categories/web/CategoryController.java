package mycode.online_shop_api.app.categories.web;


import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.categories.dtos.CategoryResponse;
import mycode.online_shop_api.app.categories.dtos.CreateCategoryRequest;
import mycode.online_shop_api.app.categories.service.CategoryCommandService;
import mycode.online_shop_api.app.categories.service.CategoryQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    CategoryCommandService categoryCommandService;
    CategoryQueryService categoryQueryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CreateCategoryRequest createCategoryRequest){

        return new ResponseEntity<>(categoryCommandService.addCategory(createCategoryRequest), HttpStatus.CREATED);
        
    }

}
