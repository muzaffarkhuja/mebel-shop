package uz.home.mebelshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.home.mebelshop.dto.ProductDto;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.model.Product;
import uz.home.mebelshop.repository.ProductRepository;
import uz.home.mebelshop.service.ProductService;
import uz.home.mebelshop.service.mapper.CategoryMapper;
import uz.home.mebelshop.service.mapper.ProductMapper;

import java.util.Map;
import java.util.Optional;

import static uz.home.mebelshop.service.validator.AppStatusCodes.*;
import static uz.home.mebelshop.service.validator.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    @Override
    public ResponseDto<ProductDto> addProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);

        try{
            productRepository.save(product);

            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message(OK)
                    .data(productMapper.toDto(product))
                    .build();
        } catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }
    @Override
    public  ResponseDto<Page<ProductDto>> getAllProducts(Integer size, Integer page, Map<String, String> params) {
        Long count = productRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size
        );

        try {
            Page<ProductDto> all = productRepository.getAll(pageRequest,
                    params.get("name"),
                    params.get("minPrice"),
                    params.get("maxPrice"),
                    params.get("category")).map(productMapper::toDto);

            return ResponseDto.<Page<ProductDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(all)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Page<ProductDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }
    @Override
    public ResponseDto<ProductDto> getProductById(Integer id) {
        try{
            return productRepository.findById(id)
                    .map(p -> ResponseDto.<ProductDto>builder()
                            .message(OK)
                            .success(true)
                            .data(productMapper.toDto(p))
                            .build())
                    .orElse(ResponseDto.<ProductDto>builder()
                            .code(NOT_FOUND_ERROR_CODE)
                            .message(NOT_FOUND)
                            .build()
                    );
        } catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR)
                    .build();
        }
    }
    @Override
    public ResponseDto<ProductDto> updateProduct(ProductDto productDto) {
        if(productDto.getId() == null){
            return ResponseDto.<ProductDto>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message("ID is null")
                    .build();
        }

        Optional<Product> optional = productRepository.findById(productDto.getId());

        if(optional.isEmpty()){
            return ResponseDto.<ProductDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        Product product = optional.get();

        if(productDto.getName() != null){
            product.setName(productDto.getName());
        }
        if(productDto.getDescription() != null){
            product.setDescription(productDto.getDescription());
        }
        if(productDto.getPrice() != null){
            product.setPrice(productDto.getPrice());
        }
        if(productDto.getIsActive() != null){
            product.setIsActive(productDto.getIsActive());
        }
        if(productDto.getCategory() != null){
            product.setCategory(categoryMapper.toEntity(productDto.getCategory()));
        }
        if(productDto.getSoldCount() != null){
            product.setSoldCount(productDto.getSoldCount());
        }

        try{
            productRepository.save(product);

            return ResponseDto.<ProductDto>builder()
                    .message(OK)
                    .success(true)
                    .data(productMapper.toDto(product))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }

    }
    @Override
    public ResponseDto<Void> deleteProduct(Integer id) {
        if(id == null){
            return ResponseDto.<Void>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message("ID is null")
                    .build();
        }
        Optional<Product> optional = productRepository.findById(id);

        if(optional.isEmpty()){
            return ResponseDto.<Void>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        try {
            productRepository.deleteById(id);

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
}
