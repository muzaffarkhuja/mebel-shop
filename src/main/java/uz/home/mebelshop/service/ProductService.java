package uz.home.mebelshop.service;

import org.springframework.data.domain.Page;
import uz.home.mebelshop.dto.ProductDto;
import uz.home.mebelshop.dto.ResponseDto;


public interface ProductService {
    ResponseDto<ProductDto> addProduct(ProductDto productDto);
    ResponseDto<Page<ProductDto>> getAllProducts(Integer size, Integer page);
    ResponseDto<ProductDto> getProductById(Integer id);
    ResponseDto<ProductDto> updateProduct(ProductDto productDto);
    ResponseDto<Void> deleteProduct(Integer id);
}
