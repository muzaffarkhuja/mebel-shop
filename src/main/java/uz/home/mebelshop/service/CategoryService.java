package uz.home.mebelshop.service;

import uz.home.mebelshop.dto.CategoryDto;
import uz.home.mebelshop.dto.ResponseDto;

import java.util.List;

public interface CategoryService {
    ResponseDto<CategoryDto> add(CategoryDto categoryDto);
    ResponseDto<List<CategoryDto>> getAll();
    ResponseDto<CategoryDto> getById(Integer id);
    ResponseDto<CategoryDto> update(CategoryDto categoryDto);
    ResponseDto<Void> delete(Integer id);
}
