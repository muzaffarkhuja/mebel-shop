package uz.home.mebelshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.home.mebelshop.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
}
