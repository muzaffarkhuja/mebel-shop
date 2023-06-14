package uz.home.mebelshop.service.mapper;

import org.mapstruct.Mapper;
import uz.home.mebelshop.dto.CategoryDto;
import uz.home.mebelshop.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends CommonMapper<CategoryDto, Category> {
}
