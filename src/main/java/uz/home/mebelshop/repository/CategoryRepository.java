package uz.home.mebelshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.home.mebelshop.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
