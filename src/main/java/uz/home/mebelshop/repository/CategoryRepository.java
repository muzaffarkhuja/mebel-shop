package uz.home.mebelshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.home.mebelshop.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select c from Category c where coalesce(:parentId, -1) = c.parentId ")
    List<Category> findAllByParentId(@Param("parentId") Integer parentId);
}
