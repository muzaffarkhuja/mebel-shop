package uz.home.mebelshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.home.mebelshop.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
