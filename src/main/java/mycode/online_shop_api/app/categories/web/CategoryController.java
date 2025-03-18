package mycode.online_shop_api.app.categories.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycode.online_shop_api.app.categories.dtos.CategoryResponse;
import mycode.online_shop_api.app.categories.dtos.CategoryResponseList;
import mycode.online_shop_api.app.categories.dtos.CreateCategoryRequest;
import mycode.online_shop_api.app.categories.dtos.UpdateCategoryRequest;
import mycode.online_shop_api.app.categories.service.CategoryCommandService;
import mycode.online_shop_api.app.categories.service.CategoryQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
@CrossOrigin
@Slf4j
public class CategoryController {

    CategoryCommandService categoryCommandService;
    CategoryQueryService categoryQueryService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/addCategory")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CreateCategoryRequest createCategoryRequest){

        return new ResponseEntity<>(categoryCommandService.addCategory(createCategoryRequest), HttpStatus.CREATED);
        
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(path = "/deleteCategory/{categoryId}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable int categoryId){

        return new ResponseEntity<>(categoryCommandService.deleteCategory(categoryId), HttpStatus.ACCEPTED);

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(path = "/updateCategory/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable int categoryId, @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return new ResponseEntity<>(categoryCommandService.updateCategory(categoryId, updateCategoryRequest), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CLIENT')")
    @GetMapping("/getAll")
    public ResponseEntity<CategoryResponseList> getAll(){
        return new ResponseEntity<>(categoryQueryService.getAllCategories(), HttpStatus.OK);
    }

}
