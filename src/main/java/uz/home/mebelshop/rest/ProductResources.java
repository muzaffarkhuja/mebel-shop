package uz.home.mebelshop.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.home.mebelshop.dto.ProductDto;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.service.impl.ProductServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductResources {

    private final ProductServiceImpl productServiceImpl;

    @PostMapping
    public ResponseDto<ProductDto> addProduct(@RequestBody ProductDto productDto){
        return productServiceImpl.addProduct(productDto);
    }

    @GetMapping()
    public ResponseDto<Page<ProductDto>> getAllProducts(@RequestParam(defaultValue = "10") Integer size,
                                                        @RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam Map<String, String> params){
        return productServiceImpl.getAllProducts(size, page, params);
    }

    @GetMapping("{id}")
    public ResponseDto<ProductDto> getProductById(@PathVariable Integer id){
        return productServiceImpl.getProductById(id);
    }

    @PatchMapping
    public ResponseDto<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        return productServiceImpl.updateProduct(productDto);
    }

    @DeleteMapping
    public ResponseDto<Void> deleteProduct(@RequestParam Integer id){
        return productServiceImpl.deleteProduct(id);
    }
}
