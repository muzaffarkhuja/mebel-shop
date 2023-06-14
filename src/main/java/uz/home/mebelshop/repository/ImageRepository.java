package uz.home.mebelshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.home.mebelshop.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
