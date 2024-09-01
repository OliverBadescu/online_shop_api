package mycode.online_shop_api.app.repository;

import mycode.online_shop_api.app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
