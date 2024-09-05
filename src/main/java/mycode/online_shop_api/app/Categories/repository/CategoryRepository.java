package mycode.online_shop_api.app.Categories.repository;

import mycode.online_shop_api.app.Categories.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
