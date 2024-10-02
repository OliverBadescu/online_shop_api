package mycode.online_shop_api.app.categories.repository;

import mycode.online_shop_api.app.categories.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
