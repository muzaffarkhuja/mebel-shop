package uz.home.mebelshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.home.mebelshop.dto.CategoryDto;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.model.Category;
import uz.home.mebelshop.repository.CategoryRepository;
import uz.home.mebelshop.service.CategoryService;
import uz.home.mebelshop.service.mapper.CategoryMapper;

import java.util.List;
import java.util.Optional;

import static uz.home.mebelshop.service.validator.AppStatusCodes.*;
import static uz.home.mebelshop.service.validator.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseDto<CategoryDto> add(CategoryDto categoryDto) {
        try{
            Category category = categoryMapper.toEntity(categoryDto);
            categoryRepository.save(category);

            return ResponseDto.<CategoryDto>builder()
                    .message(OK)
                    .success(true)
                    .data(categoryMapper.toDto(category))
                    .build();
        } catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<CategoryDto>> getAll(){
        try{
            return ResponseDto.<List<CategoryDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(categoryRepository.findAll().stream().map(categoryMapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<CategoryDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<CategoryDto> getById(Integer id){
        try{
            return categoryRepository.findById(id)
                    .map(category -> ResponseDto.<CategoryDto>builder()
                            .success(true)
                            .message(OK)
                            .data(categoryMapper.toDto(category))
                            .build())
                    .orElse(ResponseDto.<CategoryDto>builder()
                            .code(NOT_FOUND_ERROR_CODE)
                            .message(NOT_FOUND)
                            .build());
        } catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<List<CategoryDto>> getByParentId(Integer id){
        try{
            return ResponseDto.<List<CategoryDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(categoryRepository.findAllByParentId(id).stream().map(categoryMapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<CategoryDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<Void> delete(Integer id){
        if(id == null){
            return ResponseDto.<Void>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message("ID is null")
                    .build();
        }

        try {
            categoryRepository.deleteById(id);

            return ResponseDto.<Void>builder()
                    .success(true)
                    .message(OK)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Void>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<CategoryDto> update(CategoryDto categoryDto) {
        if (categoryDto.getId() == null){
            return ResponseDto.<CategoryDto>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message("Id is null!")
                    .build();
        }

        Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getId());

        if (categoryOptional.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        Category category = categoryOptional.get();

        if (categoryDto.getName() != null){
            category.setName(categoryDto.getName());
        }
        if (categoryDto.getParentId() != null){
            category.setParentId(categoryDto.getParentId());
        }

        try {
            categoryRepository.save(category);

            return ResponseDto.<CategoryDto>builder()
                    .code(OK_CODE)
                    .success(true)
                    .message(OK)
                    .data(categoryMapper.toDto(category))
                    .build();

        } catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }
}
