package mycode.online_shop_api.app.categories.web;


import lombok.AllArgsConstructor;
import mycode.online_shop_api.app.categories.dtos.CategoryResponse;
import mycode.online_shop_api.app.categories.dtos.CreateCategoryRequest;
import mycode.online_shop_api.app.categories.dtos.UpdateCategoryRequest;
import mycode.online_shop_api.app.categories.service.CategoryCommandService;
import mycode.online_shop_api.app.categories.service.CategoryQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable int categoryId){

        return new ResponseEntity<>(categoryQueryService.getCategory(categoryId), HttpStatus.ACCEPTED);

    }

    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable int categoryId){

        return new ResponseEntity<>(categoryCommandService.deleteCategory(categoryId), HttpStatus.ACCEPTED);

    }

    @PutMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable int categoryId, @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return new ResponseEntity<>(categoryCommandService.updateCategory(categoryId, updateCategoryRequest), HttpStatus.ACCEPTED);
    }

}
