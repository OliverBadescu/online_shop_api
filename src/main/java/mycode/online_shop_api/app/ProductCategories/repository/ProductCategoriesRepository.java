package mycode.online_shop_api.app.ProductCategories.repository;

import mycode.online_shop_api.app.ProductCategories.model.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategories, Integer> {
}
