package mycode.online_shop_api.app.categories.service;

import mycode.online_shop_api.app.categories.dtos.CategoryResponse;

public interface CategoryQueryService {
    CategoryResponse getCategory(int categoryId);
}
