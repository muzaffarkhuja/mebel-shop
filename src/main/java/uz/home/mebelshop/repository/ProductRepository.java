package uz.home.mebelshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.home.mebelshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p from Product p where p.name ilike coalesce(:name, p.name)" +
            "and p.price >= coalesce(:minPrice, p.price)" +
            "and p.price <= coalesce(:maxPrice, p.price)" +
            "and p.category.name = coalesce(:category, p.category.name)")
    Page<Product> getAll(PageRequest pageRequest, @Param("name") String name,
                         @Param("minPrice") String minPrice,
                         @Param("maxPrice") String maxPrice,
                         @Param("category") String category);
}
